package debug.tests;

import model.SearchResultData;
import model.TextBinarySearchTree;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;

public final class BinaryTreeUnitTests {

    @Test
    @DisplayName("Test of a tree with a single element - root")
    public void OneElementTreeTest() {
        //Arrange
        TextBinarySearchTree tree = new TextBinarySearchTree();
        tree.insert("1");

        //Act
        ArrayList<SearchResultData> result = tree.findWith("1");

        //Assert
        Assert.assertArrayEquals(
                result.toArray(),
                new SearchResultData[] { new SearchResultData("1", 1) }
        );
    }
}
