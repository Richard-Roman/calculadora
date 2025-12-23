package rom.calculadora;

public class Inverso implements OperacionUnaria {

    @Override
    public double ejecutar(double operando) {
        if (operando == 0) {
            throw new ArithmeticException("No se puede calcular el inverso de cero");
        }
        return 1.0 / operando;
    }
}
