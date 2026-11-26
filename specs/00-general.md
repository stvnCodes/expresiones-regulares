# 00. General

| Spec | Contenido | Estado |
|---|---|---|
| 01 | Base, evaluador y menú | Aprobada |
| 02 | Colombia: 1-4, 12, 17 y 20 | Aprobada; falta que Estiven repita la prueba manual |
| 03 | Fechas, horas y web: 5-8, 10 y 14 | Aprobada |
| 04 | Programación: 9, 15 y 16 | Aprobada |
| 05 | Texto y libros: 11, 13, 18, 19 y 21 | Implementada |
| 06 | Revisión, PDF y ZIP | PDF completo; faltan pruebas manuales y ZIP |

## Reglas comunes
- Cada patrón tiene formato, expresión, explicación, válidas, inválidas, texto de demostración y reemplazo.
- Las expresiones no llevan `^` ni `$`: `matches` ya exige todo el texto y `find` debe poder buscar dentro de otro.
- El catálogo conserva el orden del enunciado.
- Las decisiones sobre cada formato están en `CLAUDE.md`.

## Arquitectura
`Main -> menus -> patrones -> classes`. Los menús también usan `utils`. `Catalogo` reúne los patrones de su paquete. `classes` no depende de nadie.
