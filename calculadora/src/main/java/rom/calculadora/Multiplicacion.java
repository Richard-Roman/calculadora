package rom.calculadora;

public class Multiplicacion implements Operacion {

    @Override
    public double ejecutar(double a, double b) {
        return a * b;
    }
}
