package mul.cam.a.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.apache.tomcat.util.http.fileupload.FileUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;
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
//	
//	public int getAllBbs(LectureParam param) {
//		return dao.get
//	}
//	
	
	public boolean writeLearning(LearningDto bbs) {
		int n = dao.writeLearning(bbs);
		return n>0? true:false;
	}
	
	public LearningDto getLearning(Integer seq) {
		return dao.getLearning(seq);
	}
}
