public class N_ary_Trees {

    // https://www.geeksforgeeks.org/longest-path-in-a-directed-acyclic-graph-dynamic-programming/
    // https://leetcode.com/problems/tree-diameter/
    // https://www.hackerrank.com/challenges/cut-the-tree/problem
    static int sum = 0;
    public static int cutTheTree(List<Integer> data, List<List<Integer>> edges) {
    // Write your code here
        for(int i : data) sum+=i;
        
        // 1,2, 1,3
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        HashSet<Integer> visited = new HashSet<>();
        int[] subValues = new int[data.size()];
        int index = 0;
        for(int i : data) subValues[index++] = i;
        
        for(List<Integer> l : edges){
            List<Integer> list = map.getOrDefault(l.get(0), new ArrayList<>());
            list.add(l.get(1));
            map.put(l.get(0), list);
            
            List<Integer> list1 = map.getOrDefault(l.get(1), new ArrayList<>());
            list.add(l.get(0));
            map.put(l.get(1), list1);
        }
        
        visited.add(edges.get(0).get(0));
        dfs(map, visited, subValues, edges.get(0).get(0));
        
        int min = Integer.MAX_VALUE;
        for(int i : subValues){
            min = Math.min(min, sum - 2*i);
        }
        
        return min;
    }
    
    static int dfs(HashMap<Integer, List<Integer>> map, HashSet<Integer> visited, 
    int[] subValues, int start){
        // min = Math.min(min, Math.abs(sum - currSum));
        if(!map.containsKey(start-1)) return subValues[start-1];
        int currSum = 0;
        List<Integer> list = map.get(start-1);
        for(int i : list){
            if(visited.contains(i)) continue;

            visited.add(i);            
            currSum+= dfs(map, visited, subValues, i);
        }
        subValues[start-1] = currSum;
        return currSum;
    }

    // https://www.geeksforgeeks.org/print-all-leaf-nodes-of-an-n-ary-tree-using-dfs/
    // https://www.geeksforgeeks.org/dfs-n-ary-tree-acyclic-graph-represented-adjacency-list/
    // https://leetcode.com/problems/encode-n-ary-tree-to-binary-tree/
    // https://leetcode.com/problems/serialize-and-deserialize-n-ary-tree/
}

