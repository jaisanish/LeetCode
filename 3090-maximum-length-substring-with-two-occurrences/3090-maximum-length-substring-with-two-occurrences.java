class Solution {
    public int maximumLengthSubstring(String s) {
        int freq[]=new int[26];
        int left=0,length=0;
        for(int right=0;right<s.length();right++){
            freq[s.charAt(right)-'a']++;
            while(freq[s.charAt(right)-'a']>2){
                freq[s.charAt(left)-'a']--;
                left++;
            }
            length=Math.max(length,right-left+1);
        }
        return length;
    }
}