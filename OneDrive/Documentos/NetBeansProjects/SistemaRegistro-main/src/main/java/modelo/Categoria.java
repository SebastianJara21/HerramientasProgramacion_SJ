/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author sebas
 */
public enum Categoria {
    PASTEL,
    GALLETA,
    MOUSSE,
    BEBIDA,
    HELADO;

    public static Categoria fromString(String text) {
        for (Categoria cat : Categoria.values()) {
            if (cat.name().equalsIgnoreCase(text)) {
                return cat;
            }
        }
        throw new IllegalArgumentException("No se encontró la categoría: " + text);
    }
}
