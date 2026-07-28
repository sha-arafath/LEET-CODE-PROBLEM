import java.util.*;

class ThroneInheritance {

    private final String king;
    private final Map<String, List<String>> map;
    private final Set<String> deadSet;

    public ThroneInheritance(String kingName) {
        this.king = kingName;
        this.map = new HashMap<>();
        this.deadSet = new HashSet<>();
    }
    
    public void birth(String parentName, String childName) {
        map.computeIfAbsent(parentName, k -> new ArrayList<>()).add(childName);
    }
    
    public void death(String name) {
        deadSet.add(name);
    }
    
    public List<String> getInheritanceOrder() {
        List<String> order = new ArrayList<>();
        dfs(king, order);
        return order;
    }

    private void dfs(String current, List<String> order) {
        if (!deadSet.contains(current)) {
            order.add(current);
        }
        
        List<String> children = map.get(current);
        if (children != null) {
            for (String child : children) {
                dfs(child, order);
            }
        }
    }
}

