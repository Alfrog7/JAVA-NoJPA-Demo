package com.demo.productmanagement.model;


import java.math.BigDecimal;

public class Marca {

    private Long id;
    private String nombre;
    private BigDecimal precio;

    // Constructor vacío
    public Marca() {
    }

    // Constructor con parámetros
    public Marca(Long id, String nombre, BigDecimal precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    // Getter y Setter de id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter y Setter de nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter y Setter de precio
    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal  precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {

        return "Marca [id=" + id + ", nombre=" + nombre + ", precio=" + precio + "]";
    }
}