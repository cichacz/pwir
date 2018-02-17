package uek.szewczyk;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        if(args.length < 3) {
            //too few arguments!
            System.out.println("Too few arguments!");
            System.exit(123);
        }
        //read commandline params
        int k = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);
        int m = Integer.parseInt(args[2]);

        int bufferSize = Math.min(k, 10);

        ExecutorService generators = Executors.newFixedThreadPool(n);
        ExecutorService summators = Executors.newFixedThreadPool(m);

        for (int i = 1; i <= n; i++) {
            Generator generator = new Generator("Generator-" + i, k, bufferSize, summators);
            generators.execute(generator);
        }

        //generators can be shut down immediately since those are enqueued synchronously
        generators.shutdown();
        while(!generators.isTerminated());

        //after all generating jobs has been queued it's safe to shutdown summators
        summators.shutdown();
    }
}
