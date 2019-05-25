package neural2.network.impl;

import neural2.network.ActivationFunction;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Arrays;
import java.util.Random;

public class SynapseLine {
    private double[][] synapses;
    private double[] bias;
    private ActivationFunction activationFunction;

    public SynapseLine(int left, int right, ActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
        this.synapses = new double[left][right];
        this.bias = new double[right];
        mutate(100);
    }

    public void mutate(double percent) {
        final int left = this.synapses.length;
        final int right = this.bias.length;
        final double truePercent = percent / 100D;

        Random r = new Random(System.nanoTime());
        for (int i = 0; i < right; ++i) {
            for (int j = 0; j < left; ++j) {
                if(r.nextDouble() < truePercent) {
                    this.synapses[j][i] = r.nextDouble() * 2 - 1;
                }
            }
            if(r.nextDouble() < truePercent) {
                this.bias[i] = r.nextDouble() * 2 - 1;
            }
        }
    }

    public double[] compute(double[] leftInput) {
        double[] result = new double[this.bias.length];
        for (int i = 0; i < result.length; ++i) {
            for (int j = 0; j < leftInput.length; ++j) {
                result[i] += leftInput[j] * synapses[j][i];
            }
            result[i] = this.activationFunction.computeFunction(result[i] + this.bias[i]);
        }
        return result;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof SynapseLine)) return false;

        SynapseLine that = (SynapseLine) o;

        return new EqualsBuilder()
                .append(synapses, that.synapses)
                .append(bias, that.bias)
                .append(activationFunction, that.activationFunction)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(synapses)
                .append(bias)
                .append(activationFunction)
                .toHashCode();
    }

    @Override
    public SynapseLine clone() {
        final SynapseLine synapseLine = new SynapseLine(this.synapses.length, this.bias.length, this.activationFunction);
        synapseLine.bias = ArrayUtils.clone(this.bias);
        synapseLine.synapses = ArrayUtils.clone(this.synapses);
        return synapseLine;
    }
}
