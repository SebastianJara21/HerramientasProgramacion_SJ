/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import datos.ProductoDAO;
import java.util.List;
import modelo.Producto;

/**
 *
 * @author sebas
 */
public class ProductoServicio {

    private final ProductoDAO productoDAO;

    public ProductoServicio() {
        this.productoDAO = new ProductoDAO();
    }

    public void agregarNuevoProducto(Producto producto) {
        productoDAO.AgregarProducto(producto);
    }

    public void ActualizarProducto(Producto producto) {
        productoDAO.ActualizarProducto(producto);
    }

    public void EliminarProductoPorId(int id) {
        productoDAO.EliminarProducto(id);
    }

    public List<Producto> ListarProductos() {
        return productoDAO.ListarProducto();

    }
}
