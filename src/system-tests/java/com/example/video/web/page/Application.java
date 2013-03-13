package com.example.video.web.page;

import com.example.video.jetty.WebServer;
import org.apache.commons.lang.Validate;

import java.net.ServerSocket;

public class Application {

    private static Boolean START_SERVER = true;
    private static Application instance;
    private WebServer server;
    private Browser browser;

    public static Browser open(String url) {
        return browser().open(url);
    }

    public static Browser browser() {
        Browser browser = instance().browser;
        Validate.notNull(browser, "Application has not started succesfully. Please check earlier failed tests.");
        return browser;
    }

    private static Application instance() {
        if (instance != null) {
            return instance;
        }
        try {
            instance = new Application();
            registerShutdownHook();
            instance.start();
            return instance;

        } catch (Exception e) {
            throw new RuntimeException("Application startup failed", e);
        }
    }

    private static int findFreePort() throws Exception {
        ServerSocket socket = new ServerSocket(0);
        int port = socket.getLocalPort();
        socket.close();
        return port;
    }

    private static void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(new ShutdownHook()));
    }

    private void start() throws Exception {
        int port = 8081;
        if (START_SERVER) {
            port = findFreePort();
            server = new WebServer(port).start();
        }
        browser = new Browser("http://localhost:" + port, true);
    }

    private void stop() {
        if (browser != null) {
            browser.stop();
        }
        if (server != null) {
            server.stop();
        }
    }

    private static class ShutdownHook implements Runnable {
        public void run() {
            instance.stop();
        }
    }
}
