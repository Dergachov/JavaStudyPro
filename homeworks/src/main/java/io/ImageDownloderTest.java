package io;

public class ImageDownloderTest {
    public static void main(String[] args) {
        String url = "http://flangex.herokuapp.com/io/load";
        String saveTo = "/home/serezha/jsoup/";
        ImageDownloader flangex = new ImageDownloader(url, saveTo);

        if (flangex.getImages()) {
            System.out.println("Download from element <img> - done.");
        } else {
            System.out.println("Not founded images with element <img>");
        }
        if (flangex.getImagesFormLinks("png")) {
            System.out.println("Download from all elements with link - done.");
        } else {
            System.out.println("Not founded images.");
        }
    }
}
