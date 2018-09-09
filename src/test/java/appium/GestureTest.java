package appium;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.time.Duration;

/**
 * Created by hiteshs on 2/4/18.
 */
public class GestureTest extends BaseTest{


    @Test
    public void horizontalSwipe() throws MalformedURLException, InterruptedException {

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

    @Test
    public void dragAndDrop() throws InterruptedException, MalformedURLException {

        Thread.sleep(3000);

        driver.findElementByAccessibilityId("login").click();
        Thread.sleep(2000);
        driver.findElementByAccessibilityId("dragAndDrop").click();

        Thread.sleep(2000);
        MobileElement source = (MobileElement) driver.findElementByAccessibilityId("dragMe");
        MobileElement target = (MobileElement) driver.findElementByAccessibilityId("dropzone");
        Actions actionBuilder = new Actions(driver);
        Action dragAndDropAction = actionBuilder.clickAndHold(source)
                .moveToElement(target, 1, 1)
                .release(target)
                .build();
        dragAndDropAction.perform();
    }

}
