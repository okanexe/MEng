public class KAralik {

    public static int index = 1;

    public  static int[] KAralik(int[] arr, int nu){
        if (arr.length < nu*index) {
            System.out.println("finished");
            return arr;
        }
        else
            System.out.println(arr[arr.length-nu*index]);
            index=index + 1;
        return KAralik(arr, nu);
    }

    public  static void main (String[] args){
        /*int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        KAralik(arr,2);*/

        int[] arr2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        KAralik(arr2, 4);

    }
}
