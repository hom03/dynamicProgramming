
public class LongestCommonSubsequence {
    private String X;
    private String Y;

    public LongestCommonSubsequence(String X, String Y)
    {
        this.X = X;
        this.Y = Y;
    }

    public String compare() {
        int xLen = X.length();
        int yLen = Y.length();

        //create a table to store the lengths of LCS
        int[][] table =new int[xLen+1][xLen+1];

        //build table
        for (int i=0;i <= xLen; i++){
            for (int j=0;j <= yLen; j++){
                if (i ==0 || j == 0){
                    table[i][j] = 0;
                } else if (X.charAt(i - 1) == Y.charAt(j - 1)){
                    table[i][j] = table[i - 1][j - 1] + 1;
                } else {
                    table[i][j] = Math.max(table[i - 1][j],table[i][j - 1]);
                }
            }
        }

        StringBuilder lcs = new StringBuilder();
        int i = xLen; int j = yLen;
        while (i > 0 && j > 0){
            if (X.charAt(i-1) == Y.charAt(j-1)){
                lcs.insert(0,X.charAt(i-1));
                i--;
                j--;
            } else if (table[i-1][j] > table[i][j-1]){
                i--;
            } else {
                j--;
            }
        }

        return lcs.toString();
    }

    public static void main(String[] args) {
        // example input
        String X = "ABCBDAB";
        String Y = "BDCABA";

        // run the algorithm
        String Z = new LongestCommonSubsequence(X,Y).compare();

        // output results
        System.out.println("The longest common subsequence of '" +X + "' and '" + Y + "' is '" + Z + "'.");
    }
}
