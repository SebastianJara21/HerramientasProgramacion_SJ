/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import datos.PersonaDAO;
import datos.ProductoDAO;

/**
 *
 * @author sebas
 */
public class FacturaServicio {

    private final PersonaDAO personaDAO;
    private final ProductoDAO productoDAO;

    public FacturaServicio() {
        this.personaDAO = new PersonaDAO();
        this.productoDAO = new ProductoDAO();
    }
}
