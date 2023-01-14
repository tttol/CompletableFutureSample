import java.util.Locale;
import java.util.logging.Logger;

public class CollectorsSampleMain {
    private static final Logger log =Logger.getLogger(CollectorsSampleMain.class.getName());

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        System.setProperty("java.util.logging.SimpleFormatter.format",
                "%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS.%1$tL %4$s %3$s : %5$s%6$s%n");

        var collectorsJob = new CollectorsJob();
        collectorsJob.run();
    }
}
