package mul.cam.a.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import mul.cam.a.chatbot.Chatbot;
import mul.cam.a.chatbot.PapagoTranslator;

public class ChatbotController {
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping(value = "chatbot")
	public String chatBot(String msg) {
		System.out.println("NaverCloudController chatBot " +  new Date());
		
		String json = Chatbot.chatBot(msg);
		return json;
	}
	
}
