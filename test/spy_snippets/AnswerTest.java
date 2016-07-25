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

        searchTerms = new String[]{"c", "d", "a"};
        Assert.assertEquals("c d a", Answer.answer(document, searchTerms));

        searchTerms = new String[]{"c", "d", "a"};
        Assert.assertEquals("c d a", Answer.answer(document, searchTerms));
    }

    @Test
    public void caseCTest() {
        String document = "world there hello hello where world";
        String[] searchTerms = {"hello", "world"};
        Assert.assertEquals("world there hello", Answer.answer(document,searchTerms));
    }

    @Test
    public void caseDTest() {
        String document = "a b c r z q d a c b a a a a a d d d";
        String[] searchTerms = {"c", "d", "a"};
        Assert.assertEquals("d a c", Answer.answer(document, searchTerms));

        searchTerms = new String[]{"c"};
        Assert.assertEquals("c", Answer.answer(document, searchTerms));
    }

    @Test
    public void caseETest() {
        Assert.assertEquals("hop off it",
                Answer.answer("I hope it all works. hop off it",
                        new String[]{"hop", "it"}));

        Assert.assertEquals("this encyclopedia blog thing",
                Answer.answer("this is a a best thing word wrod word word word word this encyclopedia blog thing",
                        new String[]{"this", "thing"}));

        Assert.assertEquals("q k a o j s d x n d b",
                Answer.answer("c r i l n h y h z w e o b h q b q f x a c o y c l v l n k j r o m p d z q n c v q k a o j s d x n d b k c y v b v n m f y m b m j l h i c q z c q i k",
                        new String[]{"b", "s", "q"}));
    }
}