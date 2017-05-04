$(document).ready(function () {
    $('.like-btn').click(like);
    $('.dislike-btn').click(dislike);
});

function like() {
    function updateButtons(isLiked) {
        let likesCountField = $('#' + element + '-likes-count-' + id);
        let likesCount = Number(likesCountField.text().trim());
        if (isLiked === null || isLiked === '') {
            likesCountField.text(likesCount - 1);
            likeBtn.removeClass('liked');
        } else if (isLiked) {
            likesCountField.text(likesCount + 1);
            likeBtn.addClass('liked');
        } else if (!isLiked) {
            likesCountField.text(likesCount + 1);
            likeBtn.addClass('liked');

            $('#' + element + '-dislike-btn-' + id).removeClass('disliked');
            let dislikesCountField = $('#' + element + '-dislikes-count-' + id);
            let dislikesCount = Number(dislikesCountField.text().trim());
            dislikesCountField.text(dislikesCount - 1);
        }
    }

    let likeBtn = $(this);
    let elementInfo = likeBtn.attr('id').split('-');
    let element = elementInfo[0]; // For example - question or comment
    let id = elementInfo[elementInfo.length - 1];

    let url = '/' + (element + 's') + '/like/' + id;
    $.ajax({
        url: url,
        type: 'PUT',
    }).done(function (isLiked) {
        updateButtons(isLiked);
    });
}

function dislike() {
    function updateButton(isDisliked) {
        let dislikesCountField = $('#' + element + '-dislikes-count-' + id);
        let dislikesCount = Number(dislikesCountField.text().trim());
        if (isDisliked === null || isDisliked === '') {
            dislikesCountField.text(dislikesCount - 1);
            dislikeBtn.removeClass('disliked');
        } else if (isDisliked) {
            dislikesCountField.text(dislikesCount + 1);
            dislikeBtn.addClass('disliked');
        } else if (!isDisliked) {
            dislikesCountField.text(dislikesCount + 1);
            dislikeBtn.addClass('disliked');

            $('#' + element + '-like-btn-' + id).removeClass('liked');
            let likesCountField = $('#' + element + '-likes-count-' + id);
            let likesCount = Number(likesCountField.text().trim());
            likesCountField.text(likesCount - 1);
        }
    }

    let dislikeBtn = $(this);
    let elementInfo = dislikeBtn.attr('id').split('-');
    let element = elementInfo[0]; // For example - question or comment
    let id = elementInfo[elementInfo.length - 1];

    let url = '/' + (element + 's') + '/dislike/' + id;
    $.ajax({
        url: url,
        type: 'PUT',
    }).done(function (isDisliked) {
        updateButton(isDisliked);
    });
}