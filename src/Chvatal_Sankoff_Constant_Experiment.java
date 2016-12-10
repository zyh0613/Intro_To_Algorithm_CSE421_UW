import java.util.Random;

/**
 * Created by yuhaoz3 on 11/22/2016.
 */
public class Chvatal_Sankoff_Constant_Experiment {
    public static void main(String[] args) {
        int STRING_SIZE = 100000;
        int alphabetSetSize;
        Random r = new Random();
        for (alphabetSetSize = 1; alphabetSetSize <= 26; alphabetSetSize++) {
            int[] s1 = getRandomString(r, alphabetSetSize, STRING_SIZE);
            int[] s2 = getRandomString(r, alphabetSetSize, STRING_SIZE);
            System.out.println("k = " + alphabetSetSize + "; lambda= " + getChvatalSankoffConstant(s1, s2, STRING_SIZE) / STRING_SIZE);
        }
    }

    public static double getChvatalSankoffConstant(int[] s1, int[] s2, int n) {
        double sum = 0;
        double times = 5.0;
        for (int i = 0; i < times; i++) {
            sum += getSizeOfLCS(s1, s2, n);
        }
        return sum / times;
    }

    public static double getSizeOfLCS(int[] s1, int[] s2, int n) {
        int[][] arr = new int[2][n + 1];
        int k = 0;
        for (int i = 1; i < n + 1; i++) {
            k = i % 2;
            for (int j = 1; j < n + 1; j++) {
                if (s1[i-1] == s2[j-1]) {
                    arr[k][j] = arr[(k-1)*(k-1)][j-1] + 1;
                } else {
                    arr[k][j] = Math.max(arr[k][j-1], arr[(k-1)*(k-1)][j]);
                }
            }
        }
        return arr[k][n];
    }

    public static int[] getRandomString(Random r, int k, int n) {
        int[] str = new int[n];
        for (int i = 0; i < n; i++) {
            str[i] = r.nextInt(k);
        }
        return str;
    }
}