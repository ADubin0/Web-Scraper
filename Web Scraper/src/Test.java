import java.io.IOException;
import java.util.ArrayList;

public class Test {

	public static void main(String[] args) throws IOException {
		UFC_Scraper scrape = new UFC_Scraper();
		//scrape.List_Stats();
		String test = scrape.Stats_to_String("fighter");
		ArrayList list = (ArrayList) scrape.Stats_to_ArrayList("fighter");
		for (int i = 0; i < list.size(); i++) {
			//System.out.println(list.get(i));
			//if (i >= 6) {break;}
		}
		//test = scrape.Stats_to_ArrayList("old").toString();
		System.out.println(scrape.Fighter_Stats_Basic("Alex"));
	}

}
