package chapter3;


import io.reactivex.Observable;

public class Chapter3 {

    public static void mulitple9_9() {
        Observable source = Observable.range(1, 9)
                .flatMap(it -> Observable.range(1, 9)
                        .flatMap(it2 -> Observable.just(String.valueOf(it + " x " + it2 + " = " + it * it2) + (it2 == 9 ? "\n" : ""))));

        source.subscribe(System.out::println);
    }

    public static boolean isPrime(int n) {
        if (n <= 1)
            return false; //1은 소수가 아니다.

        if ((n & 1) == 0) //짝수는
            return (n == 2); //2이면 true 아니면 소수아니다

        for (int i = 3; i * i <= n; i++) { // i = 3 ~ sqrt(n) 까지의 홀수
            if (n % i == 0) {//i가 n의 약수라면
                return false; //약수존재. 소수아니다.
            }
        }
        return true; //소수이다
    }

    public static void mresenNumbers() {

        Observable
                .range(2, 1000000)
                .map(it -> Math.pow(2, it)-1)
                .map(Double::intValue)
//                .filter(it->µit>0)
                .filter(Chapter3::isPrime)
                .map(it->(it*(it+1))/2)
                .subscribe(System.out::println);
    }

    public static void main(String[] args) {

//        mulitple9_9();

        mresenNumbers();
    }
}
