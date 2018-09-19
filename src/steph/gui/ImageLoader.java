/*
ImageLoader.java
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
