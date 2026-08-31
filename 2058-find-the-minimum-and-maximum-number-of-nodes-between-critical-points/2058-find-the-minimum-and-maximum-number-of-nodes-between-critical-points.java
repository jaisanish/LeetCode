/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        ArrayList<Integer>l=new ArrayList<>();
        ListNode cur=head.next;
        ListNode prev=head;
        int idx=1;
        while(cur!=null){
            if(cur.next!=null && cur.val>prev.val && cur.val>cur.next.val){
                l.add(idx);
            }
            if(cur.next!=null && cur.val<prev.val && cur.val<cur.next.val){
                l.add(idx);
            }
            idx++;
            prev=cur;
            cur=cur.next;
        }
        if(l.size()<=1)return new int[]{-1,-1};

        int ans[]=new int[2];
        ans[0]=Integer.MAX_VALUE;
        ans[1]=l.get(l.size()-1)-l.get(0);
        for(int i=1;i<l.size();i++){
            ans[0]=Math.min(l.get(i)-l.get(i-1),ans[0]);
        }
        return ans;
    }
}