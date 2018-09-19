/*
MediaPlay.java
BSD 3-Clause License

Copyright (c) 2018, Stephen Pollett
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

* Redistributions of source code must retain the above copyright notice, this
  list of conditions and the following disclaimer.

* Redistributions in binary form must reproduce the above copyright notice,
  this list of conditions and the following disclaimer in the documentation
  and/or other materials provided with the distribution.

* Neither the name of the copyright holder nor the names of its
  contributors may be used to endorse or promote products derived from
  this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
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
