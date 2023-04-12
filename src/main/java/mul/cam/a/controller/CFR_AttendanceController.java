package mul.cam.a.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import mul.cam.a.service.CFR_AttendanceService;

@Controller
public class CFR_AttendanceController {
    private CFR_AttendanceService attendanceService;

   /* public CFR_AttendanceController(CFR_AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @RequestMapping("/getAttendance")
    public String getAttendanceById(@RequestParam("attendanceID") int attendanceID, Model model) {
        CFR_Attendance attendance = attendanceService.getAttendanceById(attendanceID);
        model.addAttribute("attendance", attendance);
        return "attendanceDetails"; // 해당하는 View 이름을 반환합니다.
    }*/
    
    @PostMapping("/api/compareFaces")
    public ResponseEntity<Double> compareFaces() {
        String command = "java -cp .;opencv-453.jar Compare";
        double similarity = 5.0;

        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            similarity = Double.parseDouble(reader.readLine());
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(similarity);
    }


}


