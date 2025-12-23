package rom.calculadora;

public class EstadoResultado implements EstadoCalculadora {

    public EstadoResultado(Calculadora calc) {
        // Constructor para compatibilidad
    }

    @Override
    public void ingresarNumero(Calculadora calc, double numero) {
        calc.setAcumulado(numero);
        calc.setOperacion(null);
        calc.setEstado(new EstadoIngresandoNumero(calc));
    }

    @Override
    public void seleccionarOperacion(Calculadora calc, Operacion operacion) {
        calc.setOperacion(operacion);
        calc.setEstado(new EstadoOperacionSeleccionada(calc));
    }

    @Override
    public double calcular(Calculadora calc, double numero) {
        return calc.getAcumulado();
    }

    @Override
    public void limpiar(Calculadora calc) {
        calc.setAcumulado(0);
        calc.setEstado(new EstadoInicial(calc));
    }
}