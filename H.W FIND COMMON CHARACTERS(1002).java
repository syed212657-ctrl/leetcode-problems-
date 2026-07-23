class Solution {
    public List<String> commonChars(String[] words) {
        // Step 1: Initialize minimum frequencies using the first word
        int[] minFreq = new int[26];
        for (char c : words[0].toCharArray()) {
            minFreq[c - 'a']++;
        }

        // Step 2: Compare character counts across the remaining words
        for (int i = 1; i < words.length; i++) {
            int[] currentFreq = new int[26];
            for (char c : words[i].toCharArray()) {
                currentFreq[c - 'a']++;
            }

            // Keep the minimum frequency for each character
            for (int j = 0; j < 26; j++) {
                minFreq[j] = Math.min(minFreq[j], currentFreq[j]);
            }
        }

        // Step 3: Build the result list from the final frequencies
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
