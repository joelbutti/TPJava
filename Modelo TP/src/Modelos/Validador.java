
package Modelos;

import java.util.Scanner;


public class Validador {
    
    private Scanner escaner;
    
    
    public Validador(Scanner escaner){
        
        super();
        this.escaner=escaner;
    }
    
    public double validarDouble(String pregunta, String error){
        
        boolean isCorrecto = false;
        System.out.println(pregunta);
        
        while(!isCorrecto){
            
            String sIngresado = escaner.next();
            
            try{
                double retorno = Double.parseDouble(sIngresado);
                return retorno;
            }catch(Exception e){
                System.out.println(error);
            }
        }
        return 0;
    }
    
    public int validarInt(String pregunta, String error){
        
        boolean isCorrecto = false;
        System.out.println(pregunta);
        
        while(!isCorrecto){
            
            String sIngresado = escaner.next();
            
            try{
                int retorno = Integer.parseInt(sIngresado);
                return retorno;
            }catch(Exception e){
                System.out.println(error);
            }
        }
        return 0;
    }
}
