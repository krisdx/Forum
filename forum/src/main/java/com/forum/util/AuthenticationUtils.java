package com.forum.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationUtils {

    private AuthenticationUtils() {
    }

    public static String getCurrentUserUsername() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            return auth.getName();
        } catch (NullPointerException nullPointerEx) {
            return null;
        }
    }
}