# 🎰 Casino POO — Proyecto Unidad 3

> Proyecto final de la asignatura **Programación Orientada a Objetos**  
> Tecnológico de Software · Ingeniería en Desarrollo de Software · 2do Semestre · 2026

---

## 👥 Equipo de desarrollo

| Nombre | GitHub |
|---|---|
| Astrit Cetzal | [@astritcetzal](https://github.com/astritcetzal) |
| Carlos Alfonso Llanes | [@ Carlos-Llanes-Rodriguez](https://github.com/carlosalfonso070717-blip) |
| Fernando Castro | [@Fernando-Castro-Hernandez](https://github.com/Fernando-Castro-Hernandez) |
| Mateo Martin | — |
| Venus Semino | — |

---

## 📋 Descripción del proyecto

Este proyecto es la entrega final de la Unidad 3 de **Programación Orientada a Objetos**,
desarrollado en equipo por 5 estudiantes del Tecnológico de Software. Consiste en la
simulación de un casino funcional construido completamente en Java, sin frameworks
externos ni herramientas de build — solo `javac` y `java`.

El sistema modela el funcionamiento real de un casino: los jugadores se registran con
su información personal, depositan saldo y participan en mesas de juego. Los empleados
(crupiers) son asignados a mesas y las supervisan. El casino actúa como contenedor
central que administra jugadores, empleados y juegos de forma organizada.

Se implementaron dos juegos de mesa completamente funcionales:

- **Ruleta Europea** — el jugador elige un número (0–36) y un color (Rojo, Negro o Verde).
  El sistema gira la ruleta con `Math.random()`, determina el resultado y aplica los pagos
  reales del juego: **35 a 1** si acierta el número exacto, **2 a 1** si acierta solo el color.
  El saldo se descuenta antes del giro y se acredita si hay premio.

- **BlackJack Clásico** — implementación del juego de cartas con baraja completa,
  lógica de turno del jugador y turno de la casa, condición de victoria por 21 puntos
  y manejo de ases con valor variable (1 u 11).

Para proteger la integridad del sistema, cada operación crítica está blindada con
**excepciones personalizadas**: no se puede crear una mesa con apuesta mínima menor
a $100 o máxima mayor a $35,000, no se puede jugar sin haber iniciado la sesión,
no se puede registrar el mismo jugador dos veces, y ninguna persona menor de 18 años
puede entrar al sistema — la regla se aplica desde la clase base `Persona` para que
ninguna subclase pueda saltársela.

Los datos de jugadores y empleados **se guardan automáticamente en archivos CSV**
cada vez que se realizan cambios, y se recuperan al iniciar el programa. Esto se logra
a través de una capa de persistencia basada en el patrón **Repository**: las interfaces
`JugadorRepository` y `PersonaRepository` definen el contrato, y las clases
`JugadorArchivo` y `EmpleadoArchivo` implementan la lectura y escritura real con
`PrintWriter`, `BufferedReader` y el patrón try-with-resources.

Toda la arquitectura está organizada en **4 capas bien diferenciadas** (Modelo →
Persistencia → Servicio → Main) siguiendo los principios de diseño vistos en clase.
Cada capa tiene una responsabilidad única y se comunica con la siguiente a través
de interfaces, lo que hace el sistema extensible y fácil de mantener.

---

## 🏗️ Arquitectura del sistema

El proyecto sigue una **arquitectura de 4 capas**:

```
┌─────────────────────────────────────────────┐
│                  Main / UI                  │  ← Punto de entrada, orquesta todo
├─────────────────────────────────────────────┤
│                  Servicio                   │  ← Lógica de negocio y validaciones
│         (JugadorService, EmpleadoService)   │
├─────────────────────────────────────────────┤
│                 Persistencia                │  ← Lectura/escritura CSV
│         (JugadorArchivo, EmpleadoArchivo)   │
├─────────────────────────────────────────────┤
│                   Modelo                   │  ← Clases del dominio
│  (Persona, Jugador, JugadorVIP, Empleado,  │
│   JuegoMesa, Ruleta, BlackJack, Casino)    │
└─────────────────────────────────────────────┘
```

---

## 🗂️ Estructura de paquetes

```
src/casino/
├── 📦 persona/
│   ├── Persona.java         
│   ├── Jugador.java          
│   ├── JugadorVIP.java       
│   └── Empleado.java
│
├── 📦 juegos/
│   ├── JuegoMesa.java        
│   ├── Ruleta.java
│   └── BlackJack.java
│
├── 📦 interfaces/
│   ├── Jugable.java
│   └── Apostable.java
│
├── 📦 exceptions/
│   ├── SaldoInsuficienteException.java
│   ├── ApuestaMinimaInvalidaException.java
│   ├── ApuestaMaximaInvalidaException.java
│   ├── ApuestaInvalidaRuletaException.java
│   ├── JuegoInactivoRuletaException.java
│   ├── JuegoInactivoException.java
│   ├── IDJugadorDuplicadoException.java
│   └── CedulaEmpleadoDuplicadoException.java
│
├── 📦 repositorio/
│   ├── JugadorRepository.java   (interfaz)
│   └── PersonaRepository.java   (interfaz)
│
├── 📦 persistencia/
│   ├── JugadorArchivo.java
│   └── EmpleadoArchivo.java
│
├── 📦 servicio/
│   ├── JugadorService.java
│   └── EmpleadoService.java
│
└── 📦 sistema/
    ├── Casino.java
    └── Main.java
```

---

## 🎮 ¿Cómo funciona el sistema?

### Flujo de una partida de Ruleta
```
1. casino.agregarRuleta(...)   →  Crea la mesa, valida apuestas min/max
2. mesaRuleta.iniciar(jugador) →  Activa la mesa, muestra bienvenida
3. mesaRuleta.setApuesta(...)  →  El jugador elige número, color y monto
4. empleado.supervisarMesa()   →  El crupier supervisa la mesa
5. mesaRuleta.jugar()          →  Giro aleatorio → pago 35:1 (número) o 2:1 (color)
6. mesaRuleta.terminar()       →  Muestra saldo final, cierra la partida
```

### Jerarquía de Personas
```
Persona (abstract)
├── Jugador (implements Apostable)
│   └── JugadorVIP  ← override de apostar() con límite especial + bonus
└── Empleado        ← tiene referencia a JuegoMesa (supervisarMesa)
```

### Jerarquía de Juegos
```
Jugable (interface) → iniciar / jugar / terminar
        ↑
JuegoMesa (abstract) → validaciones comunes, apuesta min/max
        ↑
   ┌────┴────┐
Ruleta    BlackJack
```

---

## 📚 Conceptos de POO de la Unidad 3 aplicados

### 🔴 Excepciones personalizadas
- Jerarquía propia de excepciones que extienden `Exception`
- `throws` en firmas de métodos que pueden fallar
- `try/catch` con múltiples excepciones capturadas en `Main`
- Validaciones en constructores (apuesta fuera de rango, jugador duplicado)

### 🟡 Colecciones y genéricos
- `List<T>` como tipo de referencia, `ArrayList<>` como implementación
- `for-each` para recorrer jugadores, empleados y juegos
- `Iterator<T>` para eliminación segura en `EmpleadoService` y `JugadorService`  
  (evita `ConcurrentModificationException`)
- Filtrado con patrón: lista vacía → recorrer → evaluar condición → agregar

```java
// Ejemplo: filtrar jugadores VIP del servicio
public List<Jugador> filtrarVIP() {
    List<Jugador> resultado = new ArrayList<>();
    for (Jugador j : jugadores)
        if (j.getRol().equals("Jugador VIP"))
            resultado.add(j);
    return resultado;
}
```

### 🟢 Persistencia simple (CSV)
- `toCSV()` serializa el objeto a una línea de texto
- `fromCSV(String)` reconstruye el objeto desde el archivo
- `PrintWriter + FileWriter` para escritura con try-with-resources
- `BufferedReader + FileReader` para lectura línea por línea
- `File.exists()` para manejar la primera ejecución sin error
- `StringBuilder` para construcción eficiente del CSV

```java
// Escritura
try (PrintWriter pw = new PrintWriter(new FileWriter(ruta))) {
    pw.println("Nombre,Apellido,Cedula,Edad,Cargo,Salario");
    for (Empleado e : empleados) { pw.println(e.toCSV()); }
}
// Lectura
try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
    String linea; boolean primera = true;
    while ((linea = br.readLine()) != null) {
        if (primera) { primera = false; continue; } // salta header
        empleados.add(Empleado.fromCSV(linea));
    }
}
```

### 🔵 Patrones de diseño
- **Repository pattern**: interfaces `JugadorRepository` / `PersonaRepository`
  separan el contrato de persistencia de su implementación
- **Inyección de dependencias**: el servicio recibe el repositorio por constructor,
  no lo instancia él mismo

---

## 📁 Archivos generados

Al ejecutar el sistema se crean automáticamente:

| Archivo | Contenido |
|---|---|
| `jugadores.csv` | Jugadores registrados vía `JugadorService` |
| `empleadoarchivo.csv` | Empleados registrados vía `EmpleadoService` |

---

## 🛡️ Validaciones del sistema

| Regla | Excepción lanzada |
|---|---|
| Apuesta mínima < $100 | `ApuestaMinimaInvalidaException` |
| Apuesta máxima > $35,000 | `ApuestaMaximaInvalidaException` |
| Apuesta fuera del rango de la mesa | `ApuestaInvalidaRuletaException` |
| Jugar sin haber iniciado la mesa | `JuegoInactivoRuletaException` |
| Registrar jugador con ID duplicado | `IDJugadorDuplicadoException` |
| Registrar empleado con cédula duplicada | `CedulaEmpleadoDuplicadoException` |
| Persona menor de 18 años | `IllegalArgumentException` |

---

*Proyecto desarrollado para la asignatura Programación Orientada a Objetos*  
*Unidad 3: Construcción y calidad de software — Enero–Abril 2026*
