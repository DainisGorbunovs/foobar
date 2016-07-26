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

import java.util.*;

public class Answer {
    private static final int start = 0;
    private static final int end   = 1;
    public static String answer(String document, String[] searchTerms) {
        // Initialise the hash map to store term indices
        Map<String, List<Integer>> indices = new HashMap<String, List<Integer>>();
        for (String term : searchTerms) {
            indices.put(term, new ArrayList<Integer>());
        }

        // Separate the word list
        String[] tokens = document.split(" ");

        // Add the indices
        List<String> termList = Arrays.asList(searchTerms);
        for (int index = 0; index < tokens.length; ++index) {
            if (termList.contains(tokens[index])) {
                indices.get(tokens[index]).add(index);
            }
        }

        // Keep track of the minimum length and the indices' interval
        int minLength = Integer.MAX_VALUE;
        int[] interval = new int[2];

        // Go through all search terms
        for (String term : indices.keySet()) {
            // Go through all positions of the term in the document
            for (Integer position : indices.get(term)) {
                List<Integer> positions = new ArrayList<Integer>(position);

                // Go through all search terms
                for (String other_term : indices.keySet()) {
                    List<Integer> distances = new ArrayList<Integer>();
                    // Find distance between the positions of the first search term's position
                    // and the iterated search term's position
                    for (Integer other_position : indices.get(other_term)) {
                        distances.add(Math.abs(position - other_position));
                    }
                    // Add to the list of positions the index of iterated
                    // search term's position which has the smallest distance between them
                    int minIndex = distances.indexOf(Collections.min(distances));
                    positions.add(indices.get(other_term).get(minIndex));
                }

                // Find length between positions
                // If smaller than recorded length, update the new interval
                int length = Collections.max(positions) - Collections.min(positions);
                if (length < minLength) {
                    interval[start] = Collections.min(positions);
                    interval[end] = Collections.max(positions);
                    minLength = length;
                }
            }
        }

        // Join the shortest interval of words together
        String snippet = "";
        for (int sliceIndex = interval[start]; sliceIndex <= interval[end]; ++sliceIndex) {
            snippet += tokens[sliceIndex];
            if (sliceIndex != interval[end]) {
                snippet += " ";
            }
        }

        return snippet;
    }

    public static void main(String[] args) {
//        String document = "many google employees can program";
//        String[] searchTerms = {"google", "program"};
        // google: 1
        // program: 4
        // combinations: [1-4]
        // shortest combination: [1-4] => "google employees can program"

//        String document = "a b c d a";
//        String[] searchTerms = {"a", "c", "d"};
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

//        String document = "Lorem Ipsum is simply dummy text of the printing and typesetting industry Lorem Ipsum " +
//                "has been the industrys standard dummy text ever since the 1500s when an unknown printer took a " +
//                "of type and scrambled it to make a type specimen book It has survived not only five centuries but " +
//                "also the leap into electronic typesetting remaining essentially unchanged It was popularised in " +
//                "the 1960s with the release of Letraset sheets containing Lorem Ipsum passages and more recently " +
//                "with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum";
//        String[] searchTerms = {"the", "industry", "Ipsum"};

        String document = "many google employees can program can google employees because " +
                "google is a technology company that writes programs";
        String[] searchTerms = {"google", "program", "can"};
        String response = answer(document, searchTerms);
        System.out.println("response = " + response);
    }
}