import java.util.*;

public class FIFO{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter frame size : ");
        int fs = sc.nextInt();
        System.out.print("Enter length of page reference String : ");
        int np = sc.nextInt();
        int[] pages = new int[np];

        for(int i=0;i<np;i++){
            pages[i] = sc.nextInt();
        }

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();

        int pageFault = 0;
        System.out.println("Pages : "+ Arrays.toString(pages));

        for(int page : pages ){
            if(!set.contains(page)){
                if(set.size()<fs){
                    set.add(page);
                    queue.add(page);
                }else{
                    int removed = queue.poll();
                    set.remove(removed);
                    set.add(page);
                    queue.add(page);
                }
                pageFault++;
                System.out.println("Page "+page+ " -> Page Fault");
            }else{
                System.out.println("Page "+page+" -> No fault");
            }
        }
        System.out.println("Total number of page faults : "+ pageFault);
    }
}