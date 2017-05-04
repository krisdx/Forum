$(document).ready(function () {
    $('.delete-btn').click(deleteComment);
});

function deleteComment() {
    let commentField = $(this).parent();
    let commentId = commentField.attr('comment-id');

    $.ajax({
        url: '/comments/delete/' + commentId,
        type: 'DELETE'
    }).done(function () {
        commentField.remove();
        decrementCommentsCount();
    });
}

function decrementCommentsCount() {
    let commentsCountField = $('#comments-count');
    let commentsCount = Number(commentsCountField.text()) - 1;
    commentsCountField.text(commentsCount);

    if (commentsCount === 1)
        $('#comments-count-text').text(' Comment');
    else
        $('#comments-count-text').text(' Comments');
}