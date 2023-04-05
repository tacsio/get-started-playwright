package org.example;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.LoadState;

import java.nio.file.Paths;

public class Main {

    public static void google() {

        try (Playwright playwright = Playwright.create()) {
            var options = new BrowserType.LaunchOptions();
            options.setHeadless(false);

            var browser = playwright.chromium().launch(options);
            var context = browser.newContext(new Browser.NewContextOptions()
                    .setRecordVideoDir(Paths.get("videos")));

            var page = context.newPage();
            page.navigate("https://google.com");
            page.getByTitle("Pesquisar").fill("rickroll");

            page.getByText("Estou com sorte").first().click();

            page.getByTitle("Play (k)").click();
            page.waitForLoadState(LoadState.NETWORKIDLE);
            context.close();
        }
    }

    public static void playwright() {
        try (Playwright playwright = Playwright.create()) {
            var browser = playwright.chromium().launch();
            var page = browser.newPage();

            page.navigate("https://playwright.dev");
            String heroTitle = page.locator(".hero__title").textContent();

            System.out.println("Title: " + page.title());
            System.out.println("Hero Title: " + heroTitle);
        }
    }

    public static void main(String[] args) {
        playwright();
        google();
    }
}