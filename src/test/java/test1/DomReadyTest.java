package test1;

import base.WebTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.CustomWaits;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


class DomReadyTest extends WebTest {

    public static final String APP_URL = "http://qatools.ro";
    private CustomWaits waits;

    @BeforeEach
    public void openLink() {
        driver.get(APP_URL);
        waits = new CustomWaits(driver, Duration.ofSeconds(2));
    }

    @Test
    void domReadyPositiveTest() {
        waits.waitUntilDomReady();
    }
}
