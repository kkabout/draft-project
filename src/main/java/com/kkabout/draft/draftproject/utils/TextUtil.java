package com.kkabout.demo.project.utils;

import com.google.common.base.Charsets;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author kangjian03@58.com
 * @date 2020/3/28
 **/
public class TextUtil {
    public static void main(String[] args) throws IOException {
        String testFilePath = "/Users/kj/Desktop/id/landlordid.txt";
        File testFile = new File(testFilePath);
        List<String> lines = Files.readLines(testFile, Charsets.UTF_8);
        File newFile = new File("landlordid.txt");

        boolean start = false;
        for (String line : lines) {
            String id = line;
            if ("1".equals(id)) {
                start = true;
            }
            try
            {
                if (start) {
                    Files.asCharSink(newFile, Charsets.UTF_8, FileWriteMode.APPEND).write(id);
                    Files.asCharSink(newFile, Charsets.UTF_8, FileWriteMode.APPEND).write("\n");
                }
            }
            catch (IOException fileIoEx)
            {
            }
        }
        System.out.println(lines.size());
        System.out.println(111);
    }

}
