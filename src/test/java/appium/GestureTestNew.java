package appium;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

/**
 * Created by hiteshs on 8/30/18.
 */
public class GestureTestNew extends BaseTest{

    private By loginScreen = MobileBy.AccessibilityId("Login Screen");
    private By username = MobileBy.AccessibilityId("username");
    private By password = MobileBy.AccessibilityId("password");
    private By loginBtn = MobileBy.AccessibilityId("loginBtn");
    private By verificationTextEspresso = By.xpath(
            "//com.facebook.react.views.text.ReactTextView[@text='You are logged in as alice']");
    private By verificationTextUiAuto2 = By.xpath(
            "//android.widget.TextView[contains(@text, 'alice')]");

    @Test
    public void dragAndDropNew() throws InterruptedException, MalformedURLException {
        Thread.sleep(3000);
        login();
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("dragAndDrop"))).click();
        //driver.findElementByAccessibilityId("dragAndDrop").click();
        MobileElement dragMe = (MobileElement) driver.findElementByAccessibilityId("dragMe");
        MobileElement drop =(MobileElement)driver.findElementByAccessibilityId("dropzone");

        Thread.sleep(2000);
        Point source = dragMe.getCenter();
        Point target = drop.getCenter();
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence dragNDrop = new Sequence(finger, 0);
        dragNDrop.addAction(finger.createPointerMove(Duration.ofSeconds(0),
                PointerInput.Origin.viewport(), source.x, source.y));
        dragNDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()));
        //need to add pause for iOS, otherwise it will fail
        dragNDrop.addAction(new Pause(finger,Duration.ofMillis(600)));
        dragNDrop.addAction(finger.createPointerMove(Duration.ofSeconds(1),
                PointerInput.Origin.viewport(),target.x, target.y));
        dragNDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));
        driver.perform(Arrays.asList(dragNDrop));

    }
    public void login() throws InterruptedException {
        //MobileElement el= (MobileElement) driver.findElementByAccessibilityId("login");
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("login"))).click();
        //Thread.sleep(2000);
    }

    @Test
    public void pinchAndZoomNew() throws InterruptedException, MalformedURLException{
        //Thread.sleep(3000);
        login();

        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("photoView"))).click();
        Thread.sleep(2000);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");
        Dimension size = driver.manage().window().getSize();
        Point source = new Point(size.getWidth(), size.getHeight());

        Sequence pinchAndZoom1 = new Sequence(finger, 0);
        pinchAndZoom1.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), source.x / 2, source.y / 2));
        pinchAndZoom1.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        pinchAndZoom1.addAction(new Pause(finger,Duration.ofMillis(600)));
        pinchAndZoom1.addAction(finger.createPointerMove(Duration.ofMillis(10000),
                PointerInput.Origin.viewport(), source.x / 3, source.y / 3));
        pinchAndZoom1.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        Sequence pinchAndZoom2 = new Sequence(finger2, 0);
        pinchAndZoom2.addAction(finger2.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), source.x / 2, source.y / 2));
        pinchAndZoom2.addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        pinchAndZoom2.addAction(new Pause(finger,Duration.ofMillis(600)));
        pinchAndZoom2.addAction(finger2.createPointerMove(Duration.ofMillis(10000),
                PointerInput.Origin.viewport(), source.x * 3 / 4, source.y * 3 / 4));
        pinchAndZoom2.addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        List sequence = new ArrayList();
        sequence.add(pinchAndZoom1);
        sequence.add(pinchAndZoom2);
        driver.perform(sequence);
        driver.switchTo().parentFrame();
    }

    @Test
    public void slider() throws InterruptedException {
        //Thread.sleep(3000);
        login();

        driver.findElementByAccessibilityId("slider1").click();
        Thread.sleep(2000);
        MobileElement slider=(MobileElement) driver.findElementByAccessibilityId("slider");
        //wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("slider")));
        Point source=slider.getLocation();
        System.out.println("coordinate:"+source.x +","+source.y);
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Dimension d=slider.getSize();

        Sequence slide = new Sequence(finger, 0);
        slide.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), source.x, source.y ));
        slide.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        slide.addAction(new Pause(finger,Duration.ofMillis(600)));
        slide.addAction(finger.createPointerMove(Duration.ofMillis(1000),
                PointerInput.Origin.viewport(), source.x+200, source.y));
        slide.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(slide));
    }

    @Test
    public void longPress() throws InterruptedException{
        //Thread.sleep(3000);
        login();
        driver.findElementByAccessibilityId("longPress").click();
        MobileElement longpress=(MobileElement) driver.findElementByAccessibilityId("longpress");
        Thread.sleep(2000);
        Point source=longpress.getLocation();
        PointerInput finger= new PointerInput(PointerInput.Kind.MOUSE,"finger");
        Sequence longPress = new Sequence(finger, 0);

        longPress.addAction(finger.createPointerMove(Duration.ofMillis(100L),
                PointerInput.Origin.viewport(),source.x,source.y));
        longPress.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        longPress.addAction(new Pause(finger,Duration.ofSeconds(2)));
        longPress.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(longPress));
        //other way of doing
        //new Actions(driver).moveToElement(longpress).clickAndHold().pause(Duration.ofSeconds(2)).release().perform();

        //Thread.sleep(5000);
    }

    @Test
    public void doubleTap() throws InterruptedException{
        Thread.sleep(3000);
        login();
        driver.findElementByAccessibilityId("doubleTap").click();
        MobileElement longpress=(MobileElement) driver.findElementByAccessibilityId("doubleTapMe");
        Thread.sleep(2000);
        Point source=longpress.getLocation();
        PointerInput finger= new PointerInput(PointerInput.Kind.TOUCH,"finger");

        Sequence doubletap = new Sequence(finger, 0);

        doubletap.addAction(finger.createPointerMove(Duration.ofSeconds(0),
                PointerInput.Origin.viewport(),source.x,source.y));
        doubletap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        doubletap.addAction(new Pause(finger,Duration.ofMillis(600)));
        doubletap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        doubletap.addAction(finger.createPointerMove(Duration.ofSeconds(0),
                PointerInput.Origin.viewport(),source.x,source.y));
        doubletap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        doubletap.addAction(new Pause(finger,Duration.ofMillis(600)));
        doubletap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(doubletap));

        Thread.sleep(2000);

    }

    @Test
    public void imagetest() throws InterruptedException, IOException, URISyntaxException {

       By photos = MobileBy.AccessibilityId("Photo Demo");
        wait.until(ExpectedConditions.presenceOfElementLocated(photos)).click();
        By sunriseImage = MobileBy.image(getReferenceImageB64());
        Thread.sleep(2000);
        //driver.findElementByAccessibilityId(sunriseImage)
        wait.until(ExpectedConditions.presenceOfElementLocated(sunriseImage)).click();
        wait.until(ExpectedConditions.alertIsPresent());
        String alertText = driver.switchTo().alert().getText();
        //Assert.assertThat(alertText, Matchers.containsString("sunrise"));
    }
    private String getReferenceImageB64() throws URISyntaxException, IOException {
        URL refImgUrl = getClass().getClassLoader().getResource("Edition031_Reference_Image.png");
        File refImgFile = Paths.get(refImgUrl.toURI()).toFile();
        return Base64.getEncoder().encodeToString(Files.readAllBytes(refImgFile.toPath()));
    }

    @Test
    public void testLogin_Espresso() throws MalformedURLException {
        //AndroidDriver driver = getDriver("Espresso");
        //WebDriverWait wait = new WebDriverWait(driver, 10);
        ExpectedCondition<WebElement> loginScreenReady =
                ExpectedConditions.presenceOfElementLocated(loginScreen);

            wait.until(loginScreenReady).click();
            driver.findElement(username).sendKeys("alice");
            driver.findElement(password).sendKeys("mypassword");
            driver.findElement(loginBtn).click();
            driver.findElement(verificationTextEspresso);

    }

    @Test
    public void testLogin_UiAutomator2() throws MalformedURLException {
        ExpectedCondition<WebElement> loginScreenReady =
                ExpectedConditions.presenceOfElementLocated(loginScreen);
        ExpectedCondition<WebElement> usernameReady =
                ExpectedConditions.presenceOfElementLocated(username);
        ExpectedCondition<WebElement> verificationReady =
                ExpectedConditions.presenceOfElementLocated(verificationTextUiAuto2);


            wait.until(loginScreenReady).click();
            wait.until(usernameReady).sendKeys("alice");
            driver.findElement(password).sendKeys("mypassword");
            driver.findElement(loginBtn).click();
            wait.until(verificationReady);


    }

}
