package susan.mx.cuestionarios.datos;

import org.springframework.data.repository.CrudRepository;

import susan.mx.cuestionarios.modelo.Pregunta;

public interface PreguntaRepository extends CrudRepository<Pregunta,Integer>{
	

}
