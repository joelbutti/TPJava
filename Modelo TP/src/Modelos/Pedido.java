
package Modelos;

import java.io.Serializable;


public class Pedido extends Articulo implements Serializable{

    private static final long serialVersionUID = -3836751470843877874L;
    
    private int cantidad;

    public Pedido(int cantidad, String cod_articulo, String nombre, String descripcion, double precio, int stock) {
        super(cod_articulo, nombre, descripcion, precio, stock);
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
}
