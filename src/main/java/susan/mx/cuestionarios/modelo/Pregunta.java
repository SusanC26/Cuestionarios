package susan.mx.cuestionarios.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity//indica que hay que persistir en BD
@EqualsAndHashCode
@Table(name="pregunta")
public class Pregunta {
	
	
	@Id
	@GeneratedValue
	@ApiModelProperty(notes="ID de la pregunta", required=true)
	private Integer id_pregunta;
	
	
	private Integer id_encuesta;
	
	private String nombre_pregunta;

	
	public Integer getId_pregunta() {
		return id_pregunta;
	}

	public void setId_pregunta(Integer id_pregunta) {
		this.id_pregunta = id_pregunta;
	}

	public Integer getId_encuesta() {
		return id_encuesta;
	}

	public void setId_encuesta(Integer id_encuesta) {
		this.id_encuesta = id_encuesta;
	}

	public String getNombre_pregunta() {
		return nombre_pregunta;
	}

	public void setNombre_pregunta(String nombre_pregunta) {
		this.nombre_pregunta = nombre_pregunta;
	}

	@Builder.Default
	@OneToMany(targetEntity=Opcion.class, fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<Opcion> opciones=new ArrayList<>();
	
	public List<Opcion> getOpciones() {
		return opciones;
	}

	public void setOpciones(List<Opcion> opciones) {
		this.opciones = opciones;
	}

	public boolean addOpcion(Opcion opcion)
	{
		return opciones.add(opcion);
	}
	

}
