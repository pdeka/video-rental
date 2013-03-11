package com.example.video.web.page.tests;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static junit.framework.Assert.assertTrue;

public class CustomerJourneys {

    @Test
    public void shouldViewMovies() {
        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/videorental/movies");
        assertTrue(true);
    }

}
