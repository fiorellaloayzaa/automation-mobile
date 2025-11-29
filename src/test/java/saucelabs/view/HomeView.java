package saucelabs.view;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import saucelabs.utils.DriverFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class HomeView {

    private WebDriverWait getWait() {
        return new WebDriverWait(DriverFactory.getDriver(), 15);
    }

    private AndroidDriver getAndroidDriver() {
        return (AndroidDriver) DriverFactory.getDriver();
    }

    public void waitForHome() {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//android.widget.TextView[@text='Products']")
        ));
    }

    public void verifyProductsVisible() {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(
                By.id("com.saucelabs.mydemoapp.android:id/productIV")
        ));
    }

    public void selectProduct(String productName) {
        try {

            WebElement productsTitle = getWait().until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//android.widget.TextView[@text='Products']")
            ));

            try {
                WebElement productText = getWait().until(ExpectedConditions.visibilityOfElementLocated(
                        MobileBy.AndroidUIAutomator(
                                "new UiScrollable(new UiSelector().scrollable(true))" +
                                        ".scrollIntoView(new UiSelector().text(\"" + productName + "\"))"
                        )
                ));


                List<WebElement> productImages = getAndroidDriver().findElements(
                        By.id("com.saucelabs.mydemoapp.android:id/productIV")
                );

                for (int i = 0; i < productImages.size(); i++) {
                    WebElement image = productImages.get(i);
                    int imageY = image.getLocation().getY();
                    int textY = productText.getLocation().getY();

                    if (Math.abs(imageY - textY) < 600) {
                        image.click();

                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return;
                    }
                }

                productImages.get(0).click();

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } catch (Exception scrollException) {
                throw new RuntimeException("Producto no encontrado: " + productName);
            }

        } catch (Exception e) {
            String currentPackage = getAndroidDriver().getCurrentPackage();

            if (!currentPackage.contains("saucelabs")) {
                getAndroidDriver().navigate().back();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }

                // Verificar si volvimos
                currentPackage = getAndroidDriver().getCurrentPackage();
                if (currentPackage.contains("saucelabs")) {
                    WebElement firstImage = getWait().until(ExpectedConditions.elementToBeClickable(
                            By.id("com.saucelabs.mydemoapp.android:id/productIV")
                    ));
                    firstImage.click();

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                    return;
                }
            }

            throw new RuntimeException("No se pudo seleccionar el producto: " + productName, e);
        }
    }
}