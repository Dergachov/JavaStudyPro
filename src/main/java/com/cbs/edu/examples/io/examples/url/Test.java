package com.cbs.edu.examples.io.examples.url;

import java.io.*;
import java.net.URL;

/**
 * Created by evgeniy on 15/09/16.
 */
public class Test {
    public static void main(String[] args) throws Exception {

        URL oracle = new URL("http://flangex.herokuapp.com/io/load");
        BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream("C:\\Users\\Evgeniy\\Desktop\\JavaStudyPro\\homeworks\\src\\main\\java\\io\\url\\index.html")
                )
        );
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
            out.write(inputLine);
        }
        out.close();
        in.close();
    }
}
