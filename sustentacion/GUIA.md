# Guía personal de sustentación

Documento de estudio. No hace parte de la entrega.

## 1. El taller en un minuto
> "Cada dato tiene una expresión regular que describe su forma. El programa permite verla, probarla con ejemplos y aplicar seis métodos de Java."

**Avance:** base, Colombia, fechas, horas y web.

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
- `Catalogo`: los reúne. Está en `patrones` para que `classes` no dependa de nadie.
- `MenuPrincipal` y `MenuPatron`: interacción.
- `Entrada`: lee y valida.
- `Main`: arranca y atrapa errores de lectura.

Recorrido: `Main` abre `MenuPrincipal`, este pide el patrón a `Catalogo`, y `MenuPatron` usa `Evaluador`.

## 5. Placas: `\b[A-Z]{3}[- ]?\d{3}\b`
Tres letras, separador opcional y tres dígitos. `ABC1234` falla porque sobra un dígito.

### Otros patrones de Colombia

| Dato | Expresión | Idea |
|---|---|---|
| Postal | `\b0500\d{2}\b` | 05 Antioquia, 00 Medellín y dos dígitos de zona |
| Fijo | `(\+57 ?\|\b)604[- ]?\d{3}[- ]?\d{2}[- ]?\d{2}\b` | 604 y siete dígitos |
| Celular | `(\+57 ?\|\b)3\d{2}[- ]?\d{3}[- ]?\d{2}[- ]?\d{2}\b` | inicia en 3, diez dígitos |
| Barras | `\b77[01]\d{10}\b` | 770 o 771 y diez dígitos: trece |
| Normas | `\b(([Ll]ey\|[Dd]ecreto) [1-9]\d{0,3} de (18\|19\|20)\d{2}\|[Aa]rtículo [1-9]\d{0,3})\b` | ley o decreto con año, o artículo |
| Precio | `\$ ?[1-9](\d{0,2}(\.\d{3})+\|\d*)(,\d{2})?(?![.,]?\d)` | con o sin puntos de miles, centavos opcionales |

(La barra `\|` en la tabla es solo `|`, que significa "o".)

- `\+`, `\$` y `\.` llevan `\` porque solos tienen otro significado.
- `(?![.,]?\d)` mira lo que sigue sin tomarlo: no deja aceptar el 12 de `$ 12.50`.

### Fechas, horas y web

- **Fecha:** se divide por días: 01-28 (todos los meses), 29-30 (menos febrero), 31 (siete meses) y 29/02 solo en bisiestos.
- **Hora 24:** `\b([01]\d|2[0-3])(:[0-5]\d){2}\b`. `[01]\d` es 00-19 y `2[0-3]` es 20-23.
- **Hora 12:** `\b(0[1-9]|1[0-2])(:[0-5]\d){2} ([AP]M|[ap]m)\b`.
- **Correo:** usuario, `@`, partes del dominio terminadas en punto y extensión de dos letras o más.
- **URL:** inicio, dominio, puerto opcional y ruta opcional.
- **Contraseña:** cada `(?=...)` es un requisito que se revisa sin avanzar, como un portero con una lista. `\p{Lu}` y `\p{Ll}` son mayúscula y minúscula, incluidas Ñ y tildes; `\p{Punct}` es un símbolo.

## 6. Preguntas probables
- **¿Por qué no basta `\d{2}/\d{2}/\d{4}` para la fecha?** Aceptaría 99/99/9999.
- **¿Cómo detecta un bisiesto?** Los dos últimos dígitos deben ser divisibles por 4. 1900 no es bisiesto y 2000 sí, por eso 2000 va aparte.
- **¿Qué es `(?=...)`?** Mira hacia adelante sin tomar caracteres; así se exigen varias cosas sin importar el orden.
- **¿El código de barras revisa el dígito de control?** No. Es una cuenta matemática y la expresión solo valida la forma.
- **¿604 es solo Medellín?** No, es de toda Antioquia. El número no permite distinguir el municipio.
- **¿Acepta cualquier postal 0500xx?** Sí, valida la forma, no que la zona exista.
- **¿Por qué `(\+57 ?|\b)`?** El número empieza con +57 o en un borde, así no se toma la mitad de un número más largo.
- **¿Por qué no usa `^` y `$`?** `matches` ya exige todo el texto, y con ellos `find` no encontraría placas dentro de una frase.
- **¿Diferencia entre `matches` y `lookingAt`?** `ABC123 hola`: `lookingAt` dice sí y `matches` no.
- **¿Por qué `quoteReplacement`?** En un reemplazo, `$1` significa un grupo. Así se toma como texto normal.
- **¿Por qué `split` pierde la última parte vacía?** Java descarta las partes vacías del final, pero conserva la del comienzo.
- **¿Cómo se agrega un patrón?** Se crea en su clase de `patrones` y se incluye en `Catalogo`. No cambian menús ni evaluador.

## 7. Orden de estudio
1. Símbolos y seis métodos. 2. `Patron` y `PatronesColombia`. 3. `Evaluador`. 4. `Catalogo`, menús, `Entrada` y `Main`.
