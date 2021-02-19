/**
 * @author romeramatias
 */

package edu.cap.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.cap.biblioteca.service.PrestamoService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j

public class ControladorPrestamo {

	@Autowired
	PrestamoService prestamosService;
	
	@GetMapping("/prestamos")
	public String inicio(Model model) {
		log.info("Inico - Prestamos Controller");
		var prestamos = this.prestamosService.listarPrestamos();
		log.info(prestamos.toString());
		model.addAttribute("prestamos", prestamos);
		return "prestamos/prestamos-admin";
	}
	
	
}
