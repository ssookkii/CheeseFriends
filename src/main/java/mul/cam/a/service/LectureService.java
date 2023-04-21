package mul.cam.a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mul.cam.a.dao.LectureDao;
import mul.cam.a.dto.LectureDto;
import mul.cam.a.dto.LectureParam;

@Service
@Transactional
public class LectureService {
	
	@Autowired
	LectureDao dao;
	
	public List<LectureDto> lectureList(LectureParam param){
		return dao.lectureList(param);

	}
	
	public boolean writeLecture(LectureDto bbs) {
		int n = dao.writeLecture(bbs);
		return n>0? true:false;
	}
	
	public List<LectureDto> learningList(LectureParam param) {
		return dao.learningList(param);
	}

}
