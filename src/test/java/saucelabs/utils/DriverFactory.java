package saucelabs.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class DriverFactory {

    private static AppiumDriver<MobileElement> driver;

    public static AppiumDriver<MobileElement> getDriver() {
        if (driver == null) {
            try {
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability("platformName", "Android");
                caps.setCapability("automationName", "UiAutomator2");
                caps.setCapability("deviceName", "emulator-5554");

                // Especifica el package y activity de la app
                caps.setCapability("appPackage", "com.saucelabs.mydemoapp.android");
                caps.setCapability("appActivity", "com.saucelabs.mydemoapp.android.view.activities.SplashActivity");

                caps.setCapability("app", "C:/Users/mawik/Downloads/mda-2.2.0-25.apk");
                caps.setCapability("autoGrantPermissions", true);

                // Aumenta el timeout para que la app tenga más tiempo para iniciar
                caps.setCapability("appWaitDuration", 30000);

                driver = new AndroidDriver<>(
                        new URL("http://127.0.0.1:4723/wd/hub"),
                        caps
                );

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return driver;
    }

    // Método para cerrar el driver
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}