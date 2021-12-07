package susan.mx.cuestionarios.servicios;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import susan.mx.cuestionarios.modelo.Pregunta;
import susan.mx.cuestionarios.negocio.PreguntaService;


@RestController
@Slf4j
public class PreguntaController {
	
	@Autowired
	private PreguntaService preguntaService;
	
	@ApiOperation
	(//documentacion del api
	  value = "Crear un nueva pregunta",
	  notes="Permite crear una nueva pregunta con id unico"
	)	
	@PostMapping(path = "/preguntas", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> create(@RequestBody @Valid Pregunta nuevapregunta) { // Validaciones
				
		
		Pregunta pregunta = preguntaService.create(nuevapregunta);
		
		if(pregunta != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(pregunta);			
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no se puede crear pregunta");
		}
	

	}
	
	
	@ApiOperation
	(//documentacion del api
			  value = "Recupera todas las preguntas",
			  notes="Permite recuperar una lista de las preguntas que existen")
	@GetMapping(path="/preguntas/{id_encuesta}/{id_pregunta}",produces=MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<?> retrievebyIde(@PathVariable("id_encuesta") Integer id_encuesta,@PathVariable ("id_pregunta")Integer id_pregunta){
		
		Optional<Pregunta>lista_pregunta=preguntaService.retrievebyId(id_encuesta, id_pregunta);
		
		if(lista_pregunta==null)
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		else
		return ResponseEntity.status(HttpStatus.OK).body(lista_pregunta);
		
	}
	
	@GetMapping(path="/preguntas",produces=MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<?> retriveAll(){
		
		Iterable <Pregunta>lista_preguntas=preguntaService.retrieveAll();
		return ResponseEntity.status(HttpStatus.OK).body(lista_preguntas);
		
	}
	
	@PutMapping(path="preguntas/{id_encuesta}/{id_pregunta}",produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable("id_encuesta") Integer id_encuesta,@PathVariable("id_pregunta") Integer id_pregunta,@RequestBody Pregunta actualizapregunta){
    	
    	Pregunta pregunta=preguntaService.updatepregunta(id_encuesta,id_pregunta,actualizapregunta);
    	if(pregunta!=null)
    	{
    		return ResponseEntity.status(HttpStatus.OK).body(pregunta);
    	}
    	else {
    		
    		return ResponseEntity.status(HttpStatus.CONFLICT).build();
    	}
    }
	
	
	@DeleteMapping(path="preguntas/{id_pregunta}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(@PathVariable("id_pregunta") Integer id_pregunta)
	{
		boolean preguntaeliminada=preguntaService.delate(id_pregunta);
		
		if(preguntaeliminada==true)
		{
			return ResponseEntity.status(HttpStatus.OK).build();
			
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no se puede eliminar pregunta, no existe");
		}
		
			}

	//preguntas/id_pregunta/opciones?id_opcion=X
	
	@PostMapping(path = "/preguntas/{id_pregunta}/opciones", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> addStudentToGroup(
			@PathVariable("id_pregunta") Integer id_pregunta,
			@RequestParam("id_opcion") Integer id_opcion) {
		
		boolean result = preguntaService.addOpcionesToPregunta(id_pregunta, id_opcion);
		
		if(result!=false) {
			return ResponseEntity.status(HttpStatus.CREATED).build(); 
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
		}
		
	
	}

}
