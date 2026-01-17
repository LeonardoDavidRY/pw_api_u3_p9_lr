package uce.edu.web.api.matricula.interfaces;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import uce.edu.web.api.matricula.application.MateriaService;
import uce.edu.web.api.matricula.domain.Materia;

@Path("/materias")
public class MateriaResource {

    @Inject
    private MateriaService materiaService;

    @GET
    @Path("/todos")
    public List<Materia> listarTodos() {
        return this.materiaService.listarTodos();
    }
    
    @GET
    @Path("/consultarPorId/{id}")
    public Materia consultarPorId(@PathParam("id") Integer id) {
        return this.materiaService.consultarPorId(id);
    }

    @GET
    @Path("/consultarPorCodigo/{codigo}")
    public Materia consultarPorCodigo(@PathParam("codigo") String codigo) {
        return this.materiaService.consultarPorCodigo(codigo);
    }

    @POST
    @Path("/crear")
    public void guardarMateria(Materia materia) {
        this.materiaService.crearMateria(materia);
    }

    @PUT
    @Path("/actualizar/{id}")
    public void actualizarMateria(@PathParam("id") Integer id, Materia materia) {
        this.materiaService.actualizarMateria(id, materia);
    }

    @PATCH
    @Path("/actualizarParcial/{id}")
    public void actualizarParcialMateria(@PathParam("id") Integer id, Materia materia) {
        this.materiaService.actualizarParcialMateria(id, materia);
    }

    @DELETE
    @Path("/borrar/{id}")
    public void borrarMateria(@PathParam("id") Integer id) {
        this.materiaService.eliminarMateria(id);
    }

    @DELETE
    @Path("/borrarPorCodigo/{codigo}")
    public void borrarPorCodigo(@PathParam("codigo") String codigo) {
        this.materiaService.eliminarPorCodigo(codigo);
    }
}
