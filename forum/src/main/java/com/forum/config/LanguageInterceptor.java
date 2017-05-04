package com.forum.config;

import com.forum.areas.user.service.UserService;
import com.forum.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Component
public class LanguageInterceptor extends LocaleChangeInterceptor {

    @Autowired
    private UserService userService;

    private static String language;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException {
        if (language == null) {
            /* Only at the first request to this site, we draw the lang from
            * the database. After that cached it, so there is no need on every request
            * to go to the database. */
            setLanguage(this.userService.getCurrentLanguage());
        }

        if (getLanguage() != null) {
            request.getSession().setAttribute(Constants.LANGUAGE_ATTRIBUTE_NAME, new Locale(getLanguage()));
            request.getSession().setAttribute("lang", getLanguage());
        }

        return true;
    }

    public static String getLanguage() {
        return language;
    }

    public static void setLanguage(String language) {
        LanguageInterceptor.language = language;
    }
}