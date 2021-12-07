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

import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import susan.mx.cuestionarios.modelo.Opcion;
import susan.mx.cuestionarios.negocio.OpcionService;

@RestController
@Slf4j
public class OpcionController {
	
	@Autowired
	private OpcionService opcionService;
	
	@PostMapping(path = "/opciones", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> create(@RequestBody @Valid Opcion nuevaopcion) { // Validaciones
				
		
		Opcion opcion = opcionService.create(nuevaopcion);
		
		if(opcion != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(opcion);			
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no se puede crear opcion");
		}
	

	}
	
	
	
	@GetMapping(path="/opciones/{id_pregunta}/{id_opcion}",produces=MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<?> retrievebyIde(@PathVariable("id_pregunta") Integer id_pregunta,@PathVariable ("id_opcion")Integer id_opcion){
		
		Optional<Opcion>lista_opcion=opcionService.retrievebyId(id_pregunta, id_opcion);
		
		if(lista_opcion==null)
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		else
		return ResponseEntity.status(HttpStatus.OK).body(lista_opcion);
		
	}
	
	
	@GetMapping(path="/opciones",produces=MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<?> retriveAll(){
		
		Iterable <Opcion>lista_opciones=opcionService.retrieveAll();
		return ResponseEntity.status(HttpStatus.OK).body(lista_opciones);
		
	}
	
	
	@PutMapping(path="opciones/{id_pregunta}/{id_opcion}",produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable("id_pregunta") Integer id_pregunta,@PathVariable("id_opcion") Integer id_opcion,@RequestBody Opcion actualizaopcion){
    	
    	Opcion opcion=opcionService.updateopcion(id_pregunta, id_opcion, actualizaopcion);
    	if(opcion!=null)
    	{
    		return ResponseEntity.status(HttpStatus.OK).body(opcion);
    	}
    	else {
    		
    		return ResponseEntity.status(HttpStatus.CONFLICT).build();
    	}
    }
	
	
	
	@DeleteMapping(path="opciones/{id_opcion}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(@PathVariable("id_opcion") Integer id_opcion)
	{
		boolean opcioneliminada=opcionService.delate(id_opcion);
		
		if(opcioneliminada==true)
		{
			return ResponseEntity.status(HttpStatus.OK).build();
			
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no se puede eliminar opcion, no existe");
		}
		
			}
	
	

}
