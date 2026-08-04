class Solution {
    static class DisjointSet{
        List<Integer>parent;
        List<Integer>size;
        public DisjointSet(int n){
            parent=new ArrayList<>();
            size=new ArrayList<>();
            for(int i=0;i<n;i++){
                parent.add(i);
                size.add(1);
            }
        }
        public int ultiParent(int u){
            if(u==parent.get(u))return u;
            int ulp = ultiParent(parent.get(u));
            parent.set(u, ulp);
            return ulp;
        }
        public void union(int u,int v){
            int up=ultiParent(u);
            int vp=ultiParent(v);
            if(vp==up)return;
            if(size.get(up)<size.get(vp)){
                parent.set(up,vp);
                size.set(vp,size.get(up)+size.get(vp));
            }
            else{
                parent.set(vp,up);
                size.set(up,size.get(up)+size.get(vp));
            }
        }
    }
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        HashMap<String,Integer>mp=new HashMap<>();
        int n=accounts.size();
        DisjointSet ds=new DisjointSet(n);
        for(int i=0;i<n;i++){
            for(int j=1;j<accounts.get(i).size();j++){
                String email=accounts.get(i).get(j);
                if(!mp.containsKey(email)){
                    mp.put(email,i);
                }
                else{
                    ds.union(i,mp.get(email));
                }
            }
        }
        List<List<String>>ans=new ArrayList<>();
        for(int i=0;i<n;i++)ans.add(new ArrayList<>());
        for(Map.Entry<String,Integer> it:mp.entrySet()){
            String mail=it.getKey();
            int ultParent=ds.ultiParent(it.getValue());
            ans.get(ultParent).add(mail);
        }
        List<List<String>>res=new ArrayList<>();
        for(int i=0;i<n;i++){
            if(ans.get(i).size()==0)continue;
            Collections.sort(ans.get(i));
            List<String>temp=new ArrayList<>();
            temp.add(accounts.get(i).get(0));
            temp.addAll(ans.get(i));
            res.add(temp);
        }
        return res;
    }
}