package tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import org.apache.logging.log4j.core.util.JsonUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.lang.reflect.Method;

public abstract class BaseTest {

    public Browser browser;

    @BeforeMethod
    public void setUp(Method method) {
        browser = AqualityServices.getBrowser();
        browser.maximize();

    }

    @AfterMethod
    public void tearDown() {
       // browser.quit();
    }
}
