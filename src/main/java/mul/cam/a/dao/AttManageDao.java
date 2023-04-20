package mul.cam.a.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mul.cam.a.dto.CFR_Attendance;

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


}
