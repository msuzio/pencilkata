package net.suzio.pencil;

public class Pencil {
  StringBuffer paper = new StringBuffer();

  public String write(String text) {
    return paper.append(text).toString();
  }
}
