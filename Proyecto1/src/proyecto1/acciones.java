
package proyecto1;

public class acciones {
    
    
    
    public static boolean confirmarAccion(){
        int confirmarSiNo;
        
        System.out.println("¿Confirmar Accion?");
        System.out.println("1-Si \n2-No");
        
        confirmarSiNo = Integer.parseInt(System.console().readLine());
        
        while(!terminarCiclo(confirmarSiNo)){
            
            System.out.println("No se ha seleccionado ninguna opcion.");
            System.out.println("1-Si \n2-No");
        }
        
        
        if (confirmarSiNo == 1) return true;
        
        return false; 

        
    }
    
    //Si no
    public static boolean terminarCiclo(int verificarNumero){
        
        if (verificarNumero == 1 && verificarNumero == 2) return false;
        
        return true;
    }
    
    //Verifica si hay productos 
    public static boolean revisarInventario(){
        int totalProductosRegistrados = inventario.numeroProducto;
        producto[] listaInventario = inventario.listaProductos;

        
        for (int producto = 0; producto <= totalProductosRegistrados; producto++){
            if (listaInventario[producto] != null){
                
                return true;
            }
        }
        
        return false;
    }

    public static boolean verificarExistencia(int codigo){
        producto[] listaInventario = inventario.listaProductos;
        
        for (int revisar = 0; revisar <= inventario.numeroProducto; revisar++ ){
            if(codigo == listaInventario[revisar].codigo){
                return true;
            }
        }
        return false;
    }
    
    
    public static void imprimirListaProducto(){
        for (int noProducto = 0; noProducto <= inventario.numeroProducto; noProducto++){
            System.out.println("Codigo: " + inventario.listaProductos[noProducto].codigo);
            System.out.println("Nombre: " + inventario.listaProductos[noProducto].nombre);
            System.out.println("------------------------");
            
        }
    }
}
