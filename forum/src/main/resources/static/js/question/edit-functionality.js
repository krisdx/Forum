$(document).ready(function () {
    $('.edit-btn').click(editComment);
    $('.edit-submit-btn').click(submitEditedComment);
    $('.edit-cancel-btn').click(cancelEditComment);
});

function editComment() {
    let buttonInfo = $(this).attr('id').split('-');
    let elementId = buttonInfo[buttonInfo.length - 1];
    let element = buttonInfo[1];
    let commentField = $('#' + element + '-text-' + elementId);

    let textArea = $('<textarea>')
        .attr('id', 'edit-' + element + '-input-field-' + elementId)
        .css('background-color', '#292B2C')
        .css('color', 'white')
        .css('padding', '5px')
        .css('overflow', 'hidden')
        .css('width', '95%')
        .css('height', '100px')
        .css('margin', '5px')
        .css('border', '1px solid #5CB85C')
        .val(commentField.text());
    commentField.replaceWith(textArea);

    $('#edit-' + element + '-btn-' + elementId).css('display', 'none');
    $('#edit-' + element + '-submit-btn-' + elementId).css('display', 'inline');
    $('#edit-' + element + '-cancel-btn-' + elementId).css('display', 'inline');
}

function cancelEditComment() {
    let buttonInfo = $(this).attr('id').split('-');
    let elementId = buttonInfo[buttonInfo.length - 1];
    let element = buttonInfo[1];
    let editElementField = $('#edit-' + element + '-input-field-' + elementId);
    let paragraph = $('<p>')
        .attr('id', element + '-text-' + elementId)
        .addClass('italic')
        .text(editElementField.val());
    editElementField.replaceWith(paragraph);

    showEditButton(element, elementId);
    hideEditSubmitButton(element, elementId);
    hideEditCancelButton(element, elementId);
}

function submitEditedComment() {
    let buttonInfo = $(this).attr('id').split('-');
    let elementId = buttonInfo[buttonInfo.length - 1];
    let element = buttonInfo[1];
    let editedElementField = $('#edit-' + element + '-input-field-' + elementId);

    let paragraph = $('<p>')
        .attr('id', 'comment-text-' + elementId)
        .addClass('italic')
        .text(editedElementField.val());
    editedElementField.replaceWith(paragraph);

    showEditButton(element, elementId);
    hideEditSubmitButton(element, elementId);
    hideEditCancelButton(element, elementId);

    let dataToSend = {editedText: paragraph.text()};
    $.ajax({
        url: '/' + element + 's' + '/edit/' + elementId,
        type: 'PUT',
        data: dataToSend
    }).done(function (editedElement) {
        updateElement(editedElement, element);
    });
}

function updateElement(editedElement, element) {
    if (!editedElement.hasBeenEdited) {
        return;
    }

    $('#' + element + '-date-' + editedElement.id).text(editedElement.date);
    $('#has-' + element + '-been-edited-' + editedElement.id).css('display', 'inline');
}

function hideEditSubmitButton(element, elementId) {
    $('#edit-' + element + '-submit-btn-' + elementId).css('display', 'none');
}

function hideEditCancelButton(element, elementId) {
    $('#edit-' + element + '-cancel-btn-' + elementId).css('display', 'none');
}

function showEditButton(element, elementId) {
    $('#edit-' + element + '-btn-' + elementId).css('display', 'inline');
}