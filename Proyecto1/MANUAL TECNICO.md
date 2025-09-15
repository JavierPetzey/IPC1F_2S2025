# MANUAL TÉCNICO 
Tenemos el metodo de llamarMenu que es la cual vamos a estar usando para estar llamando el menu principal, el código más que nada nos muestra el mensaje de la opción a elegir y 
luego usa cierta logica para verificar si la opción que elegimos es correcta, además cuenta con otro metodo para imprimir las opciones del menu.
```java
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
```
Tenemos ahora un metodo de confirmarAccion de tipo boolean y nos sirve principalmente para confirmar acciones tal como ingresar un nuevo producto a nuestro inventario y este va a imprimir un mensaje
donde va a pedir confirmar la accion.
```java
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
```
Este metodo verifica si hay productos en el inventario recorriendo el vector que contiene los productos y si encuentra al menos un producto registrado devuelve un valor true y
en caso de no encontrarlo devuelve un valor false y dependiendo de la funcion que se este ejecutando pues lanzara un mensaje para dicha opción. Existe un mismo código para verificar ventas.
```java
public static boolean revisarInventario(){
        int totalProductosRegistrados = inventario.indiceIncremento;
        producto[] listaInventario = inventario.listaProductos;

        
        for (int producto = 0; producto < totalProductosRegistrados; producto++){
            if (listaInventario[producto] != null){
                
                return true;
            }
        }
        
        return false;
    }
```
Tenemos el metodo de agregarProducto el cual usamos para registrar productos en nuestro vector y además de verificar datos correctamente por ejemplo el precio del producto que este en un rango positivo, además
de que tiene un bloque de código el cual usamos para confirmar si queremos registrar o no el producto usando el metodo anterior mostrado, y en caso de cancelar agregar producto entonces la acción se cancela y
nos devuelve al menu, pero en caso de confirmar agregar producto esté ademas de llenar nuestro vector con los datos registrados también nos incrementa dos vairables importantes que son numeroProducto y
indiceElemento que son las principales variables de nuestro programa ya que son los que llevan el codigo del producto y el indice de nuestro vector principal de inventario.
```java
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
```
Aunque existen 3 tipos de categorias diferentes por las cuales buscar productos este metodo puede describir las tres aunque sea buscar por nombre, lo que hace principalmente este metodo es que al ingresar
la cadena de texto a buscar (en este caso nombre del producto) lo va a ir buscando entre los datos ingresados y si en dado caso encuentra los datos los imprime todos, pero si no llega a encontrar ningún dato
va a lanzar un mensaje que no se encontro ningún dato. En caso de buscar categoría funciona similar a la lógica de este método, y al buscar por código lo único que va a hacer es ir comparando igualmente en 
el vector hasta encontrar un resultado.
```java
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
```
El metodo de eliminar producto funciona seleccionando el código del producto, luego mediante otro metodo se selecciona el indice donde esta el códoigo y luego vacía el vector para luego si hay más datos
mueve esos datos para que no queden espacios en el vector. 
```java
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
```
