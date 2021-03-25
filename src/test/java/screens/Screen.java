package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import utils.PropertyUtils;
import utils.WaitUtils;

import java.time.Duration;

public class Screen {
    public final static int IMPLICIT_WAIT = PropertyUtils.getIntegerProperty("implicitWait", 30);
    WaitUtils waitUtils;
    protected final AppiumDriver driver;

    protected Screen(AppiumDriver driver) {
        this.driver = driver;
        initElements();
        loadProperties();
        waitUtils = new WaitUtils();
    }

    private void initElements() {
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(IMPLICIT_WAIT)), this);
    }

    private void loadProperties() {
        //TODO: Add the properties.
    }

}
