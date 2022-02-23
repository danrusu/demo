package test1;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class FirstTest {
    private static WebDriver driver;

    @BeforeAll
    public static void beforeAll() {
        chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void afterAll() {
        driver.quit();
    }

    public void waitUntilUrlContains(String substring, Duration timeout) {
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

    @Test
    public void firstTest() {
        driver.get("http://qatools.ro/test");

        waitUntilUrlContains("html1", Duration.ofSeconds(1));
    }

/*    @Test
    public void secondTest(){
        String a = "foobarfoo";
        String b = "bar";
        assertThat(a, containsString(b));
        }
    */

}
