package com.game.webpage;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Arrays;
import java.util.List;

class WebPageReader {

    static List<String> getValuesArray() {
        WebDriver driver = getDriver();
        String result = getPageContent(driver).get(0).getText();
        result = firstCharToUpperCase(result);
        List<String> elementsStr =
                Arrays.asList(result.split("\n"));
        driver.quit();
        return elementsStr;
    }

    private static List<WebElement> getPageContent(WebDriver driver) {
        List<WebElement> elements = null;
        String element = "";
        while (elementIsNotCorrect(element) || element.isEmpty()) {
            elements = getElements(driver);
            if (!elements.isEmpty())
                element = elements.get(0).getText();
        }
        return elements;
    }

    private static String firstCharToUpperCase(String word) {
        int lastCharIndex = word.length();
        String upperCaseChar = word.substring(0,1).toUpperCase();
        return upperCaseChar + word.substring(1, lastCharIndex);
    }

    private static boolean elementIsNotCorrect(String element) {
        String invalidValue = "LOADING…";
        return element.equals(invalidValue);
    }

    private static List<WebElement> getElements(WebDriver driver) {
        return driver.findElements(By.className("Rand-stage"));
    }

    private static WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver",
                "D:\\Software\\Chrome_download\\chromedriver\\chromedriver.exe");
        String url = "https://www.randomlists.com/random-vocabulary-words";
        ChromeOptions options = getChromeOptions();
        WebDriver driver = new ChromeDriver(options);
        driver.get(url);
        return driver;
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        return options;
    }

}