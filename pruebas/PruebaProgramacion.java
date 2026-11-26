import patrones.PatronesProgramacion;

public class PruebaProgramacion {
  public static void main(String[] args) {
    probarVariables();
    probarFor();
    probarDouble();
    Verificacion.mostrarTotal("Programación");
  }

  private static void probarVariables() {
    Verificacion.acepta(PatronesProgramacion.variable(), "int x;", "int  x=0 ;", "byte b = -1;", "short s = +2;",
        "long l = 10L;", "long l = 10;", "int z = 0;", "float f = 08.5f;", "double d = 08.5;", "float f = 3.5f;", "float f = 3;", "float f = .5F;", "double d = 3;", "double d = 1.5e-3;",
        "double d = .5d;", "boolean b = false;", "char c = '\\n';", "char c = '\\'';", "char ñ = 'ñ';", "String s = \"\";",
        "String s = \"di \\\"hola\\\"; y ya\";", "String s = null;", "int _a1;", "int $a;", "int classe = 1;", "int class$ = 1;", "int forma;");
    Verificacion.rechaza(PatronesProgramacion.variable(), "", "x = 0;", "int;", "int x = ;", "int x = 3.5;", "int x = 10L;",
        "intx = 0;", "Int x = 0;", "float f = 3.5;", "double d = \"3\";", "boolean b = True;", "char c = \"a\";", "char c = 'ab';",
        "char c = '';", "String s = 'a';", "String s = \"a;", "String s = \"a\"b\";", "int a-b = 1;", "int for;", "int true = 1;", "int _;", "int x == 0;", "int x = 08;", "long x = 01L;", "double d = 08;");
    Verificacion.encuentra(PatronesProgramacion.variable(), 2, "print int x; int class; String s = \"a\";");
  }

  private static void probarFor() {
    Verificacion.acepta(PatronesProgramacion.cicloFor(), "for(i=0;i<5;i++)", "for(i = 0 ; i != n ; i--)", "for(int i=0; i<lista.size(); i+=2)",
        "for(double x=0.5; x<=2.5; x=x+0.5)", "for(i=a+b*2; i<n-1; --i)", "for(i=-5; i<-1; i++)", "for(int i=0; i+j<n; i*=2)");
    Verificacion.rechaza(PatronesProgramacion.cicloFor(), "", "FOR(i=0;i<5;i++)", "for i=0;i<5;i++", "for(;;)", "for(i=0;i<5;)", "for(i=0;i<;i++)",
        "for(i==0;i<5;i++)", "for(i=0;i=<5;i++)", "for(i=0;i<5;i+)", "for(i=0;i<5;i++;)", "for(1i=0;i<5;i++)", "for(int x : datos)", "for(i=0;i<5;i++) {");
    Verificacion.encuentra(PatronesProgramacion.cicloFor(), 1, "xfor(i=0;i<5;i++) for(i=0;i<5;i++) { }");
  }

  private static void probarDouble() {
    Verificacion.acepta(PatronesProgramacion.numeroDouble(), "0.0", "00.10", "-.5", "1e5", "-1E+5", "123456789.987654321");
    Verificacion.rechaza(PatronesProgramacion.numeroDouble(), "", ".", "-", "e5", "1.", "1..2", "1.5f", "1.5e1.2", "1 .5", "+-1.5", "3.14 ");
    Verificacion.encuentra(PatronesProgramacion.numeroDouble(), 1, "Pi es 3.14.");
    Verificacion.encuentra(PatronesProgramacion.numeroDouble(), 0, "ip 192.168.1.10 y v2.5");
    Verificacion.encuentra(PatronesProgramacion.numeroDouble(), 2, "5.5-3.2");
  }
}
