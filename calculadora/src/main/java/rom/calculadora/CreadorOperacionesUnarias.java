package rom.calculadora;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class CreadorOperacionesUnarias {

    private final Map<String, Supplier<OperacionUnaria>> operaciones = new HashMap<>();

    public CreadorOperacionesUnarias() {
        operaciones.put("RAIZ_CUADRADA", RaizCuadrada::new);
        operaciones.put("CUADRADO", Cuadrado::new);
        operaciones.put("INVERSO", Inverso::new);
        operaciones.put("CAMBIO_SIGNO", CambioSigno::new);
    }

    public OperacionUnaria crear(String tipo) {
        Supplier<OperacionUnaria> supplier = operaciones.get(tipo.toUpperCase());
        if (supplier == null) {
            throw new IllegalArgumentException("Operación unaria no soportada: " + tipo);
        }
        return supplier.get();
    }
}
