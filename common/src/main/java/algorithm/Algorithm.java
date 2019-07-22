package algorithm;

public class Algorithm{

    /**
     * 集合中所有元素的所有组合
     * @param args
     */
    public static void allCombination(String[] args) {

        final String[] strSet = {"s","x","n","j","k","l"};
        long max1 = 1<<strSet.length;
        for (int i = 1; i < max1; i++) {
            for (int j = 0; j < strSet.length; j++) {
                // TODO 啥意思啊？
                if ((i&(1<<j))!=0) {
                    System.out.print(strSet[j] + ", ");
                }
            }
            System.out.println();
        }

        final int[] numSet = {1,2,3,4};
        long max = 1<<numSet.length;
        for (int i = 1; i < max; i++) {
            for (int j = 0; j < numSet.length; j++) {
                if ((i&(1<<j))!=0) {
                    System.out.print(numSet[j] + ", ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Algorithm.allCombination(new String[]{""});
    }
}