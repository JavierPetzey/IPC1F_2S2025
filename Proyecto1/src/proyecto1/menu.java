
package proyecto1;
import java.time.LocalTime;
import java.time.LocalDate;

public class menu {
    static String bitacoraRegistro = " ";
    
    public static void llamarMenu()
    {
        boolean elegirOpcion = true;
        int menuOpcion;
        
        //Verifica si se selecciona alguna opcion disponible
        while (elegirOpcion){
            menuVisual();
            System.out.println("Seleccione alguna Opcion: ");
            
            menuOpcion = Integer.parseInt(System.console().readLine());

            if (menuOpcion >= 1 && menuOpcion <= 9){
                seleccionMenu(menuOpcion);
                elegirOpcion = false;
            }else{
                System.out.println("Ninguna Opcion ha sido seleccionada");
            }
        }
    }
    
    
    
    
    
    public static void menuVisual(){
        System.out.println("1- Agregar Producto");
        System.out.println("2- Buscar Producto");
        System.out.println("3- Eliminar Producto");
        System.out.println("4- Registrar Venta");
        System.out.println("5- Bitácora");
        System.out.println("6- Generar Reportes Inventario");
        System.out.println("7- Generar Reportes Ventas");
        System.out.println("8- Datos Del Estudiante");
        System.out.println("9- Salir");
    }

    public static void seleccionMenu(int seleccion){
        inventario accionInventario = new inventario();
        ventas parteVentas = new ventas();
        switch(seleccion){
                case 1:
                    accionInventario.agregarProducto();
                    break;
                case 2:
                    accionInventario.buscarProducto();
                    break;
                case 3:
                    accionInventario.eliminarProducto();
                    break;
                case 4:
                    parteVentas.registrarVentas();
                    break;
                case 5:
                    imprimirBitacora();
                    break;
                case 6:
                    parteVentas.generarReportesInventario();
                    break;
                case 7:
                    parteVentas.generarReportesVentas();
                    break;
                case 8: 
                    Proyecto1.Estudiante();
                    break;
                case 9:
                    System.out.println("Terminado");
                    
                    break;
            }
    }
    
    
    public static void categoriaProducto(){
        System.out.println("Seleccione la Categoria del Producto");
        System.out.println("1-Camisetas y Blusas");
        System.out.println("2-Pantalones y Jeans");
        System.out.println("3-Faldas y Vestidos");
        System.out.println("4-Chamarras y Abrigos");
        System.out.println("5-Sueteres y Sudaderas");
        System.out.println("6-Ropa Interior");
        System.out.println("7-Calzado");
        System.out.println("8-Accesorios");
        System.out.println("9-Ropa Deportiva");
        System.out.println("10-Ropa de Dormir");
    }
    
    public static String categoriaSeleccionada(int seleccionOpcion){
        switch(seleccionOpcion){
            case 1:
                return "Camisetas y Blusas";
            case 2:
                return "Pantalones y Jeans";
            case 3:
                return "Faldas y Vestidos";
            case 4:
                return "Chamarras y Abrigos";
            case 5:
                return "Sueteres y Sudaderas";
            case 6:
                return "Ropa Interior";
            case 7:
                return "Calzado";
            case 8:
                return "Accesorios";
            case 9:
                return "Ropa Deportiva";
            case 10:
                return "Ropa de Dormir";
            
        }
        return "Sin Categoria";
    }
    
    
    //Bitacora
    public static String agregarProductoBitacora(boolean SiONo, int cantidad, String Nombre, String Categoria){
        
        if (!SiONo){
            return " Se cancelo al agregar : " + cantidad + " cantidad de: " + Nombre + " en la categoria: " +Categoria+ " al inventario";
        }
        
        return " Se agrego: " + cantidad + " cantidad de: " + Nombre + " en la categoria: " +Categoria+ " al inventario";
    }
    
    public static String buscarBitacora(boolean SiNo, String Nombre){
        if (!SiNo){
            return " Se intento buscar un producto no registrado";
        }
        return " Se busco el Elemento: " + Nombre;
    }
    
    public static String eliminarBitacora(boolean SiNo, String Nombre){
        if (!SiNo){
            return " Se cancelo la accion de Elimnar: "+ Nombre;
        }
        return " Se elimmino: " + Nombre +" del Inventario";
    }
    
    public static String ventaBitacora(boolean SiNo, String Nombre, int cantidad, double Precio){
        if (!SiNo){
            return " Se Cancelo la venta del producto: " +Nombre+" con la cantidad de: " + cantidad+ " con un Total de: Q"+ Precio;
        }
        return " Se realizo la venta del producto: " +Nombre+" con la cantidad de: " + cantidad+ " con un Total de: Q"+ Precio;
    }
    
    public static String pdfInventarioBitacora(){
        
        return " Se creo un archivo pdf para el inventario";
    }
    
    public static String pdfVentaBitacora(){
        
        return " Se creo un archivo pdf para las Ventas";
    }
    
    public static void bitacora(String agregarALaLista){
        LocalTime hora = LocalTime.now();
        LocalDate fecha = LocalDate.now();
        
        bitacoraRegistro +="Fecha y Hora: "+fecha +" "+hora+  agregarALaLista + " por Javier Petzey"+"\n";
        
    }
    

    
    public static void imprimirBitacora(){
        System.out.println("Total de Acciones en el Programa: \n"+bitacoraRegistro );
        llamarMenu();
    }
    
    
}

