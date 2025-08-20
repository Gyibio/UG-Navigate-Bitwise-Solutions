package model;

import java.util.List;

public class SearchAlgorithms {
    // Binary search for Node by name (assumes sorted by name)
    public static int binarySearchNodeByName(List<Node> nodes, String target) {
        int left = 0, right = nodes.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = nodes.get(mid).getName().compareToIgnoreCase(target);
            if (cmp == 0) return mid;
            else if (cmp < 0) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }
}
