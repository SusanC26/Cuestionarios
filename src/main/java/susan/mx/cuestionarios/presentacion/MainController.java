package susan.mx.cuestionarios.presentacion;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


public class MainController {
	
	@GetMapping("/")
	public String index()
	{
			
		return "index";
	}


	@RequestMapping("/cuestionarios")
	@ResponseBody
	public String ejemplo() {
		
	
		return "pagina";
	}
}
