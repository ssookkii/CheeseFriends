package mul.cam.a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import mul.cam.a.dto.GradeDto;
import mul.cam.a.dto.SubjectDto;

@Mapper
@Repository
public interface GradeManageDao {
	
	// 교사담당과목 리스트
	List<SubjectDto> subTeacherlist(String teacher);
	
	// 과목 학생 성적리스트
	List<GradeDto> subStudentList(SubjectDto dto);
	
	// 과목 학생 성적입력
	int gradeAdd(List<GradeDto> dto);
}
