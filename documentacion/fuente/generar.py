# Genera el PDF de entrega. Uso: python3 documentacion/fuente/generar.py
import pathlib
import subprocess
import tempfile

RAIZ = pathlib.Path(__file__).resolve().parents[2]
FUENTE = pathlib.Path(__file__).parent
CHROME = "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome"

with tempfile.TemporaryDirectory() as carpeta:
    # Los resultados salen de ejecutar las expresiones del programa
    archivos = [str(f) for f in (RAIZ / "src").rglob("*.java")] + [str(FUENTE / "GenerarDesarrollo.java")]
    subprocess.run(["javac", "-encoding", "UTF-8", "-d", carpeta, *archivos], check=True)
    desarrollo = subprocess.run(["java", "-Dstdout.encoding=UTF-8", "-cp", carpeta, "GenerarDesarrollo"],
                                check=True, capture_output=True, text=True, encoding="utf-8").stdout

    pagina = (FUENTE / "documento.html").read_text(encoding="utf-8").replace("{{DESARROLLO}}", desarrollo)
    origen = pathlib.Path(carpeta) / "documento.html"
    origen.write_text(pagina, encoding="utf-8")
    subprocess.run([CHROME, "--headless", "--disable-gpu", "--no-pdf-header-footer",
                    f"--user-data-dir={carpeta}/perfil",
                    f"--print-to-pdf={RAIZ}/documentacion/Practica-Expresiones-Regulares.pdf",
                    origen.as_uri()], check=True, stderr=subprocess.DEVNULL, timeout=120)
