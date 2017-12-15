import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App{
	public static void main(String[] args){
		staticFileLocation("/public");
		String layout = "templates/layout.vtl";
		System.out.println("we are ready to start");

		ProcessBuilder process = new ProcessBuilder();
		Integer port;
		if(process.environment().get("PORT")!=null){
			port=Integer.parseInt(process.environment().get("PORT"));
		}else{
			port=4567;
		}
		setPort(port);

		get("/",(request,response)->{
			Map<String,Object> model = new HashMap<String,Object>();
			model.put("template","templates/index.vtl");
			return new ModelAndView(model,layout);
		},new VelocityTemplateEngine());
		//end of request index.vtl
	}
}