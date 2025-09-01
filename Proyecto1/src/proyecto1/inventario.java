
package proyecto1;


public class inventario {
    static int numeroProducto = 0;
    static menu regresarMenu = new menu();
    //Vector objeto que va a contener todos los productos
    static producto[] listaProductos = new producto[100];
    static acciones confirmacion = new acciones();
    
    
    public static void agregarProducto(){
        String nombreProducto, categoriaProducto;
        int cantidadStock;
        double precioProducto;
        
        
        System.out.println("-Agregar Producto-");
        nombreProducto = System.console().readLine("Nombre del Producto: ");
        categoriaProducto = System.console().readLine("Categoria del Producto: ");
        precioProducto = Double.parseDouble(System.console().readLine("Precio del Producto: "));
        cantidadStock = Integer.parseInt(System.console().readLine("Cantidad del Producto: "));
        
        
        if(!confirmacion.confirmarAccion()){
            System.out.println("Se cancelo agregar Producto");
            
            regresarMenu.llamarMenu();
            return;
        }
        
        //Objeto con diferente indice para cada producto
        listaProductos[numeroProducto] = new producto();
            
        System.out.println("Producto Agregado Correctamente");
        listaProductos[numeroProducto].nombre += nombreProducto;
        listaProductos[numeroProducto].categoria += categoriaProducto;
        listaProductos[numeroProducto].precio += precioProducto;
        listaProductos[numeroProducto].cantidadStock += cantidadStock;
        listaProductos[numeroProducto].codigo += numeroProducto;
            
        numeroProducto++;
        regresarMenu.llamarMenu();
        

    }
    
    public static void buscarProducto(){
        
    }
    
    public static void eliminarProducto(){
        int codigoEliminar;
        //Verificar si hay productos registrados
        acciones verificarInventario = new acciones();
        if(!verificarInventario.revisarInventario()){
            System.out.println("No hay productos registrados");
            return;
        }
        
        codigoEliminar = Integer.parseInt(System.console().readLine("Ingresa el código del Producto: "));
        
        
        
        if(!confirmacion.confirmarAccion()){
            System.out.println("Se cancelo Eliminar Producto");
            
            regresarMenu.llamarMenu();
            return;
        }
        
        System.out.println("Producto Eliminado Correctamente.");
        listaProductos[codigoEliminar] = null;
        numeroProducto++;
        regresarMenu.llamarMenu();
    }
    
    
}
