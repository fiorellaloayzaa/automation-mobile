package saucelabs.step;

import saucelabs.view.HomeView;
import saucelabs.view.CartView;

public class CartStep {

    HomeView home = new HomeView();
    CartView cart = new CartView();

    public void validateAppLoaded() {
        home.waitForHome();
    }

    public void verifyProducts() {
        home.verifyProductsVisible();
    }

    public void addProduct(String product, int units) {
        home.selectProduct(product);
        cart.addUnits(units);
    }

    public void validateCart() {
        cart.verifyCartIsUpdated();
        cart.goBackToHome();
    }
}