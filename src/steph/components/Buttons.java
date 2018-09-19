/*
Buttons.java
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
package steph.components;

import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import steph.gui.MediaPlay;
import steph.gui.WebLoader;

/**
 * 
 * This class defines all the buttons used within the application. It will also create an 
 * object of the appropriate class based on which button was pressed.
 * 
 * @author Stephen Pollett 
 * @version 1.0
 *
 */
@SuppressWarnings("restriction")
public class Buttons extends BorderPane implements EventHandler<ActionEvent>{
	
	private Button b1 = new Button();
	private Button b2 = new Button();
	private Button b3 = new Button();
	private Button b4 = new Button();
	private Button b5 = new Button();
	private Button b6 = new Button();
	private UserText ut = new UserText();
	private HBox mb;
	final public static byte ANY = 0;
	final public static byte PIC = 1;
	final public static byte VID = 2;
	final public static byte AUD = 3;
	final public static byte WEB = 4;
	
	/**
	 * Declares and sets the start conditions for every button used within the application.
	 * The buttons are added to a HBox which is then set to the center of the BorderPane which the class extends
	 */
	public Buttons(){
		b1.setText("Any Media");
		b1.setOnAction(this);
		b2.setText("Picture/Image");
		b2.setOnAction(this);
		b3.setText("Video/Movie");
		b3.setOnAction(this);
		b4.setText("Audio/Music");
		b4.setOnAction(this);
		b5.setText("Web");
		b5.setOnAction(this);
		//For later implementation
		//b6.setText("Make an audio playlist");
		//b6.setOnAction(this);
		mb = new HBox();
		mb.setAlignment(Pos.CENTER);
		Buttons.setAlignment(mb, Pos.CENTER);
		mb.getChildren().add(b1);
		mb.getChildren().add(b2);
		mb.getChildren().add(b3);
		mb.getChildren().add(b4);
		//mb.getChildren().add(b6);
		mb.getChildren().add(b5);
		double h = (Screen.getPrimary().getVisualBounds().getHeight() / 2);
		double w = (Screen.getPrimary().getVisualBounds().getWidth() / 2);
		this.setPrefHeight(h);
		this.setPrefWidth(w);
		this.setMaxHeight(Screen.getPrimary().getVisualBounds().getHeight());
		this.setMaxWidth(Screen.getPrimary().getVisualBounds().getWidth());
		this.setCenter(mb);
		this.setBottom(ut);
	}
	
	/**
	 * Will enable or disable access to the Buttons based on the state of the
	 * parameter. If state=true then the buttons will not be usable if state=false 
	 * then access to the buttons is allowed 
	 *  
	 * @param state Enables or disables the buttons based on whether true or false is passed
	 */
	private void disableB(boolean state){
		b1.setDisable(state);
		b2.setDisable(state);
		b3.setDisable(state);
		b4.setDisable(state);
		b5.setDisable(state);
	}

	/**
	 * Implementation of the overridden Event Handler. It checks to see which button was pressed and will 
	 * create an object of the desired class based on the user input. 
	 * 
	 */
	@Override
	public void handle(ActionEvent e) {
		
		if(e.getSource() == b1){
			disableB(true);
			MediaPlay m = new MediaPlay();
			m.display(ANY);
			disableB(false);
		}
		
		if(e.getSource() == b2){
			disableB(true);
			MediaPlay m = new MediaPlay();
			m.display(PIC);
			disableB(false);
		}
		
		if(e.getSource() == b3){
			disableB(true);
			MediaPlay m = new MediaPlay();
			m.display(VID);
			disableB(false);
			
		}
		
		if(e.getSource() == b4){
			disableB(true);
			MediaPlay m = new MediaPlay();
			m.display(AUD);
			disableB(false);
		}
		
		if(e.getSource() == b5){
			disableB(true);
			WebLoader w = new WebLoader();
			w.display();
			disableB(false);
		}
		
		if(e.getSource() == b6){
			disableB(true);
			
			disableB(false);
		}
		
	}

}
