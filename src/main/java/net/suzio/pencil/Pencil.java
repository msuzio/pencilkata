package net.suzio.pencil;

public class Pencil {

  private int maxDurability;
  private int length = Integer.MAX_VALUE;
  private int currentDurability = Integer.MAX_VALUE;
  private final StringBuffer paper = new StringBuffer();
  private int eraserDurability = -1;
  private int lastErasedAreaStart = -1;

  public Pencil withLength(final int length) {
    this.length = length;
    return this;
  }

  public Pencil withDurability(final int durability) {
    currentDurability = maxDurability = durability;
    return this;
  }

  public Pencil withEraser() {
    eraserDurability = Integer.MAX_VALUE;
    return this;
  }

  public Pencil withEraser(final int eraserDurability) {
    this.eraserDurability = eraserDurability;
    return this;
  }


  public String write(final String text) {
    //
    // if we're writing after we've erased text, we want to write into that spot (possibly overwriting existing text)
    //
    if (textIsNotEmpty(text)) {

      int insertAt = lastErasedAreaStart;

      for (final char letter : text.toCharArray()) {
        if (pencilCanWrite()) {
          if (insertAt >= 0) {
            paper.setCharAt(insertAt, letter);
            insertAt++;
          } else {
            paper.append(letter);
          }
          dullForCharacter(letter);
        }
      }
    }
    return paper.toString();
  }

  public String erase(final String textToErase) {
    if (pencilCanErase() && textIsNotEmpty(textToErase)) {
      final int textLocation = paper.lastIndexOf(textToErase);
      int eraseLocation = textLocation + textToErase.length() - 1;
      if (textLocation > -1) {
        while (pencilCanErase() && eraseLocation >= textLocation) {
          degradeEraserForCharacter(paper.charAt(eraseLocation));
          paper.setCharAt(eraseLocation, ' ');
          eraseLocation--;
        }
      }
      lastErasedAreaStart = eraseLocation + 1;
    }
    return paper.toString();
  }

  private void degradeEraserForCharacter(final char letter) {
    if (isNotBlankCharacter(letter)) {
      eraserDurability--;
    }
  }

  // Not going to haul in Apache Commons just for one StringUtils method.
  private boolean textIsNotEmpty(final String text) {
    return text != null && text.length() > 0;
  }

  private void dullForCharacter(final char letter) {
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
    return eraserDurability;
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

  private boolean pencilCanErase() {
    return eraserDurability > 0;
  }

  private boolean isCapitalLetter(final char letter) {
    return Character.isLetter(letter) && Character.toUpperCase(letter) == letter;
  }

  private boolean isNotBlankCharacter(final char letter) {
    return letter != '\n' && letter != '\r' && letter != ' ';
  }

  private boolean canBeSharpened() {
    return length != 0;
  }
}
