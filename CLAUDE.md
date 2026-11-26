# Práctica de expresiones regulares (Algoritmos y Programación 4)

Profesor: Gildardo Quintero C. Java 21 en IntelliJ, sin dependencias.
Enunciado: `~/Downloads/PRÁCTICA 2 REGEX 2026-2.pdf`. Ante cualquier duda, manda el enunciado.

## Qué pide el enunciado
- Una expresión regular por cada dato, con su formato esperado descrito.
- Programa en Java con cadenas válidas e inválidas para cada patrón.
- Demostrar cada patrón con `matches`, `lookingAt`, `find`, `replaceAll`, `replaceFirst` y `split`
  (`lookingAt` solo aparece en la sección Entrega, pero se incluye).
- Se evalúa: formulación de cada expresión, comprensión, implementación y claridad.

### Los 21 patrones
1. Placas de carro en Colombia
2. Códigos postales de Medellín
3. Teléfonos fijos de Medellín
4. Celulares de Colombia
5. Correo electrónico (general)
6. Fecha dd/mm/aaaa
7. Hora de 24 horas hh:mm:ss
8. Hora de 12 horas hh:mm:ss AM/PM
9. Declaración de variables en Java (`int contador = 0;`)
10. URL (general)
11. ISBN (especificar si es ISBN-10 o ISBN-13)
12. Código de barras en Colombia
13. Nombres y apellidos con tildes y ñ, mayúsculas y minúsculas
14. Contraseña segura (definir criterios)
15. Ciclo for (`For(k=1; k<N;k++)`)
16. Número double (`3.14159`)
17. Leyes, decretos y artículos en Colombia
18. Referencias de libros impresos en IEEE
19. Referencias de libros impresos en APA
20. Precios de productos
21. Diptongos e hiatos

## Entrega
- PDF: introducción (importancia en la validación de datos); desarrollo con una sección por patrón
  (expresión, explicación breve, válidas e inválidas, código Java de prueba y resultados);
  conclusiones. El código fuente completo va en el ZIP y no en el PDF (decisión de Estiven;
  el enunciado lo lista dentro del documento y ya se le avisó).
- Diseño de referencia: `../conjunto/documentacion/Practica-Conjuntos.pdf` (su generador está en `../conjunto/documentacion/fuente`).
- Los resultados del PDF salen de ejecutar el programa. Preguntar los aprendizajes; no inventarlos.
- ZIP final solo con lo necesario, sin borrar el proyecto.
- Equipos de máximo 3 integrantes. La fecha límite se omite por decisión de Estiven.

## Reglas de trabajo
- Explicar en español, con palabras sencillas y ejemplos: el trabajo se sustenta.
- No crear ni cambiar nada sin aprobación. Avanzar spec por spec (`specs/`): implementar, probar, explicar y esperar visto bueno.
- No inventar requisitos. Si una petición contradice el enunciado, avisar.
- Git lo maneja Estiven: no hacer commits, ramas ni push; solo dar los comandos.
- Al terminar cada spec, actualizar `sustentacion/GUIA.md` (explicación desde cero, preguntas probables, orden de estudio). Es personal: no se entrega.
- El contenido actual de `src/` es un ejemplo de clase: se reemplaza.

## Código
- Prioridad: que funcione, que esté muy organizado y que sea fácil de leer y ampliar.
- Una responsabilidad por clase y métodos cortos. Sin repetir código.
- Estilo de `../conjunto`: sangría de 2 espacios, identificadores en español, camelCase y sin tildes, y secciones `// ------------------- Nombre ------------------- //`.
- Comentarios breves y naturales, sin emojis.
- Validar toda entrada y manejar errores: el programa no debe cerrarse por un dato malo.

## Pruebas
- Casos normales, límites e inválidos por patrón. Mostrar resultados reales y decir qué no se pudo probar.
- Al final: revisión a fondo y lista de pruebas manuales para Estiven.

## Decisiones aprobadas
- Equipo: Juan Estiven Posada Rúa, Andrés Santiago Bedoya Yepes y Carlos Eduardo Hernández Munévar.
- Aplicación de consola. Se reemplaza `src/Main.java` (confirmado).
- Paquetes: `classes` (Patron, Evaluador), `patrones` (Catalogo y definiciones por tema), `menus`, `utils` (Entrada).
- Agregar un patrón no debe obligar a cambiar los menús ni el evaluador.
- Se valida la forma: no se calculan dígitos de control.
- Placa solo de carro, con guion o espacio opcional. Postal `0500` y dos dígitos. Fijo 604 y 7 dígitos; celular 10 dígitos que inician en 3; ambos con +57 opcional.
- Fecha de 1900 a 2099, según el mes y bisiestos. Hora de 12 h de 01 a 12 con AM/PM.
- Variables: tipo, nombre y valor inicial opcional, acorde al tipo en casos comunes.
- ISBN-13 e ISBN-10 son dos patrones; el menú indica cuál es. Barras: EAN-13 con 770 o 771.
- Nombres: mínimo nombre y apellido, con mayúscula inicial o todo en mayúsculas.
- Contraseña de 8 a 20: mayúscula, minúscula, dígito y símbolo, sin espacios.
- For: se acepta `for` y `For`. Double: signo opcional, punto decimal y exponente.
- Leyes: `Ley 100 de 1993`, `Decreto 1072 de 2015`, `Artículo 23`.
- IEEE y APA: solo libros impresos, sin URL ni DOI.
- Precios en pesos: `$ 12.500` o `$ 12.500,50`. Diptongos e hiatos: dos patrones que encuentran palabras. El menú tiene 23 entradas para los 21 datos.

## Orden de specs
1. Base, Evaluador y menú. 2. Colombia (1-4, 12, 17, 20). 3. Fechas, horas y web (5-8, 10, 14).
4. Programación (9, 15, 16). 5. Texto y libros (11, 13, 18, 19, 21). 6. Revisión, guía, PDF y ZIP.

## Comandos de prueba
`javac -Xlint:all -d out/pruebas $(find src pruebas -name '*.java') && java -cp out/pruebas PruebaEvaluador && java -cp out/pruebas PruebaCatalogo && java -cp out/pruebas PruebaColombia && java -cp out/pruebas PruebaFechasWeb && java -cp out/pruebas PruebaProgramacion && java -cp out/pruebas PruebaTexto`

No se incluye `.jar`: el enunciado no lo pide.

## PDF
`python3 documentacion/fuente/generar.py` crea `documentacion/Practica-Expresiones-Regulares.pdf`.
`GenerarDesarrollo.java` ejecuta los patrones reales y escribe la sección de desarrollo; si un ejemplo no da lo esperado, falla.
Las conclusiones se basan en lo que pasó en el proyecto; Estiven delegó su redacción y puede ajustarlas.
