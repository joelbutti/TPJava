
package Modelos;

import java.io.Serializable;


public class Articulo implements Serializable {

    private static final long serialVersionUID = 8934807414343767528L;
    
    private String cod_articulo;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;

    public Articulo(String cod_articulo, String nombre, String descripcion, double precio, int stock) {
        this.cod_articulo = cod_articulo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }
    
    public void sumarStock(int stock){
        this.stock += stock;
    }
    
    public void retirarStock(int stock){
        this.stock -= stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public String getCod_articulo() {
        return cod_articulo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    
}
