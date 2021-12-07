package susan.mx.cuestionarios.negocio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import susan.mx.cuestionarios.modelo.Encuesta;
import susan.mx.cuestionarios.modelo.Pregunta;
import susan.mx.cuestionarios.datos.EncuestaRepository;
import susan.mx.cuestionarios.datos.PreguntaRepository;
import susan.mx.cuestionarios.modelo.Opcion;

@Service
@Slf4j
public class PreguntaService
{
	@Autowired
	private PreguntaRepository preguntaRepository;
	@Autowired
	private EncuestaRepository encuestaRepository;
	@Autowired
	private OpcionService opcionService;
	
	
	public Pregunta create(Pregunta newpregunta)
	{
	  Pregunta returnPregunta=preguntaRepository.save(newpregunta);
	  
	  return returnPregunta;
		
	}
	
	public Optional<Pregunta> retrievebyId(Integer id_encuesta,Integer id_pregunta )
	{
		 Optional<Pregunta>  preguntafind =preguntaRepository.findById(id_pregunta);
		 Optional<Encuesta>  preguntafindP =encuestaRepository.findById(id_encuesta);
		 
		 if(preguntafind.isPresent() && preguntafindP.isPresent()) {
			 
			 
			 return preguntaRepository.findById(id_pregunta);
		 }
		 else
		 {
			 return null;
		 }
		
	}
	
	public Iterable<Pregunta>retrieveAll()
	{
		return preguntaRepository.findAll();
	}
	
	public Pregunta updatepregunta(Integer id_encuesta,Integer id_pregunta, Pregunta actualizaPregunta)
	{
		 Optional<Pregunta>  preguntafind =preguntaRepository.findById(id_pregunta);
		 Optional<Encuesta>  encuestafind =encuestaRepository.findById(id_encuesta);
		if(preguntafind.isPresent() && encuestafind.isPresent())
		{
			Pregunta question = new Pregunta();
			
			question.setId_pregunta(actualizaPregunta.getId_pregunta());
			question.setId_encuesta(actualizaPregunta.getId_encuesta());
			question.setNombre_pregunta(actualizaPregunta.getNombre_pregunta());
			question.setOpciones(actualizaPregunta.getOpciones());
			
		    
		    Pregunta actualizadopregunta= preguntaRepository.save(actualizaPregunta);
		
		
		return actualizadopregunta;
		}
		else
		{
			return null;
		}
	}
	
	
	public Pregunta retieve(Integer id_pregunta)
	{
	  
		Optional<Pregunta>  preguntafind =preguntaRepository.findById(id_pregunta);
		
		if(preguntafind.isPresent())
		{
			 return preguntafind.get();
		}else
			return null;
	}
	
	
	
	public boolean delate(Integer id_pregunta)
	{
		Optional<Pregunta> preguntafind=preguntaRepository.findById(id_pregunta);
		
		if(preguntafind.isPresent())
		{
			preguntaRepository.deleteById(id_pregunta);
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	public boolean addOpcionesToPregunta(Integer id_pregunta,Integer id_opcion)
	{
		
	  //1.recupera la opcion
	  Opcion opcion=opcionService.retieve(id_opcion);	
	  
	  //2.recuperar la pregunta
	  Optional <Pregunta> preguntaOp = preguntaRepository.findById(id_pregunta);
	  
	  //3.verificamos si la pregunta y opcion existen
	  if(!preguntaOp.isPresent() || opcion==null)
	  {
		  
		  return false;
	  }
	  //4.agrega el opcion a pregunta
	  Pregunta pregunta =preguntaOp.get();
	  
	  pregunta.addOpcion(opcion); 
	  //5.persiste el cambio
	  preguntaRepository.save(pregunta);
	  return true;
	}
	
}
