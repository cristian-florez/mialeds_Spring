$(document).ready(function() {
    // Evento que se ejecuta cuando el DOM (estructura HTML) se ha cargado completamente.
    
    // 1. Configuración del Botón "Agregar Producto"
    // Este evento se activa cuando el usuario hace clic en el botón con el id 'agregarProducto'.
    // Se puede agregar funcionalidad dentro de este bloque para gestionar la acción de agregar productos.
    $('#agregarProducto').click(function() {
        // Aquí puedes agregar el código para gestionar la lógica de agregar un producto.
    });

    // 2. Solicitud AJAX para Obtener la Lista de Productos
    // Se realiza una solicitud GET al endpoint 'http://localhost:8080/api/venta/listar' para obtener los productos.
    $.ajax({
        url: 'http://localhost:8080/api/venta/listar', // URL del endpoint de la API que devuelve la lista de productos.
        type: 'GET', // Método HTTP para la solicitud (GET para obtener datos).
        success: function(response) {
            // Callback que se ejecuta si la solicitud es exitosa.
            var productos = {}; // Objeto que almacenará los productos para el autocompletado.

            // 2.1. Procesamiento de la Respuesta
            // Recorre cada producto devuelto en la respuesta.
            response.forEach(function(producto) {
                var nombre = producto[0]; // Primer elemento del array: nombre del producto.
                var presentacion = producto[1]; // Segundo elemento del array: presentación del producto.
                // Agrega el producto al objeto 'productos' usando el formato "nombre + presentación".
                productos[nombre + " " + presentacion] = null; 
            });

            // 2.2. Configuración del Componente de Autocompletado
            // Inicializa el autocompletado en todos los inputs con la clase 'autocomplete'.
            $('input.autocomplete').autocomplete({
                data: productos, // Datos que se utilizarán para las sugerencias de autocompletado.
                limit: 5, // Límite de sugerencias que se mostrarán.
                onAutocomplete: function(val) {
                    // Función que se ejecuta cuando el usuario selecciona un valor del autocompletado.
                    console.log("Producto seleccionado:", val); // Muestra en la consola el valor seleccionado.
                },
                minLength: 1 // Mínimo número de caracteres necesarios para activar el autocompletado.
            });

            // 2.3. Depuración y Verificación
            // Muestra en la consola todos los productos obtenidos y procesados.
            console.log('Productos obtenidos:', productos); 
        },
        error: function(xhr, status, error) {
            // Callback que se ejecuta si ocurre un error en la solicitud AJAX.
            // Muestra en la consola un mensaje de error junto con la descripción del mismo.
            console.error('Error al obtener los productos:', error);
        }
    });
});
