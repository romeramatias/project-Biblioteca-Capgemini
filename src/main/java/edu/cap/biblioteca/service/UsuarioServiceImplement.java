package edu.cap.biblioteca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.cap.biblioteca.model.Rol;
import edu.cap.biblioteca.model.Usuario;
import edu.cap.biblioteca.repository.IUsuarioRepository;

@Service("userDetailsService")
public class UsuarioServiceImplement implements UserDetailsService, UsuarioService {

	@Autowired
	IUsuarioRepository usuarioRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = this.usuarioRepository.findByUsername(username);

		if (usuario == null)
			throw new UsernameNotFoundException(username);

		var roles = new ArrayList<GrantedAuthority>();

		for (Rol rol : usuario.getRoles()) {
			roles.add(new SimpleGrantedAuthority(rol.getNombre()));
		}

		return new User(usuario.getUsername(), usuario.getPassword(), roles);
	}

	@Override
	public Usuario buscarUsuario(String username) {
		return this.usuarioRepository.findByUsername(username);
	}

	@Override
	public List<Usuario> listarUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

}