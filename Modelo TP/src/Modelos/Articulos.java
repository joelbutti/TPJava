
package Modelos;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class Articulos implements Serializable{

    private static final long serialVersionUID = -8969294704557874985L;
    
    private HashMap<String, Articulo> diccionarioArticulos = new HashMap<String, Articulo>();
    private HashMap<String, Pedido> diccionarioPedidos = new HashMap<String, Pedido>();

    /**
     * Comprueba si el articulo tiene mas stock de lo que se planea restarle para no quedar en negativo
     * @param cod_articulo key del articulo que se comprobara el stock
     * @param cantidad cantidad que se le piensa restar en caso de poseer mas stock que esta
     * @return true si posee mas stock de lo que se planea restar
     */
    public boolean garantizarStock(String cod_articulo,int cantidad){
        
        Articulo art = null;
        art = this.diccionarioArticulos.get(cod_articulo.toLowerCase());
        Pedido ped = this.diccionarioPedidos.get(cod_articulo.toLowerCase());
        
        if(this.existePedido(cod_articulo.toLowerCase())){
            int cant= cantidad + ped.getCantidad();
                if(art.getStock()-cant>=0){
                    return true;
                
                }
                else{
                    return false;
                }
        }
        else{
            if(art.getStock()- cantidad >=0){
            return true;
            }
            else{
                return false;
            }
        }
        
    }
    
    /**
     * Agrega un articulo al hashmap interno
     * 
     * @param cod_articulo codigo del articulo
     * @param nombre nombre del articulo
     * @param descripcion descripcion del articulo
     * @param precio precio del articulo
     * @param stock stock del articulo
     * @return return false si el articulo existe
     */
    public boolean addArticulo(String cod_articulo, String nombre, String descripcion, double precio, int stock) {
		
	if(existeArticulo(cod_articulo)){
                this.agregarStock(cod_articulo.toLowerCase(), stock);
		return false;
	}
		
		
	Articulo art = new Articulo(cod_articulo.toLowerCase(), nombre, descripcion, precio, stock);

	this.diccionarioArticulos.put(cod_articulo.toLowerCase(), art);
		
	return true;
    }
	
    /**
     * Borra un pedido del hashmap 
     * 
     * @param cod_pedido key del pedido que se borrara
     */
    public void borrarPedido(){
        
        this.diccionarioPedidos.clear();
    }
    
    /**
     * Finalizar compra restando el stock de los articulos y borrando el pedido ya realizado
     */
    public void finalizarCompra(){
        
        Pedido ped = null;
        Articulo art = null;
        Usuarios usu = null;
        
        for(String clave : this.diccionarioPedidos.keySet()){
            
            ped=this.diccionarioPedidos.get(clave);
            art=this.diccionarioArticulos.get(clave);
            //art.retirarStock(ped.getCantidad());
            art.setStock(art.getStock()-ped.getCantidad());
            
            this.diccionarioArticulos.replace(clave,art);
        }
        
        this.diccionarioPedidos.clear();
        
    }
    
    /**
     * Agregar pedido al hashmap interno
     * 
     * @param cod_articulo codigo de articulo
     * @param cantidad  cantidad
     */
    public void addPedido(String cod_articulo,int cantidad){
        
        Articulo art = null;
        Pedido ped = null;
        
        art = this.diccionarioArticulos.get(cod_articulo.toLowerCase());
        if(existePedido(cod_articulo.toLowerCase())){
            
            ped = this.diccionarioPedidos.get(cod_articulo.toLowerCase());
            ped.setCantidad(cantidad + ped.getCantidad());
                
            this.diccionarioPedidos.put(cod_articulo.toLowerCase(), ped);
            
            
        }
        else{
            
            ped = new Pedido(cantidad, cod_articulo.toLowerCase(), art.getNombre(), art.getDescripcion(), art.getPrecio(), art.getStock());
            this.diccionarioPedidos.put(cod_articulo.toLowerCase(), ped);
        }
              
        
    }
	
    /**
     * Calcula el monto total del pedido a realizar
     * 
     * @return un double que es el total del pedido
     */
    public double calcularMonto(){
        
        Pedido ped = null;
        double total = 0;
        
        for(String clave : this.diccionarioPedidos.keySet()){
            ped=this.diccionarioPedidos.get(clave);
            total += (ped.getPrecio()*ped.getCantidad());
        }
        
        return total;
    }
    
    /**
     * Verifica si existe el articulo en el hashmap de articulos
     * 
     * @param cod_articulo key del articulo que se desea verificar
     * @return true si existe en el hashmap
     */
    public boolean existeArticulo(String cod_articulo) {
	return this.diccionarioArticulos.containsKey(cod_articulo.toLowerCase());
    }  
    
    /**
     * Agregar stock a un articulo ya existente
     * @param cod_articulo key del articulo al que se le agregara stock
     * @param stock stock a agregar al articulo existente
     */
    public void agregarStock(String cod_articulo, int stock){
        
        Articulo art = null;
        art=this.diccionarioArticulos.get(cod_articulo.toLowerCase());
        art.sumarStock(stock);
        this.diccionarioArticulos.replace(cod_articulo.toLowerCase(), art);
    }
    
    /**
     * Eliminar articulo del hashmap
     * 
     * @param cod_articulo key del articulo que se desea eliminar
     */
    public void eliminarArticulos(String cod_articulo){
        
        this.diccionarioArticulos.remove(cod_articulo.toLowerCase());
    }
    
    /**
     * Editar articulo del hashmap
     * 
     * @param cod_articulo key del articulo que se desea editar
     * @param opcion opcion del atributo que se desea editar
     * @param nuevoValor nuevo valor que se le dara al atributo
     */
    public void editarArticulos(String cod_articulo, String opcion, String nuevoValor){
        
        if(opcion.toLowerCase().equals("1")){
            
            Articulo art = null;
            art=this.diccionarioArticulos.get(cod_articulo.toLowerCase());
            art.setNombre(nuevoValor);
            this.diccionarioArticulos.replace(cod_articulo.toLowerCase(), art);
        }
        else if(opcion.toLowerCase().equals("2")){
            
            Articulo art = null;
            art=this.diccionarioArticulos.get(cod_articulo.toLowerCase());
            art.setDescripcion(nuevoValor);
            this.diccionarioArticulos.replace(cod_articulo.toLowerCase(), art);
        }
        else if(opcion.toLowerCase().equals("3")){
            
            double nuevo = Double.parseDouble(nuevoValor);
            Articulo art = null;
            art=this.diccionarioArticulos.get(cod_articulo.toLowerCase());
            art.setPrecio(nuevo);
            this.diccionarioArticulos.replace(cod_articulo.toLowerCase(), art);
        }
        else if(opcion.toLowerCase().equals("4")){
            
            int nuevo = Integer.parseInt(nuevoValor);
            Articulo art = null;
            art=this.diccionarioArticulos.get(cod_articulo.toLowerCase());
            art.setStock(nuevo);
            this.diccionarioArticulos.replace(cod_articulo.toLowerCase(), art);
            
        }
    }
    
    /**
     * Mostrar los articulos en el hashmap
     */
    public void mostrarArticulos() {
	
        Articulo art = null;
        
	for( String clave : this.diccionarioArticulos.keySet()   ) {
            art=this.diccionarioArticulos.get(clave);
            System.out.println("Codigo de Articulo = "+ art.getCod_articulo());
            System.out.println("Nombre de Articulo = "+ art.getNombre());
            System.out.println("Descripcion de Articulo = "+ art.getDescripcion());
            System.out.println("Precio Unitario = "+ art.getPrecio());
            System.out.println("Unidades disponibles del Articulo = "+ art.getStock());
            System.out.println("-------------------------------------------------------");
	}
		
    }
    
    /**
     * Mostrar los pedidos en el hashmap
     */
    public void mostrarPedidos(){
        
        Pedido ped = null;
        
        System.out.println("------------------------------------------------");
        System.out.println("Su carrito: ");
        for(String clave : this.diccionarioPedidos.keySet()){
            ped=this.diccionarioPedidos.get(clave);
            
            System.out.println("Codigo: " + ped.getCod_articulo());
            System.out.println("Nombre de Articulo: " + ped.getNombre());
            System.out.println("Cantidad a comprar: " +ped.getCantidad());
            System.out.println("------------------------------------------------");
        }
        System.out.println("Precio total: " + this.calcularMonto());
    }
    
    /**
     * Verifica si existe pedido en el hashmap
     * 
     * @param cod_articulo key del pedido que se buscara en el hashmap
     * @return true si el diccionario contiene la key 
     */
    private boolean existePedido(String cod_articulo) {
        return this.diccionarioPedidos.containsKey(cod_articulo.toLowerCase());
    }
        
}
