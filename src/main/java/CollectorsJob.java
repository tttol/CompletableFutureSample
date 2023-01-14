import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class CollectorsJob {
    public void run() {
        var entites = List.of(
                new Entity("22-H", "0001"),
                new Entity("22-H", "0003"),
                new Entity("22-H", "0002"),
                new Entity("23-H", "0002"),
                new Entity("23-H", "0001")
        );

        Function<Entity, String> func = Entity::getSeq;

        var map = entites.stream()
                .collect(
                        Collectors.toMap(Entity::getYearAndModel,
                                Function.identity(),
                                BinaryOperator.maxBy(Comparator.comparing(Entity::getSeq))
                        )
                );
        var map2 = entites.stream()
                .collect(Collectors.toMap(Entity::getYearAndModel, Entity::getSeq, (oldValue, newValue) -> {
                    var oldInt = Integer.parseInt(oldValue);
                    var newInt = Integer.parseInt(newValue);
                    return oldInt <= newInt ? newValue : oldValue;
                }));
        var map3 = entites.stream()
                .collect(Collectors.toMap(Entity::getYearAndModel,
                        Entity::getSeq,
                        (oldValue, newValue) -> oldValue.compareTo(newValue) <= 0 ? newValue : oldValue)
                );
        System.out.println(map);
    }
}