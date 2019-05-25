package neural2.builder.impl;

import neural2.builder.INeuralBuilder;
import neural2.builder.INeuralEndedBuilder;
import neural2.builder.INeuralStartedBuilder;
import neural2.network.ActivationFunction;
import neural2.network.INeuralNetwork;
import neural2.network.impl.NeuralNetwork;

import java.util.ArrayList;
import java.util.List;

public class NeuralBuilder implements INeuralBuilder, INeuralEndedBuilder, INeuralStartedBuilder {
    private List<Integer> neuralLines = new ArrayList<>();
    private ActivationFunction activationFunction;

    public NeuralBuilder(ActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
    }

    @Override
    public INeuralStartedBuilder setInputSize(int size) {
        this.neuralLines.add(size);
        return this;
    }

    @Override
    public INeuralStartedBuilder addNeuralLine(int size) {
        this.neuralLines.add(size);
        return this;
    }

    @Override
    public INeuralEndedBuilder setResultSize(int size) {
        this.neuralLines.add(size);
        return this;
    }

    @Override
    public INeuralNetwork build() {
        NeuralNetwork neuralNetwork = new NeuralNetwork(activationFunction);
        for (int i = 0; i < neuralLines.size() - 1; ++i) {
            neuralNetwork.addSynapseLine(neuralLines.get(i), neuralLines.get(i + 1));
        }
        return neuralNetwork;
    }
}
