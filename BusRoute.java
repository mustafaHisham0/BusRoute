
package busroute;
import java.util.Scanner;

public class BusRoute {
    public static void main(String[] args) {
        int overCapacityCount = 0;
        Scanner scanner = new Scanner(System.in);

        // 1. Enter Data
        System.out.print("enter number of stops : ");
        int numStops = scanner.nextInt();
        scanner.nextLine(); // Clear newline

        System.out.print("enter the bus capacity : ");
        int seatingCapacity = scanner.nextInt();
        scanner.nextLine(); // Clear newline

        // Arrays for stop names, boarding counts, and alighting counts
        String[] stopNames = new String[numStops];
        int[] boardingList = new int[numStops];
        int[] alightingList = new int[numStops];
        int[] occupancy = new int[numStops+1];

        // Fill arrays with user input
        for (int i = 0; i < numStops; i++) {
            System.out.println("\n--- Stop " + (i + 1) + " ---");
            System.out.print("Enter stop name: ");
            stopNames[i] = scanner.nextLine();

            System.out.print("Enter passengers boarding at " + stopNames[i] + ": ");
            boardingList[i] = scanner.nextInt();

            System.out.print("Enter passengers alighting at " + stopNames[i] + ": ");
            alightingList[i] = scanner.nextInt();
            scanner.nextLine(); // Clear newline\
            
             occupancy[i+1] = occupancy[i] + boardingList[i] - alightingList[i];

            // Prevent occupancy from dropping below 0
            if (occupancy[i+1] < 0) {
                System.out.println("Data error at [" + stopNames[i] + "]: cannot have more passengers alighting.");
                System.out.print("Enter passengers alighting at " + stopNames[i] + ": ");
                alightingList[i] = scanner.nextInt();
                scanner.nextLine(); // Clear newline\
            
                occupancy[i+1] = occupancy[i] + boardingList[i] - alightingList[i];
        }

            
            System.out.println("Passengers on the bus after " + stopNames[i] + ": " + occupancy[i+1]);

            // Check seating capacity
            if (occupancy[i+1] > seatingCapacity) {
                System.out.println("Warning: Bus is over capacity at [" + stopNames[i] + "]!");
                overCapacityCount++;
            }
        }
          if (occupancy[numStops]!= 0) {
            System.out.println("\nWarning: " +occupancy[numStops] + " passengers still on the bus after the final stop — please check your data.");
        }

        // 4. Display All
        System.out.println("\n=== Display All Stops ===");
        for (int i = 0; i < numStops; i++) {
            System.out.println("Stop: " + stopNames[i] + 
                               " | Boarding: " + boardingList[i] + 
                               " | Alighting: " + alightingList[i] + 
                               " | Current Occupancy: " + occupancy[i]);
        }

        // 5. Statistics
        System.out.println("\n=== Statistics ===");

        // Find the busiest stop (highest boarding)
        int maxBoarding = -1;
        String busiestStop = "";
        double totalOccupancy = 0;

        for (int i = 0; i < numStops; i++) {
            if (boardingList[i] > maxBoarding) {
                maxBoarding = boardingList[i];
                busiestStop = stopNames[i];
            }
            totalOccupancy += occupancy[i];
        }

        System.out.println("Busiest stop (highest boarding): " + busiestStop + " (" + maxBoarding + " passengers)");

        // Calculate average occupancy
        double avgOccupancy = totalOccupancy / numStops;
        System.out.printf("Average occupancy across all stops: %.2f\n", avgOccupancy);

        // Print count of over-capacity stops
        System.out.println("Number of stops exceeding bus capacity: " + overCapacityCount);

        // Check final occupancy at the last stop
        if (occupancy[numStops]!= 0) {
            System.out.println("Warning: " +occupancy[numStops] + " passengers still on the bus after the final stop — please check your data.");
        }

        scanner.close();
    }
}
