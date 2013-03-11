package com.example.video.web.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LogoutControllerTest {

    @Mock
    HttpSession session;

    @Test
    public void shouldInvalidateSessionAndRedirectToHome() throws Exception {
        LogoutController logoutController = new LogoutController();
        logoutController.get(session);

        verify(session).invalidate();
    }
}
