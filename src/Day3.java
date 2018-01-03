import java.awt.*;

public class Day3 {
    public static void main(String[] args) {
        int size = 361527;
        //size = 25;
        int[][] grid = sumGrid(size);
        printGrid(grid);
/*        Point location = location(size, grid);
        if (location != null) {
            int manhattan = Math.abs(location.x - grid.length/2) + Math.abs(location.y - grid.length/2);
            System.out.println(manhattan);
        }
        System.out.println(":(");*/
    }

    public static int[][] sumGrid(int size) {
        int height = (int) Math.ceil(Math.sqrt(size));
        if (height%2 != 1) {
            height++;
        }
        int[][] grid = new int[height][height];
        System.out.println("Grid height: " + grid.length);
        // 0 = down
        // 1 = right
        // 2 = up
        // 3 = left
        int direction = 1;
        int radius = 1;
        int x = height/2;
        int y = height/2;
        grid[x][y] = 1;

        for(int i=1;i<=height*height;) {
            for(int j=0;j<radius;j++) {
                if (i==1) {
                    i++;
                    j++;
                } else {
                    grid[x][y] = sumOfNeighbors(x, y, grid);/*
                System.out.println(i + ": " + grid[x][y] + "(" + x + "," + y + ")");*//*
                    printGrid(grid);
                    System.out.println();*/
                    if (grid[x][y] > 361527) {
                        System.out.println(grid[x][y]);
                        System.out.println("asdfkjasdfl;ajkdslf;daj");
                        i = 361528;
                        j = 361528;
                    }
                    i++;
                }
                switch(direction) {
                    case 0:
                        y++;
                        break;
                    case 1:
                        x++;
                        break;
                    case 2:
                        y--;
                        break;
                    case 3:
                        x--;
                        break;
                }

            }
            direction = (direction + 1) % 4;
            if (direction%2 == 1) {
                radius++;
            }
        }

        return grid;
    }

    public static int sumOfNeighbors(int x, int y, int[][] grid) {
        int sum = 0;
        int[][] deltas = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        for(int i=0;i<deltas.length;i++) {
            int newX = x + deltas[i][0];
            int newY = y + deltas[i][1];
            if (newX >= 0 && newX < grid.length &&
                    newY >= 0 && newY < grid[0].length) {
                sum += grid[newX][newY];
            }
        }

        return sum;
    }

    public static int[][] grid(int size) {
        int height = (int) Math.ceil(Math.sqrt(size));
        if (height%2 != 1) {
            height++;
        }
        int[][] grid = new int[height][height];
        // 0 = down
        // 1 = right
        // 2 = up
        // 3 = left
        int direction = 1;
        int radius = 1;
        int x = height/2;
        int y = height/2;

        for(int i=1;i<=height*height;) {
            for(int j=0;j<radius;j++) {
                grid[x][y] = i;
                i++;
                switch(direction) {
                    case 0:
                        y++;
                        break;
                    case 1:
                        x++;
                        break;
                    case 2:
                        y--;
                        break;
                    case 3:
                        x--;
                        break;
                }

            }
            direction = (direction + 1) % 4;
            if (direction%2 == 1) {
                radius++;
            }
        }

        return grid;
    }

    public static Point location(int value, int[][] grid) {
        Point point = new Point();
        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[i].length;j++) {
                if (grid[i][j] == value) {
                    return new Point(i,j);
                }
            }
        }
        return null;
    }

    public static void printGrid(int[][] grid) {
        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[i].length;j++){
                System.out.printf("%7d ", grid[i][j]);
            }
            System.out.println();
        }
    }
}
