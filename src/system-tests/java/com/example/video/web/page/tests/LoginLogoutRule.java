package com.example.video.web.page.tests;

import com.example.video.web.page.Application;
import com.example.video.web.page.Browser;
import org.junit.rules.ExternalResource;

public class LoginLogoutRule extends ExternalResource {

    private final Browser browser;
    private final String username;

    public LoginLogoutRule(String username) {
        this.browser = Application.browser();
        this.username = username;
    }

    public LoginLogoutRule(String username, Browser browser) {
        this.username = username;
        this.browser = browser;
    }

    public Browser browser() {
        return browser;
    }

}
