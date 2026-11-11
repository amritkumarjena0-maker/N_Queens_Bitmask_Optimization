
import java.util.Scanner;

public class NQueensBitmask {

    static int N;
    static int[] board;
    static long solutionCount = 0;

    static void printBoard() {

        System.out.println("\nSolution " + (++solutionCount) + ":");

        for (int row = 0; row < N; row++) {

            for (int col = 0; col < N; col++) {

                if (board[row] == col)
                    System.out.print(" Q ");
                else
                    System.out.print(" . ");
            }

            System.out.println();
        }
    }

    static void solveNQueens(
            int row,
            long columns,
            long leftDiagonal,
            long rightDiagonal,
            long fullMask) {

        // Base case: All queens are placed
        if (row == N) {
            printBoard();
            return;
        }

        // Calculate available positions
        long available = fullMask &
                ~(columns | leftDiagonal | rightDiagonal);

        while (available != 0) {

            // Extract rightmost available bit
            long position = available & -available;

            // Remove selected position
            available ^= position;

            // Get column index
            int col = Long.numberOfTrailingZeros(position);

            board[row] = col;

            // Recursive backtracking
            solveNQueens(
                    row + 1,
                    columns | position,
                    (leftDiagonal | position) << 1,
                    (rightDiagonal | position) >>> 1,
                    fullMask
            );
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("====================================");
        System.out.println(" N-QUEENS USING BITMASKING IN JAVA");
        System.out.println("====================================");

        System.out.print("Enter number of queens (1-15): ");
        N = sc.nextInt();

        if (N < 1 || N > 15) {
            System.out.println("Invalid input! Enter N between 1 and 15.");
            sc.close();
            return;
        }

        board = new int[N];

        long fullMask = (1L << N) - 1;

        long startTime = System.nanoTime();

        solveNQueens(
                0,
                0,
                0,
                0,
                fullMask
        );

        long endTime = System.nanoTime();

        System.out.println("\n====================================");
        System.out.println("Total Solutions: " + solutionCount);
        System.out.printf("Execution Time: %.3f ms%n",
                (endTime - startTime) / 1_000_000.0);
        System.out.println("====================================");

        sc.close();
    }
}
