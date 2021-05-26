package LeetcodePrograms;

import java.util.Scanner;


/**
 * @author Rishi Khurana
 */
public class LeakyBucketAlgorithm {
    private static int no_of_packet,bucket_capacity,array_size,current_bucket,over_flow,fixed_data_flow;
    private static int array[];
    public void LeakyBucket()
    {
        current_bucket=0;
        System.out.print("\nCurrent Bucket size :" +current_bucket );
        for(int i=0;i<array_size;i++)
        {
            int input=array[i];
            System.out.print("\n----------------------\nInput to bucket is " +input );
            over_flow=0;
            current_bucket=current_bucket+input;
            System.out.print("\nCurrent Bucket size :"+current_bucket);
            if(current_bucket<=fixed_data_flow) {
                current_bucket = 0;
            } else {
                current_bucket=current_bucket-fixed_data_flow;
            }

            if(current_bucket<=bucket_capacity) {
                System.out.print("\nNO OverFLOW");
            } else {
                over_flow=(current_bucket-bucket_capacity);
                current_bucket=bucket_capacity;
                System.out.print("\nOver Flow Occured :"+over_flow);
            }
            System.out.print("\nAfter Processing Bucket size is :"+current_bucket);

        }
    }
    public static void main(String args[])
    {
        LeakyBucketAlgorithm pr = new LeakyBucketAlgorithm();
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the Bucket Capacity : ");
        bucket_capacity = scan.nextInt();
        System.out.print("\nEnter the Bucket Fixed Data Flow : ");
        fixed_data_flow = scan.nextInt();
        System.out.print("\nEnter the Array Size : ");
        array_size=scan.nextInt();
        System.out.println("\nEnter the Input values of size : "+array_size);
        array = new int[array_size];
        for(int i=0;i<array_size;i++)
        {
            array[i]=scan.nextInt();
        }

        System.out.println("The Input for LeakyBucket is ");
        for(int i=0;i<array_size;i++)
        {
            System.out.print(array[i]+ " " );
        }

        pr.LeakyBucket();
        System.out.print("\n\nPROGRAM TERMINATING SUCCESSFULLY...\n");
        scan.close();
    }
}
