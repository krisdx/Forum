package com.forum.util;

import com.forum.areas.user.validation.ConfirmPassword;

/**
 * Static class for constant messages.
 */
public class Constants {

    private Constants(){
    }

    // i18n
    public static final String RESOURCE_BUNDLE_BASE_NAME = "i18n/messages";
    public static final String LANGUAGE_ATTRIBUTE_NAME = "org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE";

    // Errors
    public static final String PASSWORD_MISMATCH_ERROR_CODE = ConfirmPassword.class.getSimpleName();
    public static final String PASSWORD_MISMATCH_ERROR_MESSAGE = "Passwords Do Not Match";

    public static final String LOGIN_ERROR_MESSAGE = "Invalid username or password";

    // Authentication
    public static final String ANONYMOUS_USER = "anonymousUser";

    // Date Time Formats
    public static final String LOCAL_DATE_FORMAT = "dd/MMM/YYYY";
    public static final String LOCAL_DATETIME_FORMAT = "dd/MM/YY HH:mm";
}