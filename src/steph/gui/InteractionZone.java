package steph.gui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import steph.components.Buttons;
import javafx.scene.Scene;
import javafx.scene.image.Image;


/**
 * 
 * @author Stephen Pollett
 * @version 1.0
 *
 */
@SuppressWarnings("restriction")
public class InteractionZone extends Application {

	/**
	 * 
	 * 
	 */
	@Override
	public void start(Stage stage) throws Exception {
		Buttons b = new Buttons();
		Scene scene = new Scene(b);
		stage.setMaxHeight(Screen.getPrimary().getVisualBounds().getHeight());
		stage.setMaxWidth(Screen.getPrimary().getVisualBounds().getWidth());
		stage.setScene(scene);
		stage.sizeToScene();
		stage.setTitle("PPET");
		try{
			Image i = new Image(VideoLoader.class.getResourceAsStream("/PPET_Assets/PPET.png"));
			stage.getIcons().add(i);
			}catch(NullPointerException e){
				e.printStackTrace();
			}
		stage.show();
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				System.exit(0);
			}
		});
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}


}
