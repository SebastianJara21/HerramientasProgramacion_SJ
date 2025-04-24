/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import modelo.Persona;
import datos.PersonaDAO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author sebas
 */
public class PersonaServicio {

    private final PersonaDAO personaDAO;

    public PersonaServicio() {
        this.personaDAO = new PersonaDAO();
    }

    public void AgregarNuevaPersona(Persona persona) {
        personaDAO.AgregarPersona(persona);
    }

    public static int CalcularEdad(Date fechaNacimiento) {
        return PersonaDAO.CalcularEdad(fechaNacimiento);
    }

    public void ActualizarPersona(Persona persona) {
        personaDAO.ActualizarPersona(persona);
    }

    public void EliminarPersonaPorId(int id) {
        personaDAO.EliminarPersona(id);
    }

    public List<Persona> ListarPersonas() {
        return personaDAO.ListarPersona();

    }

    public List<String> buscarPorNombreParcial(String texto) {
        // (Opcional) Validación mínima antes de la consulta
        if (texto == null || texto.trim().length() < 2) {
            return Collections.emptyList();
        }
        return personaDAO.buscarNombresPorParcial(texto.trim());
    }

//    public void AgregarNuevaPersona(Persona Persona) {
//        try {
//            personaDAO.AgregarPersona(Persona);
//        } catch (SQLException ex) {
//            System.out.println("Error en capa negocio, no se puede agregar persona." + ex.getMessage());
//        }
//    }
//
//    public List<Persona> ListarPersonas() {
//        return personaDAO.ListarPersonas();
//
//    }
}
