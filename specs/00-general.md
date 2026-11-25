# 00. General

| Spec | Contenido | Estado |
|---|---|---|
| 01 | Base, evaluador y menú | Implementada, por aprobar |
| 02 | Colombia: 1-4, 12, 17 y 20 | Pendiente (placas se usó para probar la base) |
| 03 | Fechas, horas y web: 5-8, 10 y 14 | Pendiente |
| 04 | Programación: 9, 15 y 16 | Pendiente |
| 05 | Texto y libros: 11, 13, 18, 19 y 21 | Pendiente |
| 06 | Revisión, PDF, jar y ZIP | Pendiente |

## Reglas comunes
- Cada patrón tiene formato, expresión, explicación, válidas, inválidas, texto de demostración y reemplazo.
- Las expresiones no llevan `^` ni `$`: `matches` ya exige todo el texto y `find` debe poder buscar dentro de otro.
- El catálogo conserva el orden del enunciado.
- Las decisiones sobre cada formato están en `CLAUDE.md`.

## Arquitectura
`Main -> menus -> classes y utils`. `Catalogo` reúne lo que definen las clases de `patrones`.
