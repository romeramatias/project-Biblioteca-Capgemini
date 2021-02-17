/**
 * @author romeramatias
 */

package edu.cap.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.cap.biblioteca.model.Autor;
import edu.cap.biblioteca.repository.IAutorRepository;

@Service
public class AutorServiceImplement implements AutorService {

	@Autowired
	IAutorRepository autoresRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Autor> listarAutores() {
		return this.autoresRepository.findAll();
	}

}
