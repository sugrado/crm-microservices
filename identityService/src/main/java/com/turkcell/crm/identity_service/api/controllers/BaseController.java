package com.turkcell.crm.identity_service.api.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BaseController {
    protected String getCookie(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(key)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    protected void setCookie(String key, String value, int expiry, HttpServletResponse response) {
        Cookie cookie = new Cookie(key, value);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(expiry);
        cookie.setSecure(true);
        response.addCookie(cookie);
    }

    protected void clearCookies(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
    }

    protected String getIpAddress(HttpServletRequest request) {
        String ipAddress = request.getRemoteAddr();
        if (ipAddress == null) {
            ipAddress = request.getHeader("X-Forwarded-For");
        }
        if (ipAddress == null || ipAddress.isEmpty() || ipAddress.isBlank()) {
            throw new RuntimeException("IP Address is not valid");
        }
        return ipAddress;
    }

    protected int calculateCookieExpirationSeconds(int expirationDays) {
        return expirationDays * 24 * 60 * 60;
    }
}
