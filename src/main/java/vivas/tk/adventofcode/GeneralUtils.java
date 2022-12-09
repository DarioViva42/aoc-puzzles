package vivas.tk.adventofcode;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class GeneralUtils {

    private static final StackWalker STACK_WALKER = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);

    private GeneralUtils() {
    }

    public static String readPuzzleInput() {

        String[] tokens = STACK_WALKER.getCallerClass()
                .getPackageName().split("\\.");
        String day = tokens[tokens.length - 1];

        URL resource = localResource(day);
        try (InputStream inputStream = resource.openStream()) {
            return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static URL localResource(String day) {
        return GeneralUtils.class.getResource("/" + day);
    }
}
