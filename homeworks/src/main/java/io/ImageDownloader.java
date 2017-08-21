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
    private TreeSet<String> links;
    private File file;
    private FileOutputStream fileOutputStream;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public ImageDownloader(String fromURL, String absolutePathToSave) {
        this.inputURL = fromURL;
        this.outputPath = absolutePathToSave;
        this.links = new TreeSet<>();
        try {
            isDirectories(this.outputPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void isDirectories(String path) throws IOException {
        file = new File(path);
        if (!file.canWrite()) {
            if (!file.mkdirs()) {
                throw new IOException("Can't create paths " + path);
            }
            if (!file.canWrite()) {
                throw new IOException("Can't access to this path " + path);
            }
        }
    }

    private boolean deleteExistFiles(String filename) throws IOException {
        file = new File(outputPath + filename);
        if (file.isFile()) {
            if (!file.delete()) {
                throw new IOException("Can't delete old file " + outputPath + filename);
            }
            return true;
        }
        return false;
    }

    private boolean getAllLinks() {
        Document doc = null;
        try {
            doc = Jsoup.connect(inputURL).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements href = doc.select("[href]");
        if (href == null) {
            return false;
        }
        links.addAll(href.eachAttr("abs:href"));
        return true;
    }

    private boolean downloadImage(String getLink) {
        try {
            String filename = getLink.substring(getLink.lastIndexOf("/") + 1, getLink.length());
            int maximumLength = 100;
            if (filename.length() > maximumLength) {
                return false;
            }
            deleteExistFiles(filename);
            URL url = new URL(getLink);
            file = new File(outputPath + filename);
            fileOutputStream = new FileOutputStream(file);
            dataInputStream = new DataInputStream(url.openStream());
            dataOutputStream = new DataOutputStream(fileOutputStream);
            int counter;
            while ((counter = dataInputStream.read()) != -1) {
                dataOutputStream.writeByte(counter);
            }
            dataOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dataOutputStream.close();
                dataInputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean getImages() {
        Document doc = null;
        try {
            doc = Jsoup.connect(inputURL).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element image = doc.select("img").first();
        if (image == null) {
            return false;
        }
        String url = image.absUrl("src");
        if (url == null) {
            return false;
        }
        links.add(url.toLowerCase());
        return true;
    }

    public boolean getImagesFormLinks(String fileFormat) {
        boolean flagFound = false;
        getAllLinks();
        for (String urls : links) {
            if (urls.endsWith(fileFormat.toLowerCase())) {
                downloadImage(urls);
                flagFound = true;
            }
        }
        return flagFound;
    }
}