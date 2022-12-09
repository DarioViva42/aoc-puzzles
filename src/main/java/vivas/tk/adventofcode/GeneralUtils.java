package vivas.tk.adventofcode;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class GeneralUtils {

    static {
        loadSecurityProperties();
    }

    private static final StackWalker STACK_WALKER = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);
    private static final String LOCAL_PATH = "/day%s";
    private static final String SERVER_PATH = "https://adventofcode.com/%d/day/%d/%s";

    private GeneralUtils() {
    }

    public static String readPuzzleInput() {
        AdventDate date = AdventDate.fromClass(STACK_WALKER.getCallerClass());

        try (InputStream inputStream = getResource(date)) {
            return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private static InputStream getResource(AdventDate date) {
        return getResourceFromFile(date)
                .or(() -> getResourceFromServer(date))
                .orElseThrow();
    }

    private static Optional<InputStream> getResourceFromFile(AdventDate date) {
        String path = LOCAL_PATH.formatted(StringUtils.leftPad(String.valueOf(date.day()), 2));
        InputStream inputStream = GeneralUtils.class.getResourceAsStream(path);
        return inputStream != null ? Optional.of(inputStream) : Optional.empty();
    }

    private static Optional<InputStream> getResourceFromServer(AdventDate date) {
        try {
            URL url = new URL(SERVER_PATH.formatted(date.year(), date.day(), "input"));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Cookie", "session=" + System.getProperty("aoc.session"));
            return Optional.of(connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private static void loadSecurityProperties() {
        InputStream is = GeneralUtils.class.getResourceAsStream("/security.properties");
        try {
            System.getProperties().load(is);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
