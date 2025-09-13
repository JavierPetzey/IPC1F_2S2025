package proyecto1;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.time.LocalTime;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ventas {
    
    static int indiceNoRegistro = 0;
    static registroDeVenta[] totalVentasProductos = new registroDeVenta[100];
    public static void registrarVentas(){
        inventario.confirmarContenidoInventario();
        acciones.imprimirListaProducto();
        
        int codigoVender = Integer.parseInt(System.console().readLine("Seleccione el codigo del producto: "));
        int indiceCodigoVender = codigoVender - 1; 
        
        System.out.println("Producto Seleccionado: " + inventario.listaProductos[indiceCodigoVender].nombre);
        System.out.println("Stock Disponible: " + inventario.listaProductos[indiceCodigoVender].cantidadStock);
        int cantidadVender = Integer.parseInt(System.console().readLine("Seleccione la cantidad del producto a vender: "));
        
        while(cantidadVender<1 || cantidadVender > inventario.listaProductos[indiceCodigoVender].cantidadStock){
            cantidadVender = Integer.parseInt(System.console().readLine("Ingresar una Cantidad de acuerdo al stock suficiente: "));
        }
        
        double total = inventario.listaProductos[indiceCodigoVender].precio * cantidadVender;
        
        if(!acciones.confirmarAccion()){
            System.out.println("Se cancelo La venta del Producto");
            
            String ventaBitacoraRegistro = menu.ventaBitacora(false, inventario.listaProductos[indiceCodigoVender].nombre, cantidadVender, total);
            menu.bitacora(ventaBitacoraRegistro);
            menu.llamarMenu();
        }
        
        
        inventario.listaProductos[indiceCodigoVender].cantidadStock =  inventario.listaProductos[indiceCodigoVender].cantidadStock - cantidadVender;
        
                
        almacenarVenta(codigoVender, cantidadVender , total, inventario.listaProductos[indiceCodigoVender].nombre);
        
        String ventaBitacoraRegistro = menu.ventaBitacora(true, inventario.listaProductos[indiceCodigoVender].nombre, cantidadVender, total);
        menu.bitacora(ventaBitacoraRegistro);
        
        if (inventario.listaProductos[indiceCodigoVender].cantidadStock == 0){
            System.out.println("Ya no queda Stock del Producto.");
            inventario.listaProductos[indiceCodigoVender] = null;
            
            inventario.indiceIncremento--;
            for(int indice = indiceCodigoVender; indice < inventario.indiceIncremento; indice++){
            inventario.listaProductos[indice] = inventario.listaProductos[indice+1];
            }
            
        }

        menu.llamarMenu();
    }
    
    
    public static void almacenarVenta(int codigo, int cantidad, double totalVenta, String nombreProducto){
        LocalTime horaActual = LocalTime.now();
        LocalDate fechaActual = LocalDate.now();
        
        totalVentasProductos[indiceNoRegistro] = new registroDeVenta();
        
        totalVentasProductos[indiceNoRegistro].codigoProducto = codigo;
        totalVentasProductos[indiceNoRegistro].nombreVentaProducto = nombreProducto;
        totalVentasProductos[indiceNoRegistro].cantidadVendida = cantidad;
        totalVentasProductos[indiceNoRegistro].FechaYHora = "Fecha: " + fechaActual + " Hora:  " + horaActual ;
        totalVentasProductos[indiceNoRegistro].totalVenta = totalVenta;
        
        indiceNoRegistro++;
        
    }
    
    public static void generarReportesInventario(){
        inventario.confirmarContenidoInventario();
        
        String nombrePDF = nombreDeLosPDF();
        
        
        try {
            String destino = nombrePDF + "_Stock.pdf";
            Document document = new Document();
            //No se que hacen exactamente ciertas partes del código xd
            PdfWriter.getInstance(document, new FileOutputStream(destino));
            document.open();
            
            for (int noProducto = 0; noProducto < inventario.indiceIncremento; noProducto++){
                
                Phrase productos = new Phrase("Código: "+ inventario.listaProductos[noProducto].codigo+" Nombre: "+inventario.listaProductos[noProducto].nombre +" Cantidad: "+ 
                inventario.listaProductos[noProducto].cantidadStock + " Precio: " + inventario.listaProductos[noProducto].precio);
                
                document.add(productos);
                document.close();
                System.out.println("");
            }
            System.out.println("PDF de Inventario Creado");
            String bitacoraInventario = menu.pdfInventarioBitacora();
            menu.bitacora(bitacoraInventario);
            
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(ventas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        menu.llamarMenu();
    }
    
    
    public static void generarReportesVentas(){
        LocalTime horaActual = LocalTime.now();
        LocalDate fechaActual = LocalDate.now();
        confirmarContenidoVentas();
        
        
        String nombrePDF = nombreDeLosPDF();
        try {
            String destino = nombrePDF + "_Venta.pdf";
            Document document = new Document();
            
            //No se que hacen exactamente ciertas partes del código xd
            PdfWriter.getInstance(document, new FileOutputStream(destino));
            document.open();
            
            for (int noProducto = 0; noProducto < indiceNoRegistro; noProducto++){
                
                Phrase productos = new Phrase("Código: "+ totalVentasProductos[noProducto].codigoProducto+" Nombre: "+totalVentasProductos[noProducto].nombreVentaProducto +
                " Cantidad Vendida: "+ totalVentasProductos[noProducto].cantidadVendida + " Total de Venta: Q" + totalVentasProductos[noProducto].totalVenta
                + " " + totalVentasProductos[noProducto].FechaYHora);
                
                document.add(productos);
                document.close();
                System.out.println("");
            }
            System.out.println("PDF de Ventas Creado");
            
            String bitacoraVenta = menu.pdfVentaBitacora();
            menu.bitacora(bitacoraVenta);
            
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(ventas.class.getName()).log(Level.SEVERE, null, ex);
        }
        menu.llamarMenu();
    }
    
    public static void confirmarContenidoVentas(){
        //Verificar si hay productos registrados
        acciones verificarInventario = new acciones();
        if(!verificarInventario.revisarVentas()){
            System.out.println("No hay productos registrados");
            menu.llamarMenu();

        }
    }
    
    public static String nombreDeLosPDF(){
        Calendar Hora = Calendar.getInstance();
        Calendar Fecha = Calendar.getInstance();
        String HoraActual, FechaActual;
        
        int dia = Fecha.get(Calendar.DATE);
        int mes = Fecha.get(Calendar.MONTH);
        int año = Fecha.get(Calendar.YEAR);
        int hora = Hora.get(Calendar.HOUR_OF_DAY);
        int minuto = Hora.get(Calendar.MINUTE);
        int segundos = Hora.get(Calendar.SECOND);
        
        FechaActual = dia + "_" + mes + "_" + año + "_";
        HoraActual = hora + "_" + minuto + "_" + segundos;
        return FechaActual + HoraActual;
    }
}
