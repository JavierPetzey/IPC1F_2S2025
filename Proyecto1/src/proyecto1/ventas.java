package proyecto1;

import java.time.LocalTime;
import java.time.LocalDate;

public class ventas {
    
    static int indiceNoRegistro = 0;
    
    public static void registrarVentas(){
        inventario.confirmarContenidoInventario();
        acciones.imprimirListaProducto();
        
        int codigoVender = Integer.parseInt(System.console().readLine("Seleccione el codigo del producto: "));
        int indiceCodigoVender = codigoVender - 1; 
        
        System.out.println("Producto Seleccionado: " + inventario.listaProductos[indiceCodigoVender].nombre);
        System.out.println("Stock Disponible: " + inventario.listaProductos[indiceCodigoVender].cantidadStock);
        int cantidadVender = Integer.parseInt(System.console().readLine("Seleccione la cantidad del producto a vender: "));
        
        while(cantidadVender<1 && cantidadVender > inventario.listaProductos[indiceCodigoVender].cantidadStock){
            cantidadVender = Integer.parseInt(System.console().readLine("Ingresar una Cantidad de acuerdo al stock suficiente: "));
        }
        
        inventario.listaProductos[indiceCodigoVender].cantidadStock =  inventario.listaProductos[indiceCodigoVender].cantidadStock - cantidadVender;
        double total = inventario.listaProductos[indiceCodigoVender].precio * cantidadVender;
                
        almacenarVenta(codigoVender, cantidadVender , total);
        
        
        if (inventario.listaProductos[indiceCodigoVender].cantidadStock == 0){
            System.out.println("Ya no queda Stock del Producto.");
            inventario.listaProductos[indiceCodigoVender] = null;
        }
        
        
        
        
        menu.llamarMenu();
    }
    
    
    public static void almacenarVenta(int codigo, int cantidad, double totalVenta){
        LocalTime horaActual = LocalTime.now();
        LocalDate fechaActual = LocalDate.now();
        registroDeVenta[] totalVentasProductos = new registroDeVenta[100];
        
        
        totalVentasProductos[indiceNoRegistro].codigoProducto = codigo;
        totalVentasProductos[indiceNoRegistro].cantidadVendida = cantidad;
        totalVentasProductos[indiceNoRegistro].FechaYHora = "Hora: " + horaActual + " Fecha:  " + fechaActual ;
        totalVentasProductos[indiceNoRegistro].totalVenta = totalVenta;
        
    }
    
    public static void generarReportes(){
        
    }
    
}
