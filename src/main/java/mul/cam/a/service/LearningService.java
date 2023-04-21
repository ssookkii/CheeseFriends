package mul.cam.a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mul.cam.a.dao.LearningDao;
import mul.cam.a.dto.LearningDto;
import mul.cam.a.dto.LectureParam;

@Service
@Transactional
public class LearningService {

	@Autowired
	LearningDao dao;
	
	public List<LearningDto> learningList(LectureParam param){
		return dao.learningList(param);
	}
	
	public boolean writeLearning(LearningDto bbs) {
		int n = dao.writeLearning(bbs);
		return n>0? true:false;
	}
	
	public LearningDto getLearning(Integer seq) {
		return dao.getLearning(seq);
	}
}
