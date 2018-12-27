package net.suzio.pencil;

public class Pencil {

  private final int maxDurability;
  private int currentDurability;
  private StringBuffer paper = new StringBuffer();

  public Pencil() {
    this(-1);
  }

  public Pencil(int durability) {
    this.maxDurability = durability;
    this.currentDurability = maxDurability;
  }

  public String write(String text) {
    if (text != null && text.length() > 0) {
      for (char letter : text.toCharArray()) {
        if (currentDurability != 0) {
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
        currentDurability -= 2;
      } else {
        currentDurability--;
      }
    }
  }

  public int getDurability() {
    return currentDurability;
  }

  public void sharpen() {
    currentDurability = maxDurability;
  }
}
