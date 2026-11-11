
import java.util.Scanner;

public class AdvancedNQueens {

    static long totalSolutions;
    static long recursiveCalls;

    // Count solutions using bitmasking
    static long solve(
            int row,
            long columns,
            long leftDiagonal,
            long rightDiagonal,
            long fullMask) {

        recursiveCalls++;

        // All queens successfully placed
        if (row == Long.bitCount(fullMask)) {
            return 1;
        }

        long available = fullMask &
                ~(columns | leftDiagonal | rightDiagonal);

        long count = 0;

        while (available != 0) {

            // Extract rightmost available position
            long position = available & -available;

            // Remove selected position
            available ^= position;

            count += solve(
                    row + 1,
                    columns | position,
                    (leftDiagonal | position) << 1,
                    (rightDiagonal | position) >>> 1,
                    fullMask
            );
        }

        return count;
    }

    // Symmetry optimized N-Queens
    static long solveWithSymmetry(int n) {

        long fullMask = (1L << n) - 1;

        long halfCount = 0;

        // Search only left half of first row
        for (int col = 0; col < n / 2; col++) {

            long position = 1L << col;

            halfCount += solve(
                    1,
                    position,
                    position << 1,
                    position >>> 1,
                    fullMask
            );
        }

        // Mirror left-half solutions
        long result = halfCount * 2;

        // Handle middle column for odd N
        if (n % 2 != 0) {

            long middlePosition = 1L << (n / 2);

            result += solve(
                    1,
                    middlePosition,
                    middlePosition << 1,
                    middlePosition >>> 1,
                    fullMask
            );
        }

        return result;
    }

    // Performance benchmark
    static void benchmark(int n) {

        recursiveCalls = 0;

        long start = System.nanoTime();

        totalSolutions = solveWithSymmetry(n);

        long end = System.nanoTime();

        double executionTime =
                (end - start) / 1_000_000.0;

        System.out.println(
                String.format(
                        "%-8d %-15d %-18d %.3f ms",
                        n,
                        totalSolutions,
                        recursiveCalls,
                        executionTime
                )
        );
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("==============================================");
        System.out.println(" ADVANCED N-QUEENS OPTIMIZATION");
        System.out.println(" Bitmasking + Symmetry + Benchmarking");
        System.out.println("==============================================");

        System.out.println("\n1. Solve a specific N-Queens problem");
        System.out.println("2. Run performance benchmark");

        System.out.print("\nEnter choice: ");

        int choice = sc.nextInt();

        if (choice == 1) {

            System.out.print("Enter N (1-15): ");
            int n = sc.nextInt();

            if (n < 1 || n > 15) {
                System.out.println("Invalid N!");
                sc.close();
                return;
            }

            recursiveCalls = 0;

            long start = System.nanoTime();

            totalSolutions = solveWithSymmetry(n);

            long end = System.nanoTime();

            System.out.println("\nTotal Solutions: "
                    + totalSolutions);

            System.out.println("Recursive Calls: "
                    + recursiveCalls);

            System.out.printf(
                    "Execution Time: %.3f ms%n",
                    (end - start) / 1_000_000.0
            );
        }

        else if (choice == 2) {

            System.out.println("\nPERFORMANCE BENCHMARK");

            System.out.println("----------------------------------------------");

            System.out.printf(
                    "%-8s %-15s %-18s %s%n",
                    "N",
                    "Solutions",
                    "Recursive Calls",
                    "Time"
            );

            System.out.println("----------------------------------------------");

            for (int n = 1; n <= 15; n++) {
                benchmark(n);
            }

            System.out.println("----------------------------------------------");
        }

        else {
            System.out.println("Invalid choice!");
        }

        sc.close();
    }
}
