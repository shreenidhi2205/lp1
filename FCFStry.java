import java.util.*;

public class FCFStry{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes : ");
        int n = sc.nextInt();

        int[] processId = new int[n];
        int[] arrivalTime = new int[n];
        int[] burstTime = new int[n];
        int[] waitingTime = new int[n];
        int[] turnAroundTime = new int[n];

        for(int i=0;i<n;i++){
            System.out.print("Enter processId : ");
            processId[i]=sc.nextInt();
            System.out.print("Enter arrival time : ");
            arrivalTime[i]=sc.nextInt();
            System.out.print("Enter burst time : ");
            burstTime[i]=sc.nextInt();
        }

        for(int i=0;i<n-1;i++){
            for(int j=0;j<n-i-1;j++){
                if(arrivalTime[j]>arrivalTime[j+1]){
                    int temp = arrivalTime[j];
                    arrivalTime[j]=arrivalTime[j+1];
                    arrivalTime[j+1]=temp;

                    temp=burstTime[j];
                    burstTime[j]=burstTime[j+1];
                    burstTime[j+1]=temp;

                    temp=processId[j];
                    processId[j]=processId[j+1];
                    processId[j+1]=temp;
                }
            }
        }

        int time = 0 ;
        for(int i=0;i<n;i++){
            if(time<arrivalTime[i]){
                time = arrivalTime[i];
            }

            waitingTime[i] = time - arrivalTime[i];
            time += burstTime[i];
            turnAroundTime[i]=waitingTime[i]+burstTime[i];
            System.out.print("| P" + processId[i] + " ");
        }
        System.out.print("| \n");

        float avgWaitingTime = 0;
        float avgTurnAroundTime = 0;

        System.out.println("ProcessID\tArrivalTime\tBurstTime\tWaitingTime\tTurnAroundTime");
        for(int i=0;i<n;i++){
            System.out.println("P" + processId[i] + "\t\t" + arrivalTime[i] + "\t\t" + burstTime[i] + "\t\t" + waitingTime[i] + "\t\t" + turnAroundTime[i]);
            avgWaitingTime += waitingTime[i];
            avgTurnAroundTime += turnAroundTime[i];
        }

        System.out.println("Average Waiting Time = "+ (avgWaitingTime/n));
        System.out.println(String.format("Average Turnaround Time = %.2f", (avgTurnAroundTime/n)));

        sc.close();

    }
}