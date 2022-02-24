package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CustomWaits {
    private WebDriver driver;

    private Duration timeout;

    public CustomWaits(WebDriver driver, Duration timeout) {
        this.driver = driver;
        this.timeout = timeout;
    }

    public void waitUntilUrlContains(String substring) {
        try {
            new WebDriverWait(driver, timeout)
                    .until(d -> driver.getCurrentUrl().contains(substring));
        } catch (TimeoutException timeoutException) {
            assertThat(
                    getTimeoutMessage(),
                    driver.getCurrentUrl(),
                    containsString(substring));
        }
    }

    private String getTimeoutMessage() {
        return format("Timed out after %d seconds", timeout.getSeconds());
    }

    public void waitUntilDomReady() {
        try {
            new WebDriverWait(driver, timeout).until(d -> isDomReady());
        } catch (TimeoutException timeoutException) {
            assertThat(
                    getTimeoutMessage(),
                    getDomReadyState(),
                    is("complete"));
        }
    }

    private String getDomReadyState() {
        return ((JavascriptExecutor) driver)
                .executeScript("return document.readyState")
                .toString();
    }

    private boolean isDomReady() {
        return getDomReadyState().equals("complete");
    }

    public Duration getTimeout() {
        return timeout;
    }
}