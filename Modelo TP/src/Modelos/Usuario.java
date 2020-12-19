
package Modelos;

import java.io.Serializable;


public class Usuario implements Serializable{

    private static final long serialVersionUID = -6520221720480503389L;

    private String username;
    private String password;
    private String tipo;
    private double saldo;


    
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public Usuario(String username, String password, String tipo, double saldo) {
	this.username = username.toLowerCase();
	this.password = password;
        this.tipo = tipo;
        this.saldo = saldo;
    }
    
    public void sumarSaldo(double saldo){
        this.saldo += saldo;
    }
    
    public void retirarSaldo(double saldo){
        this.saldo -= saldo;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }
        

    public String getUsername() {
	return username.toLowerCase();
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
