package com.dsa.practice.demo.Scaler.DFS;

import java.util.*;

//Basic Naive
public class GraphCopy {
    Map<Node, Node> map = new HashMap<>();
    public Node cloneGraph(Node node) {
        if (node == null) return null;
        if (map.containsKey(node)) return map.get(node);
        Node clone = new Node(node.val);
        map.put(node, clone);
        for (Node nod : node.neighbors) clone.neighbors.add(cloneGraph(nod));
        return clone;
    }

    public static void main(String[] args) {

        Node node = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        Node node3 = new Node(4);
        node.neighbors.add(node1);
        node.neighbors.add(node3);
        node1.neighbors.add(node);
        node1.neighbors.add(node2);
        node2.neighbors.add(node1);
        node2.neighbors.add(node3);
        node3.neighbors.add(node);
        node3.neighbors.add(node2);

        Node ans = new GraphCopy().cloneGraph(node);
        Set<Node> array = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(ans);
        array.add(ans);
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            System.out.println(poll.val);
            for (Node temp : poll.neighbors) {
                if (!array.contains(temp)) {
                    queue.offer(temp);
                    array.add(temp);
                }
            }
        }

    }

}

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}


