package mul.cam.a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import mul.cam.a.service.MailService;

@RestController
public class MailController {

	@Autowired
	MailService service;
	
	
}
