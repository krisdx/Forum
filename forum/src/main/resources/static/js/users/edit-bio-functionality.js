const IS_BIO_EMPTY_ATTRIBUTE_NAME = 'is-bio-empty';

let emptyBioMessage = null;
let lastBioText = null;

$(document).ready(function () {
    $('#edit-bio-btn').click(editBio);
    $('#edit-bio-submit-btn').click(submitEditedBio);
    $('#edit-bio-cancel-btn').click(cancelEditBio);

    //
    emptyBioMessage = $('#bio-text').attr('empty-bio-message');
});

function editBio() {
    let bioField = $('#bio-text');

    let textArea = $('<textarea>')
        .attr('id', 'edit-bio-text')
        .attr('placeholder', bioField.attr('placeholder'))
        .css('background-color', '#292B2C')
        .css('color', 'white')
        .css('padding', '5px')
        .css('overflow', 'hidden')
        .css('width', '95%')
        .css('height', '100px')
        .css('margin', '5px')
        .css('border', '1px solid #5CB85C');
    if (bioField.attr(IS_BIO_EMPTY_ATTRIBUTE_NAME) === 'false') {
        textArea.val(bioField.text());
        textArea.attr(IS_BIO_EMPTY_ATTRIBUTE_NAME, 'false');
        lastBioText = bioField.text();
    } else {
        textArea.attr(IS_BIO_EMPTY_ATTRIBUTE_NAME, 'true');
    }

    bioField.replaceWith(textArea);

    $('#edit-bio-btn').css('display', 'none');
    $('#edit-bio-submit-btn').css('display', 'inline');
    $('#edit-bio-cancel-btn').css('display', 'inline');
}

function cancelEditBio() {
    let editBioField = $('#edit-bio-text');
    let editedBioText = $('<p>')
        .attr('id', 'bio-text')
        .attr('placeholder', editBioField.attr('placeholder'))
        .attr(IS_BIO_EMPTY_ATTRIBUTE_NAME, editBioField.attr(IS_BIO_EMPTY_ATTRIBUTE_NAME))
        .text(lastBioText);
    if (editBioField.attr(IS_BIO_EMPTY_ATTRIBUTE_NAME) === 'true') {
        editedBioText.addClass('empty-bio-message')
            .text(emptyBioMessage);
    }

    editBioField.replaceWith(editedBioText);

    showEditButton();
    hideEditSubmitButton();
    hideEditCancelButton();
}

function submitEditedBio() {
    let editBioField = $('#edit-bio-text');
    let editedBioText = $('<p>')
        .attr('id', 'bio-text')
        .attr('placeholder', editBioField.attr('placeholder'));
    if (editBioField.val() === '') {
        editedBioText.attr(IS_BIO_EMPTY_ATTRIBUTE_NAME, 'true');
        editedBioText.text(emptyBioMessage);
        editedBioText.addClass('empty-bio-message');
    } else {
        editedBioText.attr(IS_BIO_EMPTY_ATTRIBUTE_NAME, 'false');
        editedBioText.text(editBioField.val());
        lastBioText = editBioField.val();
    }

    editBioField.replaceWith(editedBioText);

    showEditButton();
    hideEditSubmitButton();
    hideEditCancelButton();

    let userId = $(this).attr('user-id');
    let dataToSend = {editedText: editBioField.val()};
    $.ajax({
        url: '/users/edit/bio/' + userId,
        type: 'PUT',
        data: dataToSend
    });
}

function hideEditSubmitButton() {
    $('#edit-bio-submit-btn').css('display', 'none');
}

function hideEditCancelButton() {
    $('#edit-bio-cancel-btn').css('display', 'none');
}

function showEditButton() {
    $('#edit-bio-btn').css('display', 'inline');
}