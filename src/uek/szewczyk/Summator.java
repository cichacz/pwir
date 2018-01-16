package uek.szewczyk;

import java.util.List;
import java.util.concurrent.Callable;

public class Summator implements Callable<Integer> {
    private List<Integer> batch;

    public Summator(List<Integer> batch) {
        this.batch = batch;
    }

    public Integer call() {
        Integer sum = 0;
        for (Integer i: batch) {
            sum += i;
        }
        return sum;
    }
}
