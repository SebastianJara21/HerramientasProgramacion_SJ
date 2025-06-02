/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import modelo.Cliente;
import modelo.Persona;
import util.PersistenceUtil;

/**
 *
 * @author sebas
 */
public class PersonaDAO {

    public PersonaDAO() {

    }

    public void AgregarPersona(Persona personaAgregar) {
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();

            em.persist(personaAgregar);

            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            System.err.println("Error de sesion de trabajo: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    public int VerificarAgregarPersona(Persona personaAgregar) {
        int result = 0;

        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            Persona personaExiste = em.createQuery(
                    "SELECT p FROM Persona p WHERE p.numIdentificacion = :numId", Persona.class)
                    .setParameter("numId", personaAgregar.getNumIdentificacion())
                    .getSingleResult();

            if (personaExiste != null) {
                System.out.println("YA EXISTE LA PERSONA");
                return result;
            }

        } catch (NoResultException ex) {
            em.getTransaction().begin();
            em.persist(personaAgregar);
            em.getTransaction().commit();
            result = 1;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            System.err.println("Error de sesion de trabajo: " + ex.getMessage());
            result = 2;
        } finally {
            em.close();
        }
        return result;
    }

    public int RegistrarPersona(Cliente personaAgregar) {
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            Long count = em.createQuery(
                    "SELECT COUNT(p) FROM Persona p WHERE p.numIdentificacion = :numId", Long.class)
                    .setParameter("numId", personaAgregar.getNumIdentificacion())
                    .getSingleResult();

            if (count > 0) {
                return 0;
            }

            em.getTransaction().begin();
            em.persist(personaAgregar);
            em.getTransaction().commit();
            return 1;
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return 2;

        } finally {
            em.close();
        }
    }

    public boolean ActualizarPersona(int id, Persona personaActualizar) {
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            Persona existente = em.find(Persona.class, id);
            if (existente == null) {
                return false;
            }

            em.merge(personaActualizar);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }

    public boolean EliminarPersona(int numId) {
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            Persona persona = em.find(Persona.class, numId);
            if (persona == null) {
                return false;
            }
            em.getTransaction().begin();
            em.remove(persona);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }

    public List<Persona> ListarPersonasRegistradas() {
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Persona p", Persona.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Persona BuscarPersonaPorCedula(String cedula) {
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Persona p WHERE p.numIdentificacion = :cedula", Persona.class)
                    .setParameter("cedula", cedula)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }
}
