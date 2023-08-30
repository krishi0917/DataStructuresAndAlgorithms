package LeetcodePrograms.LinkedinCompactTreeBuilder;

import java.util.List;

class Node<T> {

    private final T data;
    private final List<Node<T>> children;

    public Node(T data, List<Node<T>> children) {
        this.data = data;
        this.children = children;
    }

    public T getData() {
        return data;
    }

    public List<Node<T>> getChildren() {
        return children;
    }
}