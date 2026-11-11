
# Parallel N-Queens Solver

## Description
An advanced Java implementation of the N-Queens problem using Bitmasking, Backtracking, and Parallel Computing with ForkJoinPool. The project distributes independent search branches across multiple CPU workers to accelerate solution counting.

## Features
- N-Queens problem solving using Bitmasking.
- Optimized column and diagonal checking.
- Parallel execution using Java ForkJoinPool.
- RecursiveTask-based parallel search.
- Sequential and parallel performance comparison.
- CPU processor detection.
- Execution time measurement.
- Speedup calculation.
- Supports board sizes from 1 to 15.

## Technologies
- Java
- Object-Oriented Programming
- Bitwise Operations
- Backtracking
- Multithreading
- ForkJoin Framework

## Requirements
- Java JDK
- Java-supported development environment

## How to Run
Compile the program:

```bash
javac ParallelNQueens.java
```

Run the program:

```bash
java ParallelNQueens
```

## Algorithm
1. Initialize the N × N board using bitmasks.
2. Represent occupied columns and diagonals using bitwise integers.
3. Calculate available positions using bitwise operations.
4. Divide independent search branches into parallel tasks.
5. Execute tasks using ForkJoinPool.
6. Apply recursive backtracking to explore valid positions.
7. Combine results returned by parallel tasks.
8. Run sequential execution for comparison.
9. Calculate execution time and speedup.
10. Display the total number of valid solutions.

## Flowchart

```text
        START
          |
          v
    Input Board Size N
          |
          v
    Initialize Bitmasks
          |
          v
    Create ForkJoinPool
          |
          v
   Divide Search Branches
          |
          v
   Execute Parallel Tasks
          |
          v
   Bitmask Backtracking
          |
          v
    Combine Task Results
          |
          v
   Compare Sequential Time
          |
          v
   Calculate Speedup
          |
          v
    Display Results
          |
          v
         END
```

## Expected Results

| Board Size | Total Solutions |
|---|---:|
| 4 × 4 | 2 |
| 5 × 5 | 10 |
| 6 × 6 | 4 |
| 7 × 7 | 40 |
| 8 × 8 | 92 |
| 10 × 10 | 724 |
| 12 × 12 | 14,200 |
| 15 × 15 | 2,279,184 |

## Complexity Analysis
- Time Complexity: O(N!) upper bound.
- Auxiliary Space Complexity: O(N) per recursive search path.
- Bitmask column checking: O(1).
- Bitmask diagonal checking: O(1).

## Note
Parallel performance depends on CPU cores, task scheduling, and JVM overhead. Parallel execution may not be faster for smaller board sizes.

## Author
Amrit Kumar Jena
