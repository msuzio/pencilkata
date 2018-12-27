package net.suzio.pencil;

import static org.junit.Assert.*;
import org.junit.Test;

public class PencilTest {

  @Test
  public void whenPencilWritesNullNothingHappens() {
    Pencil pencil = new Pencil();
    assertEquals("A null character should have no effect","", pencil.write(null));
  }
  @Test
  public void whenPencilWritesEmptyStringNothingHappens() {
    Pencil pencil = new Pencil();
    assertEquals("Am empty string should have no effect","", pencil.write(""));
  }

  @Test
  public void whenPencilWritesTextShouldBeWritten() {
    Pencil pencil = new Pencil();
    String text = "She sells sea shells";
    assertEquals("Text should written", text, pencil.write(text));
  }

  @Test
  public void whenPencilWritesTextShouldBeAppended() {
    Pencil pencil = new Pencil();
    String text = " She sells sea shells ";
    String otherText = "by the sea shore";
    pencil.write(text);
    assertEquals("Full text should written", text + otherText, pencil.write(otherText));
  }

  @Test
  public void whenPencilWritesDurabilityDecreases() {
    final int durability = 100;
    Pencil pencil = new Pencil(durability);
    String text = "text";
    pencil.write(text);
    assertEquals("Starting durability of " + durability + "should be decreased by writing '" + text + "'", durability - text.length(), pencil.getDurability());
  }

  @Test
  public void whenPencilWritesSpaceItDoesNotDull() {
    final int durability = 100;
    Pencil pencil = new Pencil(durability);
    String text = "text";
    pencil.write(text);
    String whitespace = " ";
    pencil.write(whitespace);
    assertEquals("Starting durability of " + durability + " should not be decreased by adding '" + whitespace + "' to '" + text + "'", durability - text.length(), pencil.getDurability());
  }

  @Test
  public void whenPencilWritesInnerWhitespaceItDoesNotDull() {
    final int durability = 100;
    Pencil pencil = new Pencil(durability);
    String text = "1 2 3 \n";
    pencil.write(text);
    assertEquals("Starting durability of " + durability + " should not be decreased by inner whitespace in '" + text + "'",97, pencil.getDurability());
  }

  @Test
  public void whenPencilWritesLowercaseItDullsByOne() {
    // slightly redundant but we prefer an explicit test
    final int durability = 3;
    Pencil pencil = new Pencil(durability);
    String text = "abc";
    pencil.write(text);
    assertEquals("Starting durability of " + durability + " should decreased by 1 per character in writing '" + text + "'",0, pencil.getDurability());
  }

  @Test
  public void whenPencilWritesPunctuationItDullsByOne() {
    final int durability = 3;
    Pencil pencil = new Pencil(durability);
    String text = "a&b";
    pencil.write(text);
    assertEquals("Starting durability of " + durability + " should be decreased by 2 per character in writing '" + text + "'",0, pencil.getDurability());
  }

  @Test
  public void whenPencilWritesUppercaseItDullsByTwo() {
    // slightly redundant but we prefer an explicit test
    final int durability = 6;
    Pencil pencil = new Pencil(durability);
    String text = "ABC";
    pencil.write(text);
    assertEquals("Starting durability of " + durability + " should be decreased by 2 per character in writing '" + text + "'",0, pencil.getDurability());
  }

  @Test
  public void whenPencilDullsCompletelyItStopsWriting() {
    int durability = 5;
    Pencil pencil = new Pencil(durability);
    String text = "123456789";
    String assertion =
        "Text '" + text + "' longer than durability of '" + durability + "' should be truncated";
    assertEquals(assertion, text.substring(0, durability), pencil.write(text));
  }


  @Test
  public void sharpeningResetsDurability() {
    int durability = 5;
    Pencil pencil = new Pencil(durability);
    String text = "12345";
    pencil.write(text);
    assertEquals("Writing '" + text + "' should dull pencil", 0, pencil.getDurability());
    pencil.sharpen();
    assertEquals("resharpened pencil should be restored to original durability", durability, pencil.getDurability());

  }
}
