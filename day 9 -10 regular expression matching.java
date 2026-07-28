class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];

        // Empty string and empty pattern match
        dp[0][0] = true;

        // Handle patterns with '*' matching empty string s
        for (int j = 2; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char pChar = p.charAt(j - 1);
                char sChar = s.charAt(i - 1);

                if (pChar == '*') {
                    // Option 1: Match 0 instances of the character before '*'
                    dp[i][j] = dp[i][j - 2];

                    // Option 2: Match 1 or more instances if preceding character matches sChar
                    char prevPChar = p.charAt(j - 2);
                    if (prevPChar == sChar || prevPChar == '.') {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                } else if (pChar == sChar || pChar == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }

        return dp[m][n];
    }
}
