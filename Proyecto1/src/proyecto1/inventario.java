
package proyecto1;


public class inventario {
    static int numeroProducto = 0;
    static int indiceIncremento = 0;
    static menu regresarMenu = new menu();
    //Vector objeto que va a contener todos los productos
    static producto[] listaProductos = new producto[100];
    static acciones confirmacion = new acciones();
    
    
    public static void agregarProducto(){
        String nombreProducto ;
        int cantidadStock, categoriaProducto;
        double precioProducto;
        int numCodigo = numeroProducto+1;
        System.out.println("-Agregar Producto-");
        System.out.println("Codigo del Producto: " + numCodigo);
        nombreProducto = System.console().readLine("Nombre del Producto: ");
        
        regresarMenu.categoriaProducto();
        categoriaProducto = Integer.parseInt(System.console().readLine("Categoria del Producto: "));
        while(categoriaProducto<1 || categoriaProducto > 10){
            categoriaProducto = Integer.parseInt(System.console().readLine("Ingresar alguna de las Categorias mostradas: "));
        }
        
        String categoriaElegida = regresarMenu.categoriaSeleccionada(categoriaProducto);
        
        
        precioProducto = Double.parseDouble(System.console().readLine("Precio del Producto: "));
        while(precioProducto<1){
            precioProducto = Double.parseDouble(System.console().readLine("Ingresar un Precio Positivo: "));
        }
        
        cantidadStock = Integer.parseInt(System.console().readLine("Cantidad del Producto: "));
        while(cantidadStock<1){
            cantidadStock = Integer.parseInt(System.console().readLine("Ingresar una Cantidad Positiva: "));
        }
        
        
        if(!confirmacion.confirmarAccion()){
            System.out.println("Se cancelo agregar Producto");

            String agregarABitacora = menu.agregarProductoBitacora(false, cantidadStock, nombreProducto, categoriaElegida);
            menu.bitacora(agregarABitacora);
            regresarMenu.llamarMenu();
            return;
        }

        String agregarABitacora = menu.agregarProductoBitacora(true, cantidadStock, nombreProducto, categoriaElegida);
        menu.bitacora(agregarABitacora);
        
        //Objeto con diferente indice para cada producto
        listaProductos[indiceIncremento] = new producto();
        //Agregar Producto
        System.out.println("Producto Agregado Correctamente");
        listaProductos[indiceIncremento].nombre = nombreProducto;
        listaProductos[indiceIncremento].categoria = categoriaElegida;
        listaProductos[indiceIncremento].precio = precioProducto;
        listaProductos[indiceIncremento].cantidadStock = cantidadStock;
        listaProductos[indiceIncremento].codigo = numeroProducto + 1 ;
        
        numeroProducto++;
        indiceIncremento++;
        regresarMenu.llamarMenu();
        

    }
    
    public static void confirmarContenidoInventario(){
        //Verificar si hay productos registrados
        acciones verificarInventario = new acciones();
        if(!verificarInventario.revisarInventario()){
            System.out.println("No hay productos registrados");
            regresarMenu.llamarMenu();

        }
    }
    
    //Buscar Prodcuto
    
    public static void buscarProducto(){
        confirmarContenidoInventario();
        int buscarCriterio;
        System.out.println("Seleccione la Opcion a Bsucar");
        buscarCriterio = Integer.parseInt(System.console().readLine("1-Nombre \n 2-Categoria \n 3-Codigo \n Buscar:"));
        while(buscarCriterio<1 && buscarCriterio>3){
            buscarCriterio = Integer.parseInt(System.console().readLine("Seleccione alguna de las opciones anteriores:"));
        }
        seleccionBuscarProducto(buscarCriterio);
        
    }
    
    public static void seleccionBuscarProducto(int opcionCriterio){
        int encontrado = 0;
        switch(opcionCriterio){
            case 1:
                String buscarPorNombre = System.console().readLine("Ingresa El Producto A Buscar: ");
                
                buscarNombre(encontrado, buscarPorNombre);
            case 2:
                menu.categoriaProducto();
                int categoriaProducto = Integer.parseInt(System.console().readLine("Categoria del Producto: "));
                while(categoriaProducto<1 || categoriaProducto > 10){
                categoriaProducto = Integer.parseInt(System.console().readLine("Ingresar alguna de las Categorias mostradas: "));
                }
                String buscarPorCategoria = menu.categoriaSeleccionada(categoriaProducto);
                buscarCategoria(encontrado, buscarPorCategoria);
            case 3:
                int buscarPorCodigo = Integer.parseInt(System.console().readLine("Ingresa el Codigo del Producto a Buscar:"));
                buscarCodigo(encontrado, buscarPorCodigo);
        }
    }
    
    public static void buscarNombre(int encontrado, String buscarPorNombre){
        for (int contadorBusqueda = 0; contadorBusqueda < indiceIncremento; contadorBusqueda++){
            if (buscarPorNombre.equalsIgnoreCase(listaProductos[contadorBusqueda].nombre)){
                imprimirBusqueda(contadorBusqueda);
                
                menu.buscarBitacora(true, listaProductos[contadorBusqueda].nombre);
                encontrado = 9;
            }
        }
        if (encontrado != 9){
            System.out.println("No se ha encontrado Ningun Producto.");

            String agregarABitacora = menu.buscarBitacora(false, " ");
            menu.bitacora(agregarABitacora);
        }
        menu.llamarMenu();
    }
    
    public static void buscarCategoria(int encontrado, String buscarPorCategoria){

        for (int contadorBusqueda = 0; contadorBusqueda < indiceIncremento; contadorBusqueda++){
            if (buscarPorCategoria.equalsIgnoreCase(listaProductos[contadorBusqueda].categoria)){
                imprimirBusqueda(contadorBusqueda);
                
                String agregarABitacora = menu.buscarBitacora(true, listaProductos[contadorBusqueda].nombre);
                menu.bitacora(agregarABitacora);
                encontrado = 9;
            }
        }
        if (encontrado != 9){
            System.out.println("No se ha encontrado Ningun Producto.");
            String agregarABitacora = menu.buscarBitacora(false, " ");
            menu.bitacora(agregarABitacora);
        }
        menu.llamarMenu();
    }
    
    public static void buscarCodigo(int encontrado,int buscarPorCodigo){

        if (buscarPorCodigo == listaProductos[buscarPorCodigo-1].codigo){
            imprimirBusqueda(buscarPorCodigo-1);

            String agregarABitacora = menu.buscarBitacora(true, listaProductos[buscarPorCodigo-1].nombre);
            menu.bitacora(agregarABitacora);
            
            encontrado = 9;
        }
        if (encontrado != 9){
            System.out.println("No se ha encontrado Ningun Producto.");

            String agregarABitacora = menu.buscarBitacora(false, " ");
            menu.bitacora(agregarABitacora);
        }
        menu.llamarMenu();
    }
    
    public static void imprimirBusqueda(int productoNo){
        System.out.println("Codigo: " + listaProductos[productoNo].codigo);
        System.out.println("Nombre: " + listaProductos[productoNo].nombre);
        System.out.println("Categoria: " + listaProductos[productoNo].categoria);
        System.out.println("Cantidad: " + listaProductos[productoNo].cantidadStock);
        System.out.println("Precio: " + listaProductos[productoNo].precio);
        System.out.println("----------------------");
    }
    
    
    //Eliminar Producto
    
    public static void eliminarProducto(){
        int codigoEliminar;
        
        //Verificar si hay productos registrados
        confirmarContenidoInventario();
        acciones.imprimirListaProducto();
        
        codigoEliminar = Integer.parseInt(System.console().readLine("Ingresa el código del Producto: "));
        while(!confirmacion.verificarExistencia(codigoEliminar))
        {
            codigoEliminar = Integer.parseInt(System.console().readLine("Ingresa el código del Producto existente: "));
            
        }
        
        int indiceCodigoEliminar = indiceProductoEliminar(codigoEliminar);
        
        
        if(!confirmacion.confirmarAccion()){
            System.out.println("Se cancelo Eliminar Producto");
            String bitacoraEliminar = menu.eliminarBitacora(false, listaProductos[indiceCodigoEliminar].nombre);
            menu.bitacora(bitacoraEliminar);
            regresarMenu.llamarMenu();
            
            return;
        }
        
        String biracoraEliminarSi = menu.eliminarBitacora(true, listaProductos[indiceCodigoEliminar].nombre);
        menu.bitacora(biracoraEliminarSi);
        
        System.out.println("Producto Eliminado Correctamente.");
        listaProductos[codigoEliminar-1] = null;
        indiceIncremento--;
        
        for(int indice = indiceCodigoEliminar; indice < indiceIncremento; indice++){
            listaProductos[indice] = listaProductos[indice+1];
        }
        
        regresarMenu.llamarMenu();
    }
    
    public static int indiceProductoEliminar(int codigo){
        for(int recorrer = 0; recorrer < indiceIncremento; recorrer++){
            if(codigo == listaProductos[recorrer].codigo){
                return recorrer;
            }
        }
        return 0;
    }
    
    
}
