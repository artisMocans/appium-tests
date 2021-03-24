package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class WelcomeScreen extends Screen {

    public WelcomeScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.printful.app:id/welcomeSignInButton")
    public AndroidElement signInButton;
}
