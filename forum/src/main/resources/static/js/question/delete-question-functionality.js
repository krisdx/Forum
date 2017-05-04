$(document).ready(attachEvents);

function attachEvents() {
    $('#delete-question-btn').click(deleteQuestion);
}

function deleteQuestion() {
    let questionId = $('div[question-id]').first().attr('question-id');

    $.ajax({
        url: '/questions/delete/' + questionId,
        type: 'DELETE',
        async: false
    });
}