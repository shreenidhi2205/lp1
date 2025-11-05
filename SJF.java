import java.util.*;

public class SJF{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes : ");
        int n = sc.nextInt();

        int[] processId = new int[n];
        int[] arrivalTime = new int[n];
        int[] burstTime = new int[n];
        int[] remainingTime = new int[n];
        int[] completionTime = new int[n];
        int[] waitingTime = new int[n];
        int[] turnAroundTime = new int[n];
        boolean[] isCompleted = new boolean[n];

        for(int i=0;i<n;i++){
            System.out.print("Enter process Id : ");
            processId[i] = sc.nextInt();
            System.out.print("Enter the arrival time : ");
            arrivalTime[i] = sc.nextInt();
            System.out.print("Enter the burst time : ");
            burstTime[i] = sc.nextInt();
            remainingTime[i]=burstTime[i];
        }

        int completed = 0 ;
        int time = 0;

        while(completed<n){
            int minIndex = -1;
            int minRemainingTime = Integer.MAX_VALUE;

            for(int i=0;i<n;i++){
                if(arrivalTime[i]<=time && !isCompleted[i] && remainingTime[i]<minRemainingTime && remainingTime[i] > 0){
                    minRemainingTime =remainingTime[i];
                    minIndex = i;
                }
            }

            if(minIndex==-1){
                time++;
                continue;
            }

            remainingTime[minIndex]--;
            time++;

            if(remainingTime[minIndex]==0){
                isCompleted[minIndex] = true;
                completed++;
                completionTime[minIndex] = time;
                turnAroundTime[minIndex] = completionTime[minIndex] - arrivalTime[minIndex];
                waitingTime[minIndex] = turnAroundTime[minIndex] - burstTime[minIndex];
            }
        }

            float avgWaitingTime = 0;
            float avgTurnAroundTime = 0;

            System.out.println("ProcessId\tArrivalTime\tBurstTime\tCompletionTime\tWaitingTime\tTurnaroundTime");
            for(int i=0;i<n;i++){
                System.out.println("P"+processId[i]+"\t\t"+arrivalTime[i]+"\t\t"+burstTime[i]+"\t\t"+completionTime[i]+"\t\t"+waitingTime[i]+"\t\t"+turnAroundTime[i]);
                avgWaitingTime += waitingTime[i];
                avgTurnAroundTime += turnAroundTime[i];
            }

            System.out.println("Average Waiting Time : " + (avgWaitingTime/n));
            System.out.println("Average TurnAround Time : " + (avgTurnAroundTime/n));

            sc.close();
        }
}    
