import java.util.Locale;
import java.util.logging.Logger;

public class CompletableFutureSampleMain {
    private static final Logger log =Logger.getLogger("CompletableFutureSampleMain");

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        System.setProperty("java.util.logging.SimpleFormatter.format",
                "%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS.%1$tL %4$s %3$s : %5$s%6$s%n");

        var asyncJob = new AsyncJob();
        try {
            asyncJob.doCompletableFuture();
            asyncJob.doMultiCompletableFuture();
        } catch (Exception e) {
            log.warning(e.getMessage());
        }
    }
}
