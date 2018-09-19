/*
WebLoader.java
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

import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.web.*;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

/**
 * @author Stephen Pollett
 *
 */
@SuppressWarnings("restriction")
public class WebLoader extends Stage {
	
	private String site;
	private WebView wv;
	private WebEngine we;
	private Text t;
	private BorderPane mbp;
	private HBox obar;
	private TextField tf;
	private WebHistory wh;
	
	public WebLoader(){
		this.site = "https://www.google.ca";
		t = new Text(10,50, "The website failed to load");
		t.setFont(new Font(20));
		mbp = new BorderPane();
		tf = new TextField();
	}
	
	public WebLoader(String site){
		this.site = site;
		t = new Text(10,50, "The website failed to load");
		t.setFont(new Font(20));
		mbp = new BorderPane();
		tf = new TextField();
	}
	
	public void display(){
		wv = new WebView();
		wv.setContextMenuEnabled(true);
		we = wv.getEngine();
		wh = we.getHistory();
		we.load(site);
		we.getLoadWorker().stateProperty().addListener(
				new ChangeListener<State>(){

					@SuppressWarnings("rawtypes")
					@Override
					public void changed(ObservableValue old, State olds, State news) {
						
						if(news == State.SUCCEEDED){
							tf.setText(we.getLocation());
							if(mbp.getCenter() != wv){
							mbp.setCenter(wv);
							}
						}
						
						if(news == State.FAILED){
							if(mbp.getCenter() != t){
							mbp.setCenter(t);
							}
						}
						
					}
					
				});
		
		mbp.setCenter(wv);
		
		obar = new HBox();
		obar.setAlignment(Pos.TOP_LEFT);
		BorderPane.setAlignment(obar, Pos.TOP_LEFT);
		
		Button refresh = new Button();
		refresh.setText("(|)");
		Button forward = new Button();
		forward.setText("->");
		Button back = new Button();
		back.setText("<-");
		Button home = new Button();
		home.setText("|^|");
		
		obar.getChildren().add(back);
		obar.getChildren().add(refresh);
		obar.getChildren().add(home);
		obar.getChildren().add(forward);
		obar.getChildren().add(tf);
		
		refresh.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				we.reload();
				}	
			});
		
		forward.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				try{
				wh.go(1);
				}catch(IndexOutOfBoundsException i){
					return;
				}
			}	
		});
		
		back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e){
				try{
				wh.go(-1);
				}catch(IndexOutOfBoundsException i){
					return;
				}
			}	
		});
		
		home.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e){
				we.load("https://www.google.ca");
			}	
		});
		
		tf.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e){
				System.out.println(tf.getText());
				we.load(tf.getText());
			}	
		});
		
		mbp.setTop(obar);

		this.setScene(new Scene(mbp));
		this.setTitle("Web Browser");
		this.sizeToScene();
		try{
			Image i = new Image(VideoLoader.class.getResourceAsStream("/PPET_Assets/Web.png"));
			this.getIcons().add(i);
			}catch(NullPointerException e){
				e.printStackTrace();
			}
		
		this.show();
		
		this.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				we.load("https://www.google.ca");
			}
		});
		
	}
	
}
