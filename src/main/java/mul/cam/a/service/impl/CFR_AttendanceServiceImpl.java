package mul.cam.a.service.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mul.cam.a.dao.CFR_AttendanceDao;
import mul.cam.a.dto.AttendanceSubject;
import mul.cam.a.dto.CFR_Attendance;
import mul.cam.a.service.CFR_AttendanceService;

@Service
public class CFR_AttendanceServiceImpl implements CFR_AttendanceService {

	@Autowired
	private CFR_AttendanceDao attendanceDao;

	@Override
	public CFR_Attendance getAttendanceById(int attendanceID) {
	    // attendanceID에 해당하는 출석 정보를 조회한다
	    return attendanceDao.getAttendanceById(attendanceID);
	}

	@Override
	public void addAttendance(CFR_Attendance attendance) {
	    // 현재 시각으로 출석시각을 설정한 후 출석체크 정보를 추가한다
	    attendance.setAttendanceTime(Timestamp.valueOf(LocalDateTime.now()));
	    attendanceDao.addAttendance(attendance);
	}

	@Override
	public void updateAttendance(CFR_Attendance attendance) {
	    // 출석체크 정보를 수정한다
	    attendanceDao.updateAttendance(attendance);
	}

	@Override
	public void deleteAttendance(int attendanceID) {
	    // attendanceID에 해당하는 출석체크 정보를 삭제한다
	    attendanceDao.deleteAttendance(attendanceID);
	}

	@Override
	public void checkAttendance(CFR_Attendance attendance) {
	    // 출석체크 시각으로 설정한 후 출석체크 정보를 추가한다
	    attendance.setAttendanceTime(Timestamp.valueOf(LocalDateTime.now()));
	    attendanceDao.checkAttendance(attendance);
	}

	@Override
	public List<CFR_Attendance> getAllAttendances() {
	    // 모든 출석체크 정보를 조회한다
	    return attendanceDao.getAllAttendances();
	}

	@Override
	public List<AttendanceSubject> getSubjectByUserIdAndEduCode(String userId, String eduCode, String subCode) {
		return attendanceDao.getSubjectByUserIdAndEduCode(userId, eduCode, subCode);
	}

	@Override
    public boolean checkAttendanceId(String studentId, String subCode, String eduCode, String attendanceId) {
        List<String> existingAttendanceIds = attendanceDao.findAttendanceIdsByStudentAndSubject(studentId, subCode, eduCode);
        return !existingAttendanceIds.contains(attendanceId);
    }

}