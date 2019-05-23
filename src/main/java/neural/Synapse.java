package neural;

import org.apache.commons.math3.linear.OpenMapRealMatrix;

import java.util.List;
import java.util.Random;

public class Synapse {
    private List<Neuron> layerBefore;
    private List<Neuron> layerAfter;
    private OpenMapRealMatrix weights;

    public Synapse(List<Neuron> layerBefore, List<Neuron> layerAfter) {
        this.layerBefore = layerBefore;
        this.layerAfter = layerAfter;
        this.weights = new OpenMapRealMatrix(this.layerAfter.size(), this.layerBefore.size());
        Random rand = new Random();
        for (int r = 0; r < this.weights.getRowDimension(); ++r) {
            for (int c = 0; c < this.weights.getColumnDimension(); ++c) {
                this.weights.addToEntry(r, c, (rand.nextDouble() * 2) - 1);
            }
        }
    }

    public void compute() {
        assignMatrixToNeurons(this.weights.multiply(extractValues(layerBefore)), layerAfter);
        layerAfter.parallelStream().forEach(neuron ->
                neuron.setValue(Math.max(-0.1, neuron.getValue() + neuron.getBias()))
        );
    }

    public static OpenMapRealMatrix extractValues(List<Neuron> neurons) {
        OpenMapRealMatrix result = new OpenMapRealMatrix(neurons.size(), 1);
        for (int r = 0; r < neurons.size(); ++r) {
            result.addToEntry(r, 0, neurons.get(r).getValue());
        }
        return result;
    }

    public static void assignMatrixToNeurons(OpenMapRealMatrix matrix, List<Neuron> neurons) {
        for (int r = 0; r < neurons.size(); ++r) {
            neurons.get(r).setValue(matrix.getEntry(r, 0));
        }
    }
}
