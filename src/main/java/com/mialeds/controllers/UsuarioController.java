package com.mialeds.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.mialeds.models.Usuario;
import com.mialeds.services.UsuarioService;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    
    //metodo para editar cierta informacion del usuario
    @PutMapping("/editarUsuario")
    public String editarUsuario(
        @RequestParam("id_cambio") int id,
        @RequestParam("cambio_nombre") String nombre,
        @RequestParam("cambio_cedula") String cedula,
        @RequestParam("cambio_correo") String correo,
        @RequestParam("cambio_telefono") String telefono,
        Model model){
            //creamos un objeto usuario que utilizamos para verificar si cambio la cedula
        Usuario usuario = usuarioService.buscarPorId(id);
            try{
                //actualizamos la informacion del usuario
                usuarioService.actualizarUsuario(id, nombre, cedula, correo, telefono);
                model.addAttribute("mensaje", "Usuario editado correctamente");
            } catch (Exception e){
                model.addAttribute("error", "Error al editar el usuario: " + e.getMessage());
            }
            //verificamos si cambio la cedula, si es asi cerramos la sesion para evitar errores
            if (usuario.getCedula() != cedula){
                return "redirect:/logout";
                }
            return "redirect:/principal";
        }

        //metodo para cambiar la contraseña del usuario(faltan mejoras)
        @PutMapping("/cambiarClave")
        public String cambiarClave(
            @RequestParam("id_clave") int id,
            @RequestParam("clave_antigua") String claveAntigua,
            @RequestParam("clave_nueva1") String claveNueva,
            @RequestParam("clave_nueva2") String confirmacionClave,
            Model model){
                try{
                    //verificamos si la clave nueva y la confirmacion de la clave son iguales
                    if (claveNueva.equals(confirmacionClave)){
                        usuarioService.cambiarContrasena(id, claveAntigua, claveNueva ); 
                        model.addAttribute("mensaje", "cambio Contraseña exitoso");  
                        return "redirect:/logout";
                     //si no son iguales mostramos un mensaje de error
                    } else {
                        model.addAttribute("error", "Las contraseñas no coinciden");
                    }
                } catch (Exception e){
                    model.addAttribute("error", "Error al editar el usuario: " + e.getMessage());
                }
                    return "redirect:/principal";
            }        
}
    


