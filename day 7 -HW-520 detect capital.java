class Solution {
    public boolean detectCapitalUse(String word) {
        int caps = 0;
        int n = word.length();
        
        for (int i = 0; i < n; i++) {
            if (Character.isUpperCase(word.charAt(i))) {
                caps++;
            }
        }
        
        // All caps, no caps, or only first letter cap
        return caps == n || caps == 0 || (caps == 1 && Character.isUpperCase(word.charAt(0)));
    }
}
