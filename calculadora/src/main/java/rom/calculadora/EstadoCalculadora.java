package rom.calculadora;

public interface EstadoCalculadora {
    void ingresarNumero(Calculadora calc, double numero);
    void seleccionarOperacion(Calculadora calc, Operacion operacion);
    double calcular(Calculadora calc, double numero);
    void limpiar(Calculadora calc);
}
