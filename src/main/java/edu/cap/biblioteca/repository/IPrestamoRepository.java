/**
 * @author romeramatias
 */

package edu.cap.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.cap.biblioteca.model.Prestamo;

public interface IPrestamoRepository extends JpaRepository<Prestamo, Long>{

}
