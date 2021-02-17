/**
 * @author romeramatias
 */

package edu.cap.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.cap.biblioteca.model.Autor;

public interface IAutorRepository extends JpaRepository<Autor, Long> {

}
