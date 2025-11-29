Feature: Carrito de compras

  Scenario Outline: Validar carrito con múltiples productos
    Given estoy en la aplicación de SauceLabs
    And valido que carguen correctamente los productos en la galeria
    When agrego <UNIDADES> del siguiente producto "<PRODUCTO>"
    Then valido el carrito de compra actualice correctamente

    Examples:
      | PRODUCTO                  | UNIDADES |
      | Sauce Labs Backpack       | 1        |
      | Sauce Labs Backpack (green) | 1        |
      | Sauce Labs Backpack (orange)     | 1        |
