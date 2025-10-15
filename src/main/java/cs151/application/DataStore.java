package cs151.application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public final class DataStore {
    private static final Path DATA_DIR  = Paths.get(System.getProperty("user.home"), ".knowledgetrack");
    private static final Path DATA_FILE = DATA_DIR.resolve("languages.csv");

    private static final ObservableList<ProgrammingLanguages> LIST =
            FXCollections.observableArrayList();

    private static final ObservableList<StudentProfile> NAME =
            FXCollections.observableArrayList();

    private static boolean loadedOnce = false;

    private DataStore() {}

    public static ObservableList<ProgrammingLanguages> getList() {
        return LIST;
    }

    public static ObservableList<StudentProfile> getFullName() {
        return NAME;
    }

    public static void load() {
        if (loadedOnce) return;
        loadedOnce = true;

        if (!Files.exists(DATA_FILE)) return;

        try (BufferedReader br = Files.newBufferedReader(DATA_FILE, StandardCharsets.UTF_8)) {
            String line = br.readLine();
            if (line == null) return;
           /* if (!line.startsWith("fullName,")) {
                parseLineIntoList(line);
            }*/
            String row;
            while ((row = br.readLine()) != null) {
                parseLineIntoList(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        try {
            if (!Files.exists(DATA_DIR)) Files.createDirectories(DATA_DIR);
            try (BufferedWriter bw = Files.newBufferedWriter(DATA_FILE, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
                bw.write("programmingLanguage");
                bw.newLine();

                for (ProgrammingLanguages pl : LIST) {
                  //  bw.write(csv(pl.getFullName()));
                    //bw.write(',');
                    bw.write(csv(pl.getProgrammingLanguage()));
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static String csv(String s) {
        if (s == null) s = "";
        String q = s.replace("\"", "\"\"");
        return "\"" + q + "\"";
    }

    private static void parseLineIntoList(String line) {
        String[] cols = parseCsvLine(line, 2);
        if (cols == null) return;
        //String name = cols[0];
        String lang = cols[1];
        if (/*!name.isEmpty() || */!lang.isEmpty()) {
            LIST.add(new ProgrammingLanguages(lang));
        }
    }

    // simple CSV parser for 2 columns with optional quotes/commas
    private static String[] parseCsvLine(String line, int expectedCols) {
        if (line == null) return null;
        String[] out = new String[expectedCols];
        int idx = 0;
        StringBuilder sb = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (inQuotes) {
                if (c == '"') {
                    // lookahead for escaped quote
                    if (i + 1 < line.length() && line.charAt(i + 1) == '"') {
                        sb.append('"');
                        i++;
                    } else {
                        inQuotes = false;
                    }
                } else {
                    sb.append(c);
                }
            } else {
                if (c == '"') {
                    inQuotes = true;
                } else if (c == ',') {
                    if (idx < expectedCols) out[idx++] = sb.toString();
                    sb.setLength(0);
                } else {
                    sb.append(c);
                }
            }
        }
        if (idx < expectedCols) out[idx++] = sb.toString();
        if (idx != expectedCols) return null;
        return out;
    }
}