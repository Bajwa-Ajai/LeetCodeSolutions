// You are given a 0-indexed string s. You are also given a 0-indexed string queryCharacters of length k and a 0-indexed array of integer indices queryIndices of length k, both of which are used to describe k queries.

// The ith query updates the character in s at index queryIndices[i] to the character queryCharacters[i].

// Return an array lengths of length k where lengths[i] is the length of the longest substring of s consisting of only one repeating character after the ith query is performed.

 

// Example 1:

// Input: s = "babacc", queryCharacters = "bcb", queryIndices = [1,3,3]
// Output: [3,3,4]
// Explanation: 
// - 1st query updates s = "bbbacc". The longest substring consisting of one repeating character is "bbb" with length 3.
// - 2nd query updates s = "bbbccc". 
//   The longest substring consisting of one repeating character can be "bbb" or "ccc" with length 3.
// - 3rd query updates s = "bbbbcc". The longest substring consisting of one repeating character is "bbbb" with length 4.
// Thus, we return [3,3,4].
// Example 2:

// Input: s = "abyzz", queryCharacters = "aa", queryIndices = [2,1]
// Output: [2,3]
// Explanation:
// - 1st query updates s = "abazz". The longest substring consisting of one repeating character is "zz" with length 2.
// - 2nd query updates s = "aaazz". The longest substring consisting of one repeating character is "aaa" with length 3.
// Thus, we return [2,3].
 

// Constraints:

// 1 <= s.length <= 105
// s consists of lowercase English letters.
// k == queryCharacters.length == queryIndices.length
// 1 <= k <= 105
// queryCharacters consists of lowercase English letters.
// 0 <= queryIndices[i] < s.length

public class Solution {
    static class Node{
        char leftChar;
        char rightChar;
        int preLen;
        int sufLen;
        int len;
        int best;
        Node(char leftChar,char rightChar,int preLen,int sufLen,int len,int best){
            this.leftChar=leftChar;
            this.rightChar=rightChar;
            this.preLen=preLen;
            this.sufLen=sufLen;
            this.len=len;
            this.best=best;
        }
    }

    private Node[] tree;
    private Node merge(Node left,Node right){
        int len=left.len+right.len;
        int best=Math.max(left.best,right.best);
        if(left.rightChar==right.leftChar){
            best=Math.max(best,left.sufLen+right.preLen);
        }
        int preLen=left.preLen;
        if(left.len==left.preLen && left.rightChar==right.leftChar){
            preLen=left.preLen+right.preLen;
        }
        int sufLen=right.sufLen;
        if(right.sufLen==right.len && left.rightChar==right.leftChar){
            sufLen=right.sufLen+left.sufLen;
        }

        return new Node(left.leftChar,right.rightChar,preLen,sufLen,len,best);
    }

    private void build(int node,int l,int r,String s){
        if(l==r){
            tree[node]=new Node(s.charAt(l),s.charAt(r),1,1,1,1);
            return;
        }
        int mid=l+(r-l)/2;
        build(2*node+1,l,mid,s);
        build(2*node+2,mid+1,r,s);

        tree[node]=merge(tree[2*node+1],tree[2*node+2]);
    }

    private void update(int node ,int l,int r,int i,char c){
        if(l==r){
            tree[node]=new Node(c,c,1,1,1,1);
            return;
        }
        int mid=l+(r-l)/2;
        if(i<=mid){
            update(node*2+1,l,mid,i,c);
        }else{
            update(node*2+2,mid+1,r,i,c);
        }
        tree[node]=merge(tree[node*2+1],tree[node*2+2]);
    }

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n=s.length();
        tree=new Node[4*n];
        build(0,0,n-1,s);
        int[] answer=new int[queryIndices.length];

        for (int i = 0; i < queryIndices.length; i++) {
            update(0, 0, n - 1, queryIndices[i], queryCharacters.charAt(i));
            answer[i] = tree[0].best;
        }

        return answer;
        

    }
}