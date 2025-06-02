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

    public int AgregarNuevoProducto(Producto nuevoProducto) {
        return this.productoDAO.RegistrarProducto(nuevoProducto);
    }

    public List<Producto> ListarProductos() {
        return this.productoDAO.ListarProductosRegistrados();
    }

    public boolean EliminarProductoPorId(int numId) {
        return this.productoDAO.EliminarProducto(numId);
    }

    public boolean ActualizarProducto(int id, Producto producto) {
        return this.productoDAO.ActualizarProducto(id, producto);
    }

    public boolean actualizarStock(String codigo, int cantidadVendida) {
        Producto producto = productoDAO.BuscarProductoPorCodigo(codigo);
        if (producto != null && producto.getStock() >= cantidadVendida) {
            int nuevoStock = producto.getStock() - cantidadVendida;
            return productoDAO.actualizarStock(codigo, nuevoStock);
        }
        return false;
    }
}
