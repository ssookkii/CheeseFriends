package mul.cam.a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import mul.cam.a.dto.TestEduDto;

@Mapper
@Repository
public interface UserDao {
	
	String idcheck(String id);
	
	List<TestEduDto> subjectlist(String edu_code);

}
