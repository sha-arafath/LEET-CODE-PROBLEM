class Solution {
    public boolean isPalindrome(int x) {
        // Negative numbers are not palindromes.
        // Also, if the last digit is 0, the first digit must also be 0 (only 0 satisfies this).
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        // When the length is odd, we can drop the middle digit via revertedNumber / 10.
        // E.g., for 12321, at the end of the loop x = 12 and revertedNumber = 123.
        return x == revertedNumber || x == revertedNumber / 10;
    }
}
