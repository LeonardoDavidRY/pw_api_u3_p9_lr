package uce.edu.web.api.matricula.application;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.application.representation.EstudianteRepresentation;
import uce.edu.web.api.matricula.domain.Estudiante;
import uce.edu.web.api.matricula.infraestructure.EstudianteRepository;

@ApplicationScoped
public class EstudianteService {

    @Inject
    private EstudianteRepository estudianteRepository;

    public List<EstudianteRepresentation> listarTodos() {
        List<EstudianteRepresentation> list = new ArrayList<>();
        for (Estudiante est : this.estudianteRepository.listAll()) {
            list.add(this.mapperToER(est));
        }
        return list;
    }

    public EstudianteRepresentation consultarPorId(Integer id) {
        return this.mapperToER(estudianteRepository.findById(id.longValue()));
    }

    @Transactional
    public void crearEstudiante(EstudianteRepresentation estudiante) {
        this.estudianteRepository.persist(this.mapperToE(estudiante));
    }

    @Transactional
    public void actualizarEstudiante(Integer id, EstudianteRepresentation estudiante) {
        // Obtener la entidad directamente del repositorio (está en el contexto de
        // persistencia)
        Estudiante est = this.estudianteRepository.findById(id.longValue());

        if (est != null) {
            est.setNombre(estudiante.getNombre());
            est.setApellido(estudiante.getApellido());
            est.setFechaNacimiento(estudiante.getFechaNacimiento());
            est.setProvincia(estudiante.getProvincia());
            est.setGenero(estudiante.getGenero());
            // Ahora sí se actualiza automáticamente por dirty checking
        }
    }

    @Transactional
    public void actualizarParcialEstudiante(Integer id, EstudianteRepresentation estudiante) {
        // Obtener la entidad directamente del repositorio (está en el contexto de
        // persistencia)
        Estudiante est = this.estudianteRepository.findById(id.longValue());

        if (est != null) {
            if (estudiante.getNombre() != null) {
                est.setNombre(estudiante.getNombre());
            }
            if (estudiante.getApellido() != null) {
                est.setApellido(estudiante.getApellido());
            }
            if (estudiante.getFechaNacimiento() != null) {
                est.setFechaNacimiento(estudiante.getFechaNacimiento());
            }
            if (estudiante.getProvincia() != null) {
                est.setProvincia(estudiante.getProvincia());
            }
            if (estudiante.getGenero() != null) {
                est.setGenero(estudiante.getGenero());
            }
            // Ahora sí se actualiza automáticamente por dirty checking
        }
    }

    @Transactional
    public void eliminarEstudiante(Integer id) {
        this.estudianteRepository.deleteById(id.longValue());
    }

    public List<EstudianteRepresentation> buscarPorProvincia(String provincia, String genero) {
        return this.estudianteRepository.find("provincia = ?1 and genero = ?2", provincia, genero).stream()
                .map(this::mapperToER)
                .toList();
    }

    private EstudianteRepresentation mapperToER(Estudiante estudiante) {
        EstudianteRepresentation rep = new EstudianteRepresentation();
        rep.setId(estudiante.getId());
        rep.setNombre(estudiante.getNombre());
        rep.setApellido(estudiante.getApellido());
        rep.setFechaNacimiento(estudiante.getFechaNacimiento());
        rep.setProvincia(estudiante.getProvincia());
        rep.setGenero(estudiante.getGenero());
        return rep;
    }

    private Estudiante mapperToE(EstudianteRepresentation rep) {
        Estudiante estudiante = new Estudiante();
        estudiante.setId(rep.getId());
        estudiante.setNombre(rep.getNombre());
        estudiante.setApellido(rep.getApellido());
        estudiante.setFechaNacimiento(rep.getFechaNacimiento());
        estudiante.setProvincia(rep.getProvincia());
        estudiante.setGenero(rep.getGenero());
        return estudiante;
    }

}
