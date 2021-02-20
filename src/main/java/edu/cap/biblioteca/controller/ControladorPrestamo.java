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

import edu.cap.biblioteca.model.Copia;
import edu.cap.biblioteca.model.Libro;
import edu.cap.biblioteca.service.CopiaService;
import edu.cap.biblioteca.service.LibroService;
import edu.cap.biblioteca.service.PrestamoService;
import edu.cap.biblioteca.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j

public class ControladorPrestamo {

	@Autowired
	PrestamoService prestamosService;

	@Autowired
	LibroService librosService;

	@Autowired
	CopiaService copiasService;

	@Autowired
	UsuarioService usuariosService;

	@GetMapping("/prestamos")
	public String inicio(Model model) {
		log.info("Inico - Prestamos Controller");
		
		var prestamos = this.prestamosService.listarPrestamos();

		model.addAttribute("prestamos", prestamos);
		
		return "prestamos/prestamos-admin";
	}

	@GetMapping("/libros/prestamo/{idLibro}/{idCopia}/guardar")
	public String guardarPrestamo(Libro libro, Copia copia, @AuthenticationPrincipal User user) {
		log.info("Guardar - Prestamo Controller");

		libro = this.librosService.buscarLibro(libro);
		copia = this.copiasService.buscarCopia(copia);
		var usuario = this.usuariosService.buscarUsuario(user.getUsername());

		this.prestamosService.guardarPrestamo(libro, copia, usuario);

		return "redirect:/libros";
	}
	
	@GetMapping("/mis-prestamos")
	public String inicioUser(Model model, @AuthenticationPrincipal User user) {
		log.info("Inico - Prestamos Controller");
		
		var usuario = this.usuariosService.buscarUsuario(user.getUsername());
		var prestamos = this.prestamosService.prestamosPorUsuario(usuario.getIdUsuario());
		
		model.addAttribute("prestamos", prestamos);
		
		return "prestamos/prestamos-user";
	}

}
