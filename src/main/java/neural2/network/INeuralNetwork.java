package neural2.network;

public interface INeuralNetwork {
    void addSynapseLine(int leftSize, int rightSize);

    double[] compute(double... input);
}
