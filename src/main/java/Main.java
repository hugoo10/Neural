import neural2.builder.impl.NeuralBuilder;
import neural2.network.ActivationFunction;
import neural2.network.INeuralNetwork;
import neural2.network.impl.NeuralNetwork;

public class Main {

    public static void main(String[] args) {
        INeuralNetwork addNeuralNetwork = new NeuralBuilder().setInputSize(2)
                .addNeuralLine(6)
                .addNeuralLine(8)
                .addNeuralLine(6)
                .setResultSize(1)
                .build(new NeuralNetwork(ActivationFunction.IDENTITY));
        double[] result = addNeuralNetwork.compute(1.0, 1.0);
        System.out.println(result[0]);
    }
}
