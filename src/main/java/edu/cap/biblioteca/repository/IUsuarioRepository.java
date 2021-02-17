/**
 * @author romeramatias
 */

package edu.cap.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.cap.biblioteca.model.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByUsername(String username);

}
