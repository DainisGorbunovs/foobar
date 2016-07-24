/*
    Spy snippets
    ============

    You've been recruited by the team building Spy4Rabbits, a highly advanced search engine used to help fellow
    agents discover files and intel needed to continue the operations against Dr. Boolean's evil experiments.
    The team is known for recruiting only the brightest rabbit engineers, so there's no surprise they brought you
    on board. While you're elbow deep in some important encryption algorithm, a high-ranking rabbit official requests
    a nice aesthetic feature for the tool called "Snippet Search." While you really wanted to tell him how such a
    feature is a waste of time in this intense, fast-paced spy organization, you also wouldn't mind getting kudos
    from a leader. How hard could it be, anyway?

    When someone makes a search, Spy4Rabbits shows the title of the page. Your commander would also like it to show
    a short snippet of the page containing the terms that were searched for.

    Write a function called answer(document, searchTerms) which returns the shortest snippet of the document,
    containing all of the given search terms. The search terms can appear in any order.

    The length of a snippet is the number of words in the snippet. For example, the length of the snippet
    "tastiest color of carrot" is 4. (Who doesn't like a delicious snack!)

    The document will be a string consisting only of lower-case letters [a-z] and spaces. Words in the string will
    be separated by a single space. A word could appear multiple times in the document.
    searchTerms will be a list of words, each word comprised only of lower-case letters [a-z]. All the search
    terms will be distinct.

    Search terms must match words exactly, so "hop" does not match "hopping".

    Return the first sub-string if multiple sub-strings are shortest. For example, if the document is
    "world there hello hello where world" and the search terms are ["hello", "world"],
    you must return "world there hello".

    The document will be guaranteed to contain all the search terms.

    The number of words in the document will be at least one, will not exceed 500, and each word will
    be 1 to 10 letters long. Repeat words in the document are considered distinct for counting purposes.
    The number of words in searchTerms will be at least one, will not exceed 100, and each word will
    not be more than 10 letters long.
 */


package spy_snippets;

import com.sun.xml.internal.ws.util.StringUtils;

import java.awt.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Answer {
    private static Point test(Map<String, List<Integer>> indices, String[] searchTerms, int termIndex, int start, int end) {
        // select one index from each search term
        // find beginning and ending indices for the search terms
        // return them
        // choose another index, find beginning and ending indices for search terms

//        int begin = 0;
//        int end = 0;
//        foreach
//          test(begin,end, termIndex)

//        ArrayList begin, end
//                return final begin and end
//        test(indices, searchTerms, 1, begin, end)
//        a: [0,3] = test(indices, searchTerms, 1, 0, 0)
//           [2,4] = test(indices, searchTerms, 1, 4, 4)
//           if (termIndex==0):
//             from the arraylist find first least and return [2,4]
//        ...
//        c: test(indices, searchTerms, 2, 0, 2)
//           test(indices, searchTerms, 2, 0, 5)
//           iterate through arraylist of results and return the first least,
//           return [0, 3]
//
//           recursively test(indices, searchTerms, 2, 2, 4)
//           recursively test(indices, searchTerms, 2, 4, 5)
//           return [2, 4]
//        d: return [0, 3]
//           return [0, 5]
//           return [2, 4]
//           return [2, 5]

        List<Point> intervals = new ArrayList<Point>();
        for (Integer index : indices.get(searchTerms[termIndex])) {
            System.out.printf("Checking %d in %s\n", index, searchTerms[termIndex]);
            if (termIndex != searchTerms.length-1) {
                int newStart = start;
                int newEnd = end;
                if (index < start || start < 0) {
                    newStart = index;
                }
                if (index > end || end < 0){
                    newEnd = index;
                }
                System.out.printf("[%d] Going to check with [start-end]: [%d:%d]\n", index, newStart, newEnd);
                intervals.add(test(indices, searchTerms, termIndex + 1, newStart, newEnd));
            } else {
                int minIndex = (start < index) ? ((start >= 0) ? start : index) : index;
                int maxIndex = (end > index) ? end : index;
                Point total = new Point(minIndex, maxIndex);
                System.out.println("total.x + \",\"+ total.y = " + total.x + ","+ total.y);
                intervals.add(total);
            }
        }

        Point min = new Point();
        int minLength = -1;
        for (Point interval : intervals) {
            int length = interval.y - interval.x;
            if (length < minLength || minLength < 0) {
                minLength = length;
                min = interval;
            }
        }

        return min;
    }
//    021
//    530
//    624
//

    public static String answer(String document, String[] searchTerms) {
        // Separate the word list
        String[] wordList = document.split(" ");

        // Initialise the hash map to store term indices
        Map<String, List<Integer>> indices = new HashMap<String, List<Integer>>();
        for (String term : searchTerms) {
            indices.put(term, new ArrayList<Integer>());
        }

        // Add the indices
        List termList = Arrays.asList(searchTerms);
        for (int index = 0; index < wordList.length; ++index) {
            if (termList.contains(wordList[index])) {
                indices.get(wordList[index]).add(index);
            }
        }

        Point answer = test(indices, searchTerms, 0, -1, -1);

        System.out.println();
        System.out.println("Final answer: ");
        String response = "";
        for (int index = answer.x; index <= answer.y; ++index) {
            response += wordList[index];
            if (index != answer.y) {
                response += " ";
            }
        }
        System.out.println(answer);
        System.out.println(indices);

        return response;
    }



    public static void main(String[] args) {
//        String document = "many google employees can program";
//        String[] searchTerms = {"google", "program"};
        // google: 1
        // program: 4
        // combinations: [1-4]
        // shortest combination: [1-4] => "google employees can program"

        String document = "a b c d a";
        String[] searchTerms = {"a", "c", "d"};
        // a: 0, 4
        // c: 2
        // d: 3
        // combinations: [0-3], [2-4]
        // shortest combination: [2-4] => "c d a"

//        String document = "world there hello hello where world";
//        String[] searchTerms = {"hello", "world"};
        // hello: 2, 3
        // world: 0, 5
        // combinations: [2-0], [2-5], [3-0], [3-5]
        // shortest combinations: [0-2] => "world there hello", [3-5] => "hello where world"
        // shortest combination: [0-2] => "world there hello"

        String response = answer(document, searchTerms);
        System.out.println("response = " + response);
    }
}