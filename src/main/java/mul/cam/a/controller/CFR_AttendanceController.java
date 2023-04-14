package mul.cam.a.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.List;

import org.opencv.core.Core;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import mul.cam.a.camera.User_Face_Crop;
import mul.cam.a.dto.AttendanceSubject;
import mul.cam.a.dto.CFR_Attendance;
import mul.cam.a.service.CFR_AttendanceService;

@Controller
public class CFR_AttendanceController {
    private CFR_AttendanceService attendanceService;
   
 //출석체크 실행 시스템   
    @PostMapping("/api/compareFaces")
    public ResponseEntity<Double> compareFaces() {
        String command = "java -cp .;opencv-470.jar mul.cam.a.camera.Compare";
        double similarity = 0.0;
        System.out.println("출석체크 시스템 실행");
        System.out.println(getClass().getProtectionDomain().getCodeSource().getLocation());

        try {
        	
            ProcessBuilder builder = new ProcessBuilder(command.split(" "));
            builder.directory(new File("./target/classes"));
            Process process = builder.start();
            BufferedReader reader = new BufferedReader(new FileReader("./target/classes/similarity.txt"));
            String line = reader.readLine();
            System.out.println(command);
            System.out.println(process);
            System.out.println(line);
            if (line != null && line.matches("\\d+(\\.\\d+)?")) {
                similarity = Double.parseDouble(line.trim());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("catch");
        }
        return ResponseEntity.ok(similarity);
    }


 // userID별로 사진 크롭해서 저장
    @PostMapping("/api/imgcrop/{userId}")
    public ResponseEntity<Object> compareFaces(@PathVariable String userId) {
    	
    	
    	System.out.println(userId);
        try {
            // 이미지를 자르는 기능
            User_Face_Crop faceCrop = new User_Face_Crop();
            String imagePath = "./src/MemberFace/" + userId + ".jpg";
            System.out.println(imagePath);
            String croppedImagePath = "./src/AttendanceFace/"+"test"+userId+".jpg";
            boolean cropSuccess = faceCrop.cropUserFace(imagePath, croppedImagePath);

            // 자르기가 성공했는지에 따라 결과 반환
            if (cropSuccess) {
            	System.out.println(userId + " 생성 성공");
                return ResponseEntity.ok().build();
            } else {
            	System.out.println(userId + " 생성 실패");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Failed to crop image");
        }
    }
    
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    
 // 출석체크!
    @RequestMapping(value = "/attendance", method = RequestMethod.POST)
    public String checkAttendance(Model model, @RequestParam("userId") String userId, 
                                  @RequestParam("eduCode") String eduCode, 
                                  @RequestParam("subCode") String subCode) {

        // 과목 정보 가져오기
        List<AttendanceSubject> subjectList = attendanceService.getSubjectByUserIdAndEduCode(userId, eduCode);

     // 해당 과목의 시작시간과 현재 시간 비교
        LocalDateTime now = LocalDateTime.now();
        for (AttendanceSubject subject : subjectList) {
            if (subject.getSubCode().equals(subCode)) {
                if (now.toLocalDate().isBefore(subject.getStartDate().toLocalDate())) { // 시작 시간 전이면 출석 처리
                    CFR_Attendance attendance = new CFR_Attendance();
                    attendance.setStudentID(userId);
                    attendance.setSub_code(subCode);
                    attendance.setStatus("출석");
                    attendance.setEdu_code(eduCode);
                    attendanceService.checkAttendance(attendance);
                    break;
                } else { // 시작 시간 후면 지각 처리
                    CFR_Attendance attendance = new CFR_Attendance();
                    attendance.setStudentID(userId);
                    attendance.setSub_code(subCode);
                    attendance.setStatus("지각");
                    attendance.setEdu_code(eduCode);
                    attendanceService.checkAttendance(attendance);
                    break;
                }
            }
        }

        return "redirect:/attendance";
    }


}


