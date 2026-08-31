class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {

        ListNode prev = head;
        ListNode cur = head.next;

        int idx = 1;

        int first = -1;
        int last = -1;
        int minDist = Integer.MAX_VALUE;

        while (cur.next != null) {

            // Check whether cur is a critical point
            if ((cur.val > prev.val && cur.val > cur.next.val) ||
                (cur.val < prev.val && cur.val < cur.next.val)) {

                // First critical point
                if (first == -1) {
                    first = idx;
                }
                // Distance from previous critical point
                if (last != -1) {
                    minDist = Math.min(minDist, idx - last);
                }

                last = idx;
            }

            prev = cur;
            cur = cur.next;
            idx++;
        }

        // Less than two critical points
        if (first == -1 || first == last) {
            return new int[]{-1, -1};
        }

        int maxDist = last - first;

        return new int[]{minDist, maxDist};
    }
}