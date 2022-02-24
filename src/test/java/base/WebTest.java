package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;

public class WebTest {
    protected WebDriver driver;

    @BeforeEach
    public void beforeAll() {
        chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterEach
    public void afterAll() {
        driver.quit();
    }
}
