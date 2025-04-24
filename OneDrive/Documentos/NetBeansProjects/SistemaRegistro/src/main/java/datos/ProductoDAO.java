/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import modelo.Producto;
import util.PersistenceUtil;

/**
 *
 * @author sebas
 */
public class ProductoDAO {

    public ProductoDAO() {

    }

    public void AgregarProducto(Producto productoAgregar) {

        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();

        try {
            // se inicia la trancision
            em.getTransaction().begin();

            em.persist(productoAgregar);

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
        public void ActualizarProducto(Producto productoActualizar) {
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            // merge por si ya existe, actualiza los datos
            em.merge(productoActualizar);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            System.err.println("Error de sesión de trabajo (Actualizar): " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    public void EliminarProducto(int idProducto) {
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();

            Producto p = em.find(Producto.class, idProducto);
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

    public int verificarAgregarProducto(Producto productoAgregar) {
        int result = 0;
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();

        try {

            Producto productoExiste = em.createQuery(
                    "SELECT p FROM producto p WHERE p.codigo = :codigo", Producto.class
            ).setParameter("codigo", productoAgregar.getCodigo()).getSingleResult();

            if (productoExiste != null) {

                System.out.println("YA EXISTE EL PRODUCTO");
                em.close();
                return result;
            }

        } catch (NoResultException ex) {
            // se inicia la trancision
            em.getTransaction().begin();

            // se inserta el producto
            em.persist(productoAgregar);

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

    public List<Producto> ListarProducto() {
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();

        List<Producto> lista = new ArrayList<>();
        try {
            lista = em.createQuery("SELECT p FROM Producto p", Producto.class).getResultList();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            em.close();
        }
        return lista;
    }
}
