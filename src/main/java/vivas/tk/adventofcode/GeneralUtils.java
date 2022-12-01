package vivas.tk.adventofcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GeneralUtils {

    private GeneralUtils() {
    }

    public static List<String> readFile(String fileName) {

        List<String> lines = new ArrayList<>();

        URL resource = GeneralUtils.class.getResource(fileName);
        assert resource != null;
        try (BufferedReader bufferedReader = getBufferedReader(resource)) {
            bufferedReader.lines().forEach(lines::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    private static BufferedReader getBufferedReader(URL resource) throws IOException {
        InputStream inputStream = (InputStream) resource.getContent();
        return new BufferedReader(new InputStreamReader(inputStream));
    }
}
