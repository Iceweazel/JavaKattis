import java.util.LinkedList;

public class Grid {

  Kattio io;

  int rows;
  int cols;
  int lastRow;
  int lastCol;
  int[][] grid;

  int minMoves;

  boolean bfs() {

    boolean[][] visited = new boolean[rows][cols];
    int[][] moves = new int[rows][cols];
    minMoves = 0;

    LinkedList<Node> queue = new LinkedList<Node>();
    queue.add(new Node(0,0));
    visited[0][0] = true;
    moves[0][0] = 0;

    while(queue.size() != 0) {
      Node curr = queue.pop();
      int x = curr.row;
      int y = curr.col;
      int val = grid[x][y];

      if(x == lastRow && y == lastCol) {
        break;
      }

      if(y + val < cols) {
        if(!visited[x][y + val]) {
          queue.add(new Node(x, y + val));
          visited[x][y + val] = true;
          moves[x][y + val] = moves[x][y] + 1;
        }
      }
      if(x + val < rows) {
        if(!visited[x + val][y]) {
          queue.add(new Node(x+val, y));
          visited[x + val][y] = true;
          moves[x + val][y] = moves[x][y] + 1;
        }
      }
      if(x - val >= 0) {
        if(!visited[x - val][y]) {
          queue.add(new Node(x-val, y));
          visited[x - val][y] = true;
          moves[x - val][y] = moves[x][y] + 1;
        }
      }
      if(y - val >= 0) {
        if(!visited[x][y - val]) {
          queue.add(new Node(x, y - val));
          visited[x][y - val] = true;
          moves[x][y - val] = moves[x][y] + 1;
        }
      }

    }

    minMoves = moves[lastRow][lastCol];
    return visited[lastRow][lastCol];
  }


  void initGrid() {

    grid = new int[rows][cols];

    for(int i = 0; i < rows; i++) {

      String currRow = io.getWord();

      for(int j = 0; j < cols; j++) {

        grid[i][j] = currRow.charAt(j) - 48;
      }
    }
  }

  public Grid() {

    io = new Kattio(System.in, System.out);
    rows = io.getInt();
    cols = io.getInt();

    lastRow = rows - 1;
    lastCol = cols - 1;

    initGrid();

    if(bfs()) {
      io.println(minMoves);
    } else {
      io.println("-1");
    }

    io.close();


  }

  public static void main(String[] args) {
    new Grid();
  }
}
