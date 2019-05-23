package neural2.network.impl;

import neural2.network.INeuralNetwork;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class NeuralNetwork implements INeuralNetwork {
    private List<SynapseLine> synapseLines = new ArrayList<>();
    private Function<Double, Double> activationFunction;

    public NeuralNetwork(Function<Double, Double> activationFunction) {
        this.activationFunction = activationFunction;
    }

    @Override
    public void addSynapseLine(int leftSize, int rightSize) {
        synapseLines.add(new SynapseLine(leftSize, rightSize, activationFunction));
    }

    @Override
    public double[] compute(double[] input) {
        double[] result = input;
        for (SynapseLine synapseLine : synapseLines) {
            result = synapseLine.compute(result);
        }
        return result;
    }
}
