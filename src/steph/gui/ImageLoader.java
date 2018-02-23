package steph.gui;

import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.File;
import java.net.MalformedURLException;

import javafx.scene.image.*;
import javafx.scene.layout.BorderPane;

/**
 * @author Stephen Pollett
 *
 */
@SuppressWarnings("restriction")
public class ImageLoader extends Stage {
	
	File imagedata;
	Image image;
	BorderPane bp;
	ImageView iv;
	
	public ImageLoader(File imagedata)throws IllegalArgumentException{
		this.imagedata = imagedata;
		String temp = null;
		try {
			temp = imagedata.toURI().toURL().toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		System.out.println(temp);
		this.image = new Image((temp));
		this.iv = new ImageView(this.image);
		this.bp = new BorderPane();
	}
	
	public void display(){
		double h = (Screen.getPrimary().getVisualBounds().getHeight());
		double w = (Screen.getPrimary().getVisualBounds().getWidth());
		this.bp.setMaxHeight(h);
		this.bp.setMaxWidth(w);
		this.bp.setStyle("-fx-background-color: black");
		this.iv.setPreserveRatio(true);
		
		if(image.getHeight() > h){
		this.iv.setFitHeight((h));
		}
		
		if(image.getWidth() > w){
		this.iv.setFitWidth((w));
		}
		this.bp.setCenter(iv);
		this.setMaxHeight(h);
		this.setMaxWidth(w);
		this.setScene(new Scene(bp));
		this.setTitle(this.imagedata.getName());
		this.sizeToScene();
		this.setResizable(false);
		try{
			Image i = new Image(VideoLoader.class.getResourceAsStream("/PPET_Assets/Photo.png"));
			this.getIcons().add(i);
			}catch(NullPointerException e){
				e.printStackTrace();
			}
		this.show();
	}
	
	
}
