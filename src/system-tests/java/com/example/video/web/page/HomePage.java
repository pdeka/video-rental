package com.example.video.web.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class HomePage implements Page {

//    @FindBy(className = "someClassName")
//    private WebElement search;

    public void verify(Browser browser) {
        assertThat("Unexpected page", browser.getBodyClass(), containsString("home"));
    }

}
