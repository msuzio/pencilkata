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
    if (text != null && text.length() > 0) {
      for (char letter : text.toCharArray()) {
        if (durability != 0) {
          paper.append(letter);
          dullForCharacter(letter);
        }
      }
    }
    return paper.toString();
  }

  private void dullForCharacter(char letter) {
    if (letter != '\n' && letter != '\r' && letter != ' ') {
      if (Character.isLetter(letter) && Character.toUpperCase(letter) == letter) {
        durability -= 2;
      } else {
        durability--;
      }
    }
  }

  public int getDurability() {
    return durability;
  }
}
