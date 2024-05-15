package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

// If the element is less than the current node = we go left. Else - we go right.
public final class TextBinarySearchTree {
    private Node root;

    private int count = 0;


    public TextBinarySearchTree(Iterable<String> values) {
        for (String value : values) {
            insert(value);
        }
    }

    public TextBinarySearchTree() {
        this(new ArrayList<String>());
    }


    public void insert(String value) {
        Node newNode = new Node(value, ++count);

        // if the new node is the first one in the tree...
        if (root == null) {
            root = newNode;

            return;
        }

        root.insertLeaf(newNode);
    }

    public void insertAll(List<String> values) {
        for (String value : values) {
            insert(value);
        }
    }

    public ArrayList<SearchResultData> remove(String value) {
        if (root == null) {
            return new ArrayList<SearchResultData>();
        }

        ArrayList<Node> nodesToRemove = findWithRaw(value);
        if (nodesToRemove.isEmpty()) {
            return new ArrayList<SearchResultData>();
        }

        ArrayList<SearchResultData> removedNodesData = new ArrayList<>(nodesToRemove.size());
        for (Node node : nodesToRemove) {
            Node removedNode = node.removeSelf();
            removedNodesData.add(new SearchResultData(removedNode.value, removedNode.index));
        }

        return removedNodesData;
    }

    public ArrayList<SearchResultData> findWith(String value) {
        if (root == null) {
            return new ArrayList<SearchResultData>();
        }

        ArrayList<Node> rawSearchHits = findWithRaw(value);

        ArrayList<SearchResultData> result = new ArrayList<>(rawSearchHits.size());
        
        for (Node hit : rawSearchHits) {
        	result.add(new SearchResultData(hit.value, hit.index));
        }
        
        return result;
    }

    public ArrayList<SearchResultData> getAllValues() {
        if (root == null) {
            return new ArrayList<SearchResultData>();
        }

        ArrayList<SearchResultData> allValues = new ArrayList<>(size());

        for (Node node : root.getAllChildNodes(true)) {
            allValues.add(new SearchResultData(node.value, node.index));
        }

        return allValues;
    }


    public int size() {
        return count;
    }

    @Override
    public String toString() {
        if (root == null) {
            return "[ EMPTY ]";
        }

        return "[" + root.toString() + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TextBinarySearchTree other) {
            if (this.size() != other.size()) {
                return false;
            }

            ArrayList<SearchResultData> thisContents = this.getAllValues(),
                                        otherContents = other.getAllValues();

            for (int i = 0; i < thisContents.size(); i++) {
                SearchResultData thisElement = thisContents.get(i),
                                 otherElement = otherContents.get(i);

                if (!thisElement.equals(otherElement)) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 0;

        ArrayList<SearchResultData> allNodesData = getAllValues();
        for (int i = 0; i < allNodesData.size(); i++) {
            if (i % 2 == 0) {
                hash |= allNodesData.get(i).hashCode();
            } else {
                hash &= allNodesData.get(i).hashCode();
            }
        }

        return hash;
    }


    private ArrayList<Node> findWithRaw(String value) {
        if (root == null) {
            return new ArrayList<Node>();
        }

        return root.getChildNodesWithValue(value);
    }


    private final class Node {
        public String value;
        public int index;

        private Node parent;
        private Node left;
        private Node right;

        public Node(String value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public String toString() {
            String result = "";

            if (left != null) {
                result += left.toString();
            }

            result += " " + value + " ";

            if (right != null) {
                result += right.toString();
            }

            return result;
        }


        private void insertLeaf(Node newNode) {
            newNode.parent = this;

            if (newNode.value.compareTo(this.value) < 0) {
                if (left == null) {
                    left = newNode;
                } else {
                    left.insertLeaf(newNode);
                }
            } else {
                if (right == null) {
                    right = newNode;
                } else {
                    right.insertLeaf(newNode);
                }
            }
        }

        private Node removeSelf() {
            // De-attaching the removed Node from its tree
            if (parent.left == this) {
                parent.left = null;
            } else {
                parent.right = null;
            }

            // Replacing the removed node with its children nodes in case of...

            // No children
            if (isLeaf()) {

                return this;
            }

            // One child
            if (left == null && right != null) {
                parent.insertLeaf(right);

                return this;
            } else if (left != null && right == null) {
                parent.insertLeaf(left);

                return this;
            }

            // Two children
            Node maxNodeInLeft = left.getMaxNodeInSubbranch();
            this.index = maxNodeInLeft.index;
            this.value = maxNodeInLeft.value;

            maxNodeInLeft.removeSelf();

            return this;
        }

        private ArrayList<Node> getChildNodesWithValue(String value) {
            ArrayList<Node> result = new ArrayList<>();

            if (this.value.compareTo(value) == 0) {
                result.add(this);
            }

            if (value.compareTo(this.value) >= 0 && right != null) {
                result.addAll(right.getChildNodesWithValue(value));
            } else if (left != null) {
                result.addAll(left.getChildNodesWithValue(value));
            }

            return result;
        }

        private ArrayList<Node> getAllChildNodes(boolean addSelf) {
            ArrayList<Node> result = new ArrayList<>(size());

            if (addSelf) {
                result.add(this);
            }

            if (right != null) {
                result.addAll(right.getAllChildNodes(addSelf));
            }

            if (left != null) {
                result.addAll(left.getAllChildNodes(addSelf));
            }

            return result;
        }

        private Node getMinNodeInSubbranch() {
            if (left == null) {
                return this;
            } else {
                return left.getMinNodeInSubbranch();
            }
        }

        private Node getMaxNodeInSubbranch() {
            if (right == null) {
                return this;
            } else {
                return right.getMaxNodeInSubbranch();
            }
        }


        private boolean isLeaf() {
            return left == null && right == null;
        }

        private boolean isRoot() {
            return this == root;
        }
        
    } 
    
}
