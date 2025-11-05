import java.util.*;

public class RoundRobin{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes : ");
        int n = sc.nextInt();

        int[] processId = new int[n];
        int[] arrivalTime = new int[n];
        int[] burstTime = new int[n];
        int[] remainingTime = new int[n];
        int[] waitingTime = new int[n];
        int[] turnAroundTime = new int[n];
        int[] completionTime = new int[n];

        for(int i=0;i<n;i++){
            System.out.print("Enter process Id : ");
            processId[i] = sc.nextInt();
            System.out.print("Enter arrival time : ");
            arrivalTime[i] = sc.nextInt();
            System.out.print("Enter burst time : ");
            burstTime[i] = sc.nextInt();
            remainingTime[i]=burstTime[i];
        }

        System.out.print("Enter time quantum : ");
        int timeQuantum = sc.nextInt();

        Queue<Integer> readyQueue = new LinkedList<>();
        int time = 0;
        int completed = 0;
        boolean[] inQueue = new boolean[n];
        readyQueue.add(0);
        inQueue[0]=true;

        System.out.print("\nGantt Chart");
        while(completed<n){
            if(readyQueue.isEmpty()){
                for(int i=0;i<n;i++){
                    if(remainingTime[i]>0){
                        readyQueue.add(i);
                        inQueue[i] = true;
                        break;
                    }
                }
            }

            int i = readyQueue.poll();
            inQueue[i] = false;

            if(time<arrivalTime[i]){
                time = arrivalTime[i];
            }

            int exec = Math.min(timeQuantum,remainingTime[i]);
            System.out.print("| P"+processId[i]+" ");
            remainingTime[i] -= exec;
            time+=exec;

            for(int j=0;j<n;j++){
                if(j != i && remainingTime[j]>0 && arrivalTime[j]<=time && !inQueue[j]){
                    readyQueue.add(j);
                    inQueue[j]=true;
                }
            }

            if(remainingTime[i]==0){
                completed++;
                completionTime[i] = time;
                turnAroundTime[i] = completionTime[i] - arrivalTime[i];
                waitingTime[i] = turnAroundTime[i] - burstTime[i];
            }else{
                readyQueue.add(i);
            }
            
    }
    System.out.print("|");

            float avgWaitingTime = 0;
            float avgTurnAroundTime = 0;

            System.out.println();
            System.out.println("ProcessId\tArrivalTime\tBurstTime\tWaitingTime\tTurnAroundTime\tCompletionTime");
            for(int i=0;i<n;i++){
                System.out.println("P"+processId[i]+"\t\t"+arrivalTime[i]+"\t\t"+burstTime[i]+"\t\t"+waitingTime[i]+"\t\t"+turnAroundTime[i]+"\t\t"+completionTime[i]);
                avgWaitingTime += waitingTime[i];
                avgTurnAroundTime += turnAroundTime[i];
            }
            System.out.println("Average Waiting Time : " + (avgWaitingTime/n));
            System.out.println("Average TurnAround Time : " + (avgTurnAroundTime/n));

            sc.close();
        }
}