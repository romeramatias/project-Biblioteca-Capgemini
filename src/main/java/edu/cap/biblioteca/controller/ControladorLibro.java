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
import org.springframework.web.bind.annotation.RequestParam;

import edu.cap.biblioteca.model.Libro;
import edu.cap.biblioteca.service.AutorService;
import edu.cap.biblioteca.service.CopiaService;
import edu.cap.biblioteca.service.LibroService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ControladorLibro {

	@Autowired
	private LibroService librosService;

	@Autowired
	private AutorService autoresService;

	@Autowired
	private CopiaService copiasService;

	@GetMapping("/libros")
	public String inicio(Model model) {
		log.info("Inicio - Libro Controller");
		var libros = librosService.listarLibros();
		var totalLibros = libros.size();
		var totalCopias = this.copiasService.listarCopias().size();
		model.addAttribute("libros", libros);
		model.addAttribute("totalLibros", totalLibros);
		model.addAttribute("totalCopias", totalCopias);

		return "libros/libros";
	}

	@GetMapping("/libros/buscar")
	public String buscarLibros(Model model, @RequestParam String nombre) {
		log.info("Inicio - Libro Controller");
		log.info(nombre);
		var libros = librosService.buscarLibros(nombre);
		var totalLibros = libros.size();
		
		model.addAttribute("libros", libros);
		model.addAttribute("totalLibros", totalLibros);
		return "libros/buscar";
	}

	@GetMapping("/libros/agregar")
	public String agregar(Libro libro, Model model) {
		log.info("Agregar - Libro Controller");
		var autores = this.autoresService.listarAutores();
		model.addAttribute("autores", autores);
		return "libros/modificar";
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
		var autores = this.autoresService.listarAutores();
		model.addAttribute("autores", autores);
		libro = this.librosService.buscarLibro(libro);
		model.addAttribute("libro", libro);
		return "libros/modificar";
	}

	@GetMapping("/eliminar/{idLibro}")
	public String eliminar(Libro libro) {
		log.info("Eliminar + ID Path - Libro Controller");
		this.copiasService.eliminarCopiasPorLibro(libro.getIdLibro());
		this.librosService.eliminar(libro);
		return "redirect:/libros";
	}
}
