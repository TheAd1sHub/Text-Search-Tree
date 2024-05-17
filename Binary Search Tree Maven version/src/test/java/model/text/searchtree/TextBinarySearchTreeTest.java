package model.text.searchtree;



import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("UnnecessaryLocalVariable")
public final class TextBinarySearchTreeTest {

    private final String[] emptyDataset = new String[] { };
    private final SearchResultData[] emptySearchResultData = new SearchResultData[] { };

    private final String[] alphabetDataset = new String[] {
        "A", "B", "C", "D", "E", "F", "G",
        "H", "I", "J", "K", "L", "M", "N",
        "O", "P", "Q", "R", "S", "T", "U",
        "V", "W", "X", "Y", "Z"
    };
    private final String capitalMLetterToken = "M";
    private final SearchResultData[] capitalMLetterInstancesSearchResultData = new SearchResultData[] {
        new SearchResultData(capitalMLetterToken, 13)
    };
    private final String smallMLetterToken = "m";
    private final SearchResultData[] smallMLetterInstancesSearchResultData = emptySearchResultData;

    @SuppressWarnings("SpellCheckingInspection")
    private final String[] loremIpsumDataset = new String[] {
            "Lorem", "ipsum", "dolor", "sit", "amet", "consectetur",
            "adipiscing", "elit", "sed", "do", "eiusmod", "tempor",
            "incididunt", "ut", "labore", "et", "dolore", "magna",
            "aliqua", "Ut", "enim", "ad", "minim", "veniam", "quis",
            "nostrud", "exercitation", "ullamco", "laboris", "nisi",
            "ut", "aliquip", "ex", "ea", "commodo", "consequat", "Duis",
            "aute", "irure", "dolor", "in", "reprehenderit", "in", "voluptate",
            "velit", "esse", "cillum", "dolore", "eu", "fugiat", "nulla",
            "pariatur", "Excepteur", "sint", "occaecat", "cupidatat", "non",
            "proident", "sunt", "in", "culpa", "qui", "officia", "deserunt",
            "mollit", "anim", "id", "est", "laborum"
    };
    private final String inWordToken = "in";
    private final SearchResultData[] inWordInstancesSearchResultData = new SearchResultData[] {
            new SearchResultData(inWordToken, 41),
            new SearchResultData(inWordToken, 43),
            new SearchResultData(inWordToken, 60)
    };
    private final String nonExistingWordToken = "non-findable";
    private final SearchResultData[] nonExistingWordInstancesSearchResultData = emptySearchResultData;

    private final String[] citiesDataset = new String[] {
            "Moscow", "Berlin", "Vilnius", "Riga", "Novosibirsk"
    };
    private final SearchResultData[] citiesInstancesSearchResultData = new SearchResultData[] {
            new SearchResultData("Moscow", 1),
            new SearchResultData("Vilnius", 3),
            new SearchResultData("Riga", 4),
            new SearchResultData("Novosibirsk", 5),
            new SearchResultData("Berlin", 2),
    };


    @Test
    public void testEmptyTreeSizeMethod() {
        //Arrange
        TextBinarySearchTree tree = new TextBinarySearchTree();
        final int expectedReturn = emptySearchResultData.length;

        //Act
        final int treeSize = tree.size();

        //Assert
        assertEquals(expectedReturn, treeSize);
    }

    @Test
    public void testTreeConstructedWithEmptyCollectionSizeMethod() {
        //Arrange
        TextBinarySearchTree tree = new TextBinarySearchTree(List.of(emptyDataset));
        final int expectedReturn = emptySearchResultData.length;

        //Act
        final int treeSize = tree.size();

        //Assert
        assertEquals(expectedReturn, treeSize);
    }

    @Test
    public void testNonEmptyTreeSizeMethod() {
        //Arrange
        TextBinarySearchTree tree = new TextBinarySearchTree(List.of(alphabetDataset));
        final int expectedReturn = alphabetDataset.length;

        //Act
        final int treeSize = tree.size();

        //Assert
        assertEquals(expectedReturn, treeSize);
    }

    @Test
    public void testOneElementTreeSuccessfulSearch() {
        //Arrange
        TextBinarySearchTree tree = new TextBinarySearchTree(List.of(alphabetDataset));
        final String searchedValue = capitalMLetterToken;
        final SearchResultData[] expectedReturn = capitalMLetterInstancesSearchResultData;

        //Act
        ArrayList<SearchResultData> result = tree.findWith(searchedValue);

        //Assert
        assertArrayEquals(expectedReturn, result.toArray());
    }

    @Test
    public void testOneElementTreeUnsuccessfulSearch() {
        //Arrange
        TextBinarySearchTree tree = new TextBinarySearchTree(List.of(alphabetDataset));
        final String searchedValue = smallMLetterToken;
        final SearchResultData[] expectedReturn = smallMLetterInstancesSearchResultData;

        //Act
        ArrayList<SearchResultData> result = tree.findWith(searchedValue);

        //Assert
        assertArrayEquals(expectedReturn, result.toArray());
    }

    @Test
    public void testLoremIpsumTextSuccessfulSearch() {
        //Arrange
        TextBinarySearchTree tree = new TextBinarySearchTree(List.of(loremIpsumDataset));
        final String searchedValue = inWordToken;
        final SearchResultData[] expectedReturn = inWordInstancesSearchResultData;

        //Act
        ArrayList<SearchResultData> result = tree.findWith(searchedValue);

        //Assert
        assertArrayEquals(expectedReturn, result.toArray());
    }

    @Test
    public void testLoremIpsumTextUnsuccessfulSearch() {
        //Arrange
        TextBinarySearchTree tree = new TextBinarySearchTree(List.of(loremIpsumDataset));
        final String searchedValue = nonExistingWordToken;
        final SearchResultData[] expectedReturn = nonExistingWordInstancesSearchResultData;

        //Act
        ArrayList<SearchResultData> result = tree.findWith(searchedValue);

        //Assert
        assertArrayEquals(expectedReturn, result.toArray());
    }

    @Test
    public void testRemovalFunctionWithAlphabetLetter() {
        //Arrange
        TextBinarySearchTree tree = new TextBinarySearchTree(List.of(alphabetDataset));
        final String searchedValue = capitalMLetterToken;
        final SearchResultData[] expectedReturn = emptySearchResultData;

        //Act
        tree.remove(searchedValue);
        ArrayList<SearchResultData> result = tree.findWith(searchedValue);

        //Assert
        assertArrayEquals(expectedReturn, result.toArray());
    }

    @Test
    public void testRemovalFunctionWithLoremIpsumText() {
        //Arrange
        TextBinarySearchTree tree = new TextBinarySearchTree(List.of(loremIpsumDataset));
        final String searchedValue = inWordToken;
        final SearchResultData[] expectedReturn = emptySearchResultData;

        //Act
        tree.remove(searchedValue);
        ArrayList<SearchResultData> result = tree.findWith(searchedValue);

        //Assert
        assertArrayEquals(expectedReturn, result.toArray());
    }

    @Test
    public void testGetContentsFunctionWithCities() {
        //Arrange
        TextBinarySearchTree tree = new TextBinarySearchTree(List.of(citiesDataset));
        final SearchResultData[] expectedReturn = citiesInstancesSearchResultData;

        //Act
        ArrayList<SearchResultData> result = tree.getAllValues();

        //Assert
        assertArrayEquals(expectedReturn, result.toArray());
    }
}
