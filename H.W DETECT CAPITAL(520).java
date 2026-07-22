class Solution {
    public boolean detectCapitalUse(String word) {
        int capitalCount = 0;
        int n = word.length();

        // Count how many capital letters are in the word
        for (int i = 0; i < n; i++) {
            if (Character.isUpperCase(word.charAt(i))) {
                capitalCount++;
            }
        }

        // Condition 1: All letters are capital (e.g., "USA")
        if (capitalCount == n) {
            return true;
        }

        // Condition 2: No letters are capital (e.g., "leetcode")
        if (capitalCount == 0) {
            return true;
        }

        // Condition 3: Exactly one capital, and it's the first letter (e.g., "Google")
        if (capitalCount == 1 && Character.isUpperCase(word.charAt(0))) {
            return true;
        }

        return false;
    }
}
