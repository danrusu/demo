package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class CustomWait {
    private final WebDriver driver;
    private final Duration timeout;

    public CustomWait(WebDriver driver, Duration timeout) {
        this.driver = driver;
        this.timeout = timeout;
    }

    public void untilUrlContains(String substring) {
        try {
            new WebDriverWait(driver, timeout)
                    .until(d -> driver.getCurrentUrl().contains(substring));
        } catch (final TimeoutException timeoutException) {
            assertThat(
                    getTimeoutMessage(),
                    driver.getCurrentUrl(),
                    containsString(substring));
        }
    }

    public void untilDomReady() {
        try {
            new WebDriverWait(driver, timeout).until(d -> isDomReady());
        } catch (final TimeoutException timeoutException) {
            assertThat(
                    getTimeoutMessage(),
                    getDomReadyState(),
                    is("complete"));
        }
    }

    private String getTimeoutMessage() {
        return format("Timed out after %d seconds", timeout.getSeconds());
    }

    public Duration getTimeout() {
        return timeout;
    }

    private String getDomReadyState() {
        return ((JavascriptExecutor) driver)
                .executeScript("return document.readyState")
                .toString();
    }

    private boolean isDomReady() {
        return getDomReadyState().equals("complete");
    }

}