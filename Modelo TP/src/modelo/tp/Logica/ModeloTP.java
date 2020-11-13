
package modelo.tp.Logica;
import modelo.tp.IGU.JFrameLogin;
import javax.swing.*;
import modelo.tp.Persistencia.Archivador;



public class ModeloTP {

    
    public static void main(String[] args){
        Usuarios usu = new Usuarios();
        Archivador archi = new Archivador();
        String ruta = "usuarios.dat";
        
        if(archi.existe(ruta)){
            archi.load(ruta);
        }
        
        JFrameLogin login = new JFrameLogin();
        login.setVisible(true);
        login.setLocationRelativeTo(null);
        
    }
    
    
    
}
