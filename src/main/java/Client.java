import java.util.ArrayList;
import java.util.List;

public class Client{
	private String cName;
	private String cGender;
	private int cAge;
	private int  c_ID;
	private static List<Client> clients =new ArrayList<Client>();


	public Client(String name, String gender,int age){
		cName=name;
		cGender=gender;
		cAge=age;
		clients.add(this);
		c_ID=clients.size();
	}

	public String getName(){
		return cName;
	}

	public String getGender(){
		return cGender;
	}

	public int getAge(){
		return cAge;
	}

	public static List<Client> all(){
		return clients;
	}

	public static void clear(){
		clients.clear();
	}

	public void deleteClient(String name){

	}


}