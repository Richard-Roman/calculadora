package rom.calculadora;

public class Cuadrado implements OperacionUnaria {

    @Override
    public double ejecutar(double operando) {
        return operando * operando;
    }
}
