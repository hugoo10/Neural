package neural2.builder;

public interface INeuralStartedBuilder {
    INeuralStartedBuilder addNeuralLine(int size);

    INeuralEndedBuilder setResultSize(int size);
}
