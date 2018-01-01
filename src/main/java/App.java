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

		post("/addstylist",(request,response)->{
			Map <String,Object> model = new HashMap<String,Object>();
			String stylistname = request.queryParams("stylistname");
			Stylist newstylist = new Stylist(stylistname);
			model.put("template","templates/success.vtl");
			return new ModelAndView(model,layout);
		}, new VelocityTemplateEngine());

		get("/viewallstylists",(request,response)->{
			Map <String,Object> model = new HashMap<String,Object>();
			model.put("stylists",Stylist.all());
			model.put("template","templates/stylists.vtl");
			return new ModelAndView(model,layout);
		},new VelocityTemplateEngine());


		get("/stylists/:id",(request,response)->{
			Map <String,Object> model = new HashMap<String,Object>();
			Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
			model.put("stylist" ,stylist);
			model.put("template","templates/stylist-client-form.vtl");
			return new ModelAndView(model,layout);
		},new VelocityTemplateEngine());

		post("/client",(request,response)->{

			Map<String,Object> model = new HashMap<String,Object>();
			Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams("stylistId")));
			String clientname = request.queryParams("clientname");
			String clientgender =request.queryParams("clientgender");
			int clientAge=Integer.parseInt(request.queryParams("clientAge"));

			Client newClient = new Client(clientname, clientgender,clientAge);

			stylist.addClient(newClient);

			model.put("stylist",stylist);
			model.put("template","templates/stylist-client-added-success.vtl");

			return new ModelAndView(model,layout);
		}, new VelocityTemplateEngine());
	}
}