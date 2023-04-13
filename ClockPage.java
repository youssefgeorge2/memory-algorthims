package os;

import java.util.ArrayList;
import java.util.List;

public class ClockPage {
    public static void main(String[] args) {
        int[] pageRequests = {1, 2, 3, 4, 1, 2, 3, 2, 1, 5, 4, 1};
        int numFrames = 3;
        int numPageFaults = getNumPageFaults(pageRequests, numFrames);
        System.out.println("Total number of page faults " + numPageFaults);
    }

    public static int getNumPageFaults(int[] pageRequests, int numFrames) {
        List<Integer> frameList = new ArrayList<>();
        boolean[] referenceBits = new boolean[numFrames];
        int numPageFaults = 0;
        int clockHand = 0;

        for (int i = 0; i < pageRequests.length; i++) {
            int pageRequest = pageRequests[i];

            if (!frameList.contains(pageRequest)) {
                numPageFaults++;

                if (frameList.size() < numFrames) {
                    frameList.add(pageRequest);
                    referenceBits[clockHand] = true;
                    clockHand = (clockHand + 1) % numFrames;
                } else {
                    boolean pageFound = false;

                    while (!pageFound) {
                        if (referenceBits[clockHand]) {
                            referenceBits[clockHand] = false;
                            clockHand = (clockHand + 1) % numFrames;
                        } else {
                            frameList.set(clockHand, pageRequest);
                            referenceBits[clockHand] = true;
                            clockHand = (clockHand + 1) % numFrames;
                            pageFound = true;
                        }
                    }
                }
            } else {
                int frameIndex = frameList.indexOf(pageRequest);
                referenceBits[frameIndex] = true;
            }
        }

        return numPageFaults;
    }
}
