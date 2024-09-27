package com.mialeds.controllers; // Paquete que contiene los controladores de la aplicación

import java.util.List; // Importamos la clase List para manejar colecciones de objetos

import org.springframework.beans.factory.annotation.Autowired; // Importamos la anotación para inyectar dependencias
import org.springframework.stereotype.Controller; // Importamos la anotación que define esta clase como un controlador
import org.springframework.ui.Model; // Importamos la clase Model para pasar datos a las vistas
import org.springframework.web.bind.annotation.GetMapping; // Importamos la anotación para manejar solicitudes GET
import org.springframework.web.bind.annotation.PostMapping; // Importamos la anotación para manejar solicitudes POST

import com.mialeds.models.PermisosRoles; // Importamos el modelo PermisosRoles
import com.mialeds.models.Role; // Importamos el modelo Role
import com.mialeds.models.Usuario; // Importamos el modelo Usuario
import com.mialeds.services.RoleService; // Importamos el servicio que maneja los roles
import com.mialeds.services.UsuarioService; // Importamos el servicio que maneja los usuarios

// Esta clase es responsable de manejar las solicitudes relacionadas con el inicio de sesión y la creación de usuarios
@Controller
public class LoginController {

    // Inyectamos el servicio de usuario para poder usarlo en esta clase
    @Autowired
    private UsuarioService usuarioService;

    // Inyectamos el servicio de rol para poder usarlo en esta clase
    @Autowired
    private RoleService roleService;

    // Método que maneja la solicitud GET para la página de inicio de sesión
    @GetMapping("/login")
    public String index() {
        return "index"; // Retorna el nombre de la vista que se mostrará (index.html)
    }

    // Método que maneja la solicitud GET para la creación de un nuevo usuario
    @GetMapping("/crear-usuario")
    public String crearUsuario(Model model) {
        model.addAttribute("usuario", new Usuario()); // Creamos un nuevo objeto Usuario y lo añadimos al modelo
        List<Role> roles = roleService.listarRoles(); // Obtenemos la lista de roles desde el servicio
        List<PermisosRoles> permisos = roleService.listarPermisos(); // Obtenemos la lista de permisos desde el servicio

        // Añadimos la lista de roles y permisos al modelo para ser usados en la vista
        model.addAttribute("roles", roles);
        model.addAttribute("permisos", permisos);
        return "crear-usuario"; // Retorna el nombre de la vista que se mostrará (crear-usuario.html)
    }

    // Método que maneja la solicitud POST para crear un nuevo usuario
    @PostMapping("/crear-usuario")
    public String crearUsuario(Usuario usuario, Model model) {
        try {
            usuarioService.crearUsuario(usuario); // Intentamos crear el nuevo usuario utilizando el servicio
            model.addAttribute("mensaje", "Usuario creado correctamente"); // Añadimos un mensaje de éxito al modelo
            return "redirect:/"; // Redirigimos a la página principal después de la creación exitosa
        } catch (Exception e) {
            // Si ocurre un error, añadimos un mensaje de error al modelo
            model.addAttribute("error", "Error al crear el usuario: " + e.getMessage());
            return "crear-usuario"; // Retorna a la vista de creación de usuario si hay un error
        }
    }
}
