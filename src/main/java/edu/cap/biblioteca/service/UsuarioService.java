/**
 * @author romeramatias
 */

package edu.cap.biblioteca.service;

import java.util.List;

import edu.cap.biblioteca.model.Usuario;


public interface UsuarioService {

	List<Usuario> listarUsuarios();

	Usuario buscarUsuario(String username);

	
}
