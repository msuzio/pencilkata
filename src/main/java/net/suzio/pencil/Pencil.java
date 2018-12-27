package net.suzio.pencil;

public class Pencil {

  private int durability;
  private StringBuffer paper = new StringBuffer();

  public Pencil() {
    this(-1);
  }

  public Pencil(int durability) {
    this.durability = durability;
  }

  public String write(String text) {
    if (durability > 0) {
      if (text != null && text.length() > 0) {
        for (char letter : text.toCharArray()) {
          paper.append(letter);
          if (letter != '\n' && letter != '\r' && letter != ' ') {
            if (Character.isLetter(letter) && Character.toUpperCase(letter) == letter) {
              dull(2);
            } else {
              dull(1);
            }
          }
        }
      }
    }
    return paper.toString();
  }

  private void dull(int graphiteUsed) {
    if (durability > 0) {
      durability -= graphiteUsed;
    }
  }

  public int getDurability() {
    return durability;
  }
}
