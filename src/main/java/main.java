package cinema;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalRows;
        int totalCols;
        int action;
        final int totalPrice;
        int income = 0;
        int purcasedTickets = 0;
        float totalTickets;

        System.out.println("Enter the number of rows:");
        totalRows = (scanner.nextInt()) + 1;
        System.out.println("Enter the number of seats in each row:");
        totalCols = (scanner.nextInt()) + 1;
        String[][] cinema = new String[totalRows][totalCols];
        fillStartingHall(cinema, totalRows, totalCols);
        totalPrice = calculateTotalPrice(totalRows - 1, totalCols - 1);
        totalTickets = (totalRows - 1) * (totalCols - 1);

        while (true) {
            System.out.println();
            action = printMenu();

            if (action == 1) {
                printHall(cinema, totalRows, totalCols);
            }
            else if (action == 2) {
                income += buyTicket(cinema, totalRows-1, totalCols-1);
                purcasedTickets += 1;
            }
            else if (action == 3) {
                float currentPercentage = (purcasedTickets / totalTickets) * 100;
                 printStatistics(purcasedTickets, currentPercentage, income, totalPrice);
            }
            else {
                break;
            }
        }
        
    }

    public static int buyTicket(String[][] cinema, int totalRows, int totalCols) {
        Scanner scanner = new Scanner(System.in);
        int price;
        System.out.println();
        while (true) {
            System.out.println("Enter a row number:");
            int rowNumber = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int colNumber = scanner.nextInt();
            if (rowNumber > totalRows || colNumber > totalCols) {
                System.out.println("Wrong input!");
            }
            else {
                if (!cinema[rowNumber][colNumber].equals("B")) {
                    cinema[rowNumber][colNumber] = "B";
                    price = getPrice(totalRows - 1, totalCols - 1, rowNumber);
                    printHall(cinema, totalRows + 1, totalCols + 1);
                    break;
                } else {
                    System.out.println("That ticket has already been purchased!");
                }
            }
        }
        return price;

    }

    public static int printMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        return scanner.nextInt();
    }

    public static void printStatistics(int tickets, float percenatage, int income, int totalIncome) {
        System.out.printf("Number of purchased tickets: %d%n",tickets);
        System.out.printf("Percentage: %.2f%c%n", percenatage, '%');
        System.out.printf("Current income: $%d%n", income);
        System.out.printf("Total income: $%d%n", totalIncome);
        
    }

    public static void fillStartingHall(String[][] cinema, int totalRows, int totalCols) {
        int nRows = 0;
        int nCols = 1;
        for (int row = 0; row < totalRows; row++) {
            for (int col = 0; col < totalCols; col++) {
                if (row == 0) {
                    if (nRows == 0) {
                        cinema[row][col] = " ";
                    }
                    else {
                        cinema[row][col] = Integer.toString(nRows);
                    }
                    nRows++;
                }
                else if (col == 0) {
                    cinema[row][col] = Integer.toString(nCols);
                    nCols++;
                }
                else {
                    cinema[row][col] = "S";
                }
            }
        }
    }

    public static void printHall(String[][] cinema, int totalRows, int totalCols) {
        System.out.println("\nCinema:");
        for (int i = 0; i < totalRows; i++) {
            for (int j = 0; j < totalCols; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int getPrice(int totalRows, int totalCols, int rowNumber){
        int halfRows;
        int price;
        if (totalRows * totalCols<= 60) {
            System.out.println("\nTicket price:");
            System.out.println("$10");
            return  10;
        }
        else {
            halfRows = totalRows / 2;
            if (rowNumber <= halfRows){
                price = 10;
            }
            else {
                price = 8;
            }
            System.out.println("Ticket price:");
            System.out.println("$" + price);
        }
        return price;
    }

    public static int calculateTotalPrice(int totalRows, int totalCols) {
        int price;
        int halfRows;
        if ((totalRows) * (totalCols) <= 60) {
            price = totalRows * totalCols * 10;
        }
        else {
            halfRows = totalRows / 2;
            price = halfRows * totalCols * 10;
            price += (totalRows - halfRows) * totalCols * 8;
        }
        return price;
    }
}