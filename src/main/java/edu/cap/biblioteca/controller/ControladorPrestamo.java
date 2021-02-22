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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import edu.cap.biblioteca.model.Copia;
import edu.cap.biblioteca.model.Libro;
import edu.cap.biblioteca.model.Prestamo;
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
		var totalPrestamosActivos = this.prestamosService.prestamosActivos().size();
		var totalPrestamosFinalizados = this.prestamosService.prestamosFinalizados().size();

		model.addAttribute("prestamos", prestamos);
		model.addAttribute("totalPrestamosActivos", totalPrestamosActivos);
		model.addAttribute("totalPrestamosFinalizados", totalPrestamosFinalizados);

		return "prestamos/prestamos-admin";
	}

	@GetMapping("/prestamos/eliminar/{idPrestamo}")
	public String eliminar(Prestamo prestamo) {
		log.info("Eliminar + ID Path - Prestamo Controller");

		this.prestamosService.eliminar(prestamo);

		return "redirect:/prestamos";
	}

	@GetMapping("/libros/prestamo/{idLibro}/{idCopia}/guardar")
	public String guardarPrestamo(Libro libro, Copia copia, @AuthenticationPrincipal User user) {
		log.info("Guardar - Prestamo Controller");

		libro = this.librosService.buscarLibro(libro);
		copia = this.copiasService.buscarCopia(copia);
		var usuario = this.usuariosService.buscarUsuario(user.getUsername());

		this.prestamosService.guardarPrestamo(libro, copia, usuario);

		return "redirect:/mis-prestamos";
	}

	@GetMapping("/mis-prestamos")
	public String inicioUser(Model model, @AuthenticationPrincipal User user) {
		log.info("Inico - Prestamos Controller");

		var usuario = this.usuariosService.buscarUsuario(user.getUsername());
		var prestamos = this.prestamosService.prestamosPorUsuario(usuario.getIdUsuario());
		var prestamosActivos = this.prestamosService.prestamosActivosPorUsuario(usuario.getIdUsuario());
		var totalPrestamosActivos = prestamosActivos.size();
		var totalPrestamosDisponibles = Prestamo.LIMITE_PRESTAMOS - totalPrestamosActivos;

		model.addAttribute("prestamos", prestamos);
		model.addAttribute("prestamosActivos", prestamosActivos);
		model.addAttribute("totalPrestamosActivos", totalPrestamosActivos);
		model.addAttribute("totalPrestamosDisponibles", totalPrestamosDisponibles);

		return "prestamos/prestamos-user";
	}

	@GetMapping("/mis-prestamos/activos")
	public String prestamosActivos(Model model, @AuthenticationPrincipal User user) {

		var usuario = this.usuariosService.buscarUsuario(user.getUsername());
		var prestamos = this.prestamosService.prestamosActivosPorUsuario(usuario.getIdUsuario());
		var totalPrestamosDisponibles = Prestamo.LIMITE_PRESTAMOS - prestamos.size();

		model.addAttribute("prestamos", prestamos);
		model.addAttribute("totalPrestamosDisponibles", totalPrestamosDisponibles);

		return "prestamos/prestamos-user-activos";
	}

	@GetMapping("/mis-prestamos/devolver/{idPrestamo}")
	public String devolverLibro(Model model, Prestamo prestamo) {
		log.info("Devolver - Prestamos Controller");

		prestamo = this.prestamosService.buscarPrestamo(prestamo);
		this.prestamosService.devolucion(prestamo);

		return "redirect:/mis-prestamos";
	}

	@RequestMapping("/mis-prestamos/leer/{idPrestamo}")
	public RedirectView linkLibro(Model model, Prestamo prestamo) {
		log.info("PDF - Prestamos Controller");

		RedirectView redirectView = new RedirectView();

		prestamo = this.prestamosService.buscarPrestamo(prestamo);
		var titulo = prestamo.getLibro().getTitulo();
		titulo = titulo.replace(" ", "+");
		redirectView.setUrl("http://www.google.com/search?q=" + titulo + "+libro+pdf+google+drive+español");

		return redirectView;
	}

}
