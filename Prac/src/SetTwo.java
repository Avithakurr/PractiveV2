import java.util.*;

public class SetTwo {
    public static void main(String[] args){
          SetTwo setTwo = new SetTwo();
//          System.out.println(setTwo.groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"}));
//          System.out.println(setTwo.groupAnagramsO(new String[]{"eat","tea","tan","ate","nat","bat"}));
//          System.out.println(setTwo.createFrequencyArray("aabbadsa"));
//            System.out.println(setTwo.groupDigitSum(new ArrayList<>(List.of(9, 18, 27))));
         System.out.println(setTwo.groupCharacterPattern(new ArrayList<>(List.of("abb","cdd","xyz","mno","aab"))));

    }

    public ArrayList<ArrayList<String>> groupAnagrams(String[] arr){
        ArrayList<String> temp = new ArrayList<>();
        for(String item : arr){
            char[] tempVal = item.toCharArray();
            Arrays.sort(tempVal);
            String sortedVal = new String(tempVal);
            temp.add(sortedVal);
        }

        HashMap<String, ArrayList<Integer>> hm = new HashMap<>();


        for(int i=0; i<temp.size(); i++){
            String value = temp.get(i);
            if(hm.containsKey(value)){
                ArrayList<Integer> tempValues = hm.get(value);
                tempValues.add(i);
            }
            else{
                hm.put(value, new ArrayList<>(List.of(i)));
            }
        }

        ArrayList<ArrayList<String>> ans = new ArrayList<>();
        for(String item : hm.keySet()){
            ArrayList<String> ansTemp = new ArrayList<>();
            ArrayList<Integer> indexes = hm.get(item);
            for(int i=0; i<indexes.size(); i++){
                ansTemp.add(arr[indexes.get(i)]);
            }
            ans.add(ansTemp);
        }
        return ans;

    }


    public ArrayList<ArrayList<String>> groupAnagramsO(String[] arr){
        HashMap<ArrayList<Integer>, ArrayList<String>> frequencyMappedValues
                = new HashMap<>();
        for(String item : arr){
            ArrayList<Integer> frequency = createFrequencyArray(item);
            if(frequencyMappedValues.containsKey(frequency)){
                frequencyMappedValues.get(frequency).add(item);
            }
            else{
                frequencyMappedValues.put(frequency, new ArrayList<>(List.of(item)));
            }
        }

        ArrayList<ArrayList<String>> list = new ArrayList<>();

        for(ArrayList<Integer> key: frequencyMappedValues.keySet()){
            ArrayList<String> subList = new ArrayList<>();
            for(String values : frequencyMappedValues.get(key)){
                subList.add(values);
            }
            list.add(subList);
        }
        return list;
    }


    public ArrayList<Integer> createFrequencyArray(String value) {
        ArrayList<Integer> freq = new ArrayList<>(Collections.nCopies(26, 0));

        for (char c : value.toCharArray()) {
            freq.set(c - 'a', freq.get(c - 'a') + 1);
        }

        return freq;
    }

    public ArrayList<ArrayList<Integer>> groupDigitSum(ArrayList<Integer> arr){
        HashMap<Integer, ArrayList<Integer>> hm = new HashMap<>();
        for(Integer item : arr){
            Integer stringSum = findStringSum(item);
            hm.computeIfAbsent(stringSum, k -> new ArrayList<>()).add(item);

//            if(hm.containsKey(stringSum)){
//                hm.get(stringSum).add(item);
//            }
//            else{
//                hm.put(stringSum, new ArrayList<>(List.of(item)));
//            }
        }
        return new ArrayList<>(hm.values());
    }

    public Integer findStringSum(Integer value){
        Integer valTemp = value;
        Integer sum = 0;
        while(valTemp>0){
            Integer addItem = valTemp%10;
            sum+=addItem;
            valTemp = valTemp/10;
        }
        return sum;
    }

    public ArrayList<ArrayList<String>> groupCharacterPattern(ArrayList<String> arr){
        HashMap<String, ArrayList<String>> patternMapping = new HashMap<>();
        for(String item : arr){
            String pattern = fetchPattern(item);
            patternMapping.computeIfAbsent(pattern, key -> new ArrayList<>()).add(item);
        }

        ArrayList<ArrayList<String>> result = new ArrayList<>(patternMapping.values());
        result.sort((item1, item2) -> Integer.compare(item2.size(), item1.size()));
        return result;
    }

    public String fetchPattern(String val){
        char[] charArr = val.toCharArray();
        char currentChar = charArr[0];
        int initialCode = 1;
        StringBuilder pattern = new StringBuilder().append(initialCode);
        for(int i=1; i<charArr.length; i++){
            if(currentChar != charArr[i]){
                initialCode++;
            }
            pattern.append(initialCode);
            currentChar = charArr[i];
        }

        return pattern.toString();
    }
}
