class KthLargest {
    PriorityQueue<Integer>pq;
    int k;
    public KthLargest(int k, int[] nums) {
        this.k=k;
        pq=new PriorityQueue<>();

        for(int i=0;i<k;i++){
            if(i<nums.length){
                pq.offer(nums[i]);
            }
            else pq.offer(Integer.MIN_VALUE);
        }
        for(int i=k;i<nums.length;i++){
            if(nums[i]>pq.peek()){
                pq.poll();
                pq.offer(nums[i]);
            }
        }
    }
    
    public int add(int val) {
        if(val>pq.peek()){
            pq.poll();
            pq.offer(val);
        }
        return pq.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */