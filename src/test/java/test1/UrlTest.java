package test1;

import base.WebTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.time.Duration;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.lang.System.getProperty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertThrows;

import utils.CustomWait;


class UrlTest extends WebTest {

    public static final String APP_URL = format(
            "file://%s",
            Path.of(getProperty("user.dir"), "html", "test.html")
                    .toAbsolutePath()
    );
    private CustomWait waits;

    @BeforeEach
    public void openLink() {
        driver.get(APP_URL);
        waits = new CustomWait(driver, Duration.ofSeconds(2));
    }

    @Test
    void urlPositiveTest() {
        waits.untilUrlContains("html");
    }

    @Test
    void urlNegativeTest() {
        var assertionError = assertThrows(
                AssertionError.class,
                () -> waits.untilUrlContains("html1"));

        System.out.println(assertionError.getMessage());
        Stream.of(
                "Timed out after 2 seconds",
                "Expected: a string containing \"html1\"",
                format("but: was \"%s\"", APP_URL)
        ).forEach(assertionMessageSubstring -> assertThat(
                assertionError.getMessage(),
                containsString(assertionMessageSubstring)));
    }

}
