/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion;

import modelo.Producto;
import negocio.ProductoServicio;

/**
 *
 * @author sebas
 */
public class ProductoMain {
    public static void main(String[] args) {
        ProductoServicio servicio = new ProductoServicio();
        Producto prod1 = new Producto("equipo", 10.0, "ASDJ123");
        servicio.agregarNuevoProducto(prod1);
    }
}
