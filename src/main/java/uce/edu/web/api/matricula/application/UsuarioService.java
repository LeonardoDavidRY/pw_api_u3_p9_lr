package uce.edu.web.api.matricula.application;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.application.representation.UsuarioRepresentation;
import uce.edu.web.api.matricula.domain.Usuario;
import uce.edu.web.api.matricula.infraestructure.UsuarioRepository;

@ApplicationScoped
public class UsuarioService {

    @Inject
    private UsuarioRepository usuarioRepository;

    public List<UsuarioRepresentation> listarTodos() {
        List<UsuarioRepresentation> list = new ArrayList<>();
        for (Usuario usr : this.usuarioRepository.listAll()) {
            list.add(this.mapperToUR(usr));
        }
        return list;
    }

    public UsuarioRepresentation consultarPorId(Integer id) {
        return this.mapperToUR(usuarioRepository.findById(id.longValue()));
    }

    @Transactional
    public void crearUsuario(UsuarioRepresentation usuario) {
        this.usuarioRepository.persist(this.mapperToU(usuario));
    }

    @Transactional
    public void actualizarUsuario(Integer id, UsuarioRepresentation usuario) {
        Usuario usr = this.usuarioRepository.findById(id.longValue());

        if (usr != null) {
            usr.setUsername(usuario.getUsername());
            usr.setContrasenia(usuario.getContrasenia());
            usr.setRol(usuario.getRol());
        }
    }

    @Transactional
    public void actualizarParcialUsuario(Integer id, UsuarioRepresentation usuario) {
        Usuario usr = this.usuarioRepository.findById(id.longValue());

        if (usr != null) {
            if (usuario.getUsername() != null) {
                usr.setUsername(usuario.getUsername());
            }
            if (usuario.getContrasenia() != null) {
                usr.setContrasenia(usuario.getContrasenia());
            }
            if (usuario.getRol() != null) {
                usr.setRol(usuario.getRol());
            }
        }
    }

    @Transactional
    public void eliminarUsuario(Integer id) {
        this.usuarioRepository.deleteById(id.longValue());
    }

    public List<UsuarioRepresentation> buscarPorRol(String rol) {
        return this.usuarioRepository.find("rol = ?1", rol).stream()
                .map(this::mapperToUR)
                .toList();
    }

    private UsuarioRepresentation mapperToUR(Usuario usuario) {
        UsuarioRepresentation rep = new UsuarioRepresentation();
        rep.setId(usuario.getId());
        rep.setUsername(usuario.getUsername());
        rep.setContrasenia(usuario.getContrasenia());
        rep.setRol(usuario.getRol());
        return rep;
    }

    private Usuario mapperToU(UsuarioRepresentation rep) {
        Usuario usuario = new Usuario();
        usuario.setId(rep.getId());
        usuario.setUsername(rep.getUsername());
        usuario.setContrasenia(rep.getContrasenia());
        usuario.setRol(rep.getRol());
        return usuario;
    }

}
