import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> commonChars(String[] words) {
        int[] minFreq = new int[26];
        
        // Count frequencies for the first word
        for (char c : words[0].toCharArray()) {
            minFreq[c - 'a']++;
        }
        
        // Update minFreq with frequencies from remaining words
        for (int i = 1; i < words.length; i++) {
            int[] charCount = new int[26];
            for (char c : words[i].toCharArray()) {
                charCount[c - 'a']++;
            }
            
            for (int j = 0; j < 26; j++) {
                minFreq[j] = Math.min(minFreq[j], charCount[j]);
            }
        }
        
        // Construct the output list
        List<String> result = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            while (minFreq[i] > 0) {
                result.add(String.valueOf((char) (i + 'a')));
                minFreq[i]--;
            }
        }
        
        return result;
    }
}
