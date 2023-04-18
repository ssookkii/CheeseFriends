package mul.cam.a.camera;

import java.awt.Dimension;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

public class Compare {

    public static void main(String[] args) {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        new Compare();
        // JFrame 생성
        JFrame jframe = new JFrame("Cheese Friends");
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // 비디오 패널 생성
        VideoPanel videoPanel = new VideoPanel();

       /* JButton button = new JButton("출석하기!");

        // 출석하기 버튼 위치 
        int frameWidth = 1000;
        int buttonWidth = 140;
        int buttonHeight = 40;
        int buttonX = (frameWidth - buttonWidth) / 2;

        button.setBounds(buttonX, 30, buttonWidth, buttonHeight);

       // 폰트 사이즈 
        Font buttonFont = new Font("맑은 고딕", Font.BOLD, 18);
        button.setFont(buttonFont);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                videoPanel.saveFrame();
            }
        });
        */

        // 패널 크기 
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1000, 700));
        videoPanel.setBounds(0, 0, 1000, 700);
        layeredPane.add(videoPanel, JLayeredPane.DEFAULT_LAYER);
       // layeredPane.add(button, JLayeredPane.PALETTE_LAYER);
        jframe.setContentPane(layeredPane);
        jframe.pack();
        jframe.setVisible(true);
        videoPanel.startVideoStream();
    }

    static class VideoPanel extends JPanel {
    	
        VideoCapture capture;
        CascadeClassifier faceCascade;
        Mat frame;
        
        public void startVideoStream() {
            Timer timer = new Timer(30, e -> {
                capture.read(frame);
                try {
					detectAndDrawFaces(frame);
				} catch (FontFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                repaint();
            });
            timer.start();
        }
        
        private void detectAndDrawFaces(Mat frame) throws FontFormatException, IOException {
            Mat grayFrame = new Mat();
            Imgproc.cvtColor(frame, grayFrame, Imgproc.COLOR_BGR2GRAY);

            MatOfRect faceDetections = new MatOfRect();
            faceCascade.detectMultiScale(grayFrame, faceDetections);

            // 얼굴 비교 시작
            for (Rect rect : faceDetections.toArray()) {
                // 얼굴 자르기
                Mat detectedFace = new Mat(grayFrame, rect);
                Imgproc.resize(detectedFace, detectedFace, new Size(300, 300));

                // 이름 가져오기 확인 필요 **
                String personName = "Unknown";

                // 경로에 있는 모든 이미지 파일에 대해 비교
                File folder = new File("C:\\springboot2\\CheeseFriends\\STS\\cheesefriends_back\\src\\AttendanceFace\\");
                File[] files = folder.listFiles();
                double maxSimilarity = 0.0;
                String detectedFacePath = "C:\\springboot2\\CheeseFriends\\STS\\cheesefriends_back\\src\\DetectedFace\\detected_face.jpg";
                Imgcodecs.imwrite(detectedFacePath, detectedFace);
                for (File file : files) {
                    if (file.isFile()) {
                        double similarity = FaceComparison.compareFaces(file.getAbsolutePath(), detectedFacePath);
                        if (similarity > maxSimilarity && similarity > 0.4) {
                            maxSimilarity = similarity;
                            // 파일 이름에서 확장자 제거 후 UserID 추출
                            String filename = file.getName();
                            String userID = filename.substring(0, filename.lastIndexOf('.'));
                            personName = userID;
                        }
                    }
                }

                System.out.println("Similarity: " + maxSimilarity);

                // 얼굴에 박스 표시
                Scalar color;
                if (maxSimilarity > 0.4) {
                    color = new Scalar(80, 194, 49); // 유사함
                } else {
                    color = new Scalar(58, 58, 234); // 유사하지 않음
                }
                Imgproc.putText(frame, personName, rect.tl(), Imgproc.FONT_HERSHEY_SIMPLEX, 1, color, 2);
                Imgproc.rectangle(frame, rect.tl(), rect.br(), color, 2);
            }
        }



        private String getPersonName(double similarity) throws FontFormatException, IOException {
            String name;
            if (similarity > 0.4) {
                name = "Success";
            } else {
                name = "Unknown";
            }
            return name;
        }


        public VideoPanel() {

            capture = new VideoCapture(0);
            capture.set(Videoio.CAP_PROP_FPS, 5);


            if (!capture.isOpened()) {
                System.out.println("카메라를 열 수 없습니다.");
                return;
            }


            capture.set(Videoio.CAP_PROP_FRAME_WIDTH, 400);
            capture.set(Videoio.CAP_PROP_FRAME_HEIGHT, 250);


            faceCascade = new CascadeClassifier("C:\\Users\\User\\Downloads\\opencv\\sources\\samples\\winrt\\FaceDetection\\FaceDetection\\Assets\\haarcascade_frontalface_alt.xml");


            frame = new Mat();
        }



        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (!frame.empty()) {
                Mat resizedFrame = new Mat();
                Mat grayFrame = new Mat();

                // 사이즈 
                Imgproc.resize(frame, resizedFrame, new Size(1000, 700));

               
                Imgproc.cvtColor(resizedFrame, grayFrame, Imgproc.COLOR_BGR2GRAY);

   
                MatOfRect faceDetections = new MatOfRect();
                faceCascade.detectMultiScale(grayFrame, faceDetections);

                String savedImagePath = "C:\\springboot2\\CheeseFriends\\STS\\cheesefriends_back\\src\\AttendanceFace\\member1.jpg"; // 저장된 이미지 경로를 설정하세요.


                for (Rect rect : faceDetections.toArray()) {
                    Mat detectedFace = new Mat(grayFrame, rect);
                    Imgproc.resize(detectedFace, detectedFace, new Size(300, 300));

                    String detectedFacePath = "C:\\springboot2\\CheeseFriends\\STS\\cheesefriends_back\\src\\DetectedFace\\detected_face.jpg";
                    Imgcodecs.imwrite(detectedFacePath, detectedFace);

                    double similarity = FaceComparison.compareFaces(savedImagePath, detectedFacePath);
                   
                       
                    try (PrintWriter out = new PrintWriter("similarity.txt")) {
                        out.println(similarity);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                
                }



                BufferedImage img = matToBufferedImage(resizedFrame);


                g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
            }
        }

        
        private BufferedImage matToBufferedImage(Mat mat) {
            int type = BufferedImage.TYPE_BYTE_GRAY;
            if (mat.channels() > 1) {
                type = BufferedImage.TYPE_3BYTE_BGR;
            }
            int bufferSize = mat.channels() * mat.cols() * mat.rows();
            byte[] bytes = new byte[bufferSize];
            mat.get(0, 0, bytes);
            BufferedImage img = new BufferedImage(mat.cols(), mat.rows(), type);
            final byte[] targetPixels = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
            System.arraycopy(bytes, 0, targetPixels, 0, bytes.length);
            return img;
        }
    }
    
}
