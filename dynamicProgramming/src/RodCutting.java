
import java.util.LinkedList;

public class RodCutting {
    private int[] prices;

    public RodCutting(int[] prices)
    {
        this.prices = prices;
    }

    public LinkedList<Integer> best_cuts() {
        int n = prices.length;
        int[] dp = new int[n + 1]; // dynamic pricing
        int[] cuts = new int[n + 1]; // length of rod cut

        for (int i = 1; i <= n; i++) {
            int maxPrice = Integer.MIN_VALUE;
            for (int j = 1; j <= i; j++) {
                if (maxPrice < prices[j - 1] + dp[i - j]) {
                    maxPrice = prices[j - 1] + dp[i - j];
                    cuts[i] = j;
                }
            }
            dp[i] = maxPrice;
        }

        LinkedList<Integer> bestCuts = new LinkedList<>();
        int length = n;
        while (length > 0) {
            bestCuts.addFirst(cuts[length]);
            length -= cuts[length];
        }
        return bestCuts;
    }

    public static void main(String[] args) {
        // example input
        int[] prices = {  1, 5, 8, 9, 12, 14, 17, 19, 20, 21 };

        // run the algorithm
        LinkedList<Integer> cuts = new RodCutting(prices).best_cuts();

        // output results
        System.out.println("The best cuts for a rod of length " + prices.length + "m are");
        int total_price=0;
        for (Integer cut : cuts)
        {
            System.out.println(" - " + cut + "m selling at €"+prices[cut-1]);
            total_price += prices[cut-1];
        }
        System.out.println("The overall price is €"+total_price+".");
    }
}