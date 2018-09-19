/*
InteractionZone.java
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
