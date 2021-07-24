package com.mohan.practice.lib;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public interface FileManager extends AutoCloseable {


    static void copyFile(String originFile, String destinationFile){

        Path origin = Paths.get(originFile);
        Path destination = Paths.get(destinationFile);

        try {
            Files.copy(origin,destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static void deleteFiles(String filePath){

        File file = new File(filePath);
        File[] allFiles = file.listFiles();
        if(allFiles!=null){
            for(File f :allFiles){
                f.delete();
            }
        }

    }

    static List<String> getFileNamesInPath(String path) {

        List<String> files = new ArrayList<>();

        try {
            files = Files.list(Paths.get(path)).map(Path::toFile).sorted(Comparator.comparingLong(File::lastModified).reversed())
                    .map(File::getName).filter(f -> f.endsWith(".json")).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return files;
    }


}
