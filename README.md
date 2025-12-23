# Calculadora JavaFX

Una calculadora con arquitectura limpia: State Pattern (4 estados), Strategy Pattern (operaciones intercambiables), manejo de errores centralizado.

## Características

- Operaciones: +, −, ×, ÷, %, ^, √, x², 1/x, ±
- Funciones: C (clear all), CE (clear entry), ← (backspace)
- Formateo: BigDecimal sin notación científica
- Errores: 1/0, √(-4) capturados y mostrados

## Inicio Rápido

```bash
cd calculadora/calculadora
mvn clean compile
mvn javafx:run
```

## Documentación Clave

**[ARQUITECTURA.md](./ARQUITECTURA.md)** ← LEER PRIMERO
- ¿Por qué 4 estados?
- ¿Por qué Strategy para operaciones?
- Cómo extender sin romper nada

**Diagramas** (para visualizar):
- `DIAGRAMA_CLASES.puml` - estructura
- `DIAGRAMA_ESTADOS.puml` - máquina de estados
- `DIAGRAMA_LECTURA_RAPIDA.md` - guía visual

## Extender el Sistema

### Agregar nueva operación

```java
// 1. Crear clase
public class Logaritmo implements Operacion {
    public double ejecutar(double a, double b) {
        return Math.log(b) / Math.log(a);
    }
}

// 2. Registrar en CreadorOperaciones
operaciones.put("LOG", Logaritmo::new);

// 3. Agregar botón en CalculadoraView
case "log" -> { /* procesarEntrada */ }
```

**Para entender POR QUÉ se diseñó así, ver [ARQUITECTURA.md](./ARQUITECTURA.md).**

## Compilar y Ejecutar

```bash
mvn clean package
java -jar target/calculadora-1.0-SNAPSHOT.jar
```

## Patrones Usados

- **State**: 4 estados (`EstadoInicial`, `EstadoIngresandoNumero`, `EstadoOperacionSeleccionada`, `EstadoResultado`)
- **Strategy**: Operaciones intercambiables (6 binarias + 4 unarias)
- **Factory**: `CreadorOperaciones` y `CreadorOperacionesUnarias` desacoplan instanciación
- **MVC**: `CalculadoraView` (UI) → `CalculadoraUIController` (controlador) → `Calculadora` (dominio)

## Stack Técnico

- Java 17 OpenJDK
- JavaFX 17.0.9
- Maven 3.8.1+
2. **Historial de cálculos** (click para reutilizar)
3. **Memoria (M+, M-, MR, MC)**
4. **Conversión de unidades** (temperatura, distancia)
5. **Modo científico** (con más funciones)
6. **Precisión BigDecimal** en operaciones (no solo formateo)

## Recursos

- **Documentación**: Ver [`ARQUITECTURA.md`](./ARQUITECTURA.md)
- **Diagramas UML**: Abrir archivos `.puml` en [PlantUML Editor](http://www.plantuml.com/plantuml/uml/)

## Licencia

MIT License - Ver LICENSE file para detalles

## Desarrollador

Richard - Diciembre 2025

---

**¿Preguntas o sugerencias?** Revisa los diagramas y documentación técnica incluida. La arquitectura está diseñada para ser fácil de entender y extender.
