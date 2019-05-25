package neural2.network.impl;

import neural2.network.ActivationFunction;
import neural2.network.INeuralNetwork;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NeuralNetwork implements INeuralNetwork {
    private List<SynapseLine> synapseLines = new ArrayList<>();
    private ActivationFunction activationFunction;
    private double[] result;
    private int wins = 0;

    public NeuralNetwork(ActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
    }

    @Override
    public void addSynapseLine(int leftSize, int rightSize) {
        synapseLines.add(new SynapseLine(leftSize, rightSize, activationFunction));
    }

    @Override
    public double[] compute(double[] input) {
        this.result = null;
        double[] result = input;
        for (SynapseLine synapseLine : synapseLines) {
            result = synapseLine.compute(result);
        }
        this.result = result;
        return result;
    }

    @Override
    public INeuralNetwork copyAndApplyMutation(double percent) {
        final NeuralNetwork neuralNetwork = new NeuralNetwork(this.activationFunction);
        neuralNetwork.synapseLines = this.synapseLines.stream().map(sl -> {
            SynapseLine synapseLine = sl.clone();
            synapseLine.mutate(percent);
            return synapseLine;
        }).collect(Collectors.toList());
        return neuralNetwork;
    }

    @Override
    public double[] getResult() {
        return this.result;
    }

    @Override
    public void addWin() {
        this.wins++;
    }

    public int getWins() {
        return wins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof NeuralNetwork)) return false;

        NeuralNetwork that = (NeuralNetwork) o;

        return new EqualsBuilder()
                .append(synapseLines, that.synapseLines)
                .append(activationFunction, that.activationFunction)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(synapseLines)
                .append(activationFunction)
                .toHashCode();
    }


}
