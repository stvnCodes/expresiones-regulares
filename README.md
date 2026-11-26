# Práctica de expresiones regulares

Aplicación de consola en Java 21 que valida veintiún formatos de datos con expresiones regulares y muestra los métodos `matches`, `lookingAt`, `find`, `replaceAll`, `replaceFirst` y `split`.

## Ejecutar en IntelliJ
1. Abrir la carpeta del proyecto y seleccionar un JDK 21.
2. Ejecutar `src/Main.java`.

## Ejecutar en terminal (macOS o Linux)
```bash
javac -d out $(find src -name '*.java')
java -cp out Main
```

## Organización
- `src/classes`: el patrón y los seis métodos.
- `src/patrones`: las expresiones de cada tema y el catálogo.
- `src/menus`: interacción con el usuario.
- `src/utils`: lectura y validación.
- `documentacion`: documento de la práctica.
