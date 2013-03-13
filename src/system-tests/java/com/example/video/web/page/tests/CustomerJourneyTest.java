package com.example.video.web.page.tests;


import com.example.video.web.page.Application;
import com.example.video.web.page.Browser;
import com.example.video.web.page.HomePage;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

@RunWith(ConcordionRunner.class)
public class CustomerJourneyTest {
    public void login(String user) {
    }

    public void iGoToHomePage() {
        Browser browser = Application.open("/videorental/movies");
        browser.shows(HomePage.class).verify(browser);
    }

    public boolean iSeeTheListOfMovies() {
        return true;
    }
}
