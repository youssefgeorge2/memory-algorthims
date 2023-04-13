package os;

import java.util.*;

public class FIFOPage {
    public static void main(String[] args) {
        int[] pageRequests ={1, 2, 3, 4, 1, 2, 3, 2, 1, 5, 4, 1};
        int numFrames = 3;
        int numPageFaults = getNumPageFaults(pageRequests, numFrames);
        System.out.println("Number of page faults " + numPageFaults);
    }

    public static int getNumPageFaults(int[] pageRequests, int numFrames) {
        Queue<Integer> frameQueue = new LinkedList<>();
        Set<Integer> frameSet = new HashSet<>();
        int numPageFaults = 0;
        for (int i = 0; i < pageRequests.length; i++) {
            int pageRequest = pageRequests[i];
            if (!frameSet.contains(pageRequest)) {
                numPageFaults++;
                if (frameQueue.size() == numFrames) {
                    int frameToRemove = frameQueue.remove();
                    frameSet.remove(frameToRemove);
                }
                frameQueue.add(pageRequest);
                frameSet.add(pageRequest);
            }
        }
        return numPageFaults;
    }
}
