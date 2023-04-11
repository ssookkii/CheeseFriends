package mul.cam.a.camera;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import javax.swing.JButton;
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

        // Create JFrame
        JFrame jframe = new JFrame("Cheese Friends");
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Create VideoPanel
        VideoPanel videoPanel = new VideoPanel();

        JButton button = new JButton("출석하기!");

        // Calculate button position
        int frameWidth = 1000;
        int buttonWidth = 140;
        int buttonHeight = 40;
        int buttonX = (frameWidth - buttonWidth) / 2;

        button.setBounds(buttonX, 30, buttonWidth, buttonHeight);

        // Set font size
        Font buttonFont = new Font("맑은 고딕", Font.BOLD, 18);
        button.setFont(buttonFont);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                videoPanel.saveFrame();
            }
        });

        // Create JLayeredPane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1000, 700));
        videoPanel.setBounds(0, 0, 1000, 700);
        layeredPane.add(videoPanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(button, JLayeredPane.PALETTE_LAYER);

        // Add JLayeredPane to JFrame
        jframe.setContentPane(layeredPane);

        // Set JFrame size
        jframe.pack();

        // Set JFrame visible
        jframe.setVisible(true);

        // Start video stream
        videoPanel.startVideoStream();
    }

    static class VideoPanel extends JPanel {
        VideoCapture capture;
        CascadeClassifier faceCascade;
        Mat frame;

        public void saveFrame() {
            if (!frame.empty()) {
                // Convert the frame to grayscale
                Mat grayFrame = new Mat();
                Imgproc.cvtColor(frame, grayFrame, Imgproc.COLOR_BGR2GRAY);

                // Detect faces in the grayscale frame
                MatOfRect faceDetections = new MatOfRect();
                faceCascade.detectMultiScale(grayFrame, faceDetections);

                // Save only the first detected face
                if (faceDetections.toArray().length > 0) {
                    Rect rect = faceDetections.toArray()[0];
                    Mat detectedFace = new Mat(frame, rect);
                    Imgproc.resize(detectedFace, detectedFace, new Size(200, 200));

                    String filename = "C:\\springboot2\\opencv_tesrt2\\attendance\\capture.jpg";
                    Imgcodecs.imwrite(filename, detectedFace);
                    System.out.println("Captured face saved to: " + filename);

                    // Add this part
                    String savedImagePath = "C:\\springboot2\\opencv_tesrt2\\attendance\\detected_face.jpg"; // Set the path of the saved image.
                    double similarity = FaceComparison.compareFaces(savedImagePath, filename);
                    System.out.println("유사도: " + similarity);

                    // Attendance processing based on similarity
                    if (similarity > 0.5) {
                        System.out.println("출석 처리 완료!");
                    } else {
                        System.out.println("출석 처리 실패! 다시 시도해주세요.");
                    }
                } else {
                    System.out.println("얼굴 감지 실패! 다시 시도해주세요.");
                }
            }
        }




        public VideoPanel() {
            // Initialize VideoCapture
            capture = new VideoCapture(0);
            capture.set(Videoio.CAP_PROP_FPS, 15);

            // Check if camera opened successfully
            if (!capture.isOpened()) {
                System.out.println("카메라를 열 수 없습니다.");
                return;
            }

            // Set camera properties
            capture.set(Videoio.CAP_PROP_FRAME_WIDTH, 560);
            capture.set(Videoio.CAP_PROP_FRAME_HEIGHT, 360);

            // Load cascade classifier for face detection
            faceCascade = new CascadeClassifier("C:\\Users\\User\\Downloads\\opencv\\opencv\\sources\\samples\\winrt\\FaceDetection\\FaceDetection\\Assets\\haarcascade_frontalface_alt.xml");

            // Initialize frame
            frame = new Mat();
        }

        public void startVideoStream() {
            Timer timer = new Timer(30, e -> {
                capture.read(frame);
                repaint();
            });
            timer.start();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (!frame.empty()) {
                Mat resizedFrame = new Mat();
                Mat grayFrame = new Mat();

                // Resize the frame to fit the window
                Imgproc.resize(frame, resizedFrame, new Size(1000, 700));

                // Convert the frame to grayscale
                Imgproc.cvtColor(resizedFrame, grayFrame, Imgproc.COLOR_BGR2GRAY);

                // Detect faces in the grayscale frame
                MatOfRect faceDetections = new MatOfRect();
                faceCascade.detectMultiScale(grayFrame, faceDetections);

                String savedImagePath = "C:\\springboot2\\opencv_tesrt2\\attendance\\capture.jpg"; // 저장된 이미지 경로를 설정하세요.

                // Draw green rectangles around detected faces and compute similarity
                for (Rect rect : faceDetections.toArray()) {
                    Mat detectedFace = new Mat(grayFrame, rect);
                    Imgproc.resize(detectedFace, detectedFace, new Size(200, 200));

                    String detectedFacePath = "C:\\springboot2\\opencv_tesrt2\\attendance\\detected_face.jpg";
                    Imgcodecs.imwrite(detectedFacePath, detectedFace);
                    
                    double similarity = FaceComparison.compareFaces(savedImagePath, detectedFacePath);
                    System.out.println("Similarity: " + similarity);

                    Scalar color;
                    if (similarity > 0.5) { // 유사도 임계값을 적절하게 설정하세요.
                        color = new Scalar(0, 255, 0); // Green
                    } else {
                        color = new Scalar(0, 0, 255); // Red
                    }

                    Imgproc.rectangle(resizedFrame, rect.tl(), rect.br(), color, 2);
                }

                // Convert Mat to BufferedImage
                BufferedImage img = matToBufferedImage(resizedFrame);

                // Draw the image on the panel
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
