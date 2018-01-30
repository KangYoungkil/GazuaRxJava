package chapter1_2;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.SchedulerSupport;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.operators.observable.ObservableFromIterable;
import io.reactivex.plugins.RxJavaPlugins;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static io.reactivex.Observable.empty;
import static io.reactivex.Observable.just;

public class Chapter1_2 {


    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    public static Observable<Map.Entry> fromMap(Map map) {
        ObjectHelper.requireNonNull(map, "items is null");
        if (map.isEmpty()) {
            return empty();
        } else if (map.size() == 1) {
            Set<Map.Entry> entrySet = map.entrySet();
            return just(entrySet.stream().findFirst().get());
        }
        return RxJavaPlugins.onAssembly(Observable.fromIterable(map.entrySet()));
    }


    public static void main(String[] args) {

        Map<Integer, String> map = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            map.put(i, String.valueOf(i));
        }
        Observable source = fromMap(map);

        source.subscribe(System.out::println);

        map.replace(0, "-1");

        System.out.println("\n\n\n");

        source.subscribe(System.out::println);

    }
}
