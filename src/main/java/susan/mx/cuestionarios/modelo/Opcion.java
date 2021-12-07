package susan.mx.cuestionarios.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;

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
@Table(name="opcion")


public class Opcion {
	
	@Id
	@GeneratedValue
	@ApiModelProperty(notes="ID de la pregunta", required=true)
	private Integer id_opcion;
	
	public Integer getId_opcion() {
		return id_opcion;
	}

	public void setId_opcion(Integer id_opcion) {
		this.id_opcion = id_opcion;
	}

	public Integer getId_pregunta() {
		return id_pregunta;
	}

	public void setId_pregunta(Integer id_pregunta) {
		this.id_pregunta = id_pregunta;
	}

	public String getNombre_opcion() {
		return nombre_opcion;
	}

	public void setNombre_opcion(String nombre_opcion) {
		this.nombre_opcion = nombre_opcion;
	}

	private Integer id_pregunta;
	
	private String nombre_opcion;
	
	
	

}
