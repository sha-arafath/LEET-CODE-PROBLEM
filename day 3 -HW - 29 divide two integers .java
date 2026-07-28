class Solution {
    public int divide(int dividend, int divisor) {
        // Handle edge case for overflow: Integer.MIN_VALUE / -1 = Integer.MAX_VALUE + 1
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // Determine sign of the result
        boolean isNegative = (dividend < 0) ^ (divisor < 0);

        // Convert to long using positive values to prevent integer overflow
        long absDividend = Math.abs((long) dividend);
        long absDivisor = Math.abs((long) divisor);

        int quotient = 0;

        while (absDividend >= absDivisor) {
            long tempDivisor = absDivisor;
            long multiple = 1;

            // Double the divisor until it exceeds absDividend
            while (absDividend >= (tempDivisor << 1)) {
                tempDivisor <<= 1;
                multiple <<= 1;
            }

            absDividend -= tempDivisor;
            quotient += multiple;
        }

        return isNegative ? -quotient : quotient;
    }
}
