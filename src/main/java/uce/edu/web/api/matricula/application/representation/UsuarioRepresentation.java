package uce.edu.web.api.matricula.application.representation;

import java.util.List;

public class UsuarioRepresentation {

    private Integer id;
    private String username;
    private String contrasenia;
    private String rol;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getContrasenia() {
        return contrasenia;
    }
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }

    public List<LinkDto> links;

}
