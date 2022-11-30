import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class UFC_Scraper {
	
	Scanner scan;
	public UFC_Scraper(Scanner scan) {
		this.scan = scan;
	}
	public UFC_Scraper() {
		this.scan = new Scanner(System.in);
	}

	public static void main(String[] args) throws IOException {
		System.out.println("Fighters, upcoming events, or old events?");
		Scanner sc = new Scanner(System.in);
		String input;
		Document doc;
		while(true) {
			input = sc.nextLine().toLowerCase();
			if (input.contains("fight")){
				doc = Jsoup.connect("http://ufcstats.com/statistics/fighters?char=a&page=all").timeout(6000).get();
				System.out.println("Displaying all fighter Data.");
				break;
				}
			else if (input.contains("old")) {
				doc = Jsoup.connect("http://ufcstats.com/statistics/events/completed?page=all").timeout(6000).get();
				System.out.println("Displaying all previous events.");
				break;
			}
			else if (input.contains("up")) {
				doc = Jsoup.connect("http://ufcstats.com/statistics/events/upcoming?page=all").timeout(6000).get();
				System.out.println("Displaying all upcoming events.");
				break;
			}
			else {
				System.out.println("Please enter a valid input.");
			}
		}
		
		
		//doc = Jsoup.connect("http://ufcstats.com/statistics/events/completed?page=all").timeout(6000).get();
		Elements body = doc.select("tr");
		System.out.println(body.select("tr.b-statistics__table-row").size() + " items.");
		String str = "";
		for (Element e: body.select("tr")) {
			str = e.select("tr.b-statistics__table-row").text();
			System.out.println(str);
		}
		
		
	}

	
	public void List_Stats(Scanner scan) throws IOException {
		System.out.println("Fighters, upcoming events, or old events?");
		Scanner sc = scan;
		String input;
		Document doc;
		while(true) {
			input = sc.nextLine().toLowerCase();
			if (input.contains("fight")){
				doc = Jsoup.connect("http://ufcstats.com/statistics/fighters?char=a&page=all").timeout(6000).get();
				System.out.println("Displaying all fighter Data.");
				break;
				}
			else if (input.contains("old")) {
				doc = Jsoup.connect("http://ufcstats.com/statistics/events/completed?page=all").timeout(6000).get();
				System.out.println("Displaying all previous events.");
				break;
			}
			else if (input.contains("up")) {
				doc = Jsoup.connect("http://ufcstats.com/statistics/events/upcoming?page=all").timeout(6000).get();
				System.out.println("Displaying all upcoming events.");
				break;
			}
			else {
				System.out.println("Please enter a valid input.");
			}
		}
		Elements body = doc.select("tr");
		System.out.println(body.select("tr.b-statistics__table-row").size() + " items.");
		String str = "";
		for (Element e: body.select("tr")) {
			str = e.select("tr.b-statistics__table-row").text();
			System.out.println(str);
		}
	}
	
	public void List_Stats() throws IOException{
		this.List_Stats(this.scan);
	}
	
	public String Stats_to_String(String input) throws IOException {
		Document doc;
		input = input.toLowerCase();
		String url;
		String val = "";
		if (input.contains("fight")){
			url ="http://ufcstats.com/statistics/fighters?char=a&page=all";
		}
		else if (input.contains("old")) {
			url = "http://ufcstats.com/statistics/events/completed?page=all";
		}
		else if (input.contains("up")) {
			url = "http://ufcstats.com/statistics/events/upcoming?page=all";
		}
		else {
			return "Invalid input.";
		}
		doc = Jsoup.connect(url).timeout(6000).get();
		Elements body = doc.select("tr");
		val += (body.select("tr.b-statistics__table-row").size() + " items.") + "/n";
		for (Element e: body.select("tr")) {
			val += e.select("tr.b-statistics__table-row").text();
		}
		return val;
	}
	
	public List Stats_to_ArrayList(String input) throws IOException {
		Document doc;
		input = input.toLowerCase();
		String url;
		String val = "";
		String add = "";
		ArrayList<String> list = new ArrayList<String>();
		if (input.contains("fight")){
			url ="http://ufcstats.com/statistics/fighters?char=a&page=all";
		}
		else if (input.contains("old")) {
			url = "http://ufcstats.com/statistics/events/completed?page=all";
		}
		else if (input.contains("up")) {
			url = "http://ufcstats.com/statistics/events/upcoming?page=all";
		}
		else {
			//placeholder value
			url = "http://ufcstats.com/statistics/events/completed?page=all";
		}
		doc = Jsoup.connect(url).timeout(6000).get();
		Elements body = doc.select("tr");
		//val += (body.select("tr.b-statistics__table-row").size() + " items.") + "/n";
		for (Element e: body.select("tr")) {
			add = e.select("tr.b-statistics__table-row").text();
			val += add;
			list.add(add);
		}
		return list;
	}
	
	public String Fighter_Stats_Basic(String name) throws IOException {
		ArrayList fighter_list = (ArrayList) Stats_to_ArrayList("fight");
		String fighter = "X";
		for(int i = 0; i < fighter_list.size(); i++) {
			if (( fighter_list.get(i)).toString().contains(name)) {
				fighter = fighter_list.get(i).toString();
			}
		}
		return fighter;
	}
}
