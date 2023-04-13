package os;

import java.util.*;

public class MFUpage {
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
                    int maxCount = Collections.max(frameMap.values());
                    List<Integer> keysToRemove = new ArrayList<>();
                    for (Map.Entry<Integer, Integer> entry : frameMap.entrySet()) {
                        if (entry.getValue() == maxCount) {
                            keysToRemove.add(entry.getKey());
                        }
                    }
                    for (int key : keysToRemove) {
                        frameMap.remove(key);
                    }
                }
            }
            frameMap.put(pageRequest, frameMap.getOrDefault(pageRequest, 0) + 1);
        }
        return numPageFaults;
    }
}
