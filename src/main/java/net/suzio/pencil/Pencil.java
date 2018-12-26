package net.suzio.pencil;

public class Pencil {

  private int durability;
  StringBuffer paper = new StringBuffer();

  public Pencil() {
    this.durability = -1;
  }

  public Pencil(int durability) {
    this.durability = durability;
  }

  public String write(String text) {
    if (text != null && text.length() > 0) {
      for (char letter: text.toCharArray()) {
        paper.append(letter);
        if (letter != '\n' && letter!= '\r' && letter != ' ') {
          durability--;
        }
      }
    }
    return paper.toString();
  }

  public int getDurability() {
    return durability;
  }
}
