package mul.cam.a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mul.cam.a.dao.AttManageDao;
import mul.cam.a.dto.CFR_Attendance;

@Service
public class AttManageService {
    
	@Autowired
	private AttManageDao attManageDao;

    public List<CFR_Attendance> getAttendanceList(String studentID, String subCode, String eduCode) {
        return attManageDao.getAttendanceList(studentID, subCode, eduCode);
    }
}

