# Guía personal de sustentación

Documento de estudio. No hace parte de la entrega.

## 1. El taller en un minuto
> "Cada dato tiene una expresión regular que describe su forma. El programa permite verla, probarla con ejemplos y aplicar seis métodos de Java."

**Avance:** base lista, con placas como primer patrón.

## 2. Desde cero
Una expresión regular es un molde para texto. `Pattern` guarda el molde y `Matcher` lo compara con un texto.

| Símbolo | Significa | Ejemplo |
|---|---|---|
| `\d` | un dígito | `7` |
| `[A-Z]` | una letra del rango | `M` |
| `{3}` | exactamente tres | `\d{3}` -> `123` |
| `?` | puede estar o no | `-?` |
| `+` | una o más veces | `\d+` -> `2026` |
| `\b` | borde de palabra | separa `ABC123` de `ABC1234` |

En Java se escribe `"\\d"` porque `\` ya es especial dentro de un String.

## 3. Los seis métodos
Con `\d+` y `hoy 25 del 2026`:

| Método | Qué hace | Resultado |
|---|---|---|
| `matches` | todo el texto | No |
| `lookingAt` | el comienzo | No |
| `find` | busca dentro | 25 y 2026 |
| `replaceAll` | cambia todas | `hoy # del #` |
| `replaceFirst` | cambia la primera | `hoy # del 2026` |
| `split` | corta donde aparece | `"hoy "`, `" del "` |

## 4. Las clases
- `Patron`: los datos de un patrón.
- `Evaluador`: los seis métodos.
- `PatronesColombia`: crea patrones de un tema.
- `Catalogo`: los reúne.
- `MenuPrincipal` y `MenuPatron`: interacción.
- `Entrada`: lee y valida.
- `Main`: arranca y atrapa errores de lectura.

Recorrido: `Main` abre `MenuPrincipal`, este pide el patrón a `Catalogo`, y `MenuPatron` usa `Evaluador`.

## 5. Placas: `\b[A-Z]{3}[- ]?\d{3}\b`
Tres letras, separador opcional y tres dígitos. `ABC1234` falla porque sobra un dígito.

## 6. Preguntas probables
- **¿Por qué no usa `^` y `$`?** `matches` ya exige todo el texto, y con ellos `find` no encontraría placas dentro de una frase.
- **¿Diferencia entre `matches` y `lookingAt`?** `ABC123 hola`: `lookingAt` dice sí y `matches` no.
- **¿Por qué `quoteReplacement`?** En un reemplazo, `$1` significa un grupo. Así se toma como texto normal.
- **¿Por qué `split` pierde la última parte vacía?** Java descarta las partes vacías del final, pero conserva la del comienzo.
- **¿Cómo se agrega un patrón?** Se crea en su clase de `patrones` y se incluye en `Catalogo`. No cambian menús ni evaluador.

## 7. Orden de estudio
1. Símbolos y seis métodos. 2. `Patron` y `PatronesColombia`. 3. `Evaluador`. 4. `Catalogo`, menús, `Entrada` y `Main`.
