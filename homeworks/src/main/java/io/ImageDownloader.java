package io;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.util.TreeSet;

public class ImageDownloader {
    private String inputURL;
    private String outputPath;
    private final static int MAX_FILENAME_LENGTH = 100;

    public ImageDownloader(String fromURL, String absolutePathToSave) throws IOException {
        this.inputURL = fromURL;
        this.outputPath = absolutePathToSave;
        validPath(this.outputPath);
    }

    private void validPath(String path) throws IOException {
        File file = new File(path);
        if (!file.canWrite()) {
            if (!file.mkdirs()) {
                throw new IOException("Can't create paths " + path);
            }
            throw new IOException("Can't access to paths " + path);
        }
    }

    private void deleteOldExistFile(String filename) throws IOException {
        File file = new File(outputPath + filename);
        if (file.isFile() && !file.delete()) {
            throw new IOException("Can't delete old file " + outputPath + filename);
        }
    }

    private TreeSet<String> parseAllLinks() throws IOException {
        Document doc = Jsoup.connect(inputURL).get();
        Elements href = doc.select("[href]");
        if (href == null) {
            throw new NullPointerException("No found links JSOUP [href]");
        }
        return new TreeSet<>(href.eachAttr("abs:href"));
    }

    private void saveImageFromHref(String saveFrom) throws IOException {
        String filename = saveFrom.substring(saveFrom.lastIndexOf("/") + 1, saveFrom.length());
        deleteOldExistFile(filename);
        URL url = new URL(saveFrom);
        File file = new File(outputPath + filename);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        DataInputStream dataInputStream = new DataInputStream(url.openStream());
        DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
        if (filename.length() <= MAX_FILENAME_LENGTH) {
            int counter;
            while ((counter = dataInputStream.read()) != -1) {
                dataOutputStream.writeByte(counter);
            }
            dataOutputStream.flush();
            dataOutputStream.close();
            dataInputStream.close();
            fileOutputStream.close();
        }
    }

    public boolean downloadImageFromImg() throws IOException {
        Document doc = Jsoup.connect(inputURL).get();
        Element image = doc.select("img").first();
        if (image == null) {
            return false;
        }
        String url = image.absUrl("src");
        if (url == null) {
            return false;
        }
//        links.add(url.toLowerCase());
        return true;
    }

    public boolean downloadImageFromHref(final String fileFormat) throws IOException {
        boolean flagFound = false;
        final TreeSet<String> links = parseAllLinks();
        for (String url : links) {
            if (url.endsWith(fileFormat.toLowerCase())) {
                saveImageFromHref(url);
                flagFound = true;
            }
        }
        return flagFound;
    }
}