package animals;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
class Node {
    String value;
    int left;
    int right;


    //for objectMapping only
    Node(){}

    public void setValue(String value) {
        this.value = value;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public String getValue() {
        return value;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    Node(String value) {
        this.value = value;
        right = -1;
        left = -1;
    }
}