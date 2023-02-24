package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Upload upload = new Upload();
        upload.execute();

        Download download = new Download();
        download.execute();
    }
}