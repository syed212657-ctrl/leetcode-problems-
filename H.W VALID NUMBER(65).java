class Solution {
    public boolean isNumber(String s) {
        boolean seenDigit = false;
        boolean seenDot = false;
        boolean seenExponent = false;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                seenDigit = true;
            } 
            else if (c == '+' || c == '-') {
                // A sign is only valid at the start or immediately after 'e' or 'E'
                if (i > 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') {
                    return false;
                }
            } 
            else if (c == '.') {
                // A dot is invalid if we've already seen a dot or an exponent
                if (seenDot || seenExponent) {
                    return false;
                }
                seenDot = true;
            } 
            else if (c == 'e' || c == 'E') {
                // An exponent is invalid if we've already seen one or haven't seen a digit yet
                if (seenExponent || !seenDigit) {
                    return false;
                }
                seenExponent = true;
                seenDigit = false; // Reset: must see at least one digit after the exponent
            } 
            else {
                // Any other character is invalid
                return false;
            }
        }

        // Must end with at least one digit (either before or after the exponent)
        return seenDigit;
    }
}
