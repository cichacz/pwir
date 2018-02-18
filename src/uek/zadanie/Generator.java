package uek.zadanie;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Generator implements Runnable {
    private int numbersToGenerate;
    private int bufferSize;
    private String name;
    private CompletionService<Integer> summators;

    public Generator(String name, int numbersToGenerate, int bufferSize, ExecutorService summators) {
        this.numbersToGenerate = numbersToGenerate;
        this.bufferSize = bufferSize;
        this.summators = new ExecutorCompletionService<>(summators);
        this.name = name;
    }

    public void run() {
        Random randomizer = new Random();
        BlockingQueue<Integer> generatedNumbers = new ArrayBlockingQueue<>(bufferSize);
        int totalPacks = (numbersToGenerate-1) / bufferSize + 1;

        try {
            for (int i = 1; i <= numbersToGenerate; i++) {
                //generate random integer
                generatedNumbers.add(randomizer.nextInt(100000));

                //if enough items generated, then proceed to summator
                if(generatedNumbers.remainingCapacity() == 0 || i == numbersToGenerate) {
                    List<Integer> numbers = new ArrayList<>(generatedNumbers);
                    summators.submit(new Summator(numbers));
                    generatedNumbers.clear();
                }
            }

            //after all jobs have been scheduled, wait for their completion
            for (int i = 1; i <= totalPacks; i++) {
                Integer sum = summators.take().get();
                System.out.println("Suma listy #" + i + " z " + name + " wynosi: " + sum);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
