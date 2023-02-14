import java.util.ArrayList;
import java.util.LinkedList;

public class Week {
    private ArrayList<Date> weeklyTrades; // did have this as a basic Array with set value of 5 so couldn't create more than 5 trading
    // days in a week but then ran into trouble when printing it out it kept printing all the null values too...
    private String tradingWeek; //limit format f
    private static int counter = 0;

    public Week(String inputWeekExpiration) {
        this.tradingWeek = inputWeekExpiration;
        this.weeklyTrades = new ArrayList<>();
    }

    @Override
    public String toString() { //overriding the toString method is getting impractical because the output gets crazy long.
        // seems better to just have a method call to a private displayTrades method. but need to return a String per the over-rided
        //method signiture
        displayTrades(); // no content can exist after return statement unfortunately. So
        String repeatCharacter = "=";
        return repeatCharacter.repeat(30);


    }

    private void displayTrades(){
        System.out.println("Trades for week of " + this.tradingWeek + " : ");
        for (Date date: weeklyTrades){
            System.out.println("    " + date); // this doesn't work with text block either. Gotta do it the dirty way
//            System.out.printf("%n%d",date); can't use %d or %s with Objects I created
        }
    }

    public void addDate(Date addDate) {
        counter++;
        if (counter > 0 && counter <= 5) {
            weeklyTrades.add(addDate);

        }
        else System.out.println("can only have 5 trading days in a week");
    }

    public ArrayList<Date> getWeeklyTrades() {
        return weeklyTrades;
    }

    class Date {
        private LinkedList<Trade> tradesOnDate = new LinkedList<>(); // must instantiate the LinkedList in the field or code breaks
        private String date; // limit format

        public Date(String date) {
            this.date = date;
        }

        @Override
        public String toString() { // tried to get the Trade objects printed on different lines. Can't make it work.
            return date + " : " + tradesOnDate;
        }

        public LinkedList<Trade> getTradesOnDate() {
            return tradesOnDate;
        }

        public void addTrade(Trade addTrade) {
            this.tradesOnDate.add(addTrade);
        }


        class Trade { //realistically, this could probably just be a record since it doesn't rerally do anything... only thing
            //is that I don't have all the info at time of construction since sell price and time will have to be set later
            private String ticker;
            private String contractExpirationDate; // ensure xx/xx/xx format. possibly check input for string index values for correct char types
            private String callOrPut;
            private double strike; //the only thing I don't like is that primitive types default to 0 when out of constraints in my constructor
            private double purchasePremium;
            private int numberOfContracts;
            private int purchaseTime;
            private double sellPremium;

            //need a sell date
            private int sellTime; //really there should be a condition here were if purchase date and sell date are the same, then
            // sell time can't be before purchase time. Both also need conditions for being in bounds of trading hours

            //later this would be great if it would prompt me for spot price and first sell target to calculate risk:reward so I odn't have ro



            public Trade createTrade(String ticker, String contractExpirationDate, String callOrPut, Double strike, double purchasePremium, int numberOfContracts, int purchaseTime) {
//        if (ticker.length() == 3 || ticker.length() == 4) {
//            ticker = ticker.toUpperCase();
//        }
//        if ((contractExpirationDate.length() == 8) && (callOrPut.equalsIgnoreCase("call") || callOrPut.equalsIgnoreCase("put"))
//                && (strike > 1 && strike % .25 == 0) && (purchasePremium > .01) && (numberOfContracts >= 1) && (purchaseTime >= 930 && purchaseTime <= 1600)){
                return new Trade(ticker,contractExpirationDate,callOrPut,strike,purchasePremium,numberOfContracts,purchaseTime);
//        return null;
            }
            public Trade(String ticker, String contractExpirationDate, String callOrPut, Double strike, double purchasePremium, int numberOfContracts, int purchaseTime) {
                if (ticker.length() == 3 || ticker.length() == 4) { //only thing this doesn't fix is i can still input numbers in string form
                    ticker = ticker.toUpperCase();
                    this.ticker = ticker;
                }
                if (contractExpirationDate.length() == 8) {
                    this.contractExpirationDate = contractExpirationDate;
                } //again, substring or .index variations not working to ensure format. only other idea I have is change type back
                // to int and if int is 7 digits, add a leading 0 and put in the '/' when formatting into a string
                if (callOrPut.equalsIgnoreCase("call") || callOrPut.equalsIgnoreCase("put")) {
                    this.callOrPut = callOrPut;
                }
                if (strike > 1 && strike % .25 == 0) {
                    this.strike = strike;
                }
                if (purchasePremium > .01) {
                    this.purchasePremium = purchasePremium;
                }
                if (numberOfContracts >= 1) {
                    this.numberOfContracts = numberOfContracts;
                }
                if (purchaseTime >= 930 && purchaseTime <= 1600) {
                    this.purchaseTime = purchaseTime;
                }
            }

            @Override
            public String toString() {
                if (this.sellTime == 0) {
                    return
                            " PURCHASED " + ticker + " " + contractExpirationDate + " " + callOrPut + " at " + strike + " strike" +
                                    ", " + numberOfContracts + " contract(s) for $" + purchasePremium * 100 + "  at " +
                                    purchaseTime + " oclock.";
                } else {
                    return
                            " PURCHASED " + ticker + " " + contractExpirationDate + " " + callOrPut + " at " + strike + " strike" +
                                    ", " + numberOfContracts + " contract(s) for $" + purchasePremium * 100 + "  at " +
                                    purchaseTime + " oclock." + " Sold for $" + sellPremium * 100 + " at " + sellTime + " oclock" +
                                    '}';
                }

            }

            public void setSellPrice(double sellPremium) {
                if (sellPremium > .01) {
                    this.sellPremium = sellPremium;
                } else {
                    System.out.println("invalid");

                }
            }

            public void setSellTime(int sellTime) {
                if (sellTime >= 930 && sellTime <= 1600) {
                    this.sellTime = sellTime;
                }
            }

        }
    }
}
