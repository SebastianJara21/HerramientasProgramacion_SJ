/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import modelo.Persona;
import datos.PersonaDAO;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;

/**
 *
 * @author sebas
 */
public class PersonaServicio {

    private final PersonaDAO personaDAO;

    public PersonaServicio() {
        this.personaDAO = new PersonaDAO();
    }

    public int AgregarNuevaPersona(Cliente persona) {
        persona.CalcularEdad();
        if (persona.getEdad() >= 18) {
            String nombrePersona = persona.getNombre().toUpperCase(); 
            persona.setNombre(nombrePersona);

            String apellidoPersona = persona.getApellido().toLowerCase(); 
            persona.setApellido(apellidoPersona);

            return personaDAO.RegistrarPersona(persona);
        } else {
            return 3;
        }
    }

    public List<Persona> ListarPersonas() {
        return personaDAO.ListarPersonasRegistradas();
    }

    public boolean EliminarPersonaPorId(int numId) {
        return personaDAO.EliminarPersona(numId);
    }

    public boolean ActualizarPersona(int id, Persona persona) {
        return personaDAO.ActualizarPersona(id, persona);
    }
}
