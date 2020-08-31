
import java.util.*;

public class q5_A2 {
    int []universe={0,1,2,3,4,5,6,7,8,9,10};
    public static int []new_array(int []A)
    {
        Arrays.sort(A);
        int []new_A=new int[A.length];
        new_A[0]=A[0];
        int j=1;
        for(int i=1;i<A.length;i++)
        {
            if(new_A[j-1]!=A[i])
            {
                new_A[j]=A[i];
                j++;
            }
        }
        int []final_A=new int[j];
        for(int i=0;i<j;i++)
        {
            final_A[i]=new_A[i];
        }
        return final_A;
    }
    public static int[]get_union(int []A, int []B)
    {
        int n1=A.length;
        int n2=B.length;
        int []union=new int[11];
        int i=0,j=0,k=0;
        while(i<n1 && j<n2)
        {
            if(A[i]==B[j])
            {
                union[k]=A[i];
                k++;
                j++;
                i++;
            }
            else if(A[i]<B[j])
            {
                union[k]=A[i];
                k++;
                i++;
            }
            else
            {
                union[k]=B[j];
                j++;
                k++;
            }
        }
        while(i<n1)
        {
            union[k]=A[i];
            i++;
            k++;
        }
        while(j<n2)
        {
            union[k]=B[j];
            j++;
            k++;
        }
        return union;
    }
    public static int[]get_intersection(int[] A, int []B)
    {
        int []intersected=new int[11];
        int i=0,j=0,k=0;
        while(i<A.length && j<B.length)
        {
            if(A[i]==B[j])
            {
                intersected[k]=A[i];
                i++;
                j++;
                k++;
            }
            else if(A[i]<B[j])
            {
                i++;
            }
            else
            {
                j++;
            }
        }
        return intersected;
    }

    public static int[]get_complement(int []A)
    {
        int []temp_arr=new int[11];
        int []comp_arr=new int[11];
        int k=0,j=0;
        for(int i=0;i<A.length;i++)
        {
            temp_arr[A[i]]++;
        }
        for(int i=0;i<=10;i++)
        {
            if(temp_arr[i]==0)
            {
                comp_arr[j]=i;
                j++;
            }
        }
        return comp_arr;
    }
    public static void print_arr(int []A)
    {
        for (int i=0;i< A.length;i++) {
            if(A[i]==0 && i!=0)
                break;
            else
                System.out.print(A[i]+" ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Set<Integer> uni_set = new HashSet<Integer>();
        uni_set.addAll(Arrays.asList(new Integer[] {0,1,2,3,4,5,6,7,8,9,10}));
        Set<Integer> A_set = new HashSet<Integer>();
        Set<Integer> B_set = new HashSet<Integer>();
        System.out.println("Enter the length of A array:");
        int length = s.nextInt();
        int maxi=length;
        int mini=maxi;
        int [] A = new int[length];
        System.out.println("Enter the elements of A:");
        for(int i=0; i<length; i++ ) {
            A[i] = s.nextInt();
            A_set.add(A[i]);
        }
        System.out.println("Enter the length of B array:");
        length = s.nextInt();
        int [] B = new int[length];
        System.out.println("Enter the elements of B:");
        for(int i=0; i<length; i++ ) {
            B[i] = s.nextInt();
            B_set.add(B[i]);
        }

        A=new_array(A);
        B=new_array(B);
        System.out.println("****UNION****");
        long start = System.nanoTime();
        int []union=get_union(A,B);
        long end = System.nanoTime();
        System.out.println("The union of A and B :");
        print_arr(union);
        long x=end-start;
        System.out.println("Time taken using arrays "+ x +" sec");
        Set<Integer> hash_Set = new HashSet<Integer>();
        start = System.nanoTime();
        hash_Set.addAll(A_set);
        hash_Set.addAll(B_set);
        end = System.nanoTime();
        System.out.println("The union of A and B using sets:");
        for (int element: hash_Set) {
            System.out.print(element+" ");
        }
        System.out.println();
        x=end-start;
        System.out.println("Time taken using sets "+ x +" sec");

        System.out.println("****INTERSECTION****");
        start = System.nanoTime();
        int []intersected_arr=get_intersection(A,B);
        end = System.nanoTime();
        System.out.println("The intersection of A and B :");
        print_arr(intersected_arr);
        x=end-start;
        System.out.println("Time taken using arrays "+ x +" sec");
        start = System.nanoTime();
        Set<Integer> hash_Set_2 = new HashSet<Integer>(A_set);
        hash_Set_2.retainAll(B_set);
        end = System.nanoTime();
        System.out.println("The intersection of A and B using sets:");
        for (int element: hash_Set_2) {
            System.out.print(element+" ");
        }
        System.out.println();
        x=end-start;
        System.out.println("Time taken using sets "+ x +" sec");

        System.out.println("****COMPLEMENT****");
        start = System.nanoTime();
        int []comp_arr_A=get_complement(A);
        int []comp_arr_B=get_complement(B);
        end = System.nanoTime();
        System.out.println("Complement of A using arrays: ");
        print_arr(comp_arr_A);
        System.out.println("Complement of B using arrays: ");
        print_arr(comp_arr_B);
        x=end-start;
        System.out.println("Time taken using arrays "+ x +" sec");

        start = System.nanoTime();
        Set<Integer> comp_A= new HashSet<Integer>(uni_set);
        Set<Integer> comp_B= new HashSet<Integer>(uni_set);
        comp_A.removeAll(A_set);
        comp_B.removeAll(B_set);
        end = System.nanoTime();
        System.out.println("Complement of A using sets: ");
        for (int element: comp_A) {
            System.out.print(element+" ");
        }
        System.out.println();
        System.out.println("Complement of B using sets: ");
        for (int element: comp_B) {
            System.out.print(element+" ");
        }
        System.out.println();
        x=end-start;
        System.out.println("Time taken using sets "+ x +" sec");
    }
}
