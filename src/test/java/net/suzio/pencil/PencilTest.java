package net.suzio.pencil;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PencilTest {

  @Test
  public void whenPencilWritesNullNothingHappens() {
    final Pencil pencil = new Pencil();
    assertEquals("A null character should have no effect", "", pencil.write(null));
  }

  @Test
  public void whenPencilWritesEmptyStringNothingHappens() {
    final Pencil pencil = new Pencil();
    assertEquals("Am empty string should have no effect", "", pencil.write(""));
  }

  @Test
  public void whenPencilWritesTextShouldBeWritten() {
    final Pencil pencil = new Pencil();
    final String text = "She sells sea shells";
    assertEquals("Text should written", text, pencil.write(text));
  }

  @Test
  public void whenPencilWritesTextShouldBeAppended() {
    final Pencil pencil = new Pencil();
    final String text = " She sells sea shells ";
    final String otherText = "by the sea shore";
    pencil.write(text);
    assertEquals("Full text should written", text + otherText, pencil.write(otherText));
  }

  @Test
  public void whenPencilWritesDurabilityDecreases() {
    final int durability = 100;
    final Pencil pencil = new Pencil().withDurability(durability);
    final String text = "text";
    pencil.write(text);
    assertEquals(
        "Starting durability of " + durability + "should be decreased by writing '" + text + "'",
        durability - text.length(), pencil.getDurability());
  }

  @Test
  public void whenPencilWritesSpaceItDoesNotDull() {
    final int durability = 100;
    final Pencil pencil = new Pencil().withDurability(durability);
    final String text = "text";
    pencil.write(text);
    final String whitespace = " ";
    pencil.write(whitespace);
    assertEquals(
        "Starting durability of " + durability + " should not be decreased by adding '" + whitespace
            + "' to '" + text + "'", durability - text.length(), pencil.getDurability());
  }

  @Test
  public void whenPencilWritesInnerWhitespaceItDoesNotDull() {
    final int durability = 100;
    final Pencil pencil = new Pencil().withDurability(durability);
    final String text = "1 2 3 \n";
    pencil.write(text);
    assertEquals(
        "Starting durability of " + durability + " should not be decreased by inner whitespace in '"
            + text + "'", 97, pencil.getDurability());
  }

  @Test
  public void whenPencilWritesLowercaseItDullsByOne() {
    // slightly redundant but we prefer an explicit test
    final int durability = 3;
    final Pencil pencil = new Pencil().withDurability(durability);
    final String text = "abc";
    pencil.write(text);
    assertEquals(
        "Starting durability of " + durability + " should decreased by 1 per character in writing '"
            + text + "'", 0, pencil.getDurability());
  }

  @Test
  public void whenPencilWritesPunctuationItDullsByOne() {
    final int durability = 3;
    final Pencil pencil = new Pencil().withDurability(durability);
    final String text = "a&b";
    pencil.write(text);
    assertEquals("Starting durability of " + durability
            + " should be decreased by 2 per character in writing '" + text + "'", 0,
        pencil.getDurability());
  }

  @Test
  public void whenPencilWritesUppercaseItDullsByTwo() {
    // slightly redundant but we prefer an explicit test
    final int durability = 6;
    final Pencil pencil = new Pencil().withDurability(durability);
    final String text = "ABC";
    pencil.write(text);
    assertEquals("Starting durability of " + durability
            + " should be decreased by 2 per character in writing '" + text + "'", 0,
        pencil.getDurability());
  }

  @Test
  public void whenPencilDullsCompletelyItStopsWriting() {
    final int durability = 5;
    final Pencil pencil = new Pencil().withDurability(durability);
    final String text = "123456789";
    final String assertion =
        "Text '" + text + "' longer than durability of '" + durability + "' should be truncated";
    assertEquals(assertion, text.substring(0, durability), pencil.write(text));
  }


  @Test
  public void sharpeningResetsDurability() {
    final int durability = 5;
    final Pencil pencil = new Pencil().withDurability(durability);
    final String text = "12345";
    pencil.write(text);
    assertEquals("Writing '" + text + "' should dull pencil", 0, pencil.getDurability());
    pencil.sharpen();
    assertEquals("Resharpened pencil should be restored to original durability", durability,
        pencil.getDurability());
  }

  @Test
  public void sharpeningIsLimitedByLength() {
    final int durability = 10;
    final int length = 3;
    final Pencil pencil = new Pencil().withDurability(durability).withLength(3);
    for (int s = 0; s < length; s++) {
      pencil.write("anything");
      pencil.sharpen();
      assertEquals("Resharpened pencil should be restored to original durability", durability,
          pencil.getDurability());
    }
    pencil.write("something");
    final int beforeSharpen = pencil.getDurability();
    pencil.sharpen();
    assertEquals("Pencil should be not re-sharpened after original length is used up",
        beforeSharpen, pencil.getDurability());
  }

  @Test
  public void eraserErasesTextOccurrence() {
    final Pencil pencilWithEraser = new Pencil().withEraser();
    final String writtenText = "abcdefgabc";
    final String textToErase = "abc";
    final String expectedErasureResult = "abcdefg   ";
    pencilWithEraser.write(writtenText);
    final String erasedText = pencilWithEraser.erase(textToErase);
    assertEquals("Erased text should be replaced by blanks", expectedErasureResult, erasedText);
  }

  @Test
  public void eraserErasesSuccessiveTextOccurrences() {
    final Pencil pencilWithEraser = new Pencil().withEraser();
    pencilWithEraser
        .write("How much wood would a woodchuck chuck if a woodchuck could chuck wood?");
    assertEquals("How much wood would a woodchuck chuck if a woodchuck could       wood?",
        pencilWithEraser.erase("chuck"));
    assertEquals("How much wood would a woodchuck chuck if a wood      could       wood?",
        pencilWithEraser.erase("chuck"));
  }

  @Test
  public void eraserDoesNotEraseIfTextDoesNotMatch() {
    final Pencil pencilWithEraser = new Pencil().withEraser();
    final String text = "something";
    pencilWithEraser.write(text);
    final String textToErase = "abc";
    final String erasedText = pencilWithEraser.erase(textToErase);
    assertEquals("Erased text should be the same when removing non-matching string '" + textToErase,
        text, erasedText);
  }

  @Test
  public void pencilWithoutEraserDoesNotErase() {
    final Pencil pencilWithoutEraser = new Pencil();
    final String text = "something";
    pencilWithoutEraser.write(text);
    final String erasedText = pencilWithoutEraser.erase("thing");
    assertEquals("Pencil without eraser should not erase anything", text, erasedText);
  }

  @Test
  public void WhenEraserErasesEmptyStringNothingHappens() {
    final Pencil pencilWithEraser = new Pencil().withEraser();
    final String writtenText = pencilWithEraser.write("something");
    final String erasedText = pencilWithEraser.erase(null);
    assertEquals("Erasing a null value should not change the paper", writtenText, erasedText);
  }

  @Test
  public void WhenEraserErasesNullNothingHappens() {
    final Pencil pencilWithEraser = new Pencil().withEraser();
    final String writtenText = pencilWithEraser.write("something");
    final String erasedText = pencilWithEraser.erase(null);
    assertEquals("Erasing a null value should not change the paper", writtenText, erasedText);
  }

  @Test
  public void whenEraserErasesTextItShouldDegrade() {
    final int durability = 10;
    final Pencil pencilWithEraser = new Pencil().withEraser(durability);
    final String writtenText = "abcdef  gabc";
    final String textToErase = "abc";
    pencilWithEraser.write(writtenText);
    pencilWithEraser.erase(textToErase);
    final int degradedEraser = pencilWithEraser.getEraserDurability();
    assertEquals("Eraser should be degraded by erasing", durability - textToErase.length(),
        degradedEraser);
    pencilWithEraser.erase("f  g");
    final int degradeEraserForBlanks = pencilWithEraser.getEraserDurability();
    assertEquals("Eraser should not degrade when erasing blanks", degradedEraser - 2,
        degradeEraserForBlanks);
  }

  @Test
  public void whenEraserDegradesCompletelyItStopsErasing() {
    final int durability = 3;
    final Pencil pencilWithEraser = new Pencil().withEraser(durability);
    final String writtenText = "Buffalo Bill";
    final String textToErase = "Bill";
    final String expectedErasedText = "Buffalo B   ";
    pencilWithEraser.write(writtenText);
    final String erasedText = pencilWithEraser.erase(textToErase);
    assertEquals("Eraser should only erase " + durability + " characters before stopping",
        expectedErasedText, erasedText);
  }

  @Test
  public void whenPencilAddsEDitTextInBlanksSpaceItIsAddedToPaper() {
    final Pencil pencil = new Pencil().withEraser();
    pencil.write("An apple a day keeps the doctor away");
    pencil.erase("apple");
    final String resultText = pencil.write("onion");
    assertEquals("Text should be written over blank spaces on paper",
        "An onion a day keeps the doctor away", resultText);
  }

  @Test
  public void whenPencilAddsEditTextThatDoesNotFitItOverwrites() {
    final Pencil pencil = new Pencil().withEraser();
    pencil.write("An apple a day keeps the doctor away");
    pencil.erase("apple");
    final String resultText = pencil.write("artichoke");
    assertEquals("Text should be written over blank spaces on paper",
        "An artich@k@ay keeps the doctor away", resultText);
  }
}