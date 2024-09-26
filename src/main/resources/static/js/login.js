const form = document.querySelector('form[name="iniciar_sesion"]'); 

form.addEventListener('submit', async (event) => {
    event.preventDefault(); // Previene el envío del formulario por defecto

    const username = document.getElementById('usuario').value; // 'usuario' debe corresponder a 'cedula'
    const password = document.getElementById('contrasena').value; // 'contrasena' es correcto

    const response = await fetch('/api/auth/authenticate', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ cedula: username, contrasena: password }) // Verifica que esto coincida
    });

    if (response.ok) {
        const data = await response.json();
        const token = data.token;

        // Almacenar el token en localStorage
        localStorage.setItem('jwtToken', token);
        console.log('Token guardado:', token);


        // Redirigir a otra vista
        window.location.href = '/principal'; // Cambia esto a la ruta de tu vista deseada
    } else {
        // Manejar el error
        const errorText = await response.text(); // Obtener el cuerpo de la respuesta como texto
        console.error('Error en la respuesta:', errorText); // Imprimir en la consola
        alert('Credenciales incorrectas. Intenta de nuevo.');
    }
});

// Función para hacer una solicitud a una ruta protegida
async function fetchProtectedData() {
    const token = localStorage.getItem('jwtToken'); // Obtener el token almacenado

    const response = await fetch('/principal', {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer ' + token // Enviar el token en la cabecera
        }
    });

    if (response.ok) {
        const data = await response.text(); // O cualquier otra operación que necesites
        console.log(data); // Maneja los datos como necesites
    } else {
        const errorText = await response.text();
        window.location.href = '/index'; // Cambia esto a la ruta de tu vista deseada
    }
}

// Llama a fetchProtectedData si es necesario, por ejemplo, después de redirigir a la página principal
