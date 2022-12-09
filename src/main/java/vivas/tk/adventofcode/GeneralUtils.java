package vivas.tk.adventofcode;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
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
    public static final String FORM = "level=%s&answer=%s";

    private GeneralUtils() {
    }

    public static String sendPuzzleAnswer(int level, Object answer) {
        AdventDate date = AdventDate.fromClass(STACK_WALKER.getCallerClass());
        String form = FORM.formatted(level, answer);
        return createPostRequest(date, form)
                .map(GeneralUtils::sendPostRequest)
                .orElseThrow();
    }

    private static Optional<HttpURLConnection> createPostRequest(AdventDate date, String form) {
        try {
            URL url = new URL(SERVER_PATH.formatted(date.year(), date.day(), "answer"));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Cookie", "session=" + System.getProperty("aoc.session"));
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setDoOutput(true);

            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(form);
            out.close();
            return Optional.of(connection);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private static String sendPostRequest(HttpURLConnection connection) {
        try (InputStream inputStream = connection.getInputStream()) {
            String serverAnswer = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            Document doc = Jsoup.parse(serverAnswer);
            return doc.getElementsByTag("p").get(0).textNodes().get(0).text();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
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
        String path = LOCAL_PATH.formatted(StringUtils.leftPad(String.valueOf(date.day()), 2, '0'));
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
