package mul.cam.a.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mul.cam.a.dao.MailDao;

@Service
@Transactional
public class MailService {

	@Autowired
	MailDao dao;
	
}
