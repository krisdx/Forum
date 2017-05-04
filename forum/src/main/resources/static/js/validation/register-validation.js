let usernameCorrect = false;
let fullNameCorrect = false;
let emailCorrect = false;
let passwordCorrect = false;
let confirmPasswordCorrect = false;

$(document).ready(function () {
    $('#username').blur(checkUsername);
    $('#full-name').blur(checkFullName);
    $('#email').blur(checkEmail);

    $('#password')
        .blur(checkPassword)
        .blur(checkPasswordMatching);
    $('#confirm-password').blur(checkPasswordMatching);
});

function checkUsername() {
    let username = $('#username').val();
    let errorMessage = '';
    if (username.length < 6) {
        errorMessage = 'Username cannot be shorter than 5 symbols.';
    } else if (username.length > 20) {
        errorMessage = 'Username cannot be longer than 20 symbols.';
    }

    if (errorMessage !== '') {
        usernameCorrect = false;
        disableRegisterBtn();
        markErrorField('username-error-field', 'username', errorMessage);
    } else {
        usernameCorrect = true;
        tryEnableRegisterBtn();
        unMarkErrorField('username-error-field', 'username');
    }
}

function checkFullName() {
    let fullName = $('#full-name').val();
    let regex = new RegExp(/^([A-ZА-Я][a-zа-я]+)(\s*[A-ZА-Я][a-zа-я]+)+$/g);
    let match = regex.exec(fullName);
    if (match === null){
        fullNameCorrect = false;
        disableRegisterBtn();
        markErrorField('full-name-error-field', 'full-name', 'The Full Name is not correct.');
        return;
    }

    if (fullName === '') {
        fullNameCorrect = false;
        disableRegisterBtn();
        markErrorField('full-name-error-field', 'full-name', 'The Full Name cannot be empty.');
    } else {
        fullNameCorrect = true;
        tryEnableRegisterBtn();
        unMarkErrorField('full-name-error-field', 'full-name');
    }
}

function checkEmail() {
    let email = $('#email').val();
    let regex = new RegExp(/^[a-z][a-z.0-9]*@[a-z]+\.[a-z]+(\.[a-z]+)*$/g);
    let match = regex.exec(email);

    if (match === null) {
        emailCorrect = false;
        disableRegisterBtn();
        markErrorField('email-error-field', 'email', 'Invalid Email.');
    } else {
        emailCorrect = true;
        tryEnableRegisterBtn();
        unMarkErrorField('email-error-field', 'email');
    }
}

function checkPassword() {
    let password = $('#password').val();

    if (password.length < 6) {
        passwordCorrect = false;
        disableRegisterBtn();
        markErrorField('password-error-field', 'password', 'The password must be at least 6 digits long.');
        return;
    }

    let hasNumberRegex = new RegExp(/^.*[0-9].*$/g);
    let numberMatch = hasNumberRegex.exec(password);
    if (numberMatch === null) {
        passwordCorrect = false;
        disableRegisterBtn();
        markErrorField('password-error-field', 'password', 'The password must contain a number.');
        return;
    }

    passwordCorrect = true;
    unMarkErrorField('password-error-field', 'password');

    let hasUpperCaseLetterRegex = new RegExp(/^.*[A-ZА-Я].*$/g);
    let upperCaseLetterMatch = hasUpperCaseLetterRegex.exec(password);
    if (upperCaseLetterMatch === null) {
        passwordCorrect = false;
        disableRegisterBtn();
        markErrorField('password-error-field', 'password', 'The password must contain an upper case letter.');
        return;
    }

    passwordCorrect = true;
    unMarkErrorField('password-error-field', 'password');
}

function checkPasswordMatching() {
    let confirmPassword = $('#confirm-password').val();
    if (confirmPassword === '') {
        confirmPasswordCorrect = false;
        disableRegisterBtn();
        markErrorField('password-mismatch', 'confirm-password', 'Confirm password cannot be empty.');
        return;
    }

    let password = $('#password').val();
    if (password !== confirmPassword) {
        confirmPasswordCorrect = false;
        disableRegisterBtn();
        markErrorField('password-mismatch', 'confirm-password', 'Passwords do not match.');
        return;
    }

    confirmPasswordCorrect = true;
    tryEnableRegisterBtn();
    unMarkErrorField('password-mismatch', 'confirm-password');
}

/* Helper Functions */
function disableRegisterBtn() {
    $('#register-btn').prop('disabled', true);
}

function tryEnableRegisterBtn() {
    if (usernameCorrect && fullNameCorrect && emailCorrect &&
        passwordCorrect && confirmPasswordCorrect) {
        $('#register-btn').prop('disabled', false);
    }
}

function markErrorField(wrapperElement, inputField, errorMessage) {
    let errorField = $('#' + wrapperElement);
    errorField.css('display', 'block')
        .text(errorMessage);
    errorField.parent().parent().addClass('has-danger');
    $('#' + inputField).addClass('form-control-danger');
}

function unMarkErrorField(wrapperField, inputField) {
    let errorField = $('#' + wrapperField);
    errorField.css('display', 'none');
    errorField.parent().parent()
        .removeClass('has-danger').addClass('has-success');
    $('#' + inputField).removeClass('form-control-danger')
        .addClass('form-control-success');
}