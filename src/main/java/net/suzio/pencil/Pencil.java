package net.suzio.pencil;

public class Pencil {

  private int maxDurability;
  private int length = Integer.MAX_VALUE;
  private int currentDurability = Integer.MAX_VALUE;
  private StringBuffer paper = new StringBuffer();
  private int eraserDurability = Integer.MAX_VALUE;

  public Pencil withLength(int length) {
    this.length = length;
    return this;
  }

  public Pencil withDurability(int durability) {
    this.currentDurability = this.maxDurability = durability;
    return this;
  }

  public Pencil withEraser() {
    return this;
  }

  public Pencil withEraser(int eraserDurability) {
    this.eraserDurability = eraserDurability;
    return this;
  }


  public String write(String text) {
    if (textExists(text)) {
      for (char letter : text.toCharArray()) {
        if (pencilCanWrite()) {
          paper.append(letter);
          dullForCharacter(letter);
        }
      }
    }
    return paper.toString();
  }

  public String erase(String textToErase) {
    int textLocation = paper.lastIndexOf(textToErase);
    if (textLocation > -1) {
      int eraseLocation = textLocation;
      while (eraseLocation < textLocation + textToErase.length()) {
        degradeEraserForCharacter(paper.charAt(eraseLocation));
        paper.deleteCharAt(eraseLocation);
        paper.insert(eraseLocation, ' ');
        eraseLocation++;
      }
    }
    return paper.toString();
  }

  private void degradeEraserForCharacter(char letter) {
    if (isNotBlankCharacter(letter)) {
      eraserDurability--;
    }
  }

  private boolean textExists(String text) {
    return text != null && text.length() > 0;
  }

  private void dullForCharacter(char letter) {
    if (pencilCanWrite() && isNotBlankCharacter(letter)) {
      if (isCapitalLetter(letter)) {
        currentDurability -= 2;
      } else {
        currentDurability--;

      }
    }
  }

  int getDurability() {
    return currentDurability;
  }

  int getEraserDurability() {
    return this.eraserDurability;
  }

  public void sharpen() {
    if (canBeSharpened()) {
      currentDurability = maxDurability;
      length--;
    }
  }

  private boolean pencilCanWrite() {
    return currentDurability > 0;
  }

  private boolean isCapitalLetter(char letter) {
    return Character.isLetter(letter) && Character.toUpperCase(letter) == letter;
  }

  private boolean isNotBlankCharacter(char letter) {
    return letter != '\n' && letter != '\r' && letter != ' ';
  }

  private boolean canBeSharpened() {
    return length != 0;
  }
}
