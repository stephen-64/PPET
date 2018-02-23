package steph.gui;

import java.io.File;
import javafx.stage.Stage;
import steph.components.Buttons;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * @author Stephen Pollett
 *
 */
@SuppressWarnings("restriction")
public class MediaPlay extends Stage {
	
	private String filename;
	@SuppressWarnings("unused")
	private byte mtype;
	private File sf;
	
	public MediaPlay(){
		
	}
	
	public void display(byte mtype){
		this.setMaxHeight(Screen.getPrimary().getVisualBounds().getHeight());
		this.setMaxWidth(Screen.getPrimary().getVisualBounds().getWidth());
		this.mtype = mtype;
		FileChooser fc = new FileChooser();
		
		if(mtype == Buttons.ANY){
		fc.setTitle("Please choose the Media to display");
		fc.getExtensionFilters().addAll(
				new ExtensionFilter("All Files", "*.*"),
				new ExtensionFilter("Image Files (*.png,*.jpeg,*.gif,*.jpeg)","*.png","*.jpg","*.gif","*.jpeg","*.bmp"),
				new ExtensionFilter("Video Files (*.wav,*.mp4)" , "*.wav","*.mp4"),
				new ExtensionFilter("Audio Files (*.mp3 ,*.m4a)" ,"*.mp3" ,"*.m4a")
				);
		}else if(mtype == Buttons.PIC){
			fc.setTitle("Please the Picture to display");
			fc.getExtensionFilters().addAll(
					new ExtensionFilter("Image Files (*.png,*.jpeg,*.gif,*.jpeg)","*.png","*.jpg","*.gif","*.jpeg","*.bmp"),
					new ExtensionFilter("All Files", "*.*")
					);
		}else if(mtype == Buttons.VID){
			fc.setTitle("Please choose the Video to display");
			fc.getExtensionFilters().addAll(
					new ExtensionFilter("Video Files (*.wav,*.mp4)" , "*.wav","*.mp4"),
					new ExtensionFilter("All Files", "*.*")
					);
		}else if(mtype == Buttons.AUD){
			fc.setTitle("Please choose the Audio to play");
			fc.getExtensionFilters().addAll(
					new ExtensionFilter("Audio Files (*.mp3 ,*.m4a)" ,"*.mp3" ,"*.m4a"),
					new ExtensionFilter("All Files", "*.*")
			);
		}
		sf = fc.showOpenDialog(this);
		
		if(sf != null){
			filename = sf.getName();
			
			if(isImage(filename)){
				
				try{
				ImageLoader i = new ImageLoader(sf);
				i.display();
				return;
				}catch(IllegalArgumentException e){
					e.printStackTrace();
				}
				
			}else if(isMovie(filename)){
				try{
				VideoLoader v = new VideoLoader(sf);
				v.play();
				return;
				}catch(IllegalArgumentException e){
					e.printStackTrace();
				}
			}else if(isAudio(filename)){
				try{
				AudioLoader a = new AudioLoader(sf);
				a.play();
				return;
				}catch(IllegalArgumentException e){
					e.printStackTrace();
				}
			}else{
				return;
			}
		}
	}
	
	private boolean isImage(String filename){
		if(filename.contains(".png") || filename.contains(".PNG") || filename.contains(".jpg") || filename.contains(".JPG") || filename.contains(".gif") || filename.contains(".GIF") || filename.contains(".jpeg") || filename.contains(".JPEG") || filename.contains(".bmp") || filename.contains(".BMP")){
			return true;
		}else{
			return false;
		}
	}
	
	private boolean isMovie(String filename){
		if(filename.contains(".wav") || filename.contains(".mp4") || filename.contains(".MP4") || filename.contains(".WAV")){
			return true;
		}else{
		return false;
		}
	}
	
	private boolean isAudio(String filename){
		if(filename.contains(".mp3") || filename.contains(".m4a") || filename.contains(".MP3") || filename.contains(".M4A")){
			return true;
		}else{
			return false;
		}
	}
}
