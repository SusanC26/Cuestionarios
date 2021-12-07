package susan.mx.cuestionarios.negocio;



import  susan.mx.cuestionarios.modelo.Pregunta;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import susan.mx.cuestionarios.modelo.Encuesta;
import susan.mx.cuestionarios.datos.EncuestaRepository;


@Service
@Slf4j
public class EncuestaService {
	
	@Autowired
	private EncuestaRepository encuestaRepository;
	
	@Autowired
	private PreguntaService preguntaService;
	
	public Encuesta create(Encuesta newencuesta)
	{
	  Encuesta returnEncuesta=encuestaRepository.save(newencuesta);
	  
	  return returnEncuesta;
		
	}
	
	public Iterable<Encuesta>retrieveAll()
	{
		return encuestaRepository.findAll();
	}
	
	public Encuesta updateencuesta(Integer id_encuesta, Encuesta actualizaEncuesta)
	{
		 Optional<Encuesta>  encuestafind =encuestaRepository.findById(id_encuesta);
		if(encuestafind.isPresent())
		{
			Encuesta question = new Encuesta();
			
			question.setId_encuesta(actualizaEncuesta.getId_encuesta());
			question.setNombre_encuesta(actualizaEncuesta.getNombre_encuesta());
			question.setPreguntas(actualizaEncuesta.getPreguntas());
		    
		    Encuesta actualizadoencuesta= encuestaRepository.save(actualizaEncuesta);
		
		
		return actualizadoencuesta;
		}
		else
		{
			return null;
		}
	}
	
	public boolean delate(Integer id_encuesta)
	{
		Optional<Encuesta> encuestafind=encuestaRepository.findById(id_encuesta);
		
		if(encuestafind.isPresent())
		{
			encuestaRepository.deleteById(id_encuesta);
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	
	public boolean addPreguntaToEncuesta(Integer id_encuesta,Integer id_pregunta)
	{
		
	  //1.recupera al pregunta
	  Pregunta pregunta=preguntaService.retieve(id_pregunta);	
	  
	  //2.recuperar la encuesta
	  Optional <Encuesta> encuestaOp = encuestaRepository.findById(id_encuesta);
	  
	  //3.verificamos si el la encuesta y pregunta existe
	  if(!encuestaOp.isPresent() || pregunta==null)
	  {
		  
		  return false;
	  }
	  //4.agrega pregunta a encuesta
	  Encuesta encuesta =encuestaOp.get();
	  
	  encuesta.addPregunta(pregunta); 
	  //5.persiste el cambio
	  encuestaRepository.save(encuesta);
	  return true;
	}
	

}