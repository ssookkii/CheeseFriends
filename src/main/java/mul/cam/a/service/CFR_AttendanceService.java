package mul.cam.a.service;

import java.util.List;

import mul.cam.a.dto.AttendanceSubject;
import mul.cam.a.dto.CFR_Attendance;

public interface CFR_AttendanceService {
	// attendance ID로 출석 정보 조회
    CFR_Attendance getAttendanceById(int attendanceID); 
    // 모든 출석 정보 조회, 추가 수정 삭제 
    List<CFR_Attendance> getAllAttendances();
    void addAttendance(CFR_Attendance attendance);
    void updateAttendance(CFR_Attendance attendance);
    void deleteAttendance(int attendanceID);
    // 사용자 ID와 교육 코드로 해당 사용자의 수강 과목 조회
    List<AttendanceSubject> getSubjectByUserIdAndEduCode(String userId, String eduCode);
    // 출석체크
    void checkAttendance(CFR_Attendance attendance);
}
