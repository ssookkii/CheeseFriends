package mul.cam.a.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mul.cam.a.dao.LectureDao;
import mul.cam.a.dto.LectureDto;
import mul.cam.a.service.LectureService;

@Service
public class LectureServiceImpl implements LectureService{

	@Autowired
	LectureDao dao;
	
	@Override
	public List<LectureDto> lectureList(Integer seq){
		return dao.lectureList(seq);
	}
}
