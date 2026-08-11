// Alice and Bob continue their games with piles of stones. There are a number of piles arranged in a row, and each pile has a positive integer number of stones piles[i]. The objective of the game is to end with the most stones.

// Alice and Bob take turns, with Alice starting first.

// On each player's turn, that player can take all the stones in the first X remaining piles, where 1 <= X <= 2M. Then, we set M = max(M, X). Initially, M = 1.

// The game continues until all the stones have been taken.

// Assuming Alice and Bob play optimally, return the maximum number of stones Alice can get.

 

// Example 1:

// Input: piles = [2,7,9,4,4]

// Output: 10

// Explanation:

// If Alice takes one pile at the beginning, Bob takes two piles, then Alice takes 2 piles again. Alice can get 2 + 4 + 4 = 10 stones in total.
// If Alice takes two piles at the beginning, then Bob can take all three piles left. In this case, Alice get 2 + 7 = 9 stones in total.
// So we return 10 since it's larger.

// Example 2:

// Input: piles = [1,2,3,4,5,100]

// Output: 104

 

// Constraints:

// 1 <= piles.length <= 100
// 1 <= piles[i] <= 104

public class Solution {
    private int dp[][][];
    private int n;

    private int solve(int[] piles,int turn,int i,int M){
        if(i>=n){
            return 0;
        }
        if(dp[turn][i][M]!=-1){
            return dp[turn][i][M];
        }
        int result=(turn ==1)?-1:Integer.MAX_VALUE;
        int stones=0;
        for(int x=1;x<=2*M && i+x<=n ;x++){
            stones+=piles[i+x-1];
            if(turn==1){
                result=Math.max(result,stones+solve(piles,0,i+x,Math.max(M,x)));
            }else{
                result=Math.min(result,solve(piles,1,i+x,Math.max(M,x)));
            }
        }
        return dp[turn][i][M]=result;
    }

    public int stoneGameII(int[] piles) {
        n=piles.length;
        dp=new int[2][n+1][n+1];
        for(int j=0;j<2;j++){
            for (int i = 0; i < n+1; i++) {
                java.util.Arrays.fill(dp[j][i], -1);
            }
        }

        return solve(piles,1,0,1);

    }
}
