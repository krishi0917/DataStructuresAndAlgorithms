package LeetcodePrograms;

public class Medianof2sorted {


    public static int findk(int []input1 , int[] input2){
            int count=0;
            int a=0,b=0,c=0,d=0;
             int k=1;
             while (k < input1.length ) {
                 for (int i = 0; i < k; i++) {
                     a += input1[i];
                 }

                 for (int i = k; i < input1.length; i++) {
                     b += input1[i];
                 }

                 for (int i = 0; i < k; i++) {
                     c += input2[i];
                 }

                 for (int i = k; i < input2.length; i++) {
                     d += input2[i];
                 }

                if(a==b){
                    if(c==d){
                        if(a==c) {
                            count++;
                        }
                    }
                }
                a=0;b=0;c=0;d=0;
                k++;

             }

        return count;
}

public static void main(String []args){
        int a[] ={4,-1,0,3};
        int b[] ={-2,5,0,3};

        int c[] ={2,-2,-3,3};
        int d[] ={0,0,4,-4};

    int e[] ={4,-1,0,3};
    int f[] ={-26,0,4};

    int g[] ={3,2,6};
    int h[] ={4,1,6};

    int i[] ={1,4,2,-2,5};
    int j[] ={7,-2,-2,2,5};

    System.out.println(findk(c,d));
    }
}
