package appium;

import io.appium.java_client.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by hiteshs on 12/27/17.
 */
public class AndroidBaseTest {


    AppiumDriverLocalService service;
    AppiumDriver driver;

//    @BeforeTest
//    public void startAppiumServer() throws MalformedURLException {
////        AppiumServiceBuilder builder= new AppiumServiceBuilder().withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
////                .usingAnyFreePort();
////        service= builder.build();
////        service.start();
//        DesiredCapabilities caps = new DesiredCapabilities();
//        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
//        caps.setCapability(MobileCapabilityType.DEVICE_NAME,"android");
//        //caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,"com.android2.calculator3.Calculator");
//        //caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"com.vodqareactnative");
//        //caps.setCapability(MobileCapabilityType.APP,System.getProperty("user.dir")+"/build/AndroidCalculator.apk");
//        caps.setCapability(MobileCapabilityType.APP,System.getProperty("user.dir")+"/build/VodQA.apk");
//        //driver= new AndroidDriver<MobileElement>(service.getUrl(),caps);
//        driver= new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), caps);
//    }

    @AfterTest
    public void stopAppiumServer(){
        driver.quit();
        //service.stop();
    }

    @Test
    public void testAddition() throws InterruptedException {
//        driver.findElementById("com.android2.calculator3:id/cling_dismiss").click();
//        driver.findElementById("com.android2.calculator3:id/digit2").click();
//        driver.findElementById("com.android2.calculator3:id/plus").click();
//        driver.findElementById("com.android2.calculator3:id/digit2").click();
//        driver.findElementById("com.android2.calculator3:id/equal").click();
//        String a=driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.ViewSwitcher/android.widget.HorizontalScrollView/android.widget.LinearLayout/android.widget.EditText").getText();
//        String b=driver.findElementByClassName("android.widget.EditText").getText();
//        System.out.println("Sum is :"+a);
//        System.out.println("Sum is :"+b);
        Thread.sleep(3000);
    }

    @Test
    public void slider() throws InterruptedException, MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
        caps.setCapability(MobileCapabilityType.APP, "/Users/hiteshs/Documents/QA/AutomationProjects/build/VodQA.apk");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "aa");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        Thread.sleep(3000);

        driver.findElementByAccessibilityId("login").click();
        Thread.sleep(3000);
        driver.findElementByAccessibilityId("slider1").click();
        Thread.sleep(3000);
        MobileElement slider =
                (MobileElement) driver.findElementByAccessibilityId("slider");
        MobileElement slider1 =
                (MobileElement) driver.findElementByAccessibilityId("slider1");
        Dimension d = slider.getSize();
        Dimension d1 = slider1.getSize();

        TouchAction action = new TouchAction(driver);
        action.press(ElementOption.element(slider).withCoordinates(0,d.getHeight()/2))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
                .moveTo(ElementOption.element(slider)
                        .withCoordinates(d.getWidth()/2,d.getHeight()/2));

        TouchAction action1 = new TouchAction(driver);
        action1.press(ElementOption.element(slider1).withCoordinates(0,d1.getHeight()/2))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
                .moveTo(ElementOption.element(slider1)
                        .withCoordinates(d1.getWidth(),d1.getHeight()/2));

        new MultiTouchAction(driver).add(action).add(action1).perform();

//        touchAction.press(slider, 0,d.getHeight()/2)
//                .waitAction(Duration.ofSeconds(3))
//                .moveTo(slider,d.getWidth()/2,d.getHeight()/2);
//        touchAction.release().perform();

        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void verticleSwipe()throws InterruptedException, MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
        caps.setCapability(MobileCapabilityType.APP, "/Users/hiteshs/Documents/QA/AutomationProjects/build/VodQA.apk");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "aa");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        Thread.sleep(3000);

        driver.findElementByAccessibilityId("login").click();
        Thread.sleep(3000);
        driver.findElementByAccessibilityId("verticalSwipe").click();
        Thread.sleep(3000);

        MobileElement verticalSwipe= (MobileElement) driver.findElementByAccessibilityId("listview");
        Dimension d=verticalSwipe.getSize();
        TouchAction action= new TouchAction(driver);

        action.press(ElementOption.element(verticalSwipe,d.getWidth()/2,d.getHeight()-20))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
                .moveTo(ElementOption.element(verticalSwipe,d.getWidth()/2,d.getHeight()/2+50)).release().perform();

    }

    @Test
    public void dragAndDrop() throws InterruptedException, MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
        caps.setCapability(MobileCapabilityType.APP, "/Users/hiteshs/Documents/QA/AutomationProjects/build/VodQA.apk");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "aa");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME,"Espresso");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        Thread.sleep(3000);

        driver.findElementByAccessibilityId("login").click();
        Thread.sleep(3000);
        driver.findElementByAccessibilityId("dragAndDrop").click();
        Thread.sleep(3000);

        MobileElement drageMe= (MobileElement) driver.findElementByAccessibilityId("dragMe");
        //MobileElement dropZone= (MobileElement) driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"dropzone\"]/android.widget.TextView");
        MobileElement dropZone= (MobileElement) driver.findElementByAccessibilityId("dropZone");
        Dimension d=drageMe.getSize();
        TouchAction action= new TouchAction(driver);

//        action.press(ElementOption.element(drageMe,drageMe.getCenter().getX(),drageMe.getCenter().getY()))
//                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
//                .moveTo(ElementOption.element(dropZone,drageMe.getCenter().getX(),drageMe.getCenter().getY())).release().perform();

//        action.press(ElementOption.element(drageMe))
//                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
//                .moveTo(ElementOption.element(dropZone)).release().perform();

        Point source = drageMe.getCenter();
        Point target = dropZone.getCenter();
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence dragNDrop = new Sequence(finger, 1);
        dragNDrop.addAction(finger.createPointerMove(Duration.ofSeconds(1),
                PointerInput.Origin.viewport(), source.x, source.y));
        dragNDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()));
        dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(600),
                PointerInput.Origin.viewport(),target.x, target.y));
        dragNDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));
        driver.perform(Arrays.asList(dragNDrop));

//        action.press(drageMe)
//                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
//                .moveTo(dropZone).release().perform();

        Thread.sleep(2000);
    }

    @Test
    public void doubleTap() throws InterruptedException, MalformedURLException{

        driver.findElementByAccessibilityId("login").click();
        Thread.sleep(3000);
        driver.findElementByAccessibilityId("doubleTap").click();
        Thread.sleep(3000);

        //TouchAction action= new TouchAction(driver);

        WebElement doubleTapMe = driver.findElementByAccessibilityId("doubleTapMe");
        TapOptions tapop= new TapOptions();
            tapop
                .withPosition(PointOption.point(doubleTapMe.getSize().getWidth() / 2,
                        doubleTapMe.getSize().getHeight() / 2)).withTapsCount(2)
                .build();
        new TouchAction(driver).tap(tapop)
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).release().perform();

    }

    //TODO - skip
    @Test
    public void AndroidCaps() throws InterruptedException, MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "aa");
        caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.vodqareactnative");
        caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.vodqareactnative.MainActivity");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        Thread.sleep(10000);
    }

    @Test
    public void AndroidCapsNative() throws InterruptedException, MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "aa");
        caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.android.settings");
        caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.android.settings.Settings");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        Thread.sleep(10000);
    }

    @Test
    public void iOSCapsBasicWithTypeAndXpath() throws InterruptedException, MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6");
        caps.setCapability(MobileCapabilityType.APP,
                "/Users/hiteshs/Downloads/Vodqa.zip");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.2");
        driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        //driver.findElement(MobileBy.iOSNsPredicateString("name contains 'login'"));
        Thread.sleep(1000);

        driver.findElementByClassName("XCUIElementTypeTextField").clear();
        driver.findElementByClassName("XCUIElementTypeTextField").sendKeys("admin");
        driver.findElementByClassName("XCUIElementTypeSecureTextField").clear();
        driver.findElementByClassName("XCUIElementTypeSecureTextField").sendKeys("admin");
        driver.findElementByXPath("//XCUIElementTypeOther[@name=\"login\"]").click();
        Thread.sleep(2000);
    }

    @Test
    public void iOSCapsBasicWithId() throws InterruptedException, MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6");
        caps.setCapability(MobileCapabilityType.APP,
                "/Users/hiteshs/Downloads/Vodqa.zip");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.2");
        driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        Thread.sleep(1000);

        driver.findElementByAccessibilityId("username").clear();
        driver.findElementByAccessibilityId("username").sendKeys("admin");
        driver.findElementByAccessibilityId("password").clear();
        driver.findElementByAccessibilityId("password").sendKeys("admin");
        driver.findElementByAccessibilityId("login").click();
        Thread.sleep(2000);
    }

    @Test
    public void iosPredicate() throws InterruptedException, MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6");
        caps.setCapability(MobileCapabilityType.APP,
                "/Users/hiteshs/Downloads/Vodqa.zip");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.2");
        driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        //driver.findElement(MobileBy.iOSNsPredicateString("name contains 'login'"));
        //Thread.sleep(1000);
        WebElement el=driver.findElementByAccessibilityId("login");
        new WebDriverWait(driver,10)
                .until(ExpectedConditions.elementToBeClickable(el));
        el.click();
        //driver.findElementByAccessibilityId("login").click();
        Thread.sleep(2000);

        driver.findElement
                (MobileBy.iOSNsPredicateString
                        ("name BEGINSWITH 'Slide'")).click();
        Thread.sleep(1000);
//        driver.findElementByXPath("//XCUIElementTypeOther[@name=\" Back\"]").click();
//        Thread.sleep(1000);
//        driver.findElement(MobileBy.iOSNsPredicateString("name ENDSWITH 'number'")).click();
//        Thread.sleep(1000);
//        driver.findElementByXPath("//XCUIElementTypeOther[@name=\" Back\"]").click();
//        Thread.sleep(1000);
//        driver.findElement(MobileBy.iOSNsPredicateString("label CONTAINS 'double tap'")).click();

    }

    @Test
    public void iosChaining() throws InterruptedException, MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6");
        caps.setCapability(MobileCapabilityType.APP,
                "/Users/hiteshs/Downloads/Vodqa.zip");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.2");
        driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        //driver.findElement(MobileBy.iOSNsPredicateString("name contains 'login'"));
        Thread.sleep(1000);

        driver.findElementByAccessibilityId("login").click();
        Thread.sleep(2000);
        driver.findElementByAccessibilityId("chainedView").click();

        String a=driver.findElement(MobileBy.AccessibilityId("container2")).findElement(MobileBy.AccessibilityId("textView")).getText();
        System.out.println("a: "+a);

//        driver.findElementByXPath("//XCUIElementTypeOther[@name=\" Back\"]").click();
//        Thread.sleep(1000);
//        driver.findElement(MobileBy.iOSNsPredicateString("label CONTAINS double tap")).click();

    }

    @Test
    public void androidCapsBasicWithTypeAndId() throws InterruptedException, MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
        caps.setCapability(MobileCapabilityType.APP, "/Users/hiteshs/Downloads/VodQA.apk");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "android");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        //driver.findElement(MobileBy.iOSNsPredicateString("name contains 'login'"));
        Thread.sleep(1000);

        driver.findElementByClassName("android.widget.EditText").clear();
        driver.findElementByClassName("android.widget.EditText").sendKeys("admin");
        driver.findElementByAccessibilityId("password").clear();
        driver.findElementByAccessibilityId("password").sendKeys("admin");
        driver.findElementByAccessibilityId("login").click();
        Thread.sleep(2000);
    }



    @Test
    public void AndroidCapsFullReset() throws InterruptedException, MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "aa");
        caps.setCapability(MobileCapabilityType.APP, "/Users/shridhk/Desktop/BuyNowMobile-VirginTrains.apk");
        caps.setCapability(MobileCapabilityType.FULL_RESET, true);
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        Thread.sleep(10000);
        driver.quit();
    }

    @Test
    public void AndroidCapsNoReset() throws InterruptedException, MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "aa");
        caps.setCapability(MobileCapabilityType.APP, "/Users/shridhk/VodQaAdvancedAppium/VodQA.apk");
        caps.setCapability(MobileCapabilityType.NO_RESET, true);
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);

        Thread.sleep(10000);
        driver.quit();
    }

    @Test
    public void AndroidCapsLocators() throws InterruptedException, MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "aa");
        caps.setCapability(MobileCapabilityType.APP, "/Users/shridhk/VodQaAdvancedAppium/VodQA.apk");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);


        new WebDriverWait(driver, 30).until(ExpectedConditions.
                elementToBeClickable(MobileBy.AccessibilityId("login")));

        driver.findElementByAccessibilityId("login").click();

        new WebDriverWait(driver, 30).until(ExpectedConditions.
                elementToBeClickable(MobileBy.AccessibilityId("chainedView")));

        driver.findElementByAccessibilityId("chainedView").click();
//
        String text = driver.findElementByAccessibilityId("container3").findElement(MobileBy.AccessibilityId("textView")).getText();
        System.out.println(text);
        Thread.sleep(10000);
    }

}
