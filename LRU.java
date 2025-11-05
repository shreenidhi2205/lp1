import java.util.*;

public class LRU{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter frame size : ");
        int fs = sc.nextInt();

        System.out.print("Enter length of Page reference string : ");
        int np = sc.nextInt();
        int[] pages = new int[np];

        for(int i=0;i<np;i++){
            pages[i]=sc.nextInt();
        }

        List<Integer> memory = new ArrayList<>();
        int pageFaults = 0;

        System.out.println("Pages : " + Arrays.toString(pages));
        System.out.println("\nPages\tFrameContents\tStatus");

        for(int page : pages){
            if(memory.contains(page)){
                memory.remove(Integer.valueOf(page));
                memory.add(page);
                System.out.println(page +"\t"+memory+"\tNoFault");
            }else{
                pageFaults++;

                if(memory.size() == fs){
                    memory.remove(0);
                }
                memory.add(page);

                System.out.println(page+"\t"+memory+"\tPageFault");
            }
        }
        System.out.print("Total number of page faults : "+pageFaults);
    }
}