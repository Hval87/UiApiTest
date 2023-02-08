package util;

import aquality.selenium.browser.AqualityServices;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.Base64;
import java.util.Set;

public class BrowserUtil {
    public static final String TAB_CLOSE_SCRIPT="window.close ()";

    public static void addCookie(String param, String value) {
        AqualityServices.getBrowser().getDriver().manage().addCookie(new Cookie(param, value));
    }

    public static void switchToAnotherTab() {
        WebDriver driver=AqualityServices.getBrowser().getDriver();
        String originalWindow = driver.getWindowHandle();
        Set<String> listHandles = driver.getWindowHandles();
        for (String str : listHandles) {
            if (!str.equals(originalWindow)) {
                driver.switchTo().window(str);
            }
        }
    }

    public static void closeTab() {
        JavascriptExecutor js = (JavascriptExecutor) AqualityServices.getBrowser().getDriver();
        js.executeScript(TAB_CLOSE_SCRIPT);
    }

    public static String getScreenshotAsString(){
        byte [] img=AqualityServices.getBrowser().getScreenshot();
        return Base64.getEncoder().encodeToString(img);
    }
}
