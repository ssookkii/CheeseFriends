package mul.cam.a.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.opencv.core.Core;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import mul.cam.a.camera.User_Face_Crop;
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


 // 일단 테스트용 사진 자르기
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

}


