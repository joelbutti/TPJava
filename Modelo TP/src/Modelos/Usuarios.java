package Modelos;

import java.io.Serializable;
import java.util.HashMap;

public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1516057873287607358L;


	
        //private static final long serialVersionUID = 142727278242L;

	
	private HashMap<String, Usuario> diccionarioUsuarios = new HashMap<String, Usuario>();
	
        public boolean esEmpleado(String username){
            
            Usuario usu = this.diccionarioUsuarios.get(username.toLowerCase());
            
            if(usu.getTipo().equals("Empleado")){
                return true;
            }
            return false;
        }
        
	/**
	 * Agrega un usuario al hashmap interno del objeto.
	 * 
	 * @param username es el nombre de usuario del usuario.
	 * @param password es la contrase�a del usuario.
	 * @param nickname es el nombre con el que se va a mostrar al usuario en el sistema.
	 * 
	 * @return true si el usuario se cre� correctamente, false si el usuario ya
	 * existe.
	 * 
	 * **/
	public boolean addUsuario(String username, String password, String tipo, double saldo) {
		
		if(existeUsuario(username.toLowerCase())){
			return false;
		}
		
		
		Usuario usu = new Usuario(username,password,tipo, saldo);

		this.diccionarioUsuarios.put(username.toLowerCase(), usu);
		
		return true;
	}
	
	/**
         *Verifica si existe el usuario en el diccionario
         *@param username es la key que buscara en el diccionario
         * 
         * @return return true si el usuario existe en el hashmap
         **/
	public boolean existeUsuario(String username) {
		return this.diccionarioUsuarios.containsKey(username.toLowerCase());
	}
	
	public Usuario getUsuario(String username) {
		return this.diccionarioUsuarios.get(username.toLowerCase());
	}
	
        /**
         *Recorre el hashmap y muestra todos los usuarios en el
         **/
	public void mostrarUsuarios() {
		
		for( String clave : this.diccionarioUsuarios.keySet()   ) {
			System.out.println(clave);
			System.out.println(this.getUsuario(clave));
		}
		
	}
        
        /**
         * Verifica si el usuario tiene saldo suficiente para pagar el total de la compra
         * @param username la key del usuario que se buscara en el hashmap
         * @param total el saldo total que se verificara que haya un minimo para pagarlo
         * @return true si tiene mas saldo que el total a gastar
         */
        
        public boolean dineroSuficiente(String username, double total){
            
            Usuario usu = this.diccionarioUsuarios.get(username.toLowerCase());
            
            if(usu.getSaldo()-total >= 0){
                
                return true;
            }
            else{
                return false;
            }
        }
        
        /**
         * Agrega dinero al saldo del usuario
         * @param username la key del usuario al que se le añadira saldo
         * @param saldo la cantidad de saldo que se le añadira 
         */
        public void agregarDinero(String username, double saldo){
            
            Usuario usu = this.diccionarioUsuarios.get(username.toLowerCase());
            usu.sumarSaldo(saldo);
            this.diccionarioUsuarios.replace(username.toLowerCase(), usu);
            
        }
        
        /**
         * Retira dinero al saldo del usuario
         * @param username la key del usuario al que se le retirara el saldo
         * @param saldo la cantidad de saldo que se le retirara
         */
        public void retirarDinero(String username, double saldo){
            
            Usuario usu = this.diccionarioUsuarios.get(username.toLowerCase());
            usu.retirarSaldo(saldo);
            this.diccionarioUsuarios.replace(username.toLowerCase(), usu);
        }
        
        /**
         * Mostrar el saldo del usuario
         * @param username key del usuario al que se le mostrara el saldo
         * @return  atributo saldo del usuario
         */
        public double mostrarDinero(String username){
            
            Usuario usu = this.diccionarioUsuarios.get(username.toLowerCase());
            
            return usu.getSaldo();
        }
        
        /**
         * Transferir saldo de un usuario a otro
         * @param username key del usuario al que se le retirara el saldo para transferir
         * @param destino key del usuario que recibira el saldo a transferir
         * @param saldo cantidad de saldo que se transferira
         */
        public void transferirDinero(String username, String destino, double saldo){
            
            Usuario usu1 = this.diccionarioUsuarios.get(username.toLowerCase());
            Usuario usu2 = this.diccionarioUsuarios.get(destino);
            
            usu1.retirarSaldo(saldo);
            usu2.sumarSaldo(saldo);
            
            this.diccionarioUsuarios.replace(username.toLowerCase(), usu1);
            this.diccionarioUsuarios.replace(destino, usu2);
        }
	
        /**
         * 
         * @param username
         * @param saldo
         * @return 
         */
	
        
	
	
}
