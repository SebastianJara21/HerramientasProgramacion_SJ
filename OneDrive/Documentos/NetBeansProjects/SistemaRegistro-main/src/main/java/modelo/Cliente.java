/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import javax.persistence.*;
import java.time.LocalDate;

/**
 *
 * @author sebas
 */
@Entity
@Table(name = "cliente")
public class Cliente extends Persona {

    private String direccion;

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, orphanRemoval = true)
    private Fidelidad fidelidad;

    public Cliente() {

    }

    public Cliente(String nombre, String apellido, String numIdentificacion,
            String correo, LocalDate fechaNacimiento, String direccion) {
        super(nombre, apellido, numIdentificacion, correo, fechaNacimiento);
        this.direccion = direccion;
    }

    public Cliente(String nombre, String apellido, String numIdentificacion,
            String correo, LocalDate fechaNacimiento, String direccion,
            Fidelidad fidelidad) {
        super(nombre, apellido, numIdentificacion, correo, fechaNacimiento);
        this.direccion = direccion;
        this.fidelidad = fidelidad;
    }

    public Cliente(int id, String nombre, String apellido, String numIdentificacion,
            String correo, LocalDate fechaNacimiento, int edad, String direccion,
            Fidelidad fidelidad) {
        super(id, nombre, apellido, numIdentificacion, correo, fechaNacimiento, edad);
        this.direccion = direccion;
        this.fidelidad = fidelidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Fidelidad getFidelidad() {
        return fidelidad;
    }

    public void setFidelidad(Fidelidad fidelidad) {
        this.fidelidad = fidelidad;
    }
}
