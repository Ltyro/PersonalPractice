//package com.lnstark.pp.leetcode;
//
//import java.util.*;
//
///**
// * https://leetcode.cn/problems/design-graph-with-shortest-path-calculator
// *
// *
// *
// * @author Zaoji_Lai
// * @since 1.0
// * @date 2026年3月15日
// */
//public class Q2642 {
//
//	public static void main(String[] args) {
//		int n = 4;
//		int[][] edges = {{0, 2, 5}, {0, 1, 2}, {1, 2, 1}, {3, 0, 3}};
//		Graph g = new Graph(n, edges);
//		System.out.println(g.shortestPath(3, 2)); // 6
//		System.out.println(g.shortestPath(0, 3));// -1
//		g.addEdge(new int[]{1,3,4});
//		System.out.println(g.shortestPath(0, 3));//6
//	}
//
//
//	static class Graph {
//		List<Integer[]> edgeList = new ArrayList<>();
//		Map<Integer, Map<Integer, Integer>> shortestPath = new HashMap<>();
//
//		public Graph(int n, int[][] edges) {
//			for (int[] edge : edges) {
//				edgeList.add(Arrays.stream(edge).boxed().toArray(Integer[]::new));
//			}
//			for (Integer[] edge : edgeList) {
//				refreshShortestEdgePath(edge[0], edge[2], edge[1]);
//				Integer len = shortestPath.get(edge[0]).get(edge[2]);
//
//			}
//
//		}
//
//		public void addEdge(int[] edge) {
//			edgeList.add(Arrays.stream(edge).boxed().toArray(Integer[]::new));
//		}
//
//		public int shortestPath(int node1, int node2) {
//
//		}
//
//		private void refreshShortestEdgePath(int p1, int p2, int len) {
//			Map<Integer, Integer> subMap = shortestPath.computeIfAbsent(p1, k -> new HashMap<>());
//			if (subMap.get(p2) == null || subMap.get(p2) > len) {
//				subMap.put(p2, len);
//			}
//		}
//	}
//
//}
