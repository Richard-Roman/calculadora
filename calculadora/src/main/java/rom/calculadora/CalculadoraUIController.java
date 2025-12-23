package rom.calculadora;

import javafx.scene.control.TextField;

public class CalculadoraUIController {

    private final Calculadora calculadora = new Calculadora();
    private final CreadorOperaciones creadorOp = new CreadorOperaciones();
    private final CreadorOperacionesUnarias creadorOpUnarias = new CreadorOperacionesUnarias();
    private StringBuilder numeroActual = new StringBuilder();
    private double ultimoNumero = 0;
    private boolean iniciarNuevoNumero = false;

    public void procesarEntrada(String entrada, TextField display) {

        if (entrada.matches("[0-9]")) {
            // Si debemos iniciar un nuevo número, limpiamos el buffer y notificamos a la calculadora
            if (iniciarNuevoNumero) {
                numeroActual.setLength(0);
                iniciarNuevoNumero = false;
            }
            
            numeroActual.append(entrada);
            display.setText(numeroActual.toString());
            return;
        }

        if (entrada.equals(".")) {
            if (iniciarNuevoNumero) {
                numeroActual.setLength(0);
                iniciarNuevoNumero = false;
            }
            
            if (numeroActual.length() == 0) {
                numeroActual.append("0.");
            } else if (numeroActual.indexOf(".") == -1) {
                numeroActual.append(".");
            }
            display.setText(numeroActual.toString());
            return;
        }

        // Convertir número actual si existe
        if (numeroActual.length() > 0) {
            ultimoNumero = Double.parseDouble(numeroActual.toString());
        }

        switch (entrada) {
            case "+" -> {
                ejecutarSeguro(() -> {
                    // Opción A: no permitir operador al inicio (display "0" y sin buffer)
                    if (numeroActual.length() == 0 && "0".equals(display.getText())) {
                        return; // ignorar
                    }
                    if (numeroActual.length() > 0) {
                        calculadora.ingresarNumero(ultimoNumero);
                        numeroActual.setLength(0);
                    }
                    calculadora.seleccionarOperacion(creadorOp.crear("SUMA"));
                    iniciarNuevoNumero = false;
                }, display);
            }
            case "−" -> {
                ejecutarSeguro(() -> {
                    if (numeroActual.length() == 0 && "0".equals(display.getText())) {
                        return;
                    }
                    if (numeroActual.length() > 0) {
                        calculadora.ingresarNumero(ultimoNumero);
                        numeroActual.setLength(0);
                    }
                    calculadora.seleccionarOperacion(creadorOp.crear("RESTA"));
                    iniciarNuevoNumero = false;
                }, display);
            }
            case "×" -> {
                ejecutarSeguro(() -> {
                    if (numeroActual.length() == 0 && "0".equals(display.getText())) {
                        return;
                    }
                    if (numeroActual.length() > 0) {
                        calculadora.ingresarNumero(ultimoNumero);
                        numeroActual.setLength(0);
                    }
                    calculadora.seleccionarOperacion(creadorOp.crear("MULTIPLICACION"));
                    iniciarNuevoNumero = false;
                }, display);
            }
            case "÷" -> {
                ejecutarSeguro(() -> {
                    if (numeroActual.length() == 0 && "0".equals(display.getText())) {
                        return;
                    }
                    if (numeroActual.length() > 0) {
                        calculadora.ingresarNumero(ultimoNumero);
                        numeroActual.setLength(0);
                    }
                    calculadora.seleccionarOperacion(creadorOp.crear("DIVISION"));
                    iniciarNuevoNumero = false;
                }, display);
            }
            case "=" -> {
                ejecutarSeguro(() -> {
                    if (numeroActual.length() > 0) {
                        ultimoNumero = Double.parseDouble(numeroActual.toString());
                        calculadora.ingresarNumero(ultimoNumero);
                        numeroActual.setLength(0);
                    }
                    calculadora.calcular(ultimoNumero);
                    display.setText(calculadora.getDisplay());
                    iniciarNuevoNumero = true;  // Después de = , el siguiente dígito inicia nuevo número
                }, display);
            }
            case "C" -> {
                calculadora.limpiar();
                display.setText("0");
                numeroActual.setLength(0);
                ultimoNumero = 0;
                iniciarNuevoNumero = false;
            }
            case "CE" -> {
                // Clear Entry: limpia solo el número actual sin afectar la operación
                numeroActual.setLength(0);
                display.setText("0");
                iniciarNuevoNumero = false;
            }
            case "←" -> {
                // Backspace: elimina el último dígito
                if (numeroActual.length() > 0) {
                    numeroActual.setLength(numeroActual.length() - 1);
                    display.setText(numeroActual.length() == 0 ? "0" : numeroActual.toString());
                }
            }
            case "√" -> {
                ejecutarSeguro(() -> {
                    if (numeroActual.length() > 0) {
                        ultimoNumero = Double.parseDouble(numeroActual.toString());
                        calculadora.ingresarNumero(ultimoNumero);
                    }
                    calculadora.aplicarOperacionUnaria(creadorOpUnarias.crear("RAIZ_CUADRADA"));
                    display.setText(calculadora.getDisplay());
                    numeroActual.setLength(0);
                    iniciarNuevoNumero = true;
                }, display);
            }
            case "x²" -> {
                ejecutarSeguro(() -> {
                    if (numeroActual.length() > 0) {
                        ultimoNumero = Double.parseDouble(numeroActual.toString());
                        calculadora.ingresarNumero(ultimoNumero);
                    }
                    calculadora.aplicarOperacionUnaria(creadorOpUnarias.crear("CUADRADO"));
                    display.setText(calculadora.getDisplay());
                    numeroActual.setLength(0);
                    iniciarNuevoNumero = true;
                }, display);
            }
            case "1/x" -> {
                ejecutarSeguro(() -> {
                    if (numeroActual.length() > 0) {
                        ultimoNumero = Double.parseDouble(numeroActual.toString());
                        calculadora.ingresarNumero(ultimoNumero);
                    }
                    calculadora.aplicarOperacionUnaria(creadorOpUnarias.crear("INVERSO"));
                    display.setText(calculadora.getDisplay());
                    numeroActual.setLength(0);
                    iniciarNuevoNumero = true;
                }, display);
            }
            case "±" -> {
                ejecutarSeguro(() -> {
                    if (numeroActual.length() > 0) {
                        ultimoNumero = Double.parseDouble(numeroActual.toString());
                        calculadora.ingresarNumero(ultimoNumero);
                    }
                    calculadora.aplicarOperacionUnaria(creadorOpUnarias.crear("CAMBIO_SIGNO"));
                    display.setText(calculadora.getDisplay());
                    numeroActual.setLength(0);
                    iniciarNuevoNumero = true;
                }, display);
            }
            case "%" -> {
                ejecutarSeguro(() -> {
                    if (numeroActual.length() == 0 && "0".equals(display.getText())) {
                        return;
                    }
                    if (numeroActual.length() > 0) {
                        calculadora.ingresarNumero(ultimoNumero);
                        numeroActual.setLength(0);
                    }
                    calculadora.seleccionarOperacion(creadorOp.crear("MODULO"));
                    iniciarNuevoNumero = false;
                }, display);
            }
        }
    }

    private void ejecutarSeguro(Runnable accion, TextField display) {
        try {
            accion.run();
        } catch (ArithmeticException | IllegalArgumentException e) {
            display.setText("Error");
            calculadora.limpiar();
            numeroActual.setLength(0);
            ultimoNumero = 0;
            iniciarNuevoNumero = true;
        }
    }
}
