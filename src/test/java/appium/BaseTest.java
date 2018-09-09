package appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by hiteshs on 8/29/18.
 */
public class BaseTest {
    public String uia2="UIAutomator2";
    public String espresso="Espresso";
    protected AppiumDriver driver;
    public WebDriverWait wait;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        androidCap();
        //iOSCap();
        wait= new WebDriverWait(driver,10);
    }

    public void androidCap() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, espresso);
        caps.setCapability(MobileCapabilityType.APP, "/Users/hiteshs/Documents/QA/appiumworkshop/src/test/resources/apk/VodQA-1.apk");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "aa");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
    }

    public void iOSCap() throws MalformedURLException {
                        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.APP,
                "/Users/hiteshs/Documents/QA/appiumworkshop/src/test/resources/apk/TheApp-v1.7.0.app.zip");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME,
                "iPhone 8");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION,
                "11.2");
        driver = new IOSDriver(
                new URL("http://127.0.0.1:4723/wd/hub"), caps);

    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}

