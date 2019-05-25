import neural2.builder.INeuralEndedBuilder;
import neural2.builder.impl.NeuralBuilder;
import neural2.network.ActivationFunction;
import neural2.network.INeuralNetwork;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        final INeuralEndedBuilder builder = new NeuralBuilder(ActivationFunction.IDENTITY)
                .setInputSize(2)
                .addNeuralLine(6)
                .addNeuralLine(8)
                .addNeuralLine(8)
                .addNeuralLine(6)
                .setResultSize(1);

        final int nbNN = 100;

        List<INeuralNetwork> addNetwordList = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < nbNN; ++i) {
            INeuralNetwork nn = builder.build();
            addNetwordList.add(nn);
        }


        INeuralNetwork winner;
        Random r1 = new Random(System.nanoTime());
        double[] inf;
        double resf;
        do {
            double[] in = new double[]{r1.nextInt(100), r1.nextInt(100)};
            inf = in;
            double res = in[0] + in[1];
            resf = res;

            addNetwordList.forEach(nn -> threads.add(new Thread(() -> nn.compute(in))));
            threads.forEach(Thread::run);
            threads.forEach(thread -> {
                try {
                    thread.join();
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            });

            List<INeuralNetwork> newNeurals = new ArrayList<>();
            addNetwordList.stream()
                    .sorted((nn1, nn2) -> ((Double) (Math.abs(nn1.getResult()[0] - res) - Math.abs(nn2.getResult()[0] - res))).intValue())
                    .limit(10)
                    .peek(nn -> {
                        newNeurals.add(nn);
                        for (int i = 0; i < 9; i++) {
                            newNeurals.add(nn.copyAndApplyMutation(10));
                        }
                    })
                    //   .filter(nn1 -> Math.abs(nn1.getResult()[0] - res) < 1D)
                    .findFirst()
                    .ifPresent(INeuralNetwork::addWin);
            addNetwordList = newNeurals;
            winner = addNetwordList.parallelStream().max(Comparator.comparingInt(INeuralNetwork::getWins)).get();
        } while (winner.getWins() < 10);

        System.out.println(winner.getResult()[0] + " -- " + inf[0] + " + " + inf[1] + " = " + resf);
    }
}
