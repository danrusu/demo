package utils;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class CustomWaits {
    private CustomWaits(){}

    public static void waitUntilUrlContains(String substring, WebDriver driver, Duration timeout) {
        try {
            new WebDriverWait(driver, timeout)
                    .until(d -> driver.getCurrentUrl().contains(substring));
        } catch (TimeoutException timeoutException) {
            assertThat(
                    format("Timed out after %d seconds", timeout.getSeconds()),
                    driver.getCurrentUrl(),
                    containsString(substring));
        }
    }
}
