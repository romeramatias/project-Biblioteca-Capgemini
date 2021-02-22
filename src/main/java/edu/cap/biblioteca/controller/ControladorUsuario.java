/**
 * @author romeramatias
 */

package edu.cap.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.cap.biblioteca.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ControladorUsuario {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/usuario")
	public String inicio(Model model, @AuthenticationPrincipal User user) {
		log.info("Index - Inicio Controller");

		var usuario = this.usuarioService.buscarUsuario(user.getUsername());
		
		model.addAttribute("usuario", usuario);

		return "usuarios/user-data";
	}
}
