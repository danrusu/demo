package test1;

import base.WebTest;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertThrows;
import utils.CustomWaits;


class UrlTest extends WebTest {

    public static final String APP_URL = "http://qatools.ro/test";
    private CustomWaits waits;

    @BeforeEach
    public void openLink(){
        driver.get(APP_URL);
        waits = new CustomWaits(driver, Duration.ofSeconds(2));
    }

    @Test
    void urlPositiveTest() {
        waits.waitUntilUrlContains("html");
    }

    @Test
    void urlNegativeTest() {
        var assertionError = assertThrows(
                AssertionError.class,
                () -> waits.waitUntilUrlContains("html1"));

        Stream.of(
                "Timed out after 2 seconds",
                "Expected: a string containing \"html1\"",
                "but: was \"http://qatools.ro/test.html\""
        ).forEach(assertionMessageSubstring -> assertThat(
                assertionError.getMessage(),
                containsString(assertionMessageSubstring)));

        System.out.println(assertionError.getMessage());
    }

}
