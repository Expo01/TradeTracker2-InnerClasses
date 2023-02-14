import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

//        Date february1323 = new Date("02/13/23");
//        Date.Trade QQQ021323 = february1323.new Trade("QQQ", "02/17/23", "call", 305d, .50,1,945);


        Week weekFebruary1323 = new Week("02/17/23"); // new week generated

        Week.Date dateFebruary1323 = weekFebruary1323.new Date("02/13/23");
        Week.Date.Trade QQQ021323 = dateFebruary1323.new Trade("QQQ", "02/17/23", "call", 305d, .50, 1, 945);
        weekFebruary1323.addDate(dateFebruary1323);
        dateFebruary1323.addTrade(QQQ021323);

        Week.Date dateFebruary1423 = weekFebruary1323.new Date("02/14/23"); // new date and trade generated but no new week
        Week.Date.Trade SPY021423 = dateFebruary1423.new Trade("SPY", "02/15/23", "call", 405d, .45, 1, 950);
        weekFebruary1323.addDate(dateFebruary1423);
        dateFebruary1423.addTrade(SPY021423);

        //new trade generated but no new date or week
        Week.Date.Trade AMD021423 = dateFebruary1423.new Trade("AMD", "02/17/23", "put", 80d, .40, 1, 1000);
        dateFebruary1423.addTrade(AMD021423);

        reviewTrades(weekFebruary1323); //iterates by date, and displays all trades of that date

    }

    private static void reviewTrades(Week inputWeek) {
        printIterationMenu();
        System.out.println("");
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        boolean forward = true;
        ArrayList<Week.Date> localList = inputWeek.getWeeklyTrades(); //Week.Date indicates that the objects from
        // innerclass Date of Week outclass will be contents of the ArrayList
        ListIterator<Week.Date> listIterator = localList.listIterator(); //again, Date objects of the Week outclass will be iterated
        if (localList.size() == 0) {
            System.out.println("No trades in list");
            return;
        }
//        else {
//            System.out.printf("Now displaying trades from first trading date of the week: \n\t" + );
//        }

        while (!quit) {
            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 0:
                    System.out.println("Quitting trade review");
                    quit = true;
                    break;
                case 1:
                    if (!listIterator.hasPrevious()) { // toString prints out date field and its ArrayList of Trades which also have
                        //an overriden toString method to display all their fields
                        System.out.println("now displaying trade(s) from first trading day of the week : " + listIterator.next().toString());
                        forward = true;
                    } else {
                        System.out.println("cannot call argument option '1' when not at beginning of list");
                    }
                    // for some reason, if I don't include a print statement with this, it ignores the .toString content
                    // but if I try to print the listIterator.next.tostring, it errors.
                    break;
                case 2:
                    if (!forward) {
                        if (listIterator.hasNext()) {
                            listIterator.next();
                        }
                        forward = true;
                    }
                    if (listIterator.hasNext()) {
                        System.out.println("Now displaying next trade: " + listIterator.next().toString());
                    } else {
                        System.out.println("No other trades in list");
                    }
                    break;

                case 3:
                    if (forward) {
                        if (listIterator.hasPrevious()) {
                            listIterator.previous();
                        }
                        forward = false;
                    }
                    if (listIterator.hasPrevious()) {
                        System.out.println("Now displaying previous trade " + listIterator.previous().toString());
                    } else {
                        System.out.println("We are at the start of the playlist");
                    }
                    break;
                case 4:
                    System.out.println(inputWeek);
                    break;
                case 5:
                    printIterationMenu();
                    break;

            }
        }
    }

    public static void printIterationMenu() {
        System.out.println("enter 0 to quit, 1 to begin viewing of trade list, 2 to display next trade, 3 to display previous trade, " +
                "4 to display the weekly trades, 5 to display menu");
    }


}