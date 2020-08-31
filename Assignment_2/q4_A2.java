public class q4_A2 {
    public static void main(String[] args) {
        int ans=0;
        long sum=0;
        for(int i=1;i<Integer.MAX_VALUE;i++)
        {
            sum+=i;
            if(sum==i*i && ans==0)
                ans=i;
            else if(sum==i*i && i<ans)
                ans=i;
        }
        System.out.println("The smallest number that follows the given criterion is "+ans);
    }
}
