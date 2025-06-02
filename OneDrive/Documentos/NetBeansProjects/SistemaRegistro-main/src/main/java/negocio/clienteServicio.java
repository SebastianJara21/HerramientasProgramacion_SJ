/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import datos.PersonaDAO;
import javax.persistence.EntityManager;
import modelo.Cliente;
import modelo.Fidelidad;
import modelo.Persona;
import util.PersistenceUtil;

/**
 *
 * @author sebas
 */
public class clienteServicio {

    private final PersonaDAO personaDAO;

    public clienteServicio() {
        this.personaDAO = new PersonaDAO();
    }

    public Cliente buscarClientePorCedula(String cedula) {
        Persona persona = personaDAO.BuscarPersonaPorCedula(cedula);
        return (persona instanceof Cliente) ? (Cliente) persona : null;
    }

    public boolean acumularPuntos(String cedula, int puntos) {
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();

            Cliente cliente = em.createQuery(
                    "SELECT c FROM Cliente c JOIN FETCH c.fidelidad WHERE c.numIdentificacion = :cedula",
                    Cliente.class
            ).setParameter("cedula", cedula).getSingleResult();

            if (cliente != null && cliente.getFidelidad() != null) {
                cliente.getFidelidad().setPuntosAcumulados(
                        cliente.getFidelidad().getPuntosAcumulados() + puntos
                );
                em.merge(cliente);
                em.getTransaction().commit();
                return true;
            }
            return false;
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }
}
