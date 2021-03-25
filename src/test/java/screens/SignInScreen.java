package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class SignInScreen extends Screen {

    private static final String EMAIL_ERROR = "Please enter a valid email";
    private static final String PASSWORD_ERROR = "Please enter your password";

    public SignInScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View[7]/android.widget.EditText")
    public AndroidElement emailField;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View[7]/android.view.View[2]/android.widget.EditText")
    public AndroidElement passwordField;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View[7]/android.view.View[3]/android.widget.Button")
    public AndroidElement signIn;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View[7]/android.view.View[2]")
    public AndroidElement emailError;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View[7]/android.view.View[3]/android.view.View[2]")
    public AndroidElement passwordError;

    public void inputCredentials(String login, String password) {
        waitUtils.waitForElementToBeVisible(emailField, driver);
        emailField.sendKeys(login);
        passwordField.sendKeys(password);
    }

    public boolean isLoginInvalid() {
        waitUtils.waitForElementToBeVisible(emailError, driver);
        return emailError.getText().equals("Oops, your email or password is incorrect");
    }

    public boolean isEmptyLoginValidated() {
        waitUtils.waitForElementToBeVisible(emailError, driver);
        return emailError.getText().equals(EMAIL_ERROR) && passwordError.getText().equals(PASSWORD_ERROR);
    }
}