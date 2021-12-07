package susan.mx.cuestionarios.negocio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import susan.mx.cuestionarios.modelo.Opcion;
import susan.mx.cuestionarios.modelo.Pregunta;
import susan.mx.cuestionarios.datos.OpcionRepository;
import susan.mx.cuestionarios.datos.PreguntaRepository;

@Service
@Slf4j
public class OpcionService {
	@Autowired
	private OpcionRepository opcionRepository;
	@Autowired
	private PreguntaRepository preguntaRepository;
	
	
	public Opcion create(Opcion newopcion)
	{
	  Opcion returnOpcion=opcionRepository.save(newopcion);
	  
	  return returnOpcion;
		
	}
	
	
	public Optional<Opcion> retrievebyId(Integer id_pregunta,Integer id_opcion )
	{
		 Optional<Opcion>  opcionfind =opcionRepository.findById(id_opcion);
		 Optional<Pregunta> opcionfindP =preguntaRepository.findById(id_pregunta);
		 
		 if(opcionfind.isPresent() && opcionfindP.isPresent()) {
			 
			 
			 return opcionRepository.findById(id_opcion);
		 }
		 else
		 {
			 return null;
		 }
		
	}
	
	
	
	public Iterable<Opcion> retrieveAll()
	{
		return opcionRepository.findAll();
	}
	
	public Opcion updateopcion(Integer id_pregunta,Integer id_opcion, Opcion actualizaopcion)
	{
		 Optional<Pregunta>  preguntafind =preguntaRepository.findById(id_pregunta);
		 Optional<Opcion>  opcionfind =opcionRepository.findById(id_opcion);
		if(preguntafind.isPresent() && opcionfind.isPresent())
		{
			Opcion option = new Opcion();
			
			option.setId_opcion(actualizaopcion.getId_opcion());
			option.setId_pregunta(actualizaopcion.getId_pregunta());
			option.setNombre_opcion(actualizaopcion.getNombre_opcion());
			
			
		    
		    Opcion actualizadaopcion= opcionRepository.save(actualizaopcion);
		
		
		return actualizadaopcion;
		}
		else
		{
			return null;
		}
	}
	
	
	
	public Opcion retieve(Integer id_opcion)
	{
	  
		Optional<Opcion> opcionfind =opcionRepository.findById(id_opcion);
		
		if(opcionfind.isPresent())
		{
			 return opcionfind.get();
		}else
			return null;
	}
	
	
	
	public boolean delate(Integer id_opcion)
	{
		Optional<Opcion> opcionfind=opcionRepository.findById(id_opcion);
		
		if(opcionfind.isPresent())
		{
			opcionRepository.deleteById(id_opcion);
			return true;
		}
		else
		{
			return false;
		}
		
	}
	

}
