package test1;

import base.ChromeDriverExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.CustomWait;

import java.nio.file.Path;
import java.time.Duration;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.lang.System.getProperty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static utils.FileUtils.getFileURIForBrowser;

@ExtendWith(ChromeDriverExtension.class)
class UrlTest {

    public static final String LOCAL_HTML_URI = getFileURIForBrowser(Path.of(
            getProperty("user.dir"),
            "html", "test.html"));

    private CustomWait waits;

    @BeforeEach
    public void openLink(ChromeDriver driver) {
        driver.get(LOCAL_HTML_URI);
        waits = new CustomWait(driver, Duration.ofSeconds(2));
    }

    @Test
    void urlPositiveTest() {
        waits.untilUrlContains("html");
    }

    @Test
    void urlNegativeTest() {
        final var expectedAssertionError = assertThrows(
                AssertionError.class,
                () -> waits.untilUrlContains("html1"));

        System.out.println(expectedAssertionError.getMessage());
        Stream.of(
                "Timed out after 2 seconds",
                "Expected: a string containing \"html1\"",
                format("but: was \"%s\"", LOCAL_HTML_URI)
        ).forEach(assertionMessageSubstring -> assertThat(
                expectedAssertionError.getMessage(),
                containsString(assertionMessageSubstring)));
    }

}
