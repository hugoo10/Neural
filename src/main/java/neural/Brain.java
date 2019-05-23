package neural;

import java.util.ArrayList;
import java.util.List;

public class Brain {
    private List<List<Neuron>> layers;
    private List<Synapse> synapses;

    public Brain(int nbNeurons) {
        this.layers = new ArrayList<>();
        this.synapses = new ArrayList<>();
        this.generateNeurons(nbNeurons);
    }

    public List<Neuron> generateNeurons(int nbNeurons) {
        List<Neuron> neurons = new ArrayList<>();
        for (int i = 0; i < nbNeurons; ++i) {
            neurons.add(new Neuron());
        }
        this.layers.add(neurons);
        return neurons;
    }

    public void addLayer(int nbNeurons) {
        int newLayerIdx = this.layers.size();
        this.layers.add(generateNeurons(nbNeurons));
        this.synapses.add(new Synapse(this.layers.get(newLayerIdx - 1), this.layers.get(newLayerIdx)));
    }

    public List<Neuron> compute() {
        this.synapses.forEach(Synapse::compute);
        return this.layers.get(this.layers.size() - 1);
    }
}
