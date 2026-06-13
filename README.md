# breadth-and-depth-search

Path finding in an undirected graph using depth-first search and breadth-first search in Java.

---

## About

Written in Java, this project finds paths between two named vertices in an undirected graph loaded from a text file. Both depth-first search (DFS) and breadth-first search (BFS) are available. The graph data is read from `theDatabase.txt`, where each line contains a pair of connected vertex names. The BFS and DFS implementations are based on reference code from "Algorithms, 4th Edition" by Sedgewick and Wayne.

## Usage

Run the program and enter a source location and a destination. Then choose an algorithm (1 for DFS, 2 for BFS, 3 to pick new locations, or 0 to exit). The path between the two locations is printed to stdout.

## Getting Started

### Prerequisites

- Java 11 or later
- `theDatabase.txt` in the working directory

### Building

**Unix**
```
javac -d out src/se/kth/*.java
```

**Windows**
```
javac -d out src\se\kth\*.java
```

### Running

**Unix**
```
java -cp out se.kth.Main
```

**Windows**
```
java -cp out se.kth.Main
```

---

MIT License - see [LICENSE](LICENSE)
