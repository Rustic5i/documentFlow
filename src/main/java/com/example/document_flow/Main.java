package com.example.document_flow;

import com.example.document_flow.config.ReadFileSql;
import com.example.document_flow.web.controller.DocumentRequestHandler;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        DocumentRequestHandler requestHandler = new DocumentRequestHandler();
        requestHandler.setRequest(args);

        FilenameFilter filenameFilter = (dir,name)-> Pattern.compile("^\\V[0-9]*?([0-9]*[.])?[0-9]+").matcher(name).find();

        Cat cat = new Cat();
        String url = cat.get();
        System.out.println(url);
        File directory = new File(url);
        Set<String> stringSet = Arrays.stream(directory.list(filenameFilter)).sorted((o1, o2) -> {
            Pattern pattern1 = Pattern.compile("^\\V[0-9]*?([0-9]*[.])?[0-9]+");
            Matcher matcher1 = pattern1.matcher(o1);
            matcher1.find();
            Float version = Float.parseFloat(matcher1.group().substring(1));
            Matcher matcher2 = pattern1.matcher(o2);
            matcher2.find();
            Float version2 = Float.parseFloat(matcher2.group().substring(1));
            int bl  = version.compareTo(version2);
            return bl;
        }).collect(Collectors.toCollection(TreeSet::new));
        for (String version: stringSet) {
            System.out.println(version);
        }

        ReadFileSql readFileSql = new ReadFileSql();
        readFileSql.read().forEach(System.out::println);

//        String strings = actualFile[0];
//        Pattern pattern = Pattern.compile("^\\V[0-9]*?([0-9]*[.])?[0-9]+");
//
//        for (int i = 0; i < actualFile.length; i++) {
//            Matcher matcher = pattern.matcher(actualFile[i]);
//            matcher.find();
//            System.out.println(matcher.group().substring(1));
//        }
//        System.out.println(actualFile);
////        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
////        reader.lines().forEach(System.out::println);
//        Set<String> strings1 = new TreeSet<>(((o1, o2) -> {
//            Pattern pattern1 = Pattern.compile("^\\V[0-9]*?([0-9]*[.])?[0-9]+");
//            Matcher matcher1 = pattern1.matcher(o1);
//            matcher1.find();
//            Float version = Float.parseFloat(matcher1.group().substring(1));
//            Matcher matcher2 = pattern1.matcher(o2);
//            matcher2.find();
//            Float version2 = Float.parseFloat(matcher2.group().substring(1));
//            return version.compareTo(version2);
//        }));
    }

    static class Cat {

        public String get() {
            return Objects.requireNonNull(Objects.requireNonNull(this.getClass().getClassLoader().getResource("table/")).getFile(),
                    MessageFormat.format("Папка ресурсов {0} не найдено", 1));
        }
    }
}
