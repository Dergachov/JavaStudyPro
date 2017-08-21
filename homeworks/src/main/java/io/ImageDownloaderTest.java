package io;

public class ImageDownloaderTest {
    //set args: http://flangex.herokuapp.com/io/load png ./
    public static void main(String[] args) {
        if (args.length == 0 || 3 > args.length) {
            System.out.println("Args: URL, files extension, path for saving.");
            System.exit(1);
        }
        ImageDownloader flangex = new ImageDownloader(args[0], args[2]);
        if (flangex.getImagesFormLinks(args[1])) {
            System.out.println("Download complete.");
        } else {
            System.out.println("Not founded images.");
        }
    }
}
