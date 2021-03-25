package testcases;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.PropertyUtils;

import java.net.MalformedURLException;
import java.net.URL;

public class TestRunHelper {

    public static AppiumDriver driver;
    public final static String APPIUM_SERVER_URL = PropertyUtils.getProperty("appium.server.url", "http://127.0.0.1:4723/wd/hub");
    public final static int IMPLICIT_WAIT = PropertyUtils.getIntegerProperty("implicitWait", 30);
    AppiumDriverLocalService appiumDriverLocalService;

    public AppiumDriver setUpAppium() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        setDesiredCapabilitiesForAndroid(capabilities);
        driver = new AppiumDriver(new URL(APPIUM_SERVER_URL), capabilities);

        DesiredCapabilities desiredCapabilities = getDesiredCapabilities();
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.withIPAddress("127.0.0.1");
        builder.usingAnyFreePort();
        builder.withCapabilities(desiredCapabilities);
        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");

        appiumDriverLocalService = AppiumDriverLocalService.buildService(builder);
        appiumDriverLocalService.start();
        return driver;
    }

    public void tearDownAppium(AppiumDriver driver) {
        driver.quit();
        appiumDriverLocalService.stop();
    }

    private void setDesiredCapabilitiesForAndroid(DesiredCapabilities desiredCapabilities) {
        String PLATFORM_NAME = PropertyUtils.getProperty("android.platform");
        String PLATFORM_VERSION = PropertyUtils.getProperty("android.platform.version");
        String DEVICE_NAME = PropertyUtils.getProperty("android.device.name");
        String APP_PACKAGE_NAME = PropertyUtils.getProperty("android.app.packageName");
        String APP_ACTIVITY_NAME = PropertyUtils.getProperty("android.app.activityName");
        String APP_FULL_RESET = PropertyUtils.getProperty("android.app.full.reset");
        String APP_NO_RESET = PropertyUtils.getProperty("android.app.no.reset");

        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM_NAME);
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, PLATFORM_VERSION);
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, APP_PACKAGE_NAME);
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, APP_ACTIVITY_NAME);
        desiredCapabilities.setCapability(MobileCapabilityType.FULL_RESET, APP_FULL_RESET);
        desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, APP_NO_RESET);
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
    }

    private DesiredCapabilities getDesiredCapabilities() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
        desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, true);
        return desiredCapabilities;
    }
}
