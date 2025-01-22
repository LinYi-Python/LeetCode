import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName MapofHighestPeak1765
 * @Description TODO
 * @Author LinPython
 * @Date 1/22/25 00:56
 * @Version 1.0
 **/
public class MapofHighestPeak1765 {
    public int[][] highestPeak(int[][] isWater) {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0 ,0};

        int rows = isWater.length;
        int cols = isWater[0].length;

        // Initialize the height matrix with -1 (unprocessed cells)
        int[][] cellHeights = new int[rows][cols];
        for (int[] row : cellHeights) {
            Arrays.fill(row, -1);
        }

        Queue<int[]> cellQueue = new LinkedList<>();

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++){
                if (isWater[x][y] == 1) {
                    cellQueue.add(new int[] { x, y });
                    cellHeights[x][y] = 0;
                }
            }
        }

        int heightOfNextLayer = 1;

        while (!cellQueue.isEmpty()) {
            int layerSize = cellQueue.size();

            for (int i = 0; i < layerSize; i++) {
                int[] currentCell = cellQueue.poll();

                // Check all four possible directions for neighboring cells
                for (int d = 0; d < 4; d++) {
                    int neighborX = currentCell[0] + dx[d];
                    int neighborY = currentCell[1] + dy[d];

                    // Check if the neighbor is valid and unprocessed
                    if (
                            isValidCell(neighborX, neighborY, rows, cols) &&
                                    cellHeights[neighborX][neighborY] == -1
                    ) {
                        cellHeights[neighborX][neighborY] = heightOfNextLayer;
                        cellQueue.add(new int[] { neighborX, neighborY });
                    }
                }
            }
            heightOfNextLayer++;
        }
        return cellHeights;
    }

    private boolean isValidCell(int x, int y, int rows, int cols) {
        return x >= 0 && y >= 0 && x < rows && y < cols;
    }
}
