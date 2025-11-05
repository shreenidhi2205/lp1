import java.util.*;

public class WorstFit{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of memory blocks : ");
        int nb = sc.nextInt();
        int[] blockSize = new int[nb];

        for(int i=0;i<nb;i++){
            System.out.print("Enter block " +(i+1)+ " size : ");
            blockSize[i]=sc.nextInt();
        }

        System.out.print("Enter number of processes : ");
        int n = sc.nextInt();

        int[] processSize = new int[n];
        int[] allocation = new int[n];
        Arrays.fill(allocation,-1);

        for(int i=0;i<n;i++){
            System.out.print("Enter process "+(i+1)+" size : ");
            processSize[i]=sc.nextInt();

            int worstIndex = -1;
            for(int j=0;j<nb;j++){
                if(blockSize[j]>=processSize[i]){
                    if(worstIndex==-1 || blockSize[j]>blockSize[worstIndex]){
                        worstIndex = j;
                    }
                }
            }
            if(worstIndex!=-1){
                allocation[i] = worstIndex+1;
                blockSize[worstIndex]-=processSize[i];
            }
        }
        System.out.println("\nMemory Allocation Table : ");
        System.out.println("Process\tSize\tBlockAllocated");
        for(int i=0;i<n;i++){
            if(allocation[i]!=-1){
                System.out.println("P"+(i+1)+"\t"+processSize[i]+"\t"+allocation[i]);
            }else{
                System.out.println("P"+(i+1)+"\t"+processSize[i]+"\tNot Allocated");
            }
        }

        System.out.println("\nRemaining Memory in blocks:");
        for(int i=0;i<nb;i++){
            System.out.println("Block"+(i+1)+" -> " + blockSize[i]+" KB");       
        }

        sc.close();
    }
}