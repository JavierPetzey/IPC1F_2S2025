
package proyecto1;


public class inventario {
    static int numeroProducto = 0;
    static menu regresarMenu = new menu();
    //Vector objeto que va a contener todos los productos
    static producto[] listaProductos = new producto[100];
    static acciones confirmacion = new acciones();
    
    
    public static void agregarProducto(){
        String nombreProducto ;
        int cantidadStock, categoriaProducto;
        double precioProducto;
        
        
        System.out.println("-Agregar Producto-");
        nombreProducto = System.console().readLine("Nombre del Producto: ");
        
        regresarMenu.categoriaProducto();
        categoriaProducto = Integer.parseInt(System.console().readLine("Categoria del Producto: "));
        while(categoriaProducto<1 && categoriaProducto > 10){
            categoriaProducto = Integer.parseInt(System.console().readLine("Ingresar alguna de las Categorias mostradas: "));
        }
        
        String categoriaElegida = regresarMenu.categoriaSeleccionada(categoriaProducto);
        
        
        precioProducto = Double.parseDouble(System.console().readLine("Precio del Producto: "));
        while(precioProducto<0){
            precioProducto = Double.parseDouble(System.console().readLine("Ingresar un Precio Positivo: "));
        }
        
        cantidadStock = Integer.parseInt(System.console().readLine("Cantidad del Producto: "));
        while(cantidadStock<0){
            cantidadStock = Integer.parseInt(System.console().readLine("Ingresar una Cantidad Positiva: "));
        }
        
        
        
        if(!confirmacion.confirmarAccion()){
            System.out.println("Se cancelo agregar Producto");
            
            regresarMenu.llamarMenu();
            return;
        }
        
        //Objeto con diferente indice para cada producto
        listaProductos[numeroProducto] = new producto();
            //Agregar Producto
        System.out.println("Producto Agregado Correctamente");
        listaProductos[numeroProducto].nombre += nombreProducto;
        listaProductos[numeroProducto].categoria += categoriaElegida;
        listaProductos[numeroProducto].precio += precioProducto;
        listaProductos[numeroProducto].cantidadStock += cantidadStock;
        listaProductos[numeroProducto].codigo += numeroProducto + 1 ;
            
        numeroProducto++;
        regresarMenu.llamarMenu();
        

    }
    
    public static void confirmarContenidoInventario(){
        //Verificar si hay productos registrados
        acciones verificarInventario = new acciones();
        if(!verificarInventario.revisarInventario()){
            System.out.println("No hay productos registrados");
            regresarMenu.llamarMenu();
            return;
        }
    }
    
    public static void buscarProducto(){
        confirmarContenidoInventario();
        int buscarCriterio;
        System.out.println("Seleccione la Opcion a Bsucar");
        buscarCriterio = Integer.parseInt(System.console().readLine("1-Nombre \n 2-Categoria \n 3-Codigo \n Buscar:"));
        while(buscarCriterio<1 && buscarCriterio>3){
            buscarCriterio = Integer.parseInt(System.console().readLine("Seleccione alguna de las opciones anteriores:"));
        }
        
        
        
    }
    
    
    
    public static void eliminarProducto(){
        int codigoEliminar;

        
        //Verificar si hay productos registrados
        confirmarContenidoInventario();
        
        
        
        codigoEliminar = Integer.parseInt(System.console().readLine("Ingresa el código del Producto: "));
        while(!confirmacion.verificarExistencia(codigoEliminar))
        {
            codigoEliminar = Integer.parseInt(System.console().readLine("Ingresa el código del Producto existente: "));
        }
        
        
        
        
        if(!confirmacion.confirmarAccion()){
            System.out.println("Se cancelo Eliminar Producto");
            
            regresarMenu.llamarMenu();
            return;
        }
        
        System.out.println("Producto Eliminado Correctamente.");
        listaProductos[codigoEliminar-1] = null;
        numeroProducto++;
        regresarMenu.llamarMenu();
    }
    
    
}
