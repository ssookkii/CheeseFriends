package mul.cam.a.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mul.cam.a.dto.CFR_Attendance;
import mul.cam.a.dto.SubjectDto;
import mul.cam.a.service.AttManageService;

@RestController
@RequestMapping("/attManage")
public class AttManageController {
    @Autowired
    private AttManageService attManageService;
    
    @GetMapping("/{studentID}/{subCode}/{eduCode}")
    public ResponseEntity<?> getAttendanceList(@PathVariable String studentID,
                                                @PathVariable String subCode,
                                                @PathVariable String eduCode) {
        List<CFR_Attendance> attendanceData = attManageService.getAttendanceList(studentID, subCode, eduCode);
        List<Map<String, String>> newAttendanceData = new ArrayList<>();
        for (CFR_Attendance attendance : attendanceData) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedTime = dateFormat.format(attendance.getAttendanceTime());
            
            Map<String, String> newAttendance = new HashMap<>();
            newAttendance.put("attendanceTime", formattedTime);
            newAttendance.put("status", attendance.getStatus());
            newAttendanceData.add(newAttendance);
        }
        return ResponseEntity.ok(newAttendanceData);
    }
    
    @GetMapping("/subjects/{userId}")
    public ResponseEntity<List<SubjectDto>> getSubjectsByUserId(@PathVariable String userId) {
        List<SubjectDto> subjects = attManageService.getSubjectsByUserId(userId);
        return ResponseEntity.ok(subjects);
    }
    
    @GetMapping("/{subCode}")
    public List<CFR_Attendance> getAttendanceBySubjectCode(@PathVariable String subCode) {
      return attManageService.getAttendanceBySubjectCode(subCode);
    }
    

    @PostMapping("/{attendanceID}/attendance")
    public ResponseEntity<Integer> updateAttendanceStatus(@PathVariable String attendanceID, @RequestBody CFR_Attendance updatedAttendance) {
    	int attendance = attManageService.updateAttendanceStatus(attendanceID, updatedAttendance.getStatus());
    	return ResponseEntity.ok(attendance);
    }

    @PostMapping("/{attendanceID}/comment")
    public ResponseEntity<Integer> updateAttendancecomment(@PathVariable String attendanceID, @RequestBody CFR_Attendance updatedAttendancecomment) {
    	int attendance = attManageService.updateAttendancecomment(attendanceID, updatedAttendancecomment.getComment());
    	return ResponseEntity.ok(attendance);
    }
    
}

