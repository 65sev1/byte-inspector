package cbr;

import java.io.File;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SecurityAccessExecutor {

    public static SecurityAccessExecutor create() {
        return new SecurityAccessExecutor();
    }

    public void execute(int nThreads, Function<String, String> func, String path) throws InterruptedException {
        final List<Callable<String>> result = Search.files(path, f -> f.isFile() && f.getName().endsWith(".class")).stream()
                .map(file -> (Callable<String>) () -> func.apply(file.getAbsolutePath())).collect(Collectors.toList());
        ExecutorService service = Executors.newFixedThreadPool(nThreads);
        service.invokeAll(result);
        service.shutdown();
    }

    public static class Search {
        public static List<File> files(String root, Predicate<File> predicate) {
            final List<File> result = new ArrayList<>();
            final Queue<File> directory = new PriorityQueue<>();
            File file = new File(root);
            if (file.isFile()) {
                result.add(file);
                return result;
            }
            Collections.addAll(directory, Objects.requireNonNull(file.listFiles()));
            while (!directory.isEmpty()) {
                file = directory.poll();
                if (file.isDirectory()) {
                    Collections.addAll(directory, Objects.requireNonNull(file.listFiles()));
                }
                if (predicate.test(file)) {
                    result.add(file);
                }
            }
            return result;
        }
    }
}
