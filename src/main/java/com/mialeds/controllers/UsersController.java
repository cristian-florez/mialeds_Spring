package com.mialeds.controllers;

import java.text.ParseException;


import com.mialeds.models.Usuario;
import com.mialeds.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping("/new")
	public String goNewUser(Model model) {
		model.addAttribute("user", new Usuario());
		return "users/user";
	}

	@RequestMapping("/save")
	public String save(@ModelAttribute Usuario usuario, BindingResult binRes, Model model) throws ParseException {
		try {
			usuarioService.insertar(usuario);
			return "redirect:/login";
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return "users/user";
		}
	}
}
