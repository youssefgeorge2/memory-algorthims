package os;

import java.util.*;

public class LRUpage {
    public static void main(String[] args) {
        int[] pageRequests = {1, 2, 3, 4, 1, 2, 3, 2, 1, 5, 4, 1};
        int numFrames = 3;
        int numPageFaults = getNumPageFaults(pageRequests, numFrames);
        System.out.println("Number of page faults " + numPageFaults);
    }

    public static int getNumPageFaults(int[] pageRequests, int numFrames) {
        LinkedHashMap<Integer, Integer> frameMap = new LinkedHashMap<>();
        int numPageFaults = 0;
        for (int i = 0; i < pageRequests.length; i++) {
            int pageRequest = pageRequests[i];
            if (!frameMap.containsKey(pageRequest)) {
                numPageFaults++;
                if (frameMap.size() == numFrames) {
                    Iterator<Integer> iterator = frameMap.keySet().iterator();
                    iterator.next();
                    iterator.remove();
                }
            } else {
                frameMap.remove(pageRequest);
            }
            frameMap.put(pageRequest, i);
        }
        return numPageFaults;
    }
}
