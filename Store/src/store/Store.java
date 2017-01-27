/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package store;

/**
 *
 * @author Riverto
 */
public class Store {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Instancia de la clase Articulo
        Article telefono = new Article(120899,11500.00,"Iphone 6s 16Gb");
        //Compra de 50 telefonos
        telefono.purchase(50, 9890.00);
        //Muestra la existencia en almacen
        System.out.println("La existencia en almacen del articulo es " + telefono.checkExistence());
        //Se realiza una venta
        System.out.println("Se venden 15 telefonos");
        telefono.sale(15);
        System.out.println("La nueva existencia despues de la venta es "+telefono.checkExistence());
        //Se realiza actualizacion de precios
        System.out.println("Hay una promo, y el nuevo precio es $10,000.00");
        telefono.updatePrice(10000.00);
    }
    
}
