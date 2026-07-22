class Solution {
    public boolean checkIfPangram(String sentence) {
        // Quick check: A pangram must have at least 26 characters
        if (sentence.length() < 26) {
            return false;
        }

        int seen = 0;

        for (int i = 0; i < sentence.length(); i++) {
            // Calculate bit position (0 for 'a', 1 for 'b', etc.)
            int bit = sentence.charAt(i) - 'a';
            // Set the corresponding bit
            seen |= (1 << bit);

            // Early exit if all 26 bits are set
            if (seen == (1 << 26) - 1) {
                return true;
            }
        }

        return seen == (1 << 26) - 1;
    }
}
