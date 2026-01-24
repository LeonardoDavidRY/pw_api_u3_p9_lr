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
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.matricula.application.EstudianteService;
import uce.edu.web.api.matricula.application.HijoService;
import uce.edu.web.api.matricula.application.representation.EstudianteRepresentation;
import uce.edu.web.api.matricula.domain.Estudiante;
import uce.edu.web.api.matricula.domain.Hijo;

@Path("/estudiantes")
public class EstudianteResource {

    @Inject
    private EstudianteService estudianteService;

    @Inject
    private HijoService hijoService;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public List<EstudianteRepresentation> listarTodos() {
        System.out.println("Listando todos los estudiantes ######");
        return this.estudianteService.listarTodos();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public EstudianteRepresentation consultarPorId(@PathParam("id") Integer id) {
        return this.estudianteService.consultarPorId(id);
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardarEstudiante(EstudianteRepresentation estudiante) {
        this.estudianteService.crearEstudiante(estudiante);
        return Response.status(Response.Status.CREATED).entity(estudiante).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarEstudiante(@PathParam("id") Integer id, Estudiante estudiante) {
        this.estudianteService.actualizarEstudiante(id, estudiante);
        return Response.status(209).entity(null).build();
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void actualizarParcialEstudiante(@PathParam("id") Integer id, Estudiante estudiante) {
        this.estudianteService.actualizarParcialEstudiante(id, estudiante);
    }

    @DELETE
    @Path("/{id}")
    public void borrarEstudiante(@PathParam("id") Integer id) {
        this.estudianteService.eliminarEstudiante(id);
    }

    @GET
    @Path("/provincia/genero")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Estudiante> buscarPorProvincia(@QueryParam("provincia") String provincia, @QueryParam("genero") String genero) {
        System.out.println("Listando todos los estudiantes por provincia ######");
        return this.estudianteService.buscarPorProvincia(provincia, genero);
    }

    @GET
    @Path("/{id}/hijos")
    public List<Hijo> buscarHijosPorEstudiante(@PathParam("id") Integer id) {
        return this.hijoService.buscarPorEstudianteId(id);
    }
}

