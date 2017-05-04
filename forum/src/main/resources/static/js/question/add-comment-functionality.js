$(document).ready(function () {
    $('#add-comment-submit-btn').click(addComment);
});

function addComment() {
    let comment = $('#input-comment-field').val();
    let questionId = $('div[question-id]').first().attr('question-id');
    let dataToSend = {comment: comment, questionId: Number(questionId)};
    $.post('/comments/add', dataToSend)
        .done(function (commentViewModel) {
            attachNewCommentToDocument(commentViewModel);
            incrementCommentCount();
            $('#input-comment-field').val('');
        });
}

function attachNewCommentToDocument(commentViewModel) {
    let userIcon = $('<i>').addClass('fa fa-user').css('padding-right', '5px');
    let username = $('<span>').text(commentViewModel.username);

    let calendarIcon = $('<i>').addClass('fa fa-calendar');
    let dateTime = $('<span>').attr('id', 'comment-date-' + commentViewModel.id)
        .text(commentViewModel.date);

    let editButtonIcon = $('<i>').addClass('fa fa-pencil').css('padding-right', '5px');

    let editButton = $('<button>')
        .attr('id', 'edit-comment-btn-' + commentViewModel.id)
        .prop('type', 'button')
        .addClass('edit-btn')
        .click(editComment)
        .append(editButtonIcon);

    let deleteButtonIcon = $('<i>').addClass('fa fa-times');
    let deleteButton = $('<button>')
        .attr('id', 'delete-comment-btn-' + commentViewModel.id)
        .prop('type', 'button')
        .addClass('delete-btn')
        .click(deleteComment)
        .append(deleteButtonIcon);

    let editSubmitButton = $('<button>')
        .attr('id', 'edit-comment-submit-btn-' + commentViewModel.id)
        .prop('type', 'button')
        .css('display', 'none')
        .addClass('edit-submit-btn')
        .click(submitEditedComment)
        .text('Edit');
    let editCancelButton = $('<button>')
        .attr('id', 'edit-comment-cancel-btn-' + commentViewModel.id)
        .prop('type', 'button')
        .css('display', 'none')
        .addClass('edit-cancel-btn')
        .click(cancelEditComment)
        .text('Cancel');

    let hasCommentBeenEdited = $('<span>')
        .attr('id', 'has-comment-been-edited-' + commentViewModel.id)
        .addClass('edited')
        .css('display', 'none');

    let thumpsUpIcon = $('<i>').addClass('fa fa-thumbs-up');
    let likesCount = $('<span>').attr('id', 'comment-likes-count-' + commentViewModel.id).text(' 0');
    let likeButton = $('<button>')
        .attr('id', 'comment-like-btn-' + commentViewModel.id)
        .prop('type', 'button')
        .addClass('btn like-btn')
        .click(like);
    likeButton.append(thumpsUpIcon);
    likeButton.append(likesCount);

    let thumpsDownIcon = $('<i>').addClass('fa fa-thumbs-down');
    let dislikesCount = $('<span>').attr('id', 'comment-dislikes-count-' + commentViewModel.id).text(' 0');
    let dislikeButton = $('<button>')
        .attr('id', 'comment-dislike-btn-' + commentViewModel.id)
        .prop('type', 'button')
        .addClass('btn dislike-btn')
        .click(dislike);
    dislikeButton.append(thumpsDownIcon);
    dislikeButton.append(dislikesCount);

    let comment = $('<span>')
        .attr('id', 'comment-text-' + commentViewModel.id)
        .addClass('italic')
        .css('padding', '10px')
        .text(commentViewModel.comment);

    let newComment = $('<div>')
        .attr('comment-id', commentViewModel.id)
        .addClass('bg-inverse comment')
        .append(userIcon).append(username)
        .append(calendarIcon).append(dateTime)
        .append(hasCommentBeenEdited)
        .append(editSubmitButton).append(editCancelButton)
        .append(deleteButton).append(editButton)
        .append($('<br>'))
        .append(comment)
        .append($('<br>'))
        .append(likeButton).append(dislikeButton);

    $('#comments-section').append(newComment);
}

function incrementCommentCount() {
    let commentsCountField = $('#comments-count');
    let commentsCount = Number(commentsCountField.text()) + 1;
    commentsCountField.text(commentsCount);

    if (commentsCount === 1)
        $('#comments-count-text').text(' Comment');
    else
        $('#comments-count-text').text(' Comments');
}