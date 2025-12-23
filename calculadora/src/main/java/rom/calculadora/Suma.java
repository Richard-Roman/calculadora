package rom.calculadora;

public class Suma implements Operacion {

    @Override
    public double ejecutar(double a, double b) {
        return a + b;
    }
}

