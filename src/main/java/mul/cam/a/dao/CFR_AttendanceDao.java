package mul.cam.a.dao;

import java.util.List;

import mul.cam.a.dto.CFR_Attendance;

public interface CFR_AttendanceDao {
    CFR_Attendance getAttendanceById(int attendanceID);
    List<CFR_Attendance> getAllAttendances();
    void addAttendance(CFR_Attendance attendance);
    void updateAttendance(CFR_Attendance attendance);
    void deleteAttendance(int attendanceID);
}
