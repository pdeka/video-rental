package com.example.video.web.page.tests;

import com.example.video.web.page.Application;
import com.example.video.web.page.Browser;
import com.example.video.web.page.HomePage;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static junit.framework.Assert.assertTrue;

public class CustomerJourneySimpleTests {

    @Test
    public void shouldViewMovies() {
        Browser browser = Application.open("/videorental/movies");
        browser.shows(HomePage.class).verify(browser);
    }

}
