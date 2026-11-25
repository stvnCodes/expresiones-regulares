import patrones.PatronesFechas;
import patrones.PatronesWeb;

import java.time.DateTimeException;
import java.time.LocalDate;

public class PruebaFechasWeb {
  public static void main(String[] args) {
    probarFechas();
    probarHoras();
    probarWeb();
    probarContrasena();
    Verificacion.mostrarTotal("Fechas y web");
  }

  // Compara todas las fechas posibles con el calendario de Java
  private static void probarFechas() {
    for (int anio = 1900; anio <= 2099; anio++) {
      for (int mes = 0; mes <= 13; mes++) {
        for (int dia = 0; dia <= 32; dia++) {
          String texto = String.format("%02d/%02d/%04d", dia, mes, anio);
          if (existe(anio, mes, dia)) {
            Verificacion.acepta(PatronesFechas.fecha(), texto);
          } else {
            Verificacion.rechaza(PatronesFechas.fecha(), texto);
          }
        }
      }
    }
    Verificacion.rechaza(PatronesFechas.fecha(), "", "01/01/1899", "01/01/2100", "01-01-2023", "01/01/23", " 01/01/2023");
    Verificacion.encuentra(PatronesFechas.fecha(), 0, "101/01/20235");
  }

  private static boolean existe(int anio, int mes, int dia) {
    try {
      LocalDate.of(anio, mes, dia);
      return true;
    } catch (DateTimeException error) {
      return false;
    }
  }

  private static void probarHoras() {
    for (int hora = 0; hora <= 99; hora++) {
      for (int minuto = 0; minuto <= 99; minuto++) {
        String texto = String.format("%02d:%02d:00", hora, minuto);
        String alReves = String.format("%02d:00:%02d", hora, minuto);
        comparar(hora <= 23 && minuto <= 59, hora >= 1 && hora <= 12 && minuto <= 59, texto);
        comparar(hora <= 23 && minuto <= 59, hora >= 1 && hora <= 12 && minuto <= 59, alReves);
      }
    }
    Verificacion.rechaza(PatronesFechas.hora24(), "", "14:30:00 PM", "14-30-00", "14:30:000");
    Verificacion.rechaza(PatronesFechas.hora12(), "", "02:30:00 Am", "02:30:00  AM", "2:30:00 AM", "02:30:00 XM");
  }

  private static void comparar(boolean es24, boolean es12, String texto) {
    if (es24) {
      Verificacion.acepta(PatronesFechas.hora24(), texto);
    } else {
      Verificacion.rechaza(PatronesFechas.hora24(), texto);
    }
    if (es12) {
      Verificacion.acepta(PatronesFechas.hora12(), texto + " AM", texto + " pm");
    } else {
      Verificacion.rechaza(PatronesFechas.hora12(), texto + " AM", texto + " pm");
    }
  }

  private static void probarWeb() {
    Verificacion.acepta(PatronesWeb.correo(), "a@b.co", "a_b-c@d.e.f.org", "1@2.com");
    Verificacion.rechaza(PatronesWeb.correo(), "", "@b.co", "a@", "a@b.c", ".a@b.co", "a.@b.co", "a@b..co", "a@@b.co", "a@b.co1", "a@b-.co", "a@b.co.");
    Verificacion.encuentra(PatronesWeb.correo(), 1, "Correo: a@b.co.");

    Verificacion.acepta(PatronesWeb.url(), "http://a.co", "https://a.co/", "https://a.co?x=1", "https://a.co#y", "https://a.co:80", "https://es.wikipedia.org/wiki/Java_(lenguaje)");
    Verificacion.rechaza(PatronesWeb.url(), "", "https://", "www.com", "https://a..co", "https://-a.co", "https://a.c", "https://a.co:", "https://a.co:123456", "https://a.co/x y");
    Verificacion.encuentra(PatronesWeb.url(), 2, "Vea https://a.co/x, www.b.com.co.");
  }

  private static void probarContrasena() {
    Verificacion.acepta(PatronesWeb.contrasena(), "aB3$5678", "aB3$5678901234567890", "$3Baaaaa", "Ñandu#2026a");
    Verificacion.rechaza(PatronesWeb.contrasena(), "", "aB3$567", "aB3$56789012345678901", "aB3$ 5678", " aB3$5678", "12345678", "Niñoñas2026");
    Verificacion.encuentra(PatronesWeb.contrasena(), 1, "clave aB3$5678 corta aB3$");
  }
}
