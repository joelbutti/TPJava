/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.tp.Persistencia;

import java.io.File; 
import java.io.FileInputStream; 
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.io.ObjectInputStream; 
import java.io.ObjectOutputStream; 
public class Archivador {
 /** * Crea un archivo con el nombre dado. Retorna true si se creo bien y falso si 
* ya existía. Puede mostrar un error y no funcionar correctamente si el archivo 
* en cuestion esta en uso. 
* 
*@param ruta es la ruta donde se desea crear el archivo. 
* @return true si el archivo se creo correctamente. false si ya existia. **/ 
public boolean createFile(String ruta) { 
    File archivo = new File(ruta); 
    try {  
        return archivo.createNewFile(); 
    } 
    catch (Exception e) { e.printStackTrace(); 
return false; 
    } 
} 
/** 
* Carga un objeto desde un archivo. Puede mostrar un error y no funcionar 
* correctamente si el archivo en cuestion esta en uso. 
* 
* @param ruta es la ruta donde se desea crear el archivo. 
* @return un objeto (de tipo Object) que este guardado en el archivo ruta. Hay 
* que castearlo para guardarlo en una variable del tipo 
* correspondiente. **/ 
public Object load(String ruta) { 
    try { 
        File archivo = new File(ruta); 
        ObjectInputStream input = new ObjectInputStream(new FileInputStream(archivo)); 
        Object obj = input.readObject(); 
        input.close(); 
        return obj; 
    } 
    catch (Exception e) { 
        e.printStackTrace(); 
        return ""; 
    } 
} 
/** 
* Guarda un objeto en un archivo. Puede mostrar un error y no funcionar 
* correctamente si el archivo en cuestion esta en uso. 
* 
* @param objeto es el objeto a guardar en el archivo. 
* @param ruta es la ruta del archivo donde se desea guardar. 
* **/ 
public void save(Object objeto, String ruta) { 
    File archivo = new File(ruta); 
    try { 
        ObjectOutputStream ouput = new ObjectOutputStream(new FileOutputStream(archivo, false)); 
        ouput.writeObject(objeto); 
        ouput.close(); 
    } 
    catch (Exception e) { 
        e.printStackTrace(); 
    } 
} 
/** 
* Verifica si existe un en la ruta seleccionada. 
* @param la ruta del archivo. 
* @return true si existe el archivo, false si no existe. 
* 
**/ 
public boolean existe(String ruta) { 
    File archivo = new File(ruta); 
    return archivo.exists(); 
    } 
}
