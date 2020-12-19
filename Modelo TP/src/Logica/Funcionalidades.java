
package Logica;

import Modelos.Archivador;
import Modelos.Articulos;
import Modelos.Usuario;
import Modelos.Usuarios;
import Modelos.Validador;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class Funcionalidades {
    
    private static Scanner escaner = new Scanner(System.in);
    private static Usuarios usuarios = new Usuarios();
    private static Articulos articulos = new Articulos();
    private static Archivador archi = new Archivador();
    private String usuarioActivo = null;
    private static String rutaUsuarios = "usuarios.dat";
    private static String rutaArticulos = "articulos.dat";
    private static Validador vali = new Validador(escaner);
    
    public void bancoCliente(){
        
        System.out.println("MENU - BANCO DIGITAL");
        System.out.println("------------------------------------------------");
        
        System.out.println("Saldo actual: " + usuarios.mostrarDinero(this.getUsuarioActivo()));
        System.out.println("¿Que accion desea realizar?");
        
        System.out.println("1. Agregar Dinero (A)");
        System.out.println("2. Retirar Dinero (R)");
        System.out.println("3. Transferir Dinero (T)");
        System.out.println("Ingrese cualquier otra tecla si desea regresar al menu");
        String opcion = escaner.next();
        
        if(opcion.toLowerCase().equals("a")){
            
            System.out.println("Ingrese la cantidad de saldo que desea agregar");
            double saldo = vali.validarDouble("", "Ingrese una cantidad valida");
            
            while(saldo <0){
                
                System.out.println("Debe ingresar una cantidad superior a 0");
                System.out.println("Ingrese la cantidad de saldo que desea agregar");
                saldo = vali.validarDouble("", "Ingrese una cantidad valida");
            } 
            
            usuarios.agregarDinero(this.getUsuarioActivo(), saldo);
            this.guardarArchivo();
            System.out.println("Saldo agregado satisfactoriamente");
            this.menuCliente();
            
            
        }
        else if(opcion.toLowerCase().equals("r")){
            
            System.out.println("Ingrese la cantidad de saldo que desea retirar");
            double saldo = vali.validarDouble("", "Ingrese una cantidad valida");
            
            while(saldo <0){
                
                System.out.println("Debe ingresar una cantidad superior a 0");
                System.out.println("Ingrese la cantidad de saldo que desea retirar");
                saldo = vali.validarDouble("", "Ingrese una cantidad valida");
            } 
            
            if(usuarios.dineroSuficiente(this.getUsuarioActivo(), saldo)){
            
                usuarios.retirarDinero(this.getUsuarioActivo(), saldo);
                this.guardarArchivo();
                System.out.println("Saldo retirado satisfactoriamente");
                this.menuCliente();
            
            }
            else{
                System.out.println("No cuenta con saldo suficiente, vuelva a intentarlo");
                this.bancoCliente();
            }
        }
        else if(opcion.toLowerCase().equals("t")){
            
            System.out.println("Ingrese el Nombre de Usuario al que le quiere transferir dinero");
            String destino = escaner.next();
            
            while(destino.equals(usuarioActivo)){
                
                System.out.println("No puede transferirse dinero a usted mismo, ingrese otro destinatario");
                destino = escaner.next();
            }
            
            if(usuarios.existeUsuario(destino)){
                
                if(usuarios.esEmpleado(destino)){
                    
                    System.out.println("No se permite transferir dinero a empleados, vuelva a intentarlo");
                    this.bancoCliente();
                }
                else if (!usuarios.esEmpleado(destino)){
                    System.out.println("Ingrese la cantidad de saldo que desea transferir");
                    double saldo = vali.validarDouble("", "Ingrese una cantidad valida");
            
                    while(saldo <0){
                
                        System.out.println("Debe ingresar una cantidad superior a 0");
                        System.out.println("Ingrese la cantidad de saldo que desea transferir");
                        saldo = vali.validarDouble("", "Ingrese una cantidad valida");
                    } 
                
                    if(usuarios.dineroSuficiente(this.getUsuarioActivo(), saldo)){
                    
                        usuarios.transferirDinero(this.getUsuarioActivo(),destino, saldo);
                        this.guardarArchivo();
                        System.out.println("Su saldo ha sido transferido satisfactoriamente");
                        this.menuCliente();
                    }
                    else{
                        System.out.println("No cuenta con saldo suficiente, vuelva a intentarlo");
                        this.bancoCliente();
                    }
                }
            }
            else{
                
                System.out.println("El Nombre de Usuario al que desea transferir no existe, por favor intentelo nuevamente");
                this.bancoCliente();
            }
           
        }
        else{
            System.out.println("Dirigiendose al Menu principal");
            this.menuCliente();
            
        }
        
    }
    
    public void mostrarArticulo(){
        
        System.out.println("MENU - MOSTRAR ARTICULOS");
        System.out.println("------------------------------------------------");
        
        articulos.mostrarArticulos();
        
        System.out.println("Presione 'V' para volver al menu");
        String opcion = escaner.next();
        
        if(opcion.toLowerCase().equals("v")){
            this.menuEmpleado();
        }
        else{
            this.mostrarArticulo();
        }
    }
    
    public void editarArticulo(){
        
        System.out.println("MENU - EDITAR ARTICULO");
        System.out.println("------------------------------------------------");
        
        System.out.println("Ingrese el codigo del articulo que desea editar");
        String cod_articulo = escaner.next();
        
        if(articulos.existeArticulo(cod_articulo)){
            System.out.println("Seleccione el campo que desea editar");
            System.out.println("1. Nombre");
            System.out.println("2. Descripcion");
            System.out.println("3. Precio Unitario");
            System.out.println("4. Stock");
            System.out.println("5. Regresar");
            String opcion = escaner.next();
            escaner.nextLine();
            String nuevoValor;
            Double nuevoDouble;
            int nuevoInt;
            String nuevo;
            
            if(opcion.toLowerCase().equals("1") || opcion.toLowerCase().equals("2")){
                
                System.out.println("Ingrese el nuevo valor");
                nuevoValor = escaner.nextLine();
                articulos.editarArticulos(cod_articulo, opcion, nuevoValor);
                this.guardarArchivo();
                System.out.println("Articulo editado correctamente");
                this.menuEmpleado();
            }
            else if( opcion.toLowerCase().equals("3") ){
                System.out.println("Ingrese el nuevo valor");
                nuevoDouble = vali.validarDouble("", "Ingrese un valor valido");
                
                
                while (nuevoDouble<= 0){
                    System.out.println("Ingrese un precio positivo");
                    System.out.println("Ingrese el nuevo valor");
                    nuevoDouble = vali.validarDouble("", "Ingrese un valor valido");
                    
                }
                
                nuevo= String.valueOf(nuevoDouble);
                
                articulos.editarArticulos(cod_articulo, opcion, nuevo);
                this.guardarArchivo();
                System.out.println("Articulo editado correctamente");
                this.menuEmpleado();
                
            }
            else if( opcion.toLowerCase().equals("4") ){
                System.out.println("Ingrese el nuevo valor");
                //nuevoValor = escaner.nextLine();
                
                nuevoInt = vali.validarInt("", "Ingrese un valor valido");
                //int nuevo = Integer.parseInt(nuevoValor);
                while (nuevoInt< 0){
                    System.out.println("Ingrese un stock positivo");
                    System.out.println("Ingrese el nuevo valor");
                    
                    nuevoInt = vali.validarInt("", "Ingrese un valor valido");
                    
                    
                }
                
                nuevo= String.valueOf(nuevoInt);
                
                articulos.editarArticulos(cod_articulo, opcion, nuevo);
                this.guardarArchivo();
                System.out.println("Articulo editado correctamente");
                this.menuEmpleado();
                
            }
            else if(opcion.toLowerCase().equals("5")){
                this.menuEmpleado();
            }
            else{
                System.out.println("Opcion incorrecta, vuelva a intentarlo");
                this.editarArticulo();
            }
        }
        else{
            System.out.println("El articulo no existe, vuelva a intentarlo");
            this.editarArticulo();
        }
    }
    
    public void eliminarArticulo(){
        
        System.out.println("MENU - ELIMINAR ARTICULOS");
        System.out.println("------------------------------------------------");
        
        
        System.out.println("Ingrese el codigo del articulo que desea eliminar ");
        System.out.println("Presione (S) para regresar");
        String cod_articulo = escaner.next();
        
        if(cod_articulo.toLowerCase().equals("s")){
            
            this.menuEmpleado();
        }
        
        if(articulos.existeArticulo(cod_articulo)){
            System.out.println("¿Esta seguro que desea eliminar el articulo con codigo " + cod_articulo + "? (S/N)");
            String eliminar = escaner.next();
            
            if(eliminar.toLowerCase().equals("s")){
                articulos.eliminarArticulos(cod_articulo);
                System.out.println("Articulo eliminado satisfactoriamente");
                this.guardarArchivo();
                this.menuEmpleado();
            }
            else if(eliminar.toLowerCase().equals("n")){
                this.menuEmpleado();
            }
            else{
                System.out.println("Opcion incorrecta, vuelva a intentarlo");
                this.menuEmpleado();
            }
        }
       
    }
    
    public void agregarArticulo(){
    
        System.out.println(" MENU - AGREGAR ARTICULO!");
        System.out.println("---------------------------------------------------------");
        articulos.mostrarArticulos();
        System.out.println("Ingrese codigo del articulo");
        System.out.println("Presione (S) para regresar");
        String cod_articulo = escaner.next();
        
        if (cod_articulo.toLowerCase().equals("s")) {
            
            this.menuEmpleado();
        }
        
        if(articulos.existeArticulo(cod_articulo)){
            System.out.println("El articulo ya existe ¿Desea agregar stock?(S/N)");
            String opcion = escaner.next();
            
            if(opcion.toLowerCase().equals("s")){
                System.out.println("¿Cuantas unidades del articulo agregara?");
                int sumarStock = vali.validarInt("", "Ingrese una cantidad valida");
                
                while(sumarStock <= 0){
                System.out.println("Debe ingresar una cantidad positiva, ingrese una cantidad valida");
                sumarStock = vali.validarInt("", "Ingrese una cantidad valida");

                }
                articulos.agregarStock(cod_articulo, sumarStock);
                this.guardarArchivo();
                
                System.out.println("Articulo añadido satisfactoriamente");
                
                System.out.println("¿Desea volver al menu de empleados? (S/N)");
                String volver= escaner.next();
            
                if(volver.toLowerCase().equals("s")){
                    this.menuEmpleado();
                }
                else if(volver.toLowerCase().equals("n")){
                    this.agregarArticulo();
                }
                else{
                    System.out.println("Opcion incorrecta");
                    System.out.println("Direccionando al menu de agregar articulo . . .");
                    this.agregarArticulo();
                }
                    this.agregarArticulo();
                }
                else if(opcion.toLowerCase().equals("n")){
                    System.out.println("Usted no desea agregar mas stock");
                    System.out.println("Volviendo al menu para agregar articulos. . .");
                    this.agregarArticulo();
                }
                else{
                    System.out.println("Opcion incorrecta, vuelva a intentarlo");
                    this.agregarArticulo();
                }
        }
        else{
            System.out.println("Ingrese el nombre del articulo");
            
            
            String nombre = escaner.next();
            escaner.nextLine();
            
            System.out.println("Ingrese descripcion del articulo");
            String descripcion = escaner.nextLine();
            
            System.out.println("Ingrese el precio unitario del articulo");
            double precio = vali.validarDouble("", "Ingrese un precio valido");
            
            while (precio<= 0){
                System.out.println("Ingrese un precio positivo");
                System.out.println("Ingrese el nuevo valor");
                precio = vali.validarDouble("", "Ingrese un precio valido");
                    
                    
            }
            
            System.out.println("Ingrese cuantas unidades del articulo se agregaran");
            int stock = vali.validarInt("", "Ingrese una cantidad valida");
            
            while(stock < 0){
                System.out.println("Debe ingresar una cantidad positiva, ingrese una cantidad valida");
                stock = vali.validarInt("", "Ingrese una cantidad valida");

            }
            
            articulos.addArticulo(cod_articulo, nombre, descripcion, precio, stock);
            this.guardarArchivo();
            
            System.out.println("¿Desea seguir agregando productos? (S/N)");
            String volver= escaner.next();
            
            if(volver.toLowerCase().equals("s")){
                this.agregarArticulo();
            }
            else if(volver.toLowerCase().equals("n")){
                this.menuEmpleado();
            }
            else{
                System.out.println("Opcion incorrecta");
                System.out.println("Direccionando al menu de agregar articulo . . .");
                this.agregarArticulo();
            }
            
        }
        
    }
    
    
    public void clienteCompra(){
        
        System.out.println("MENU - CARRO DE COMPRAS");
        System.out.println("----------------------------------------------------");
        articulos.mostrarArticulos();
        
        System.out.println("Ingrese el codigo del articulo que desea agregar al carrito");
        System.out.println("Presione (S) para dirigirse al menu");
        String cod_articulo = escaner.next();
        
        if (cod_articulo.toLowerCase().equals("s")){
            this.menuCliente();
        }
        if(articulos.existeArticulo(cod_articulo)){
            
            System.out.println("Ingrese la cantidad que desea agregar de dicho articulo");
            int cantidad = vali.validarInt("", "Ingrese un numero valido");
        
            if(articulos.garantizarStock(cod_articulo, cantidad)&& cantidad>0){
                    
                articulos.addPedido(cod_articulo, cantidad);
            }
            else{
                System.out.println("Cantidad invalida, vuelva a intentarlo");
                this.clienteCompra();
            }
        
        
            while(true){
                
                articulos.mostrarPedidos();
                System.out.println("Desea comprar otro articulo? (S/N)");
                String opc = escaner.next();
            
                if(opc.toLowerCase().equals("s")){
                
                    articulos.mostrarArticulos();
        
                    System.out.println("Ingrese el codigo del articulo que desea agregar al carrito");
                    cod_articulo = escaner.next();
        
                    if(!articulos.existeArticulo(cod_articulo)){
                        
                        System.out.println("No se encontro articulo con ese codigo, vuelva a intentarlo");
                        this.menuCliente();
                    }
                    System.out.println("Ingrese la cantidad que desea agregar de dicho articulo");
                    cantidad = vali.validarInt("", "Ingrese un numero valido");
                
                    if(articulos.garantizarStock(cod_articulo, cantidad)&& cantidad >0 ){
                    
                        articulos.addPedido(cod_articulo, cantidad);
                    }
                    else{
                        System.out.println("Cantidad invalida, vuelva a intentarlo");
                        this.clienteCompra();
                    }
                }
                else if(opc.toLowerCase().equals("n")){
                
                    break;
                }
                else{
                    System.out.println("Opcion incorrecta");
                }
            }
        
            System.out.println("El total a pagar es de " + articulos.calcularMonto() + "pesos");
            String comprar = null;
        
            while(comprar!="s" || comprar!="n"){
                System.out.println("¿Desea realizar la compra? (S/N)");
                comprar = escaner.next();
                if(comprar.toLowerCase().equals("n")){
                    articulos.borrarPedido();
                    this.guardarArchivo();
                    this.menuCliente();
                }
        
                if(usuarios.dineroSuficiente(this.getUsuarioActivo(), articulos.calcularMonto())){
            
                    System.out.println("Felicidades, compra realizada!");
                    usuarios.retirarDinero(this.getUsuarioActivo(), articulos.calcularMonto());
                    articulos.finalizarCompra();
                    this.guardarArchivo();
                    this.menuCliente();
            
                }
                else{
                    System.out.println("Saldo insuficiente, deposite y vuelva a intentarlo");
                    articulos.borrarPedido();
                    this.guardarArchivo();
                    this.menuCliente();
                }
            }
        }
        else{
            System.out.println("No se encontro articulo con ese codigo, vuelva a intentarlo");
            this.menuCliente();
        }
        
        
    }
    
    public void menuEmpleado(){
        
        System.out.println("MENU - EMPLEADO");
        System.out.println("----------------------------------------------------");
        System.out.println("¿Que accion desea realizar?");
        System.out.println("1.Mostrar Articulos ");
        System.out.println("2.Agregar Articulos ");
        System.out.println("3.Editar Articulos ");
        System.out.println("4.Eliminar Articulos ");
        System.out.println("5.Salir del sistema");
        
        int opcion = vali.validarInt("", "Ingrese una opcion valida");
        
        if(opcion == 1){
            this.mostrarArticulo();
        }
        else if(opcion == 2){
            this.agregarArticulo();
        }
        else if(opcion == 3){
            this.editarArticulo();
        }
        else if(opcion == 4){
            this.eliminarArticulo();
        }
        else if(opcion ==5){
            System.exit(0);
        }
        else {
            System.out.println("Opcion incorrecta, por favor vuelva a intentarlo");
            this.menuEmpleado();
        }
    }
    
    public void menuCliente(){
        
        System.out.println(" MENU - CLIENTE!");
        System.out.println("----------------------------------------------------");
        System.out.println("¿Que accion desea realizar? ");
        System.out.println("Comprar o ingresar al Banco digital (C/B) ");
        System.out.println("Si desea salir (S)");
        String opcion = escaner.next();
        
        if(opcion.toLowerCase().equals("c")){
            this.clienteCompra();
        }
        else if(opcion.toLowerCase().equals("b")){
            this.bancoCliente();
        }
        else if(opcion.toLowerCase().equals("s")){
            System.out.println("Gracias por utilizar nuestro sistema, esperamos verlo pronto!");
            System.exit(0);
        }
        else{
            System.out.println("Opcion incorrecta, por favor vuelva a intentarlo");
            this.menuCliente();
        }
    }
    
    public void crearUsuario(){
        
        System.out.println("MENU - CREACION DE NUEVO USUARIO!");
        System.out.println("----------------------------------------------------");
        System.out.println("Ingrese un nombre de usuario");
        System.out.println("Presione (S) para regresar");
        String username = escaner.next();
        
        if (username.toLowerCase().equals("s")) {
            
            this.menuInicial();
        }
        
        if(usuarios.existeUsuario(username)){
            System.out.println("El nombre de usuario que quiere utilizar ya esta en uso, vuelva a intentarlo");
            this.menuInicial();
        }
        else{
            System.out.println("Ingrese el password");
            String password = escaner.next();
            
            System.out.println("Esta cuenta es tipo Empleado o tipo Cliente? (E/C)");
            String tipo = escaner.next();
            if(tipo.toLowerCase().equals("e")){
                tipo = "Empleado";
            }
            else if(tipo.toLowerCase().equals("c")){
                tipo = "Cliente";
            }
            else{
                System.out.println("Tipo incorrecto, vuelva a intentarlo");
                this.crearUsuario();
            }
            
            usuarios.addUsuario(username, password, tipo, 0);
            this.guardarArchivo();
            this.menuInicial();
            
        }
    }
    
    
    
    public void menuInicial(){
        System.out.println("BIENVENIDO AL SISTEMA!");
        System.out.println("----------------------------------------------------");
        System.out.println("Si desea loguearse presione 'L'");
        System.out.println("Si desea crear un nuevo usuario presione 'C'");
        String opcion = escaner.next();
        
        if(opcion.toLowerCase().equals("l")){
            this.login();
        }
        else if(opcion.toLowerCase().equals("c")){
            this.crearUsuario();
        }
        else{
            System.out.println("Opcion Incorrecta, por favor vuelva a intentarlo");
            this.menuInicial();
        }	
    }
   
   public void cargarArchivo(){
       if (archi.existe(rutaUsuarios)) {
		usuarios = (Usuarios) archi.load(rutaUsuarios);
	}
       
       if (archi.existe(rutaArticulos)) {
		articulos = (Articulos) archi.load(rutaArticulos);
	}
   } 
   
   public void guardarArchivo(){
       archi.save(usuarios, rutaUsuarios);
       archi.save(articulos, rutaArticulos);
   }
   
   public void crearArchivo(){
       if(archi.createFile(rutaUsuarios)) {
		archi.save(usuarios, rutaUsuarios);
	}
       
       if(archi.createFile(rutaArticulos)) {
		archi.save(articulos, rutaArticulos);
	}
        
       
   } 
    
   public void login(){
        
	System.out.println("MENU - LOGIN!");	
        System.out.println("----------------------------------------------------");
	System.out.println("Ingrese nombre de usuario");
        System.out.println("Presione (S) para regresar");
        
        
	String username = escaner.next();
        
        if(username.toLowerCase().equals("s")){
            this.menuInicial();
        }
		
	if(usuarios.existeUsuario(username)) {
		System.out.println("Ingrese password");
			
		String password = escaner.next();
		if(usuarios.getUsuario(username).validarPassword(password)) {
                        
                        this.setUsuarioActivo(username);
                    
                        System.out.println("Hola! "+this.getUsuarioActivo() +" Como estas?");
                        if(usuarios.getUsuario(username).getTipo().equals("Empleado")){
                            this.menuEmpleado();
                        }
                        else{
                            this.menuCliente();
                        }
		}
                else{
                    System.out.println("Password incorrecto");
                    this.menuInicial();
                }
                
	}
        else{
            System.out.println("Usuario incorrecto");
            this.menuInicial();
        }
    }

    public String getUsuarioActivo() {
        return usuarioActivo;
    }

    public void setUsuarioActivo(String usuarioActivo) {
        this.usuarioActivo = usuarioActivo;
    }
   
   
}
