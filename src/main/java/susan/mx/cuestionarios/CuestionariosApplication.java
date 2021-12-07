package susan.mx.cuestionarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import susan.mx.cuestionarios.servicios.EncuestaController;

@SpringBootApplication

//@ComponentScan(basePackageClasses = EncuestaController.class)
public class CuestionariosApplication {
	
	

	public static void main(String[] args) {
		SpringApplication.run(CuestionariosApplication.class, args);
	}

}
