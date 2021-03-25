package StepDefinitions;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.NoArgsConstructor;
import org.testng.Assert;
import screens.SignInScreen;
import screens.WelcomeScreen;
import testcases.TestRunHelper;

import java.net.MalformedURLException;

@NoArgsConstructor
public class LoginStepDefs {

    private final TestRunHelper testRunHelper = new TestRunHelper();
    private WelcomeScreen welcomeScreen;
    private SignInScreen signInScreen;
    private AppiumDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        driver = testRunHelper.setUpAppium();
        welcomeScreen = new WelcomeScreen(driver);
        signInScreen = new SignInScreen(driver);
    }

    @After
    public void tearDown() {
        testRunHelper.tearDownAppium(driver);
    }

    @Given("user taps sign in")
    public void userTapsSignIn() {
        welcomeScreen.signInButton.click();
    }

    @And("inputs {string} as email")
    public void inputsAsEmail(String email) {
        signInScreen.emailField.sendKeys(email);
    }

    @And("inputs {string} as password")
    public void inputsAsPassword(String password) {
        signInScreen.passwordField.sendKeys(password);
    }

    @When("logs in")
    public void logsIn() {
        signInScreen.signIn.click();
    }

    @Then("invalid credentials error is displayed")
    public void invalidCredentialsErrorIsDisplayed() {
        Assert.assertTrue(signInScreen.isLoginInvalid());
    }

    @Then("empty credentials are validated")
    public void emptyCredentialsAreValidated() {
        Assert.assertTrue(signInScreen.isEmptyLoginValidated());
    }
}