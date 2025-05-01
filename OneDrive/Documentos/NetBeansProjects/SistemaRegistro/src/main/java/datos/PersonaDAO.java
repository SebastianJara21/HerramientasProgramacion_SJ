/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import util.PersistenceUtil;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import modelo.Persona;

/**
 *
 * @author sebas
 */
public class PersonaDAO {

//    private final ConexionDB conexionDB;
//
    public PersonaDAO() {
//        this.conexionDB = new ConexionDB();
    }

//    public void AgregarPersona(Persona persona) throws SQLException {
//        String sql = "INSERT INTO usuario (nombre, apellido, correo, cedula, fechaNacimiento) VALUES (?, ?, ?, ?, ?)";
//        Connection conn = conexionDB.AbrirConexion();
//        try (PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
//            stmt.setString(1, persona.getNombre());
//            stmt.setString(2, persona.getApellido());
//            stmt.setString(3, persona.getCorreo());
//            stmt.setString(4, persona.getCedula());
//            stmt.setDate(5, new Date(persona.getFechaNacimiento().getTime()));
//
//            int filasAfectadas = stmt.executeUpdate();
//            if (filasAfectadas > 0) {
//                try (ResultSet generateKeys = stmt.getGeneratedKeys()) {
//                    if (generateKeys.next()) {
//                        int idGenerado = generateKeys.getInt(1);
//                        System.out.println("Registro Exitoso con ID: " + idGenerado);
//                    } else {
//                        System.out.println("No se genero ningun ID.");
//                    }
//                }
//            } else {
//                System.out.println("No se puede insertar el registro.");
//            }
//        } catch (SQLException ex) {
//            System.out.println("Error al agregar persona: " + ex.getMessage());
//        } finally {
//            ConexionDB.CerrarConexion(conn);
//        }
//    }
    public void AgregarPersona(Persona personaAgregar) {

        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();

        try {
            // se inicia la trancision
            em.getTransaction().begin();

            // se incerta la persona
            em.persist(personaAgregar);

            // confirmar y guardar los cambios
            em.getTransaction().commit();

        } catch (Exception ex) {

            // revertir todo, no guardar las ultimas acciones
            em.getTransaction().rollback();
            System.err.println("Error de sesion de trabajo: " + ex.getMessage());

        } finally {
            em.close();
        }
    }

    public void ActualizarPersona(Persona personaActualizar) {
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            // merge por si ya existe, actualiza los datos
            em.merge(personaActualizar);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            System.err.println("Error de sesión de trabajo (Modificar): " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    public void EliminarPersona(int idPersona) {
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();

            Persona p = em.find(Persona.class, idPersona);
            if (p != null) {
                em.remove(p);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            System.err.println("Error de sesión de trabajo (Eliminar): " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    public List<Persona> ListarPersona() {
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();

        List<Persona> lista = new ArrayList<>();
        try {
            lista = em.createQuery("SELECT p FROM Persona p", Persona.class).getResultList();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            em.close();
        }
        return lista;
    }

    public static int CalcularEdad(Date fechaNacimiento) {

        Calendar fechaNac = Calendar.getInstance();

        //El metodo setTime es para Date, arreglar para que funcione con LocalDate
        //fechaNac.setTime(fechaNacimiento);
        Calendar hoy = Calendar.getInstance();

        int edad = hoy.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);

        // Si aún no ha llegado el cumpleaños de este año, restamos uno
        if (hoy.get(Calendar.MONTH) < fechaNac.get(Calendar.MONTH)
                || (hoy.get(Calendar.MONTH) == fechaNac.get(Calendar.MONTH)
                && hoy.get(Calendar.DAY_OF_MONTH) < fechaNac.get(Calendar.DAY_OF_MONTH))) {
            edad--;
        }

        return edad;

    }

    // [0] ya existe [1] no existe, registro exitoso [2] error inesperado
    public int verificarAgregarPersona(Persona personaAgregar) {
        int result = 0;
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();

        try {

            Persona personaExiste = em.createQuery(
                    "SELECT p FROM persona p WHERE p.cedula = :cedula", Persona.class
            ).setParameter("cedula", personaAgregar.getCedula()).getSingleResult();

            if (personaExiste != null) {

                System.out.println("YA EXISTE LA PERSONA");
                em.close();
                return result;
            }

        } catch (NoResultException ex) {
            // se inicia la trancision
            em.getTransaction().begin();

            // se incerta la persona
            em.persist(personaAgregar);

            // confirmar y guardar los cambios
            em.getTransaction().commit();
            result = 1;
        } catch (Exception ex) {

            // revertir todo, no guardar las ultimas acciones
            em.getTransaction().rollback();
            System.err.println("Error de sesion de trabajo: " + ex.getMessage());
            result = 2;

        } finally {
            em.close();
        }
        return result;
    }

    public List<String> buscarNombresPorParcial(String texto) {
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            TypedQuery<String> q = em.createQuery(
                    "SELECT CONCAT(p.nombre, ' ', p.apellido) "
                    + "FROM Persona p "
                    + "WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :txt, '%'))",
                    String.class
            );
            q.setParameter("txt", texto);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public int RegistrarPersona(Persona personaAgregar) {
        // Inicia la sesion de trabajo con la base de datos
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            Long count = em.createQuery(
                    "SELECT COUNT(p) FROM Persona p WHERE p.cedula = :numId", Long.class)
                    .setParameter("numId", personaAgregar.getCedula())
                    .getSingleResult();

            // Existe la persona, porque el contador dio un resultado
            if (count > 0) {
                return 0;
            }

            // Se inicia la transicion
            em.getTransaction().begin();
            // Se inserta la persona
            em.persist(personaAgregar);
            // Confirmar y guardar los cambios
            em.getTransaction().commit();
            return 1;
        } catch (Exception ex) {
            // Revertir todo, no guardar nada
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return 2;

        } finally {
            em.close();
        }
    }

    public Persona BuscarPersonaPorCedula(String cedula) {
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Persona p WHERE p.cedula = :cedula", Persona.class)
                    .setParameter("cedula", cedula)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }

//    public List<Persona> ListarPersonas() {
//        Connection conn = conexionDB.AbrirConexion();
//        List <Persona> ListaPersonas = new ArrayList<Persona>();
//        
//        try {
//            String sql = "SELECT * from usuario";
//            var stmt = conn.createStatement();
//            ResultSet result = stmt.executeQuery(sql);
//            while (result.next()) {
//                var ItemPersona = new Persona(result.getInt("id"),result.getString("nombre"),result.getString("correo"));
//
//                ItemPersona.setApellido(result.getString("apellido"));
//                ItemPersona.setCedula(result.getString("cedula"));
//                ItemPersona.setFechaNacimiento(result.getDate("fechaNacimiento"));
//                //agregamos una persona al listado
//                ListaPersonas.add(ItemPersona);
//                
//            }
//
//        } catch (SQLException ex) {
//            System.out.println("Error al listar personas: " + ex.getMessage());
//
//        }
//
//        return ListaPersonas;
//    }
}
