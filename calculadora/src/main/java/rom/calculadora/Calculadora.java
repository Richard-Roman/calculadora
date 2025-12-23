package rom.calculadora;

import java.math.BigDecimal;

public class Calculadora {

    private EstadoCalculadora estado;
    private double acumulado;
    private Operacion operacion;
    private String displayBuffer;

    public Calculadora() {
        this.estado = new EstadoInicial(this);
        this.acumulado = 0;
        this.displayBuffer = "0";

    }

    void setEstado(EstadoCalculadora estado) {
        this.estado = estado;
    }

    // delegación al estado
    public void ingresarNumero(double numero) {
        estado.ingresarNumero(this, numero);
        actualizarDisplay(numero);
    }

    public void seleccionarOperacion(Operacion operacion) {
        estado.seleccionarOperacion(this, operacion);
    }

    public double calcular(double numero) {
        double resultado = estado.calcular(this, numero);
        actualizarDisplay(resultado);
        return resultado;
    }

    public double aplicarOperacionUnaria(OperacionUnaria operacion) {
        double resultado = operacion.ejecutar(acumulado);
        acumulado = resultado;
        actualizarDisplay(resultado);
        return resultado;
    }

    public void limpiar() {
        estado.limpiar(this);
        displayBuffer = "0";
    }

    void setAcumulado(double n) {
        this.acumulado = n;
    }

    double getAcumulado() {
        return acumulado;
    }

    void setOperacion(Operacion op) {
        this.operacion = op;
    }

    Operacion getOperacion() {
        return operacion;
    }

    public String getDisplay() {
        return displayBuffer;
    }

    void setDisplay(String texto) {
        this.displayBuffer = texto;
    }

    private String formatearNumero(double numero) {
        return BigDecimal.valueOf(numero)
                .stripTrailingZeros()
                .toPlainString();
    }

    void actualizarDisplay(double numero) {
        displayBuffer = formatearNumero(numero);
    }
}


