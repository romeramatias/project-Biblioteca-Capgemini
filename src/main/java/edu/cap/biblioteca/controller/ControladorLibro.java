/**
 * @author romeramatias
 */

package edu.cap.biblioteca.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import edu.cap.biblioteca.model.Libro;
import edu.cap.biblioteca.service.LibroService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ControladorLibro {

	@Autowired
	private LibroService librosService;

	@GetMapping("/libros")
	public String inicio(Model model) {
		log.info("Inicio - Libro Controller");

		
		var libros = librosService.listarLibros();
		log.info(libros.toString());
		model.addAttribute("libros", libros);

		return "libros/libros";
	}

	@GetMapping("/libros/agregar")
	public String agregar(Libro libro) {
		log.info("Agregar - Libro Controller");
		return "/libros/modificar";
	}

	@PostMapping("/libros/guardar")
	public String guardar(@Valid Libro libro, Errors errores) {
		log.info("Guardar - Libro Controller");

		if (errores.hasErrors()) {
			return "modificar";
		}

		this.librosService.guardarLibro(libro);
		return "redirect:/libros";
	}

	@GetMapping("/libros/editar/{idLibro}")
	public String editar(Libro libro, Model model) {
		log.info("Editar + ID - Libro Controller");
		log.info(libro.toString());
		libro = this.librosService.buscarLibro(libro);
		model.addAttribute("libro", libro);
		return "/libros/modificar";
	}
}
