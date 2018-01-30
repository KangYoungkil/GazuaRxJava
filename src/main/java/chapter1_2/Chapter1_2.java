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
    public static <T> Observable<T> fromMap(Map source) {
        ObjectHelper.requireNonNull(source, "source is null");
        return RxJavaPlugins.onAssembly(new ObservableFromIterable<T>(source.entrySet()));
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
