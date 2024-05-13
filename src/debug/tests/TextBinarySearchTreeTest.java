package debug.tests;

import model.SearchResultData;
import model.TextBinarySearchTree;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

import java.util.ArrayList;
import java.util.List;

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


    @Test
    @DisplayName("Test of a size() method in a tree with no elements inserted")
    public void emptyTreeSizeMethodTest() {
        //Arrange
        TextBinarySearchTree tree = new TextBinarySearchTree();
        final int expectedReturn = emptySearchResultData.length;

        //Act
        final int treeSize = tree.size();

        //Assert
        Assert.assertEquals(expectedReturn, treeSize);
    }

    @Test
    @DisplayName("Test of a size() method in a tree with empty collection given in constructor.")
    public void treeWithEmptyCollectionGivenSizeMethodTest() {
        //Arrange
        TextBinarySearchTree tree = new TextBinarySearchTree(List.of(emptyDataset));
        final int expectedReturn = emptySearchResultData.length;

        //Act
        final int treeSize = tree.size();

        //Assert
        Assert.assertEquals(expectedReturn, treeSize);
    }

    @Test
    @DisplayName("Test of a size() method in a tree with dataset elements inserted")
    public void nonEmptyTreeSizeMethodTest() {
        //Arrange
        TextBinarySearchTree tree = new TextBinarySearchTree(List.of(alphabetDataset));
        final int expectedReturn = alphabetDataset.length;

        //Act
        final int treeSize = tree.size();

        //Assert
        Assert.assertEquals(expectedReturn, treeSize);
    }

    @Test
    @DisplayName("Test of a positive search in a tree with uppercase EN alphabet letters")
    public void oneElementTreeSuccessfulSearchTest() {
        //Arrange
        TextBinarySearchTree tree = new TextBinarySearchTree(List.of(alphabetDataset));
        final String searchedValue = capitalMLetterToken;
        final SearchResultData[] expectedReturn = capitalMLetterInstancesSearchResultData;

        //Act
        ArrayList<SearchResultData> result = tree.findWith(searchedValue);

        //Assert
        Assert.assertArrayEquals(result.toArray(), expectedReturn);
    }

    @Test
    @DisplayName("Test of an unsuccessful search in a tree containing single element")
    public void oneElementTreeUnsuccessfulSearchTest() {
        //Arrange
        TextBinarySearchTree tree = new TextBinarySearchTree(List.of(alphabetDataset));
        final String searchedValue = smallMLetterToken;
        final SearchResultData[] expectedReturn = smallMLetterInstancesSearchResultData;

        //Act
        ArrayList<SearchResultData> result = tree.findWith(searchedValue);

        //Assert
        Assert.assertArrayEquals(result.toArray(), expectedReturn);
    }
}