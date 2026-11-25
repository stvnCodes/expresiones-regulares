package classes;

import java.util.regex.Pattern;

public class Patron {
  // ------------------- Atributos ------------------- //
  private String nombre;
  private Pattern expresion;
  private String formato = "";
  private String explicacion = "";
  private String[] validas = {};
  private String[] invalidas = {};
  private String textoDemostracion = "";
  private String reemplazo = "";

  // ------------------- Constructores ------------------- //
  public Patron(String nombre, String expresion) {
    this.nombre = nombre;
    this.expresion = Pattern.compile(expresion);
  }

  // ------------------- Getters & Setters ------------------- //
  public String getNombre() {
    return nombre;
  }

  public Pattern getExpresion() {
    return expresion;
  }

  public String getFormato() {
    return formato;
  }

  public void setFormato(String formato) {
    this.formato = formato;
  }

  public String getExplicacion() {
    return explicacion;
  }

  public void setExplicacion(String explicacion) {
    this.explicacion = explicacion;
  }

  public String[] getValidas() {
    return validas;
  }

  public void setValidas(String... validas) {
    this.validas = validas;
  }

  public String[] getInvalidas() {
    return invalidas;
  }

  public void setInvalidas(String... invalidas) {
    this.invalidas = invalidas;
  }

  public String getTextoDemostracion() {
    return textoDemostracion;
  }

  public String getReemplazo() {
    return reemplazo;
  }

  // Texto que contiene el dato varias veces y por qué se cambia al reemplazar
  public void setDemostracion(String texto, String reemplazo) {
    this.textoDemostracion = texto;
    this.reemplazo = reemplazo;
  }
}
