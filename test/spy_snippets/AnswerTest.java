package spy_snippets;

import org.junit.Assert;
import org.junit.Test;

public class AnswerTest {
    @Test
    public void caseATest() {
        String document = "many google employees can program";
        String[] searchTerms = {"google", "program"};
        Assert.assertEquals("google employees can program", Answer.answer(document, searchTerms));
    }

    @Test
    public void caseBTest() {
        String document = "a b c d a";
        String[] searchTerms = {"a", "c", "d"};
        Assert.assertEquals("c d a", Answer.answer(document, searchTerms));
    }
}