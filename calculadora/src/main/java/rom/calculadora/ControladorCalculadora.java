package rom.calculadora;

public class ControladorCalculadora {

    private final Calculadora calculadora = new Calculadora();

    public void ingresarNumero(double n) {
        calculadora.ingresarNumero(n);
    }

    public void seleccionarOperacion(Operacion op) {
        calculadora.seleccionarOperacion(op);
    }

    public double calcular(double n) {
        return calculadora.calcular(n);
    }

    public String getDisplay() {
        return calculadora.getDisplay();
    }
}
