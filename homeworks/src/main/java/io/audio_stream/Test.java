package io.audio_stream;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.*;


public class Test {
    public static void main(String[] args) {
        byte[] bytes = readWAVAudioFileData("C:\\Users\\Evgeniy\\Desktop\\JavaStudyPro\\homeworks\\src\\main\\java\\io\\audio_stream\\01_You_Are_My_Light.wav");
        writeWAV(bytes);
    }

    public static byte[] readWAVAudioFileData(final String filePath){
        byte[] data = null;
        try {
            final ByteArrayOutputStream baout = new ByteArrayOutputStream();
            final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, baout);
            audioInputStream.close();
            baout.close();
            data = baout.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void writeWAV(byte[] bytes) {
        try {
            OutputStream outputStream = new FileOutputStream("new.wav");
            for (int i = 0; i < 4000000; i++) {
                outputStream.write(bytes[i]);
            }
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
