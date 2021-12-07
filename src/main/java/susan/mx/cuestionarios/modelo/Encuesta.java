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
//import org.hibernate.annotations.Table;

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
@Table(name="encuesta")
public class Encuesta {
	@Id
	@GeneratedValue
	@ApiModelProperty(notes="ID de la encuesta", required=true)
	private Integer id_encuesta;
	
	public Integer getId_encuesta() {
		return id_encuesta;
	}

	@ApiModelProperty(notes="Nombre de la encuesta", required=true)
	public void setId_encuesta(Integer id_encuesta) {
		this.id_encuesta = id_encuesta;
	}

	public String getNombre_encuesta() {
		return nombre_encuesta;
	}

	public void setNombre_encuesta(String nombre_encuesta) {
		this.nombre_encuesta = nombre_encuesta;
	}


	@ApiModelProperty(notes="nombre de la encuesta, required=false")
	private String nombre_encuesta;
	
	@Builder.Default
	@OneToMany(targetEntity=Pregunta.class, fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<Pregunta> preguntas=new ArrayList<>();
	
	
	public List<Pregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}

	public boolean addPregunta(Pregunta pregunta)
	{
		return preguntas.add(pregunta);
	}

	
	
	
	
	

}
