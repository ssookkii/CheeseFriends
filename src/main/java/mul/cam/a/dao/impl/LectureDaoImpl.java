package mul.cam.a.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mul.cam.a.dao.LectureDao;
import mul.cam.a.dto.LectureDto;

@Repository
public class LectureDaoImpl implements LectureDao{

	@Autowired
	SqlSession session;
	
	String ns = "Lecture.";
	
	@Override
	public List<LectureDto> lectureList(Integer seq) {
		return session.selectList(ns + "lectureList", seq);
	}
}
