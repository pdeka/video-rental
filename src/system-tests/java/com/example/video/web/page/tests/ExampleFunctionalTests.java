package com.example.video.web.page.tests;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static junit.framework.Assert.assertTrue;

public class ExampleFunctionalTests {

    @Test
    public void shouldRunAndNotBreakTheBuildForNow() {

        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://www.google.com");
        assertTrue(true);
    }

}
