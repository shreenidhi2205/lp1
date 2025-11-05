import java.util.*;

public class PriorityScheduling{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes : ");
        int n = sc.nextInt();

        int[] processId = new int[n];
        int[] arrivalTime = new int[n];
        int[] burstTime = new int[n];
        int[] priority = new int[n];
        int[] waitingTime = new int[n];
        int[] turnAroundTime = new int[n];
        boolean[] isCompleted = new boolean[n];

        for(int i=0;i<n;i++){
            System.out.print("Enter processId : ");
            processId[i]=sc.nextInt();
            System.out.print("Enter arrival time : ");
            arrivalTime[i]=sc.nextInt();
            System.out.print("Enter burst time : ");
            burstTime[i] = sc.nextInt();
            System.out.print("Enter priority (lower number has higher priority) : ");
            priority[i]=sc.nextInt();
        }

        int completed =0;
        int time =0;

        while(completed<n){
            int index = -1;
            int highestPriority = Integer.MAX_VALUE; //smaller = higher priority

            for(int i=0;i<n;i++){
                if(arrivalTime[i]<=time && !isCompleted[i] && priority[i]<highestPriority){
                    highestPriority = priority[i];
                    index = i;
                }
            }

            if(index==-1){
                time++;
                continue;
            }

            if(arrivalTime[index]>time){
                time = arrivalTime[index];
            }

            waitingTime[index] = time - arrivalTime[index];
            time += burstTime[index];
            turnAroundTime[index] = waitingTime[index] + burstTime[index];
            isCompleted[index]=true;
            completed++;
        }

        float avgWaitingTime = 0;
        float avgTurnAroundTime = 0;

        System.out.println("\nProcessId\tArrivalTime\tBurstTime\tWaitingTime\tTurnAroundTime");
        for(int i=0;i<n;i++){
            System.out.println("P"+processId[i]+"\t\t"+arrivalTime[i]+"\t\t"+burstTime[i]+"\t\t"+waitingTime[i]+"\t\t"+turnAroundTime[i]);
            avgWaitingTime+=waitingTime[i];
            avgTurnAroundTime+=turnAroundTime[i];
        }

        System.out.printf("\nAverage Waiting Time : %.2f" , (avgWaitingTime/n));
        System.out.println(String.format("\nAverage Turnaround Time : %.2f" , (avgTurnAroundTime/n)));

        sc.close();
    }
}