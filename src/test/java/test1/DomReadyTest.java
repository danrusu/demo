package test1;

import base.WebTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.CustomWait;

import java.nio.file.Path;
import java.time.Duration;

import static java.lang.String.format;
import static java.lang.System.getProperty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


class DomReadyTest extends WebTest {

    public static final String APP_URL = format(
            "file://%s",
            Path.of(getProperty("user.dir"), "html", "test.html")
                    .toAbsolutePath()
    );
    private CustomWait wait;

    @BeforeEach
    public void openLink() {
        driver.get(APP_URL);
        wait = new CustomWait(driver, Duration.ofSeconds(2));
    }

    @Test
    void domReadyPositiveTest() {
        wait.untilDomReady();
    }

    @Test
    void domReadyNegativeTest() {
        //TODO
    }
}
