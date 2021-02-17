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
		log.info("Usuario logeado" + user);

		// Buscar los roles para segun que rol mostrar distinto html
		// El de admin que le muestre un dashboard de todo
		// y el de user algo mas amistoso, para solicitar prestamos, ver estado de
		// cuenta
		// ver los libros
		//var roles = user.getAuthorities();

		log.info(user.getAuthorities().toString());

		var usuario = this.usuarioService.buscarUsuario(user.getUsername());
		model.addAttribute("usuario", usuario);

		return "index";
	}
}
