import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Logger;

public class AsyncJob {
    private final Logger log = Logger.getLogger("Run");
    /**
     * CompletableFutureを使った単発処理
     * @throws Exception
     */
    public void doCompletableFuture() throws Exception {
        log.info("[START] doAsyncTask");

        Supplier<Integer> getIntVal = () -> 0;
        Function<Integer, Integer> func = value -> {
            try {
                log.info("[START]重い処理");
                for (int i = 0; i < 3; i++) {
                    Thread.sleep(1000);
                    log.info("%d秒経過".formatted(++value));
                }
            } catch (InterruptedException e) {
                log.warning("割り込み例外");
            } finally {
                log.info("[END]重い処理");
            }
            return value;
        };
        Consumer<Integer> out = value -> log.info("value=%d".formatted(value));

        CompletableFuture<Void> future = CompletableFuture.supplyAsync(getIntVal)
                .thenApply(func)
                .thenAccept(out);

        // future.get()をするとfuncの重い処理が終わるまで待つ。future.get()がない場合は待たない。
        future.get();

        log.info("[END] doAsyncTask");
    }

    /**
     * CompletableFutureを使った複数処理
     * @throws Exception
     */
    public void doMultiCompletableFuture() throws Exception {
        log.info("[START] doMultiCompletableFuture");
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int n = i;
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> log.info("task-%d is executed".formatted(n)));
            futures.add(future);
        }
        // .allOf().get()をすると処理が終わるまで待つ。.allOf().get()ない場合は待たない。
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[] {})).get();
        log.info("[END] doMultiCompletableFuture");
    }
}