package com.example.document_flow.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Класс специализируется на считывание файла расположенные в папке {@code resources/table/ } в формате {@code sql}
 * Важно требование к наименованию файлов.
 * 1. имя sql-файла должно начинаться с заглавной буквы "V"
 * 2. после буквы "V" должна идти версия файла или число для порядка выполнения sql-запросов.
 *
 * @author Баратов Руслан
 */
public class ReadFileSql {

    /**
     * Регулярное вырождение проверяет что б имя файла начиналось с буквы "V" и после нее было какое-либо число;
     */
    private static final Pattern PATTERN = Pattern.compile("^\\V[0-9]*?([0-9]*[.])?[0-9]+");

    private final String fileName = Objects.requireNonNull(Objects.requireNonNull(this.getClass().getClassLoader().getResource("table/")).getFile(),
            MessageFormat.format("Папка ресурсов {0} не найдено", 1));

    /**
     * Читать sql файлы
     *
     * @return список sql скриптов
     * @throws IOException При возникновении ошибки ввода-вывода
     */
    public List<String> read() throws IOException {
        List<String> sqlScripts = new ArrayList<>();
        for (File file : getListFiles()) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
                sqlScripts.add(reader.lines().reduce((acc, x) -> acc + x).get());
            }
        }
        return sqlScripts;
    }

    /**
     * Реализация функционального интерфейса для сортировки файлов, по версии файла указанный в наименования после буквы "V".
     * Пример V3_person.sql где 3-версия файла
     * Отсортировывает файлы по возрастания версии
     *
     * @param file1 первый файл, подлежащий сравнению
     * @param file2 второй файл для сравнения
     * @return
     */
    private int compare(File file1, File file2) {
        Matcher matcher1 = PATTERN.matcher(file1.getName());
        matcher1.find();
        Matcher matcher2 = PATTERN.matcher(file2.getName());
        matcher2.find();
        Float version = Float.parseFloat(matcher1.group().substring(1));
        Float version2 = Float.parseFloat(matcher2.group().substring(1));
        return version.compareTo(version2);
    }

    /**
     * @return список отфильтрованных файлов по требованиям и отсортированный по версии файла указанный в наименования
     * Пример V3_person.sql где 3-версия файла
     */
    private Collection<File> getListFiles() {
        File directory = new File(fileName);
        return Arrays.stream(Objects.requireNonNull(directory.listFiles(getFilenameFilter())))
                .sorted(this::compare).collect(Collectors.toCollection(TreeSet::new));
    }

    /**
     * Реализация функционального интерфейса, где
     * прописано логика фильтра для проверки, следует ли включать указанный файл в список файлов.
     * 1. имя sql-файла должно начинаться с заглавной буквы "V"
     * 2. после буквы "V" должно идти версия файла или число для порядка выполнения sql-запросов.
     *
     * @return реализации функционального интерфейса
     */
    private FilenameFilter getFilenameFilter() {
        return (dir, name) -> PATTERN.matcher(name).find();
    }
}
