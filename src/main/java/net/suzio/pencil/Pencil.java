package net.suzio.pencil;

public class Pencil {

  private int maxDurability;
  private int length = -1;
  private int currentDurability = -1;
  private StringBuffer paper = new StringBuffer();

  public Pencil withLength(int length) {
    this.length = length;
    return this;
  }

  public Pencil withDurability(int durability) {
    this.currentDurability = this.maxDurability = durability;
    return this;
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
    if (currentDurability > 0) {
      if (letter != '\n' && letter != '\r' && letter != ' ') {
        if (Character.isLetter(letter) && Character.toUpperCase(letter) == letter) {
          currentDurability -= 2;
        } else {
          currentDurability--;
        }
      }
    }
  }

  public int getDurability() {
    return currentDurability;
  }

  public void sharpen() {
    if (length != 0) {
      currentDurability = maxDurability;
      length--;
    }
  }
}
