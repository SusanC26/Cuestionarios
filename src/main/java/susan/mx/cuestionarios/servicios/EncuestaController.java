package susan.mx.cuestionarios.servicios;


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

import susan.mx.cuestionarios.modelo.Encuesta;
import susan.mx.cuestionarios.negocio.EncuestaService;

@RestController
@Slf4j
public class EncuestaController {
	
	@Autowired
	private EncuestaService encuestaService;
	
	@ApiOperation
	(//documentacion del api
	  value = "Crear un nueva encuesta",
	  notes="Permite crear una nueva encuesta con id unico"
	)	
	@PostMapping(path = "/encuestas", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> create(@RequestBody @Valid Encuesta nuevaEncuesta) { // Validaciones
				
		
		Encuesta encuesta = encuestaService.create(nuevaEncuesta);
		
		if(encuesta != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(encuesta);			
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no se puede crear encuesta");
		}
	

	}
	
	
	@ApiOperation
	(//documentacion del api
			  value = "Recupera todas las encuestas",
			  notes="Permite recuperar una lista de las encuestas que existen")
	@GetMapping(path="/encuestas",produces=MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<?> retriveAll(){
		
		Iterable <Encuesta>lista_encuestas=encuestaService.retrieveAll();
		return ResponseEntity.status(HttpStatus.OK).body(lista_encuestas);
		
	}
	
	@PutMapping(path="encuestas/{id_encuesta}",produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable("id_encuesta") Integer id_encuesta,@RequestBody Encuesta actualizaEncuesta){
    	
    	Encuesta encuesta=encuestaService.updateencuesta(id_encuesta, actualizaEncuesta);
    	if(encuesta!=null)
    	{
    		return ResponseEntity.status(HttpStatus.OK).body(encuesta);
    	}
    	else {
    		
    		return ResponseEntity.status(HttpStatus.CONFLICT).build();
    	}
    }
	
	
	@DeleteMapping(path="encuestas/{id_encuesta}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(@PathVariable("id_encuesta") Integer id_encuesta)
	{
		boolean encuestaeliminada=encuestaService.delate(id_encuesta);
		
		if(encuestaeliminada==true)
		{
			return ResponseEntity.status(HttpStatus.OK).build();
			
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no s epuede eliminar encuesta, no existe");
		}
		
			}
	
	//encuestas/id_encuesta/preguntas?id_pregunta=X
	@PostMapping(path = "/encuestas/{id_encuesta}/preguntas", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> addStudentToGroup(
			@PathVariable("id_encuesta") Integer id_encuesta,
			@RequestParam("id_pregunta") Integer id_pregunta) {
		
		boolean result = encuestaService.addPreguntaToEncuesta(id_encuesta, id_pregunta);
		
		if(result!=false) {
			return ResponseEntity.status(HttpStatus.CREATED).build(); 
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
		}
		
	
	}

}
