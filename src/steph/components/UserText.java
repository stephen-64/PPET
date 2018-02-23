package steph.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

/**
 * @author Stephen Pollett
 * @version 1.0
 *
 */
@SuppressWarnings("restriction")
public class UserText extends TextField implements EventHandler<ActionEvent>{
	
	private String prompt;
	private String input;
	
	public UserText(){
		prompt = "Input";
		this.setPromptText(prompt);
		this.setOnAction(this);
	}
	
	public UserText(String prompt){
		this.prompt = prompt;
		this.setPromptText(prompt);
		this.setOnAction(this);
	}

	@Override
	public void handle(ActionEvent e) {
		input = this.getText();
		this.clear();
	}

}
