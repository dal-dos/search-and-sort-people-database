
public class Cruise12022 {
	private String CID;
	private int persons;
	private String startDate;
	private int days;
	private int ship;
	private String cabin;
	private double cabinAmt;
	private String options;
	
	
	public Cruise12022(String data) {
		String arr[] = data.split(",");
		CID = arr[0];
		persons = Integer.parseInt(arr[1]);
		startDate = arr[2];
		days = Integer.parseInt(arr[3]);
		ship = Integer.parseInt(arr[4]);
		cabin = arr[5];
		options = arr[6];		
	}
	
	public String getCID() {
		return CID;
	}
	
	public int getPersons() {
		return persons;
	}
	
	public String getStartDate() {
		return startDate;
	}
	
	public int getDays() {
		return days;
	}
	
	public String ShipName() {
		String name = "";
		switch(ship) {
		case 1:
			name = "Celebrity Dreams";
		break;
		case 2:
			name = "NCL Wonders";
		break;
		case 3:
			name = "HAL Infinity";
		break;
		default:
			name = "RCCL Princess";
		}
		return name;
	}
	
	public String Cabin() {
		String name = "";
		if(cabin.contentEquals("B")) {
			name = "Balcony";
		} else if(cabin.contentEquals("I")) {
			name = "Inside";
		}else {
			name = "Suite";
		}
		return name;
	}
	
	public double CabinAmt() {
		if(cabin.contentEquals("B")) {
			cabinAmt = 140;
		} else if(cabin.contentEquals("I")) {
			cabinAmt = 100;
		}else {
			cabinAmt = 210;
		}
		return cabinAmt*persons*days;
	}
	
	public String Options() {
		
		String temp = "";
		
		if(options.contains("S")) {
			temp = temp + "Spa " ;
		}
		if(options.contains("E")) {
			temp = temp + "Excursion " ;
			
		}
		if(options.contains("W")) {
			temp = temp + "WiFi " ;
		}
		if(options.contains("D")) {
			temp = temp + "Drink " ;
		}
		if(options.contains("N")) {
			temp = temp + "NA " ;
		}
		
		temp = temp.trim();
		temp = temp.replace(" ", " & ");
		
		return temp;
	}
	
	public double OptionsAmt() {
		double optionsAmt = 0;
		if(options.contains("S")) {
			optionsAmt += 100 * persons * days;
		}
		if(options.contains("E")) {
			optionsAmt += 80 * persons * days;
		}
		if(options.contains("W")) {
			optionsAmt += 50;
		}
		if(options.contains("D")) {
			optionsAmt += 200 * persons;
		}
		if(options.contains("N")) {
			optionsAmt += 0;
		}
		
		return optionsAmt;
	}
	
	public double TotalAmt() {
		return OptionsAmt() + CabinAmt();
	}
	
}
