
import java.util.*;

public class HFTsimulator 
{
    private double totalProfit;

    public HFTsimulator() 
    {
        totalProfit = 0.0;
    }

    public void executeCycle(double marketPrice, int buyQuantity, int sellQuantity, int previousTradingVolume) 
    {
        
        double adjustedBuyPrice = marketPrice - 0.05;
        double adjustedSellPrice;
        if (sellQuantity < buyQuantity) 
        {
            double adjustment = calculateAdjustment(previousTradingVolume);
            adjustedSellPrice = marketPrice + (adjustment * marketPrice);
        } 
        else 
        {
            adjustedSellPrice = marketPrice + 0.05;
        }
        double costPrice = adjustedBuyPrice * buyQuantity;
        double sellPrice = adjustedSellPrice * sellQuantity;

        
        double profit = sellPrice - costPrice;
        totalProfit += profit;
    }

    private double calculateAdjustment(int previousTradingVolume) 
    {
        double adjustment = 0.0;

        if (previousTradingVolume > 100) 
        {
            adjustment = 0.05; 
        } 
        else if (previousTradingVolume > 50) 
        {
            adjustment = 0.75; 
        } 
        else if (previousTradingVolume > 20) 
        {
            adjustment = 1.0; 
        } 
        else 
        {
            adjustment = 2.0;
        }

        return adjustment;
    }

    public double getTotalProfit() 
    {
        return totalProfit;
    }

    public static void main(String[] args) 
    {
        HFTsimulator sm = new HFTsimulator();
        Scanner sc = new Scanner(System.in);

        for (int i = 1; i <= 5; i++) 
        {
            System.out.print("Enter the buy quantity for stock " + i + ": ");
            int buyQuantity = sc.nextInt();
            System.out.print("Enter the sell quantity for stock " + i + ": ");
            int sellQuantity = sc.nextInt();
            System.out.print("Enter the market price for stock " + i + ": ");
            double marketPrice = sc.nextDouble();
            System.out.print("Enter the previous trading volume for stock " + i + ": ");
            int previousTradingVolume = sc.nextInt();

            
            int numCycles = (buyQuantity / 10) + 1; // Assuming 10 trades per cycle, rounding up

            for (int cycle = 0; cycle < numCycles; cycle++) {
                sm.executeCycle(marketPrice, buyQuantity, sellQuantity, previousTradingVolume);
            }

            
            System.out.println("Traded stock " + i + " - Buy Quantity: " + buyQuantity +
                    ", Sell Quantity: " + sellQuantity + ", Market Price: " + marketPrice +
                    ", Cycles: " + numCycles);
        }

        
        System.out.println("Total Profit: " + sm.getTotalProfit());

        sc.close();
    }
}
