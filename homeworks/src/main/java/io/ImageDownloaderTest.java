package io;

import java.io.IOException;

public class ImageDownloaderTest {
    //set args: http://flangex.herokuapp.com/io/load png ./
    public static void main(String[] args) {
        try {
            ImageDownloader flangex = new ImageDownloader("http://flangex.herokuapp.com/io/load", "/home/serezha/jsoup/");
            flangex.downloadImageFromImg();
            flangex.downloadImageFromHref("png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
