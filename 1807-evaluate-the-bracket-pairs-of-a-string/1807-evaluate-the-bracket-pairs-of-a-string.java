class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String,String>mp=new HashMap<>();
        for(List<String>know:knowledge){
            mp.put(know.get(0),know.get(1));
        }
        StringBuilder result=new StringBuilder();
        for(int i=0;i<s.length();i++){
            char cur=s.charAt(i);
            int end=i+1;
            if(cur=='('){
                while(s.charAt(end)!=')')end++;
                String key=s.substring(i+1,end);
                result.append(mp.getOrDefault(key,"?"));
                i=end;
            }
            else{
                result.append(cur);
            }
        }
        return result.toString();
    }
}