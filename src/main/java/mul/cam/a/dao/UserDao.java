package mul.cam.a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import mul.cam.a.dto.TestEduDto;
import mul.cam.a.dto.UserDto;

@Mapper
@Repository
public interface UserDao {
	
	String idcheck(String id);
	
	List<TestEduDto> subjectlist(String edu_code);
	
	String eduname(String edu_code);
	String subname(String sub_code);
	
	int adduser(UserDto dto);
	
	int addusersubject(TestEduDto dto);

}
