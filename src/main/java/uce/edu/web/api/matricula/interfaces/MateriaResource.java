package uce.edu.web.api.matricula.interfaces;

import java.util.List;

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
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.matricula.application.MateriaService;
import uce.edu.web.api.matricula.domain.Materia;

@Path("/materias")
public class MateriaResource {

    @Inject
    private MateriaService materiaService;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos() {
        List<Materia> materias = this.materiaService.listarTodos();
        return Response.ok(materias).build();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarPorId(@PathParam("id") Integer id) {
        Materia materia = this.materiaService.consultarPorId(id);
        return Response.ok(materia).build();
    }

    @GET
    @Path("/codigo/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarPorCodigo(@PathParam("codigo") String codigo) {
        Materia materia = this.materiaService.consultarPorCodigo(codigo);
        return Response.ok(materia).build();
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardarMateria(Materia materia) {
        this.materiaService.crearMateria(materia);
        return Response.status(Response.Status.CREATED).entity(materia).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarMateria(@PathParam("id") Integer id, Materia materia) {
        this.materiaService.actualizarMateria(id, materia);
        return Response.status(209).entity(null).build();
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarParcialMateria(@PathParam("id") Integer id, Materia materia) {
        this.materiaService.actualizarParcialMateria(id, materia);
        return Response.status(209).entity(null).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response borrarMateria(@PathParam("id") Integer id) {
        this.materiaService.eliminarMateria(id);
        return Response.status(Response.Status.OK).entity(null).build();
    }

    @DELETE
    @Path("/codigo/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response borrarPorCodigo(@PathParam("codigo") String codigo) {
        this.materiaService.eliminarPorCodigo(codigo);
        return Response.status(Response.Status.OK).entity(null).build();
    }
}
