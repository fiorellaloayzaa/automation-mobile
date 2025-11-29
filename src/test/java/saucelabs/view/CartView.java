package saucelabs.view;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import saucelabs.utils.DriverFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class CartView {

    private WebDriverWait getWait() {
        return new WebDriverWait(DriverFactory.getDriver(), 15);
    }

    private AndroidDriver getAndroidDriver() {
        return (AndroidDriver) DriverFactory.getDriver();
    }

    By productImage = By.id("com.saucelabs.mydemoapp.android:id/productIV");
    By plusButton = By.id("com.saucelabs.mydemoapp.android:id/plusIV");
    By minusButton = By.id("com.saucelabs.mydemoapp.android:id/minusIV");
    By addToCartButton = By.id("com.saucelabs.mydemoapp.android:id/cartBt");
    By cartIcon = By.id("com.saucelabs.mydemoapp.android:id/cartRL");

    private void scrollDown() {
        Dimension size = getAndroidDriver().manage().window().getSize();
        int startX = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);

        new TouchAction(getAndroidDriver())
                .press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(startX, endY))
                .release()
                .perform();
    }

    public void addUnits(int units) {
        try {
            getWait().until(ExpectedConditions.visibilityOfElementLocated(productImage));

            scrollDown();
            Thread.sleep(1000);

            if (units > 1) {
                for (int i = 1; i < units; i++) {
                    WebElement plusBtn = getWait().until(ExpectedConditions.elementToBeClickable(plusButton));
                    plusBtn.click();
                    Thread.sleep(300);
                }
            }

            // Hacer clic en "Add to cart"
            WebElement addBtn = getWait().until(ExpectedConditions.elementToBeClickable(addToCartButton));
            addBtn.click();

            Thread.sleep(500);

            // Volver a la pantalla principal
            getAndroidDriver().navigate().back();

            Thread.sleep(500);

        } catch (Exception e) {
            System.out.println(getAndroidDriver().getPageSource());
            throw new RuntimeException("No se pudo agregar el producto al carrito", e);
        }
    }

    public void verifyCartIsUpdated() {
        // Hacer clic en el ícono del carrito
        getWait().until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
        getWait().until(ExpectedConditions.visibilityOfElementLocated(cartIcon));
    }

    public void goBackToHome() {
        getAndroidDriver().navigate().back();
    }
}