package uce.edu.web.api.matricula.interfaces;

import java.util.List;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.matricula.application.UsuarioService;
import uce.edu.web.api.matricula.application.representation.UsuarioRepresentation;
import uce.edu.web.api.matricula.application.representation.LinkDto;

@Path("/usuarios")
public class UsuarioResource {

    @Inject
    private UsuarioService usuarioService;

    @Context
    private UriInfo uriInfo;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    //@RolesAllowed("admin")
    public List<UsuarioRepresentation> listarTodos() {
        System.out.println("Listando todos los usuarios ######");
        List<UsuarioRepresentation> usuarios = this.usuarioService.listarTodos();
        usuarios.forEach(usuario -> construirLinks(usuario));
        return usuarios;
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    //@RolesAllowed("admin")
    public UsuarioRepresentation consultarPorId(@PathParam("id") Integer id) {
        return this.construirLinks(this.usuarioService.consultarPorId(id));
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    //@RolesAllowed("admin")
    public Response guardarUsuario(UsuarioRepresentation usuario) {
        this.usuarioService.crearUsuario(usuario);
        return Response.status(Response.Status.CREATED).entity(usuario).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    //@RolesAllowed("admin")
    public Response actualizarUsuario(@PathParam("id") Integer id, UsuarioRepresentation usuario) {
        this.usuarioService.actualizarUsuario(id, usuario);
        return Response.status(209).entity(null).build();
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    //@RolesAllowed("admin")
    public void actualizarParcialUsuario(@PathParam("id") Integer id, UsuarioRepresentation usuario) {
        this.usuarioService.actualizarParcialUsuario(id, usuario);
    }

    @DELETE
    @Path("/{id}")
    //@RolesAllowed("admin")
    public void borrarUsuario(@PathParam("id") Integer id) {
        this.usuarioService.eliminarUsuario(id);
    }

    @GET
    @Path("/rol")
    @Produces(MediaType.APPLICATION_JSON)
    //@RolesAllowed("admin")
    public List<UsuarioRepresentation> buscarPorRol(@QueryParam("rol") String rol) {
        System.out.println("Listando todos los usuarios por rol ######");
        return this.usuarioService.buscarPorRol(rol);
    }

    private UsuarioRepresentation construirLinks(UsuarioRepresentation usuario){
        String self = uriInfo.getBaseUriBuilder()
            .path(UsuarioResource.class)
            .path(String.valueOf(usuario.getId()))
            .build().toString();

        usuario.links = List.of(new LinkDto(self, "self"));
        return usuario;
    }
}
