class Solution {
    public boolean isAnagram(String s, String sTarget) {
        // If lengths are different, they can't be anagrams
        if (s.length() != sTarget.length()) {
            return false;
        }

        // An array to keep track of character counts for 'a' through 'z'
        int[] charCounts = new int[26];

        // Increment for s, decrement for sTarget
        for (int i = 0; i < s.length(); i++) {
            charCounts[s.charAt(i) - 'a']++;
            charCounts[sTarget.charAt(i) - 'a']--;
        }

        // If any count is not zero, they are not anagrams
        for (int count : charCounts) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }
}
