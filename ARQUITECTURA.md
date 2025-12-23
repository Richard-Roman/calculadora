# Arquitectura - Decisiones Clave

## ¿Por qué 4 estados?

```
Initial → Ingresando → OpSeleccionada → Resultado
```

Cada estado responde: **¿Qué operación es válida ahora?**

| Estado | Acumulado | Operación | Próximo Input |
|--------|-----------|-----------|---|
| Initial | 0 | null | dígito |
| Ingresando | 3.0 | null | operador o = |
| OpSeleccionada | 3.0 | Suma | segundo número |
| Resultado | 7.0 | anterior | número nuevo u operador |

**Problema que resuelve:**
- Presionar "+" al inicio → IGNORADO (no es valido en Initial)
- Presionar "3" dos veces → "33" no "6" (porque Ingresando no ejecuta)
- Presionar "=" sin operador → retorna acumulado (es no-op válido)

Sin este diseño necesitarías validar en controller o calculadora → violación de responsabilidades.

---

## ¿Por qué Strategy para operaciones?

Calculadora ejecuta `operacion.ejecutar(a, b)` sin conocer que es Suma o Division.

**Ventaja:** Agregar √ = 1 clase nueva
```java
public class RaizCuadrada implements OperacionUnaria {
    if (n < 0) throw new ArithmeticException(...);
    return Math.sqrt(n);
}
```

**Sin Strategy:** Calculadora necesitaría:
```java
if (operacion == "RAIZ") {
    if (n < 0) throw...
    return Math.sqrt(n);
}
```

Esto escala exponencialmente con cada operación nueva.

---

## ¿Dónde se validan errores?

**Cada operación valida su restricción:**
- `Division` → valida b ≠ 0
- `RaizCuadrada` → valida n ≥ 0
- `Inverso` → valida n ≠ 0

**Controller captura:**
```java
try {
    calculadora.calcular(numero);
} catch (ArithmeticException e) {
    display.setText("Error");
    calculadora.limpiar();
}
```

**Beneficio:** Si agregas Logaritmo con su propia validación, Controller no cambia.

---

## ¿Por qué BigDecimal.stripTrailingZeros()?

```
7.0 → "7"     (no "7.0")
0.1 → "0.1"   (no "0.100000")
```

Sin esto, los números se ven extraños. Pero esto es **formateo visual**, no lógica matemática, así que vive en `Calculadora.formatearNumero()`, no en operaciones.

---

## ¿Cómo extender sin romper?

### Agregar nueva operación binaria

```java
// 1. Nueva clase (CreadorOperaciones ya maneja instanciación)
public class Logaritmo implements Operacion {
    @Override
    public double ejecutar(double a, double b) {
        return Math.log(b) / Math.log(a);
    }
}

// 2. Registrar en factory (1 línea)
operaciones.put("LOG", Logaritmo::new);

// 3. Agregar botón (1 case en switch)
case "log" -> ejecutarSeguro(() -> {
    if (numeroActual.length() > 0) {
        ultimoNumero = Double.parseDouble(numeroActual.toString());
    }
    calculadora.seleccionarOperacion(creadorOp.crear("LOG"));
}, display);
```

**Clases que NO cambian:**
- Calculadora
- Estados
- Otras operaciones

Esta es la esencia del **Open/Closed Principle**: abierto a extensión, cerrado a modificación.

---

## ¿Por qué Controller entre UI y Dominio?

```
CalculadoraView (botón presionado)
    ↓
CalculadoraUIController (parsea "3" → 3.0, atrapa errores)
    ↓
Calculadora (ingresarNumero, calcular)
```

**Problema sin Controller:**
- View instancia Calculadora directamente
- View debe saber cómo parsear números
- View debe saber qué excepciones atrapar
- Cuando cambias Calculadora, cambias View

**Con Controller:**
- View solo sabe llamar `procesarEntrada(texto)`
- Controller es responsable de la traducción
- Calculadora es completamente independiente de UI
- Podrías reemplazar UI por CLI sin tocar Calculadora

