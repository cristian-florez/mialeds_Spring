package com.mialeds.models;

//importar las clases de jakarta persistence para mapear la clase a la base de datos
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private int idProducto;

    @Column(name = "nombre", nullable = false, length = 250)
    private String nombre;

    @Column(name = "presentacion", nullable = false, length = 250)
    private String presentacion;

    @Column(name = "cantidad_existente", nullable = false)
    private int cantidadExistente = 0;

    @Column(name = "precio_compra", nullable = false, precision = 10, scale = 2)
    private int precioCompra;

    @Column(name = "precio_venta", nullable = false, precision = 10, scale = 2)
    private int precioVenta;

    public Producto() {
    }

    public Producto(int idProducto, String nombre, String presentacion, int cantidadExistente, int precioCompra, int precioVenta) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.presentacion = presentacion;
        this.cantidadExistente = cantidadExistente;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
    }
     //constructor para crear un producto sin id
    public Producto(String nombre, String presentacion, int cantidadExistente, int precioCompra, int precioVenta) {
        this.nombre = nombre;
        this.presentacion = presentacion;
        this.cantidadExistente = cantidadExistente;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public int getCantidadExistente() {
        return cantidadExistente;
    }

    public void setCantidadExistente(int cantidadExistente) {
        this.cantidadExistente = cantidadExistente;
    }

    public int getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(int precioCompra) {
        this.precioCompra = precioCompra;
    }

    public int getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(int precioVenta) {
        this.precioVenta = precioVenta;
    }

}
