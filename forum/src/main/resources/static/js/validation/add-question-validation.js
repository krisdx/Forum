const MIN_TAG_LENGTH = 1;

let titleCorrect = false;
let questionCorrect = false;
let tagsCorrect = false;

$(document).ready(function () {
    changeInputBackgroundColors();

    $('#title').focusout(validateTitle);
    $('#question').focusout(validateQuestion);
    $('#tags').focusout(validateTags);
});

function changeInputBackgroundColors() {
    $('input')
        .css('color', 'white')
        .css('background-color', '#292B2C');
    $('textarea')
        .css('color', 'white')
        .css('background-color', '#292B2C');
}

function validateTitle() {
    let title = $('#title').val();
    if (title.trim() === '') {
        titleCorrect = false;
        disableRegisterButton();
        markErrorField('title-error-field', 'The title cannot be empty.');
    } else {
        titleCorrect = true;
        tryEnableRegisterButton();
        unMarkErrorField('title-error-field');
    }
}

function validateQuestion() {
    let questionField = $('#question');
    if (questionField.val().trim() === '') {
        questionCorrect = false;
        questionField.css('border', '2px solid #D9534F');
        disableRegisterButton();
        markErrorField('question-error-field', 'The question cannot be empty.');
    } else {
        questionCorrect = true;
        questionField.css('border', '2px solid #5CB85C');
        tryEnableRegisterButton();
        unMarkErrorField('question-error-field');
    }
}

function validateTags() {
    let input = $('#tags').val();
    let tags = input.split(',');

    let validTags = new Set();
    for (let tag of tags) {
        tag = tag.trim();
        if (tag === '') {
            displayError('Please, enter a tag after the comma.');
            return;
        }

        if (tag.length <= MIN_TAG_LENGTH) {
            displayError(`The tag "<strong><i>${tag}</i></strong>" should be with length grater than 1.`);
            return;
        }

        let firstChar = tag[0];
        if (!isDigitOrLetter(firstChar)) {
            displayError(`The tag "<strong><i>${tag}</i></strong>" should start with a letter or a digit, and not with "<strong><i>${firstChar}</i></strong>"`);
            return;
        }

        if (isUpperCase(firstChar)) {
            displayError(`The tag "<strong><i>${tag}</i></strong>" cannot contain an uppercase letter "<strong><i>${firstChar}</i></strong>"`);
            return;
        }

        let lastChar = tag[tag.length - 1];
        if (!isDigitOrLetter(lastChar)) {
            displayError(`The tag "<strong><i>${tag}</i></strong>" should end with a letter or a digit, and not with "<strong><i>${lastChar}</i></strong>"`);
            return;
        }

        if (isUpperCase(lastChar)) {
            displayError(`The tag "<strong><i>${tag}</i></strong>" cannot contain an uppercase letter "<strong><i>${firstChar}</i></strong>"`);
            return;
        }

        for (let i = 1; i < tag.length - 1; i++) {
            if (!isDigitOrLetter(tag[i]) && tag[i] !== '_') {
                displayError(`The tag "<strong><i>${tag}</i></strong>" contains unallowed character "<strong><i>${tag[i]}</i></strong>"`);
                return;
            }

            if (isUpperCase(tag[i])) {
                displayError(`The tag "<strong><i>${tag}</i></strong>" cannot contain an uppercase letter "<strong>${tag[i]}</strong>"`);
                return;
            }
        }

        if (validTags.has(tag)) {
            displayError(`The tag "<strong><i>${tag}</i></strong>" is already defined.`);
            return;
        }

        validTags.add(tag);
    }

    tagsCorrect = true;
    tryEnableRegisterButton();
    unMarkErrorField('tags-error-field');
}

/*
 Helper Functions
 */
function displayError(errorMessage) {
    tagsCorrect = false;
    disableRegisterButton();
    markErrorField('tags-error-field', errorMessage);
}

function isDigitOrLetter(ch) {
    return isDigit(ch) || isLetter(ch);
}

function isDigit(ch) {
    return ch >= '0' && ch <= '9';
}

function isLetter(ch) {
    return ch.length === 1 && ch.match(/[a-zа-я]/i);
}

function isUpperCase(ch) {
    return !isDigit(ch) && ch === ch.toUpperCase();
}

function disableRegisterButton() {
    $('#add-question-btn').prop('disabled', true);
}

function tryEnableRegisterButton() {
    if (titleCorrect && questionCorrect && tagsCorrect) {
        $('#add-question-btn').removeClass('btn-primary').addClass('btn-success')
            .prop('disabled', false);
    }
}

function markErrorField(errorFieldName, errorMessage) {
    let errorField = $('#' + errorFieldName);
    errorField.css('display', 'block')
        .html(errorMessage);
    errorField.parent().addClass('has-danger');
}

function unMarkErrorField(errorFieldName) {
    let errorField = $('#' + errorFieldName);
    errorField.css('display', 'none');
    errorField.parent().removeClass('has-danger');
}