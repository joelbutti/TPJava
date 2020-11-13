package modelo.tp.Logica;

import java.io.Serializable;
import java.util.HashMap;

public class Usuarios implements Serializable {

	/**
	 * 
	 */
	
	private HashMap<String, Usuario> diccionarioUsuarios = new HashMap<String, Usuario>();
	
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
	public boolean addUsuario(String username, String password, String tipo) {
		
		if(existeUsuario(username)){
			return false;
		}
		
		
		Usuario usu = new Usuario(username,password,tipo);

		this.diccionarioUsuarios.put(username.toLowerCase(), usu);
		
		return true;
	}
	
	
	public boolean existeUsuario(String username) {
		return this.diccionarioUsuarios.containsKey(username.toLowerCase());
	}
	
	public Usuario getUsuario(String username) {
		return this.diccionarioUsuarios.get(username.toLowerCase());
	}
	
	public void mostrarUsuarios() {
		
		for( String clave : this.diccionarioUsuarios.keySet()   ) {
			System.out.println(clave);
			System.out.println(this.getUsuario(clave));
		}
		
	}
	
	
	
	
}
