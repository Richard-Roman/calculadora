package rom.calculadora;

public class Potencia implements Operacion {

    @Override
    public double ejecutar(double a, double b) {
        return Math.pow(a, b);
    }
}
