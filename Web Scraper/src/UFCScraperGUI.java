import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.*;

public class UFCScraperGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("UFC Scraper");
        UFC_Scraper sc = new UFC_Scraper();

        Label title = new Label("UFC Web Scraper");
        
        // Create buttons for different options
        Button fighterDataButton = new Button("All Fighter Data");
        Button upcomingEventsButton = new Button("All Upcoming Events");
        Button oldEventsButton = new Button("All Old Events");
        Button exitBtn = new Button("Exit");
        Button enter = new Button("Enter");
        TextArea outTxt = new TextArea();
        outTxt.setWrapText(true);
        outTxt.setEditable(false);
        
        TextField inTxt = new TextField();
        
        Label searchLa = new Label("Search");
        
        ToggleGroup toggleGroup = new ToggleGroup(); // Create a ToggleGroup

        RadioButton fighterTog = new RadioButton("Fighters");
        RadioButton upTog = new RadioButton("Upcoming Events");
        RadioButton oldTog = new RadioButton("Old Events");

        // Set the ToggleGroup for each radio button
        fighterTog.setToggleGroup(toggleGroup);
        upTog.setToggleGroup(toggleGroup);
        oldTog.setToggleGroup(toggleGroup);

        // Define actions for the buttons
        fighterDataButton.setOnAction(e -> {
        	try {
        		ArrayList ls = (ArrayList) sc.Fighter_Stats_Search_List("");
				outTxt.clear();
				for (int i = 0; i < ls.size(); i++) {
					outTxt.appendText((String) ls.get(i) + '\n');
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });

        upcomingEventsButton.setOnAction(e -> {
        	try {
        		ArrayList upl = sc.Upcoming_Search_List("");
        		outTxt.clear();
				for (int i = 0; i < upl.size(); i++) {
					outTxt.appendText((String) upl.get(i) + '\n');
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });

        oldEventsButton.setOnAction(e -> {
        	try {
        		ArrayList ol = sc.Old_Search_List("");
        		outTxt.clear();
				for (int i = 0; i < ol.size(); i++) {
					outTxt.appendText((String) ol.get(i) + '\n');
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
        });
        
        enter.setOnAction(e -> {
        	try {
				ArrayList ls = (ArrayList) sc.Fighter_Stats_Search_List(inTxt.getText());
				if (fighterTog.isSelected()) {
					outTxt.clear();
					for (int i = 0; i < ls.size(); i++) {
						outTxt.appendText((String) ls.get(i) + '\n');
					}
				}
				if (oldTog.isSelected()) {
					outTxt.clear();
					String[] oldEv = sc.Past_Event_Lookup(inTxt.getText());
					for (int i = 0; i < oldEv.length; i++) {
						outTxt.appendText(oldEv[i] + '\n');
					}
				}
				if (upTog.isSelected()) {
					outTxt.clear();
					String[] upEv = sc.Upcoming_Event_Lookup(inTxt.getText());
					for (int i = 0; i < upEv.length; i++) {
						outTxt.appendText(upEv[i] + '\n');
					}
				}
        	} catch (IOException e1) {
        		System.out.print("Error");
        		e1.printStackTrace();
        	}
        });
        
        exitBtn.setOnAction(e -> {
        	primaryStage.close();
        });

        // Create a layout to arrange the buttons
        HBox buttonRow = new HBox(5);
        buttonRow.getChildren().addAll(fighterDataButton,upcomingEventsButton,oldEventsButton);
        buttonRow.setPadding(new Insets(20));
        VBox layout = new VBox(5); // 5px spacing
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(title,fighterTog,oldTog,upTog, buttonRow, searchLa, inTxt, enter, outTxt, exitBtn );

        // Create a scene and set it on the stage
        Scene scene = new Scene(layout, 700, 600);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}