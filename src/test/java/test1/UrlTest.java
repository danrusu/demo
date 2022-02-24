package test1;

import base.WebTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static utils.CustomWaits.waitUntilUrlContains;


class UrlTest extends WebTest {

    public static final String APP_URL = "http://qatools.ro/test";
    private Duration twoSecondsDuration = Duration.ofSeconds(2);

    @BeforeEach
    public void openLink(){
        driver.get(APP_URL);
    }

    @Test
    void urlTestPositive() {
        waitUntilUrlContains("html", driver, twoSecondsDuration);
    }

    @Test
    void urlTestNegative() {
        var assertionError = assertThrows(
                AssertionError.class,
                () -> waitUntilUrlContains("html1", driver, twoSecondsDuration));

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
