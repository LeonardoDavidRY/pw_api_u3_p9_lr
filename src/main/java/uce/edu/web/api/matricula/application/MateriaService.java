package uce.edu.web.api.matricula.application;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.domain.Materia;
import uce.edu.web.api.matricula.infraestructure.MateriaRepository;

@ApplicationScoped
public class MateriaService {

    @Inject
    private MateriaRepository materiaRepository;

    public List<Materia> listarTodos() {
        return materiaRepository.listAll();
    }
    public Materia consultarPorId(Integer id) {
        return materiaRepository.findById(id.longValue());
    }
    public Materia consultarPorCodigo(String codigo) {
        return materiaRepository.find("codigo", codigo).firstResult();
    }
    @Transactional
    public void crearMateria(Materia materia) {
        this.materiaRepository.persist(materia);
    }

    @Transactional
    public void actualizarMateria(Integer id, Materia materia) {
        Materia mat = this.consultarPorId(id);

        mat.setCodigo(materia.getCodigo());
        mat.setNombre(materia.getNombre());  
        mat.setNumCreditos(materia.getNumCreditos());
        //se actualiza automaticamente por dirty checking
    }

    @Transactional
    public void actualizarParcialMateria(Integer id, Materia materia) {
        Materia mat = this.consultarPorId(id);

        if (materia.getCodigo() != null) {
            mat.setCodigo(materia.getCodigo());
        }
        if (materia.getNombre() != null) {
            mat.setNombre(materia.getNombre());
        }
        if (materia.getNumCreditos() != null) {
            mat.setNumCreditos(materia.getNumCreditos());
        }
        //se actualiza automaticamente por dirty checking
    }

    @Transactional
    public void eliminarMateria(Integer id) {
        this.materiaRepository.deleteById(id.longValue());
    }

    @Transactional
    public void eliminarPorCodigo(String codigo) {
        this.materiaRepository.delete("codigo", codigo);
    }


}
