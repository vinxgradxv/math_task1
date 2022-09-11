import java.util.Scanner;

class Main {
    private static int[][] res;
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        int m1;
        int n1;
        int m2;
        int n2;
        System.out.println("Введите размерность первой матрицы. Пример - 10 10");
        m1 = console.nextInt();
        n1 = console.nextInt();
        int[][] matrix1;
        if (m1 > 0 && n1 > 0) {
            System.out.println("Введите первую матрицу");
            matrix1 = readMatrix(m1, n1, console);
        }
        else {
            throw new ArrayIndexOutOfBoundsException("Размерность матрицы должна быть положительной");
        }
        System.out.println("Введите размерность второй матрицы. Пример - 10 10");
        m2 = console.nextInt();
        n2 = console.nextInt();
        int[][] matrix2;
        if (m2 > 0 && n2 > 0) {
            System.out.println("Введите вторую матрицу");
            matrix2 = readMatrix(m2, n2, console);
        } else {
            throw new ArrayIndexOutOfBoundsException("Размерность матрицы должна быть положительной");
        }
        if (n1 == m2) {
            res = new int[m1][n2];
            for (int i = 0; i < m1; i++) {
                for (int j = 0; j < n2; j++) {
                    int s = 0;
                    for (int l = 0; l < n1; l++) {
                        s += matrix1[i][l] * matrix2[l][j];
                    }
                    res[i][j] = s;
                }
            }
            System.out.println("Результирующая матрица:");
            for (int i = 0; i < m1; i++) {
                StringBuilder otv = new StringBuilder("");
                for (int j = 0; j < n2; j++) {
                    otv.append(String.valueOf(res[i][j])).append(" ");
                }
                System.out.println(otv);
            }
            System.out.println("Детерминант:");
            if (m1 == n2) {
                System.out.println(determinant(res));
            }
            else {
                System.out.println("Результирующая матрица должна быть квадратной , чтобы можно было высчитать детерминант");
            }
        }
        else {
            System.out.println("Количество столбцов первой матрицы должно совпадать с количеством строк второй");
        }

    }

    private static int[][] readMatrix(int m, int n, Scanner console) {
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = console.nextInt();
            }
        }
        return res;
    }

    private static int determinant(int[][]cur) {
        int s = 0;
        if (cur.length == 1) {
            return cur[0][0];
        }
        else {
            int mul = 1;
            for (int i = 0; i < cur.length; i ++) {
                int[][] newCur = new int[cur.length - 1][cur.length - 1];
                for (int j = 1; j < cur.length; j++) {
                    int realL = 0;
                    for (int l = 0; l < cur.length; l++) {
                        if (l != i) {
                            newCur[j - 1][realL] = cur[j][l];
                            realL += 1;
                        }
                    }
                }
                s += mul * determinant(newCur) * cur[0][i];
                mul *= -1;
            }
        }
        return s;
    }
}