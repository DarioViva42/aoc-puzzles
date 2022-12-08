package vivas.tk.adventofcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collector;

public class GeneralUtils {

    private static final StackWalker STACK_WALKER = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);

    private GeneralUtils() {
    }

    public static List<String> readPuzzleInput() {

        String[] tokens = STACK_WALKER.getCallerClass()
                .getPackageName().split("\\.");
        String day = tokens[tokens.length - 1];

        URL resource = GeneralUtils.class.getResource("/" + day);
        assert resource != null;
        try (BufferedReader bufferedReader = getBufferedReader(resource)) {
            return bufferedReader.lines().toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    private static BufferedReader getBufferedReader(URL resource) throws IOException {
        InputStream inputStream = (InputStream) resource.getContent();
        return new BufferedReader(new InputStreamReader(inputStream));
    }

    public static Collector<String, List<List<String>>, List<List<String>>> splitBySeparator(Predicate<String> sep) {
        return Collector.of(
                () -> new ArrayList<>(List.of(new ArrayList<>())),
                (l, elem) -> {
                    if (sep.test(elem)) {
                        l.add(new ArrayList<>());
                    } else l.get(l.size() - 1).add(elem);
                },
                (l1, l2) -> {
                    l1.get(l1.size() - 1).addAll(l2.remove(0));
                    l1.addAll(l2);
                    return l1;
                }
        );
    }
}
