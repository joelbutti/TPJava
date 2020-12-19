
package Logica;

import Modelos.Archivador;
import Modelos.Usuario;
import Modelos.Usuarios;
import java.util.Scanner;


public class Main {

    
    public static void main(String[] args) {
        
        Funcionalidades func = new Funcionalidades();
        func.crearArchivo();
        
        func.cargarArchivo();
        func.menuInicial();
    }
    
}
