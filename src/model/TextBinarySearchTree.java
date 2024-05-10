package model;

import java.util.ArrayList;
import java.util.Collection;

// If the element is less than the current node = we go left. Else - we go right.
public final class TextBinarySearchTree {
    private Node root;

    private int count = 0;

    public TextBinarySearchTree(Collection<String> values) {
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
        if (count == 1) {
            root = newNode;

            return;
        }

        root.insertLeave(newNode);
    }

    public ArrayList<SearchResultData> findWith(String value, boolean ignoreCase) {
        if (root == null) {
            return new ArrayList<SearchResultData>();
        }

        ArrayList<Node> rawSearchHits;

        rawSearchHits = root.getChildNodesWithValue(value, ignoreCase);

        ArrayList<SearchResultData> result = new ArrayList<>(rawSearchHits.size());
        
        for (Node hit : rawSearchHits) {
        	result.add(new SearchResultData(hit.value, hit.index));
        }
        
        return result;
    }

    public int size() {
        return count;
    }
    
    public ArrayList<SearchResultData> findWith(String value) {
    	return findWith(value, false);
    }

    @Override
    public String toString() {
        if (root == null) {
            return "[ EMPTY ]";
        }

        String result = "[" + root.toString() + "]";

        return result;
    }


    private final class Node {
        public final String value;
        public final int index;

        private Node left;
        private Node right;

        public Node(String value, int index) {
            this.value = value;
            this.index = index;

            //System.out.println("'" + this.value + "'");
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

        private void insertLeave(Node newNode) {
            if (newNode.value.compareTo(this.value) < 0) {
                if (left == null) {
                    left = newNode;
                } else {
                    left.insertLeave(newNode);
                }
            } else {
                if (right == null) {
                    right = newNode;
                } else {
                    right.insertLeave(newNode);
                }
            }
        }
        

        private ArrayList<Node> getChildNodesWithValue(String value, Boolean ignoreCase) {
            ArrayList<Node> result = new ArrayList<>();

            System.out.println(this.value);
            if (this.left != null) {
                System.out.println("Left = " + this.left.value);
            }
            if (this.right != null) {
                System.out.println("Right = " + this.right.value);
            }



            if (ignoreCase && this.value.compareToIgnoreCase(value) == 0) {
                result.add(this);
            } else if (this.value.compareTo(value) == 0) {
                result.add(this);
            }

            if (value.compareTo(this.value) >= 0 && right != null) {
                result.addAll(right.getChildNodesWithValue(value, ignoreCase));
            } else if (left != null) {
                result.addAll(left.getChildNodesWithValue(value, ignoreCase));
            }

            return result;
        }

        private ArrayList<Node> getChildNodesWithValue(String value) {
            return getChildNodesWithValue(value, false);
        }

        // TODO: Find the way to make getLeavesWithValue() and getLeavesWithValueIgnoreCase() work in accordance with DRY principle
        // DONE: Wrote a whole new method instead of these two.
        @Deprecated
        private ArrayList<Node> getLeavesWithValue(String value) {
            ArrayList<Node> result = new ArrayList<>();

            if (this.value.compareTo(value) == 0) {
                result.add(this);
            }

            if (this.value.compareTo(value) >= 0 && right != null) {
                result.addAll(right.getLeavesWithValue(value));
            } else if (left != null) {
                result.addAll(left.getLeavesWithValue(value));
            }

            return result;
        }

        @Deprecated
        private ArrayList<Node> getLeavesWithValueIgnoreCase(String value) {        	
        	ArrayList<Node> result = new ArrayList<>();
            
            if (this.value.compareToIgnoreCase(value) == 0) {
                result.add(this);
            }

            if (this.value.compareTo(value) < 0 && left != null) {
                result.addAll(left.getLeavesWithValueIgnoreCase(value));
            } else if (right != null) {
                result.addAll(right.getLeavesWithValueIgnoreCase(value));
            }

            return result;
        }
        
    } 
    
}
