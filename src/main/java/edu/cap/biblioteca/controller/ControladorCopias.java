/**
 * @author romeramatias
 */

package edu.cap.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.cap.biblioteca.model.Copia;
import edu.cap.biblioteca.model.Libro;
import edu.cap.biblioteca.service.CopiaService;
import edu.cap.biblioteca.service.LibroService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ControladorCopias {

	@Autowired
	CopiaService copiasService;

	@Autowired
	LibroService librosService;

	@GetMapping("/libros/{idLibro}/copias")
	public String inicio(Libro libro, Model model) {
		log.info("Inicio - Copias Controller");
		var copias = this.copiasService.listarCopiasPorLibro(libro.getIdLibro());
		libro = this.librosService.buscarLibro(libro);

		model.addAttribute("libro", libro);
		model.addAttribute("copias", copias);

		return "/copias/copias";
	}

	@GetMapping("/libros/{idLibro}/copias/{idCopia}/eliminar")
	public String eliminar(Copia copia) {
		log.info("Eliminar + ID Path - Persona Controller");
		log.info(copia.toString());
		copia = this.copiasService.buscarCopia(copia);
		this.copiasService.eliminar(copia);
		return "redirect:/libros/{idLibro}/copias/";
	}

	@GetMapping("/libros/{idLibro}/copias/agregar")
	public String agregar(Copia copia, Model model) {
		this.copiasService.guardarCopia(copia);
		return "redirect:/libros/{idLibro}/copias/";
	}

}
