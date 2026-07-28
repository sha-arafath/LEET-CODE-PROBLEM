import java.util.ArrayList;
import java.util.List;

class Solution {
    public String[] findWords(String[] words) {
        // Map each English alphabet letter to its keyboard row index (0, 1, or 2)
        int[] charRowMap = new int[26];
        String[] rows = {"qwertyuiop", "asdfghjkl", "zxcvbnm"};
        
        for (int row = 0; row < rows.length; row++) {
            for (char c : rows[row].toCharArray()) {
                charRowMap[c - 'a'] = row;
            }
        }

        List<String> result = new ArrayList<>();

        for (String word : words) {
            String lowerWord = word.toLowerCase();
            int targetRow = charRowMap[lowerWord.charAt(0) - 'a'];
            boolean isValid = true;

            for (int i = 1; i < lowerWord.length(); i++) {
                if (charRowMap[lowerWord.charAt(i) - 'a'] != targetRow) {
                    isValid = false;
                    break;
                }
            }

            if (isValid) {
                result.add(word);
            }
        }

        return result.toArray(new String[0]);
    }
}
