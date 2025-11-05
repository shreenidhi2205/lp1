import java.util.*;

public class NextFit{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of memory blocks: ");
        int nb = sc.nextInt();
        int[] blockSize = new int[nb];

        for(int i=0;i<nb;i++){
            System.out.print("Enter block "+(i+1)+" size :");
            blockSize[i] = sc.nextInt();
        }

        System.out.print("Enter number of processes : ");
        int n = sc.nextInt();
        int[] processSize = new int[n];
        int[] allocation = new int[n];
        Arrays.fill(allocation,-1);

        int lastAllocated =0;

        for(int i=0;i<n;i++){
            System.out.print("Enter process "+(i+1)+" size : ");
            processSize[i] = sc.nextInt();

            boolean allocated = false;
            int count =0;

            while(count<nb){
                int j = (lastAllocated + count)%nb;
                if(blockSize[j]>=processSize[i]){
                    allocation[i] = j+1;
                    blockSize[j] -= processSize[i];
                    lastAllocated=j;
                    allocated =true;
                    break;
                }
                count++;
            }
            if(!allocated){
                allocation[i]=-1;
            }
        }
        System.out.println("\n Memory Allocation Table");
        System.out.println("Process\tSize\tBlockAllocated");
        for(int i=0;i<n;i++){
            if(allocation[i]!=-1){
            System.out.println("P"+(i+1)+"\t"+processSize[i]+"\tB"+allocation[i]);
            } else{
                System.out.println("P"+(i+1)+"\t"+processSize[i]+"\tNotAllocated");
            }
        }

        System.out.println("\nRemaining Memory in Blocks:");
        for(int i=0;i<nb;i++){
            System.out.println("Block "+(i+1)+" -> "+ blockSize[i]+" KB");
        }

        sc.close();
    }
}