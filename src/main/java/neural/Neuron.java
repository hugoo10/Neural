package neural;

public class Neuron {
    private String name = "no_name";
    private double value = 0;
    private double bias = 0.1;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getBias() {
        return bias;
    }

    public void setBias(double bias) {
        this.bias = bias;
    }
}
