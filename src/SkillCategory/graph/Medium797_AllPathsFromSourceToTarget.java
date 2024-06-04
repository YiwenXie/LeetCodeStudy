package SkillCategory.graph;

import java.util.ArrayList;
import java.util.List;

public class Medium797_AllPathsFromSourceToTarget {
    List<List<Integer>> result;
    List<Integer> path;

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        result = new ArrayList<>();
        path = new ArrayList<>();
        // all path start
        path.add(0);
        backtracking(0, graph);
        return result;
    }

    public void backtracking(int startIndex, int[][] graph) {
        if (startIndex == graph.length - 1) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < graph[startIndex].length; i++) {
            path.add(graph[startIndex][i]);
            backtracking(graph[startIndex][i], graph);
            path.remove(path.size() - 1);
        }
    }


}
