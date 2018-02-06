package appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

/**
 * Created by hiteshs on 2/4/18.
 */
public class GestureTest {

    public AppiumDriver driver;

    @Test
    public void horizontalSwipe() throws MalformedURLException, InterruptedException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME,
                "iOS");
        caps.setCapability(MobileCapabilityType.APP,
                "/Users/hiteshs/Documents/QA/vodqa.zip");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME,
                "iPhone X");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION,
                "11.2");
        driver = new IOSDriver(
                new URL("http://127.0.0.1:4723/wd/hub"), caps);
        Thread.sleep(3000);

        driver.findElementByAccessibilityId("login").click();
        Thread.sleep(2000);
        driver.findElementByAccessibilityId("slider1").click();

        Thread.sleep(2000);

        MobileElement slider=(MobileElement) driver.findElementByAccessibilityId("slider");

        TouchAction action= new TouchAction(driver);
        Dimension d=slider.getSize();

        action.press(ElementOption.
                element(slider,0,d.getHeight()/2))
                .waitAction(WaitOptions.
                        waitOptions(Duration.ofSeconds(2)))
                .moveTo(ElementOption.element(
                        slider,d.getWidth()/2,d.getHeight()/2))
                .release().perform();

    }

    @Test
    public void test() throws MalformedURLException, InterruptedException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME,
                "android");
        caps.setCapability(MobileCapabilityType.APP,
                "/Users/hiteshs/Documents/QA/AutomationProjects/build/VodQA.apk");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME,
                "test");
//        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION,
//                "11.2");
        driver = new AndroidDriver(
                new URL("http://127.0.0.1:4723/wd/hub"), caps);
        Thread.sleep(3000);

        driver.findElementByAccessibilityId("login").click();
        Thread.sleep(2000);
        driver.findElementByAccessibilityId("doubleTap").click();
        Thread.sleep(2000);
        MobileElement el= (MobileElement) driver.findElementByAccessibilityId("doubleTapMe");
        TouchAction action = new TouchAction(driver);
        action.press(ElementOption
                .element(el,el.getCenter().getX(),el.getCenter().getY()))
                .release()
                .press(ElementOption
                        .element(el,el.getCenter().getX(),
                                el.getCenter().getY()))
                .release().perform();

        Thread.sleep(2000);
        driver.quit();
    }

    @Test
    public void longPress() throws MalformedURLException, InterruptedException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME,
                "android");
        caps.setCapability(MobileCapabilityType.APP,
                "/Users/hiteshs/Documents/QA/AutomationProjects/build/VodQA.apk");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME,
                "test");
//        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION,
//                "11.2");
        driver = new AndroidDriver(
                new URL("http://127.0.0.1:4723/wd/hub"), caps);
        Thread.sleep(3000);

        driver.findElementByAccessibilityId("login").click();
        Thread.sleep(2000);
        driver.findElementByAccessibilityId("longPress").click();
        MobileElement longpress=(MobileElement) driver.findElementByAccessibilityId("longpress");
        Thread.sleep(2000);

        TouchAction act= new TouchAction(driver);
        act.longPress(LongPressOptions
                .longPressOptions()
                .withDuration(Duration.ofSeconds(2))
                .withElement(ElementOption.element(longpress)))
                .perform();
        Thread.sleep(2000);
        driver.quit();
    }
}
