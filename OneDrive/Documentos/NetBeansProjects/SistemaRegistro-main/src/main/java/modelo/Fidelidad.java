/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDate;
import javax.persistence.*;

/**
 *
 * @author sebas
 */
@Entity
@Table(name = "fidelidad")
public class Fidelidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "cliente_id", nullable = false, unique = true)
    private Cliente cliente;

    private int puntosAcumulados;

    private int puntosCanjeados;

    private LocalDate fechaAfiliacion;

    public Fidelidad() {

    }

    public Fidelidad(Cliente cliente, int puntosAcumulados, int puntosCanjeados,
            LocalDate fechaAfiliacion) {
        this.cliente = cliente;
        this.puntosAcumulados = puntosAcumulados;
        this.puntosCanjeados = puntosCanjeados;
        this.fechaAfiliacion = fechaAfiliacion;
    }

    public Fidelidad(int id, Cliente cliente, int puntosAcumulados, int puntosCanjeados,
            LocalDate fechaAfiliacion) {
        this.id = id;
        this.cliente = cliente;
        this.puntosAcumulados = puntosAcumulados;
        this.puntosCanjeados = puntosCanjeados;
        this.fechaAfiliacion = fechaAfiliacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getPuntosAcumulados() {
        return puntosAcumulados;
    }

    public void setPuntosAcumulados(int puntosAcumulados) {
        this.puntosAcumulados = puntosAcumulados;
    }

    public int getPuntosCanjeados() {
        return puntosCanjeados;
    }

    public void setPuntosCanjeados(int puntosCanjeados) {
        this.puntosCanjeados = puntosCanjeados;
    }

    public LocalDate getFechaAfiliacion() {
        return fechaAfiliacion;
    }

    public void setFechaAfiliacion(LocalDate fechaAfiliacion) {
        this.fechaAfiliacion = fechaAfiliacion;
    }
}
