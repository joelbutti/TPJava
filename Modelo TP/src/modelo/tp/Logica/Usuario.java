
package modelo.tp.Logica;

import java.io.Serializable;

public class Usuario implements Serializable{

	/**
	 * 
	 */
	
	
	private String username;
	private String password;
        private String tipo;

	public Usuario(String username, String password, String tipo) {
		this.username = username;
		this.password = password;
                this.tipo = tipo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
        

	public String getUsername() {
		return username;
	}
        
        public String getTipo(){
            return tipo;
        }
	

	
	
	/**
	 * Valida si el string ingresado es igual a la contrase�a usuario.
	 * @param password el string a validar.
	 * @return true si coincide, false si no coincide.
	 * 
	 * **/
	public boolean validarPassword(String password) {
		return this.getPassword().equals(password);
	}

}
