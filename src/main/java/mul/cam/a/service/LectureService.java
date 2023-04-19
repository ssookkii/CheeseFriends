package mul.cam.a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mul.cam.a.dao.LectureDao;
import mul.cam.a.dto.LectureDto;

@Service
@Transactional
public class LectureService {
	
	@Autowired
	LectureDao dao;
	
	public List<LectureDto> lectureList(Integer seq){
		return dao.lectureList(seq);

	}
}
