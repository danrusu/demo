package test1;

import base.WebTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.CustomWait;

import java.nio.file.Path;
import java.time.Duration;

import static java.lang.System.getProperty;
import static utils.FileUtils.getFileURIForBrowser;

class DomReadyTest extends WebTest {

    public static final String LOCAL_HTML_URI = getFileURIForBrowser(Path.of(
            getProperty("user.dir"),
            "html", "test.html"));
    private CustomWait wait;

    @BeforeEach
    public void openLink() {
        driver.get(LOCAL_HTML_URI);
        wait = new CustomWait(driver, Duration.ofSeconds(2));
    }

    @Test
    void domReadyPositiveTest() {
        wait.untilDomReady();
    }

    @Test
    @Disabled("not implemented yet")
    void domReadyNegativeTest() {
        //TODO
    }
}
