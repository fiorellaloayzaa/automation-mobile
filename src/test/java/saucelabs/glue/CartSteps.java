package saucelabs.glue;

import io.cucumber.java.es.*;
import saucelabs.step.CartStep;

public class CartSteps {

    CartStep cart = new CartStep();

    @Dado("estoy en la aplicación de SauceLabs")
    public void estoy_en_la_aplicacion_de_saucelabs() {
        cart.validateAppLoaded();
    }

    @Y("valido que carguen correctamente los productos en la galeria")
    public void valido_productos_en_galeria() {
        cart.verifyProducts();
    }

    @Cuando("agrego {int} del siguiente producto {string}")
    public void agrego_producto(int unidades, String producto) {
        cart.addProduct(producto, unidades);
    }

    @Entonces("valido el carrito de compra actualice correctamente")
    public void valido_carrito() {
        cart.validateCart();
    }
}
