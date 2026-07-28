import java.util.HashSet;
import java.util.Set;

class Solution {
    public int numJewelsInStones(String jewels, String stones) {
        Set<Character> jewelSet = new HashSet<>();
        
        // Add all jewel types to the set
        for (char c : jewels.toCharArray()) {
            jewelSet.add(c);
        }
        
        int count = 0;
        // Count how many stones are jewels
        for (char c : stones.toCharArray()) {
            if (jewelSet.contains(c)) {
                count++;
            }
        }
        
        return count;
    }
}
