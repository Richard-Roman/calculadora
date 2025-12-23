package rom.calculadora;

public class CambioSigno implements OperacionUnaria {

    @Override
    public double ejecutar(double operando) {
        return -operando;
    }
}
