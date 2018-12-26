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
    paper.append(text);
    durability -= text.length();
    return paper.toString();
  }

  public int getDurability() {
    return durability;
  }
}
