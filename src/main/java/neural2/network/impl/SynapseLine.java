package neural2.network.impl;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Function;

public class SynapseLine {
    private double[][] synapses;
    private double[] bias;
    private Function<Double, Double> activationFunction;

    public SynapseLine(int left, int right, Function<Double, Double> activationFunction) {
        this.activationFunction = activationFunction;
        this.synapses = new double[left][right];
        this.bias = new double[right];
        Random r = new Random(System.nanoTime());
        for (int i = 0; i < right; ++i) {
            for (int j = 0; j < left; ++j) {
                this.synapses[j][i] = r.nextDouble() * 2 - 1;
            }
            this.bias[i] = r.nextDouble() * 2 - 1;
        }
    }

    public double[] compute(double[] leftInput) {
        Arrays.stream(leftInput).forEach(d -> System.out.print(d + ";"));
        System.out.println();
        System.out.println("=========================================================");
        double[] result = new double[this.bias.length];
        for (int i = 0; i < result.length; ++i) {
            for (int j = 0; j < leftInput.length; ++j) {
                result[i] += leftInput[j] * synapses[j][i];
            }
            result[i] = this.activationFunction.apply(result[i] + this.bias[i]);
        }
        return result;
    }
}
