package animals;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static animals.Grammar.*;

import static animals.Multilang.getProp;

@JsonInclude(JsonInclude.Include.NON_NULL)
class BinaryTree {
    List<Node> tree = new ArrayList<>();

    public void add(String value) {
        Node temp = new Node(value);
        tree.add(temp);
    }

    @JsonIgnore
    public boolean isLeaf(int key) {
        Node temp = tree.get(key);
        return temp.left == -1 && temp.right == -1;
    }

    @JsonIgnore
    public Node getNode(int key) {
        return tree.get(key);
    }

    @JsonIgnore
    public String getValue(int key) {
        return getNode(key).value;
    }

    @JsonIgnore
    public int getLeft(int key) {
        return getNode(key).left;
    }

    @JsonIgnore
    public int getRight(int key) {
        return getNode(key).right;
    }

    @JsonIgnore
    public void setLeft(int currentKey, int leftKey) {
        getNode(currentKey).left = leftKey;
    }

    @JsonIgnore
    public void setRight(int currentKey, int rightKey) {
        getNode(currentKey).right = rightKey;
    }

    @JsonIgnore
    public void insert(String value, int insertPosition, int parentKey, Boolean parentBranch, boolean turn) {
        this.add(value);
        //turn==true => fact is correct for the fist animal
        if (null != parentBranch) {
            if (parentBranch) this.setRight(parentKey, tree.size() - 1);
            else this.setLeft(parentKey, tree.size() - 1);
        }

        if (turn) {
            this.setLeft(tree.size() - 1, insertPosition);
            this.setRight(tree.size() - 1, tree.size() - 2);
        } else {
            this.setRight(tree.size() - 1, insertPosition);
            this.setLeft(tree.size() - 1, tree.size() - 2);
        }
    }

    @JsonIgnore
    public int root() {
        return tree.size() > 1 ? 2 : tree.size() == 1 ? 0 : -1;
    }

    @JsonIgnore
    public void printLeaves() {
        tree.stream()
                .filter(t -> t.getLeft() == -1)
                .map(Node::getValue)
                .map(Grammar::removeArticle)
                .map(t -> " - " + t)
                .sorted()
                .forEach(System.out::println);
    }

    @JsonIgnore
    public List<String> searchByValue(String value) {
        return recursiveSearch(value, tree.get(this.root()), new ArrayList<>());
    }

    @JsonIgnore
    private List<String> recursiveSearch(String value, Node current, List<String> way) {
        if (current.getLeft() == -1 && current.getRight() == -1) {
            if (current.getValue().equals(value)) way.add("");
            else if (way.size() > 0) way.remove(way.size() - 1);
        } else {
            if (way.size() == 0 || !"".equals(way.get(way.size() - 1))) {
                way.add(negativeFact(current.getValue(), getProp("pronoun")).replaceAll(getProp("articleDefAdd"), " - "));
                way = recursiveSearch(value, tree.get(current.getLeft()), way);
                way.add(positiveFact(current.getValue(), getProp("pronoun")).replaceAll(getProp("articleDefAdd"), " - "));
                way = recursiveSearch(value, tree.get(current.getRight()), way);
            }
        }
        return way;
    }

    @JsonIgnore
    public List<String> getStatistics() {
        List<String> stat = new ArrayList<>();
        if (tree.size() >= 0) {
            stat.add(tree.get(this.root()).getValue());
            stat.add("" + tree.size());
            stat.add("" + (tree.size() / 2 + 1));
            stat.add("" + tree.size() / 2);
            int[] minMaxAv = minMaxAvDepth(tree.get(root()), new int[]{-1, Integer.MAX_VALUE, 0}, -1);
            stat.add("" + minMaxAv[0]);
            stat.add("" + minMaxAv[1]);
            stat.add(String.format("%.1f", ((double) minMaxAv[2] / (tree.size() / 2 + 1))));
        } else Collections.fill(stat, "0");
        return stat;
    }

    //stats[0] - height of the tree;
    //stats[1] - minimum animal's depth;
    //stats[2] - animal's depths sum;
    @JsonIgnore
    private int[] minMaxAvDepth(Node current, int[] stats, int height) {
        height++;
        if (current.getLeft() == -1 && current.getRight() == -1) {
            if (height > stats[0]) stats[0] = height;
            if (height < stats[1]) stats[1] = height;
            stats[2] += height;
        } else {
            stats = minMaxAvDepth(tree.get(current.getLeft()), stats, height);
            stats = minMaxAvDepth(tree.get(current.getRight()), stats, height);
        }

        return stats;
    }

    @JsonIgnore
    public void printTree() {
        recursivePrintTree("└", tree.get(root()));
    }

    @JsonIgnore
    private void recursivePrintTree(String base, Node current) {
        if (current.getLeft() == -1 && current.getRight() == -1) System.out.printf("%s %s%n", base, current.getValue());
        else System.out.printf("%s %s%n", base, factToQuestion(current.getValue()).replace(" - ", ""));

        base = base.replace("└", " ");
        base = base.endsWith(" ") ? base + "├" : base.replace("├", "│├");
        if (current.getRight() != -1) recursivePrintTree(base, tree.get(current.getRight()));
        base = base.replace("├", "└");
        if (current.getLeft() != -1) recursivePrintTree(base, tree.get(current.getLeft()));
    }

    //for objectMapping only
    public BinaryTree() {
    }

    public List<Node> getTree() {
        return tree;
    }

    public void setTree(List<Node> tree) {
        this.tree = tree;
    }


}

