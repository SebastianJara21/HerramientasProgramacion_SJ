/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import modelo.DetalleFactura;
import modelo.Factura;
import util.PersistenceUtil;

/**
 *
 * @author Sebas
 */
public class FacturaDAO {

    public int RegistrarFactura(Factura facturaAgregar) {
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(facturaAgregar);
            em.getTransaction().commit();
            return 0;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            System.err.println("Error de sesión de trabajo: " + ex.getMessage());
            return 1;
        } finally {
            em.close();
        }
    }

    public Factura obtenerFacturaCompletaPorId(int idFactura) {
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.createQuery("""
                    SELECT f FROM Factura f
                    JOIN FETCH f.persona
                    LEFT JOIN FETCH f.detalles d
                    LEFT JOIN FETCH d.producto
                    WHERE f.id = :idFactura
                    """, Factura.class)
                    .setParameter("idFactura", idFactura)
                    .getSingleResult();
//            return em.createQuery("SELECT p FROM Factura p WHERE p.id = :idS", Factura.class)
//                    .setParameter("idS", idFactura)
//                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
}
