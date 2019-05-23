package neural2.network;

import java.util.HashMap;
import java.util.function.Function;

public class ActivationFunction {
    private static HashMap<Double, Double> activation = new HashMap<>();
    private static HashMap<Double, Double> derivative = new HashMap<>();

    public static final Function<Double, Double> IDENTITY = x -> x; // (-\infty ,\infty )
    public static final Function<Double, Double> IDENTITY_D = x -> 1D;

    public static final Function<Double, Double> BINARY_STEP = x -> x < 0 ? 0D : 1D; // 0 ou 1
    public static final Function<Double, Double> BINARY_STEP_D = x -> 1D;

    public static final Function<Double, Double> SIGMOID = x -> 1D / (1 + Math.exp(-x)); // 0 < r < 1
    public static final Function<Double, Double> SIGMOID_D = x -> {
        double res1 = SIGMOID.apply(x);
        return res1 * (1 - res1);
    };

    public static final Function<Double, Double> TANH = x -> {
        double expx = Math.exp(x);
        double expmx = Math.exp(-x);
        return (expx - expmx) / (expx + expmx);
    }; // -1 < r < 1
    public static final Function<Double, Double> TANH_D = x -> 1 - Math.pow(TANH.apply(x), 2);
}
