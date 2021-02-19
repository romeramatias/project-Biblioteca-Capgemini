/**
 * @author romeramatias
 */

package edu.cap.biblioteca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ControladorInicio {

	@GetMapping("/")
	public String inicio(Model model) {
		log.info("Inicio - Controlador Index");
		return "inicio";
	}
}
