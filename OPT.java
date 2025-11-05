import java.util.*;

public class OPT {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of frames: ");
        int frameCount = sc.nextInt();

        System.out.print("Enter length of page reference string: ");
        int n = sc.nextInt();

        int[] pages = new int[n];
        System.out.println("Enter the page reference string:");
        for (int i = 0; i < n; i++) {
            pages[i] = sc.nextInt();
        }

        List<Integer> frames = new ArrayList<>(frameCount);
        int pageFaults = 0;

        System.out.println("\nPage\tFrame Contents\t\tStatus");

        for (int i = 0; i < n; i++) {
            int page = pages[i];

            // Case 1: Page already in memory
            if (frames.contains(page)) {
                System.out.printf("%d\t%s\tNo Fault%n", page, frames);
                continue;
            }

            // Case 2: Page fault occurs
            pageFaults++;

            // If frames are not full → simply add the page
            if (frames.size() < frameCount) {
                frames.add(page);
            } else {
                // Find the page to replace (Optimal)
                int indexToReplace = findOptimalPage(frames, pages, i + 1);
                frames.set(indexToReplace, page);
            }

            System.out.printf("%d\t%s\tPage Fault%n", page, frames);
        }

        System.out.println("\nTotal Page Faults: " + pageFaults);
        sc.close();
    }

    // Function to find the page that will not be used for the longest time in the future
    private static int findOptimalPage(List<Integer> frames, int[] pages, int startIndex) {
        int farthestIndex = -1;
        int pageToReplaceIndex = -1;

        for (int i = 0; i < frames.size(); i++) {
            int currentPage = frames.get(i);
            int j;
            for (j = startIndex; j < pages.length; j++) {
                if (pages[j] == currentPage) {
                    if (j > farthestIndex) {
                        farthestIndex = j;
                        pageToReplaceIndex = i;
                    }
                    break;
                }
            }
            // If page not found in future, replace it immediately
            if (j == pages.length) {
                return i;
            }
        }

        // If all pages are used again, replace the farthest one
        return (pageToReplaceIndex == -1) ? 0 : pageToReplaceIndex;
    }
}
