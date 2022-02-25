package base;

import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;

public class ChromeDriverExtension implements
        ParameterResolver,
        BeforeAllCallback,
        AfterEachCallback {
    private WebDriver driver;

    @Override
    public boolean supportsParameter(
            ParameterContext parameterContext,
            ExtensionContext extensionContext
    ) throws ParameterResolutionException {
        return (parameterContext.getParameter().getType() == ChromeDriver.class);
    }

    @Override
    public Object resolveParameter(
            ParameterContext parameterContext,
            ExtensionContext extensionContext
    ) throws ParameterResolutionException {
        driver = new ChromeDriver();
        return driver;
    }

    @Override
    public void beforeAll(ExtensionContext extensionContext) {
        chromedriver().setup();
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) {
        driver.quit();
    }
}
