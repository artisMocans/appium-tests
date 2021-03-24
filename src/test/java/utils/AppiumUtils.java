package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;

import java.util.List;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;

public class AppiumUtils {
    public static WaitUtils waitUtils = new WaitUtils();

    public static boolean isElementDisplayed(IOSElement element) {
        waitUtils.staticWait(500);
        boolean isPresent = false;
        try {
            element.isDisplayed();
            isPresent = true;
        } catch (Exception e) {
            isPresent = false;
        }
        return isPresent && element.isDisplayed();
    }

    public static void scrollToElement(MobileElement element, AppiumDriver driver) {
        int i = 0;
        do {
            i++;
            TouchAction swipe = new TouchAction(driver).press(PointOption.point(200, 500))
                    .waitAction(waitOptions(ofSeconds(2)))
                    .moveTo(PointOption.point(200, -100)).release();
            swipe.perform();
        } while (!isElementDisplayed((IOSElement) element)); //(i==2);
    }

    public static void scrollUpToElement(MobileElement element, AppiumDriver driver) {
        Dimension size = driver.manage().window().getSize();
        int start_Y = (int) (size.getHeight() * 0.80);
        int end_Y = (int) (size.getHeight() * 0.20);
        int start_end_X = (int) (size.getWidth() * 0.50);
        while (!isElementDisplayed((IOSElement) element)) {
            TouchAction swipe = new TouchAction(driver).press(PointOption.point(start_end_X, end_Y))
                    .waitAction(waitOptions(ofSeconds(2)))
                    .moveTo(PointOption.point(start_end_X, start_Y)).release().perform();
        }
    }

    public static void verticalScroll(AppiumDriver driver) {
        Dimension size = driver.manage().window().getSize();
        int start_Y = (int) (size.getHeight() * 0.75);
        int end_Y = (int) (size.getHeight() * 0.10);
        int start_end_X = (int) (size.getWidth() * 0.50);

        TouchAction swipe = new TouchAction(driver).press(PointOption.point(start_end_X, start_Y))
                .waitAction(waitOptions(ofSeconds(2)))
                .moveTo(PointOption.point(start_end_X, end_Y)).release().perform();
    }

    public static void verticalScroll(AppiumDriver driver, int startY, int endY) {
        Dimension size = driver.manage().window().getSize();
       // int start_Y = (int) (size.getHeight() * 0.95);
      //  int end_Y = (int) (size.getHeight() * 0.10);
        int start_end_X = (int) (size.getWidth() * 0.50);

        TouchAction swipe = new TouchAction(driver).press(PointOption.point(start_end_X, startY))
                .waitAction(waitOptions(ofSeconds(6)))
                .moveTo(PointOption.point(start_end_X, endY)).release().perform();
    }

    public static void doSwipe(int startX, int startY, int endX, int endY, AppiumDriver driver) {
        TouchAction swipe = new TouchAction(driver).press(PointOption.point(startX, startY))
                .waitAction(waitOptions(ofMillis(800)))
                .moveTo(PointOption.point(endX, endY)).release().perform();
    }


    public static void clickOnPoint(int x, int y, AppiumDriver driver) {
        new TouchAction(driver).press(PointOption.point(x, y)).release().perform();
    }

    public static String getRandomNumber() {
        return String.valueOf(System.currentTimeMillis() % 100000);
    }

    public static boolean isAlertPresent(AppiumDriver driver) {
        boolean alertIsPresent = false;
        try {
            driver.switchTo().alert();
            alertIsPresent = true;
        } catch (Exception ex) {
            alertIsPresent = false;
        }
        return alertIsPresent;
    }


    public static AndroidElement getElementFromText(AppiumDriver driver, List<AndroidElement> elementList, String textOfElement) {
        for (AndroidElement el : elementList) {
            if (textOfElement.equals(el.getText())) {
                return el;
            }
        }
        return null;
    }


}
