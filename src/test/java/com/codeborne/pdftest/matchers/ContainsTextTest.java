package com.codeborne.pdftest.matchers;

import com.codeborne.pdftest.PDF;
import org.junit.Test;

import static com.codeborne.pdftest.PDFMatchers.containsText;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class ContainsTextTest {
  @Test
  public void canAssertThatPdfContainsText() {
    PDF pdf = new PDF(getClass().getClassLoader().getResource("50quickideas.pdf"));
    assertThat(pdf, containsText("50 Quick Ideas to Improve your User Stories"));
    assertThat(pdf, containsText("Gojko Adzic"));
    assertThat(pdf, containsText("©2013 - 2014 Gojko Adzic"));
    assertThat(pdf, containsText("#50quickideas"));
    assertThat(pdf, containsText("https://twitter.com/search?q=#50quickideas"));
  }

  @Test
  public void shouldBeCaseSensitive() {
    PDF pdf = new PDF(getClass().getClassLoader().getResource("50quickideas.pdf"));
    assertThat(pdf, not(containsText("50 quick ideas")));
  }

  @Test
  public void shouldIgnoreWhitespaces() {
    PDF pdf = new PDF(getClass().getClassLoader().getResource("50quickideas.pdf"));
    assertThat(pdf, containsText("Gojko       Adzic"));
    assertThat(pdf, containsText("\tGojko \t Adzic\t"));
    assertThat(pdf, containsText("Gojko \n Adzic\t\n"));
    assertThat(pdf, containsText("Gojko \n Adzic\n"));
    assertThat(pdf, containsText("Gojko\r\n Adzic\r\n"));
    assertThat(pdf, containsText("Gojko \u00a0 Adzic\r\n"));
  }
}
