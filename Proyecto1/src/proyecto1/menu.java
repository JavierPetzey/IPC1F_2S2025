
package proyecto1;

public class menu {
    int numeroProducto;
    
    public static void llamarMenu()
    {
        boolean elegirOpcion = true;
        int menuOpcion;
        
        //Verifica si se selecciona alguna opcion disponible
        while (elegirOpcion){
            menuVisual();
            System.out.println("Seleccione alguna Opcion: ");
            
            menuOpcion = Integer.parseInt(System.console().readLine());

            if (menuOpcion >= 1 && menuOpcion <= 8){
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
        System.out.println("5- Generar Reportes");
        System.out.println("6- Ver Datos del Estudiante");
        System.out.println("7- Bitácora");
        System.out.println("8- Salir");
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
                    parteVentas.registrarVentas();
                    break;
                case 6:
                    parteVentas.generarReportes();
                    break;
                case 7:
                    
                    break;
                case 8: 
                    
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
    
    
    
    public static void bitacora(){
        
    }
}

