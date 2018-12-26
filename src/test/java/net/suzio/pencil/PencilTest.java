package net.suzio.pencil;

import static org.junit.Assert.*;
import org.junit.Test;

public class PencilTest {

  @Test
  public void whenPencilWritesonPaperTextShouldBeWritten() {
    Pencil pencil = new Pencil();
    String text = "She sells sea shells";
    assertEquals(text, pencil.write(text));
  }

}
