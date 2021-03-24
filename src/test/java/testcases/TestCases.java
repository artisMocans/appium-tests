package testcases;

import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import screens.SignInScreen;
import screens.WelcomeScreen;

public class TestCases extends BaseTest {

    AppiumDriverLocalService appiumDriverLocalService;

    @Test
    public void loginViaInvalidCredentials() {
        WelcomeScreen welcomeScreen = new WelcomeScreen(driver);
        SignInScreen signInScreen = new SignInScreen(driver);

        welcomeScreen.signInButton.click();
        signInScreen.inputCredentials("artismocans@gmail.com", "abcd1234");
        signInScreen.signIn.click();

        Assert.assertTrue(signInScreen.isLoginInvalid());
    }

    @BeforeTest
    @Override
    public void setUpPage() {
        DesiredCapabilities desiredCapabilities = getDesiredCapabilities();
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.withIPAddress("127.0.0.1");
        builder.usingPort(4723);
        builder.withCapabilities(desiredCapabilities);
        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");
        appiumDriverLocalService = AppiumDriverLocalService.buildService(builder);
        appiumDriverLocalService.start();
    }

    @AfterSuite
    @Override
    public void tearDownAppium() {
        super.tearDownAppium();
        appiumDriverLocalService.stop();
    }

    public DesiredCapabilities getDesiredCapabilities() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
        desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, true);
        return desiredCapabilities;
    }
}
