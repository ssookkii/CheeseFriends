package mul.cam.a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import mul.cam.a.dto.AttendanceSubject;
import mul.cam.a.dto.CFR_Attendance;

public interface CFR_AttendanceDao {
    CFR_Attendance getAttendanceById(int attendanceID);
    List<CFR_Attendance> getAllAttendances();
    void addAttendance(CFR_Attendance attendance);
    void updateAttendance(CFR_Attendance attendance);
    void deleteAttendance(int attendanceID);
    List<AttendanceSubject> getSubjectByUserIdAndEduCode(String userId, String eduCode, String subCode);
    void checkAttendance(CFR_Attendance attendance);
    List<String> findAttendanceIdsByStudentAndSubject(@Param("studentId") String studentId,
                                                           @Param("subCode") String subCode, 
                                                           @Param("eduCode") String eduCode);
    }

