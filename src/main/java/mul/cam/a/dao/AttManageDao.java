package mul.cam.a.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mul.cam.a.dto.CFR_Attendance;
import mul.cam.a.dto.SubjectDto;

@Repository
public class AttManageDao {
	
	 private final String NS = "AttManage.";

    @Autowired
    private SqlSession sqlSession;
	    
    public List<CFR_Attendance> getAttendanceList(String studentID, String subCode, String eduCode) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("studentID", studentID);
        paramMap.put("subCode", subCode);
        paramMap.put("eduCode", eduCode);

        return sqlSession.selectList(NS + "getAttendanceList", paramMap);
    }

    public List<SubjectDto> getSubjectsByUserId(String userId) {
        return sqlSession.selectList(NS+ "getSubjectsByUserId", userId);
    }

    public List<CFR_Attendance> getAttendanceBySubjectCode(String subCode) {
    	  return sqlSession.selectList(NS+ "getAttendanceBySubjectCode", subCode);
      }

    public int updateAttendanceStatus(String attendanceID, String status) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("attendanceID", attendanceID);
        parameters.put("status", status);
        return sqlSession.update(NS+ "updateAttendanceStatus", parameters);
      }
    
    public int updateAttendancecomment(String attendanceID, String comment) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("attendanceID", attendanceID);
        parameters.put("comment", comment);
        return sqlSession.update(NS+"updateAttendancecomment", parameters);
      }

}
