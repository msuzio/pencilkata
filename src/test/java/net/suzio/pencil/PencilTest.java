package net.suzio.pencil;

import static org.junit.Assert.*;
import org.junit.Test;

public class PencilTest {

  @Test
  public void whenPencilWritesNullNothingHappens() {
    Pencil pencil = new Pencil();
    assertEquals("", pencil.write(null));
  }
  @Test
  public void whenPencilWritesEmptyNothingHappens() {
    Pencil pencil = new Pencil();
    assertEquals("", pencil.write(""));
  }

  @Test
  public void whenPencilWritesonPaperTextShouldBeWritten() {
    Pencil pencil = new Pencil();
    String text = "She sells sea shells";
    assertEquals("Text should written", text, pencil.write(text));
  }

  @Test
  public void whenPencilWritesonPaperTextShouldBeAppended() {
    Pencil pencil = new Pencil();
    String text = " She sells sea shells ";
    String otherText = "by the sea shore";
    pencil.write(text);
    assertEquals("full text should written", text + otherText, pencil.write(otherText));
  }

  @Test
  public void whenPencilWritesDurabilityDecreases() {
    final int durability = 100;
    Pencil pencil = new Pencil(durability);
    String text = "text";
    pencil.write(text);
    assertEquals(durability - text.length(), pencil.getDurability());
  }

  @Test
  public void whenPencilWritesSpaceItDoesNotDull() {
    final int durability = 100;
    Pencil pencil = new Pencil(durability);
    String text = "text";
    pencil.write(text);
    pencil.write(" ");
    assertEquals(durability - text.length(), pencil.getDurability());
  }
}
