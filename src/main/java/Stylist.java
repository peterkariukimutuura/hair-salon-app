import java.util.ArrayList;
import java.util.List;


public class Stylist{
	private String sName;
	private  int mId;
	private static List<Stylist> allStylist = new ArrayList<Stylist>();
	private List<Client> clients;


	public Stylist(String name){
		sName=name;
		allStylist.add(this);
		mId=allStylist.size();
		clients = new ArrayList<Client>();


	}

	public String getName(){
		return sName;
	}

	public static List<Stylist> all(){
		return allStylist;
	}

	public static void clear(){
		allStylist.clear();
	} 


	public int getId(){
		return mId;
	}

	public static Stylist find(int id){
		return allStylist.get(id-1);
	}

	public List<Client> getClients(){
		return clients;
	}

	public void addClient(Client client){
		clients.add(client);
	}


}