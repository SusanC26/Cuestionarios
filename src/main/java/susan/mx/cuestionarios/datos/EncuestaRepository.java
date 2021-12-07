package susan.mx.cuestionarios.datos;


import org.springframework.data.repository.CrudRepository;


import susan.mx.cuestionarios.modelo.Encuesta;
/**
 * 
 * @author SUSAN
 * almacenar y recuperar datos
 *
 */

public interface EncuestaRepository extends CrudRepository<Encuesta,Integer> {

}
