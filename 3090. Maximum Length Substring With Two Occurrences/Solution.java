// Given a string s, return the maximum length of a substring such that it contains at most two occurrences of each character.
 

// Example 1:

// Input: s = "bcbbbcba"

// Output: 4

// Explanation:

// The following substring has a length of 4 and contains at most two occurrences of each character: "bcbbbcba".
// Example 2:

// Input: s = "aaaa"

// Output: 2

// Explanation:

// The following substring has a length of 2 and contains at most two occurrences of each character: "aaaa".
 

// Constraints:

// 2 <= s.length <= 100
// s consists only of lowercase English letters.


public class Solution {
    public int maximumLengthSubstring(String s) {
        HashMap<Character,Integer> mp=new HashMap<>();
        int left=0;
        int maxLen=0;
        for(int i=0;i<s.length();i++){
            mp.put(s.charAt(i),mp.getOrDefault(s.charAt(i),0)+1);
            while(mp.getOrDefault(s.charAt(i),0)>2){
                mp.put(s.charAt(left),mp.getOrDefault(s.charAt(left),0)-1);
                left++;
            }
            maxLen=Math.max(maxLen,i-left+1);
        }
        return maxLen;
    }
}