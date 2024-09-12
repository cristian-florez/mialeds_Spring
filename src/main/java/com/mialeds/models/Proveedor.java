package com.mialeds.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "proveedor")
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private int idProveedor;

    @Column(name = "nombre", nullable = false, length = 250)
    private String nombre;

    @Column(name = "NIT", nullable = false, length = 15, unique = true)
    private String nit;

    @Column(name = "correo_electronico", nullable = false, length = 250)
    private String correoElectronico;

    @Column(name = "telefono", nullable = false, length = 10)
    private String telefono;

    public Proveedor() {
    }

    public Proveedor(int idProveedor, String nombre, String nit, String correoElectronico, String telefono) {
        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.nit = nit;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
