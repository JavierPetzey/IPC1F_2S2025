
package proyecto1;

public class menu {
    
    
    public static void llamarMenu()
    {
        boolean elegirOpcion = true;
        int menuOpcion;

        while (elegirOpcion){
            menuVisual();
            System.out.println("Seleccione alguna Opcion: ");
            
            menuOpcion = Integer.parseInt(System.console().readLine());
            
            seleccionMenu(menuOpcion);
            
            if (menuOpcion > 1 && menuOpcion < 8){
                
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
    
    
    
    
    
    public static void bitacora(){
        
    }
}

