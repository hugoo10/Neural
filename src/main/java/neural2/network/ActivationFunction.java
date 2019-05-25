package neural2.network;

import java.util.function.Function;

public enum ActivationFunction {
    IDENTITY(
            x -> x,
            x -> 1D
    ),
    BINARY_STEP(
            x -> x < 0 ? 0D : 1D,
            x -> 1D
    ),
    SIGMOID(
            x -> 1D / (1 + Math.exp(-x)),
            x -> {
                double res1 = 1D / (1 + Math.exp(-x));
                return res1 * (1 - res1);
            }
    ),
    TANH(
            x -> {
                double expx = Math.exp(x);
                double expmx = Math.exp(-x);
                return (expx - expmx) / (expx + expmx);
            },
            x -> {
                double expx = Math.exp(x);
                double expmx = Math.exp(-x);
                double tanh = (expx - expmx) / (expx + expmx);
                return 1 - Math.pow(tanh, 2);
            }
    );

    private Function<Double, Double> function;
    private Function<Double, Double> derivative;

    ActivationFunction(Function<Double, Double> function, Function<Double, Double> derivative) {
        this.function = function;
        this.derivative = derivative;
    }

    public double computeFunction(double input) {
        return function.apply(input);
    }

    public double computeDerivative(double input) {
        return derivative.apply(input);
    }
}
