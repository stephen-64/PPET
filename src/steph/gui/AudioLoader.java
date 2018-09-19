/*
AudioLoader.java
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
import java.net.MalformedURLException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.*;
//import javafx.scene.media.MediaView;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;
//import javafx.stage.Screen;
import javafx.stage.*;

/**
 * @author Stephen Pollett
 *
 */
@SuppressWarnings("restriction")
public class AudioLoader extends Stage{
	
	private File imagedata;
	private BorderPane bp;
	private Media m;
	private MediaPlayer mp;
	//private MediaView mv;
	private String temp = null;
	private HBox mbar;
	private Text t;
	
	/**
	 * 
	 */
	public AudioLoader(File imagedata) throws IllegalArgumentException{
		this.imagedata = imagedata;
		this.bp =new BorderPane();
		try {
			temp = imagedata.toURI().toURL().toString();
			System.out.println(temp);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public void play(){
		
	this.m = new Media(temp);
	
	this.mp = new MediaPlayer(m);
	mp.setAutoPlay(true);
	
	t = new Text(10,50, imagedata.getName());
	t.setFont(new Font(20));
	bp.setCenter(t);
	
	
	//this.mv = new MediaView(mp);
	//bp.setCenter(mv);
	
	mbar = new HBox();
	mbar.setAlignment(Pos.CENTER);
	BorderPane.setAlignment(mbar, Pos.CENTER);
	Button play = new Button();
	play.setText("||");
	Button forward = new Button();
	forward.setText(">>");
	Button back = new Button();
	back.setText("<<");
	Button stop = new Button();
	stop.setText("#");
	mbar.getChildren().add(back);
	mbar.getChildren().add(stop);
	mbar.getChildren().add(play);
	mbar.getChildren().add(forward);
	bp.setBottom(mbar);
	
	play.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent e) {
				Status stat = mp.getStatus();
			
				if(stat == Status.UNKNOWN || stat == Status.HALTED){
					return;
				}
			
				if(stat==Status.PAUSED || stat == Status.READY || stat==Status.STOPPED){
					if(stat==Status.STOPPED){
						play.setText("||");
					}
					mp.play();
					play.setText("||");
				}else{
					mp.pause();
					play.setText("->");
				}
			}	
		});
	
	forward.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent e) {
			Status stat = mp.getStatus();
			if(stat != Status.STOPPED){
				mp.seek(mp.getCurrentTime().add(Duration.seconds(15)));
			}
		}	
	});
	
	back.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent e) {
			Status stat = mp.getStatus();
			if(stat != Status.STOPPED){
			mp.seek(mp.getCurrentTime().subtract(Duration.seconds(15)));
			}
		}	
	});
	
	stop.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent e) {
			mp.stop();
			play.setText("->");
		}	
	});
	
	mp.setOnEndOfMedia( new Runnable(){
		@Override
		public void run() {
			mp.stop();
			play.setText("->");
			close();
		}
		
	});
	/*
	double h = (Screen.getPrimary().getVisualBounds().getHeight() / 2);
	double w = (Screen.getPrimary().getVisualBounds().getWidth() / 2);
	this.mv.setPreserveRatio(true);
	this.mv.setFitHeight((h-1));
	this.mv.setFitWidth((w-1));
	this.bp.setMaxHeight(h);
	this.bp.setMaxWidth(w);
	this.bp.setPrefHeight(h);
	this.bp.setPrefWidth(w);
	this.bp.setStyle("-fx-background-color: black");
	*/
	this.setMaxHeight(Screen.getPrimary().getVisualBounds().getHeight());
	this.setMaxWidth(Screen.getPrimary().getVisualBounds().getWidth());
	this.setTitle(imagedata.getName());
	bp.setPrefWidth(200);
	this.setScene(new Scene(bp));
	this.sizeToScene();
	this.setResizable(true);
	this.setX(0);
	this.setY(0);
	try{
		Image i = new Image(VideoLoader.class.getResourceAsStream("/PPET_Assets/Music2.png"));
		this.getIcons().add(i);
		}catch(NullPointerException e){
			e.printStackTrace();
		}
	this.show();
	
	
	this.setOnCloseRequest(new EventHandler<WindowEvent>() {
		@Override
		public void handle(WindowEvent event) {
			mp.stop();
		}
	});
	
	}
}