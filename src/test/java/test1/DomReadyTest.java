package test1;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.CustomWait;

import java.nio.file.Path;
import java.time.Duration;

import static java.lang.System.getProperty;
import static utils.FileUtils.getFileURIForBrowser;

@ExtendWith(SeleniumJupiter.class)
class DomReadyTest {

    public static final String LOCAL_HTML_URI = getFileURIForBrowser(Path.of(
            getProperty("user.dir"),
            "html", "test.html"));
    private CustomWait wait;

    @BeforeEach
    public void openLink(ChromeDriver driver) {
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
