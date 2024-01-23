package ru.job4j.concurrent.pool;

public class RolColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }
    }

    public static Sums[] sum(int[][] matrix) {
        int rowSize = matrix.length;
        int colSize = matrix[0].length;
        Sums[] sums = new Sums[rowSize];
        int rowSum = 0;

        for (int i = 0; i < rowSize; i++) {
            int colSum = 0;
            for (int j = 0; j < colSize; j++) {
                colSum += matrix[i][j];
            }
            /*           sums[i] = new Sums().setColSum(colSum);

             */
        }
    }

    public static Sums[] asyncSum(int[][] matrix) {

    }

}
