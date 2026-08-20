import java.util.*;
import java.util.logging.Logger;

/**
# Set A — Frequency Maps

### A1. First Unique Element
Return the first element in the array that occurs exactly once. Return `-1` if none exists.

        | Input | Expected |
        |---|---|
        | `[4, 5, 1, 2, 0, 4]` | `5` |
        | `[7, 7, 7]` | `-1` |
        | `[9]` | `9` |
        | `[1, 2, 1, 2, 3]` | `3` |

        ---

        ### A2. Permutation Check
Given two arrays, return `true` if one is a rearrangement of the other (same elements, same counts).

        | Input | Expected |
        |---|---|
        | `a = [1,2,3,2]`, `b = [2,3,2,1]` | `true` |
        | `a = [1,2,3]`, `b = [1,2,2]` | `false` |
        | `a = [1,1,2]`, `b = [1,2,2]` | `false` |
        | `a = []`, `b = []` | `true` |

        *Follow-up your interviewer will ask: solve it without sorting, and then discuss when sorting would actually be the better choice.*

        ---

        ### A3. Top K Frequent Elements
Return the `k` most frequent elements. If two elements have the same frequency, the **smaller value** comes first.

        | Input | Expected |
        |---|---|
        | `arr = [1,1,1,2,2,3]`, `k = 2` | `[1, 2]` |
        | `arr = [5,5,4,4,3]`, `k = 2` | `[4, 5]` |
        | `arr = [1,2,3,4]`, `k = 4` | `[1, 2, 3, 4]` |
        | `arr = [6,6,6]`, `k = 1` | `[6]` |

        ---

        ### A4. Majority Element
Return the element appearing **strictly more than** `n/2` times. Return `-1` if there isn't one.

        | Input | Expected |
        |---|---|
        | `[2,2,1,2,3,2]` | `2` |
        | `[1,2,3]` | `-1` |
        | `[1,1,2,2]` | `-1` |
        | `[8]` | `8` |

        *Solve with a HashMap first. Then solve it again in O(1) space — that second solution is the one that scores.*
**/

public class SetOne {
    Logger logger = Logger.getLogger(SetOne.class.getName());
    public static void main(String[] args){
        SetOne setOne = new SetOne();
//        System.out.println(setOne.firstUniqueElementsO(new int[]{1,2,3,5,1,1}));

//        System.out.println(setOne.permutationCheckO(new int[]{}, new int[]{}));

//        System.out.println(setOne.topKFrequency(new int[]{6,6,6}, 1));

//        System.out.println("non optimized ans : " + setOne.majorityElements(new int[]{2,2,1,2,3,2}) +
//                "\n optimized answer : " + setOne.majorityElementsBoyersVoting(new int[]{2,2,1,2,3,2})
//        );

        System.out.println(setOne.minimumDeletions(new int[]{1,1,1,2,2,3,3,4,4}));

    }

 //Naive approach
    public int firstUniqueElements(int[] arr){
        final int n = arr.length;
        for(int i=0; i<n; i++){
            int freq = 1;
            int element = arr[i];
            logger.info("value is : "+element);
           for(int j=0; j<n; j++){
               if(i==j){
                   continue;
               }
               if(element == arr[j]){
                   freq++;
               }
           }
           if(freq == 1){
               return element;
           }
        }
        return -1;
    }

    //optimized approach
    public int firstUniqueElementsO(int[] arr){
        final int n = arr.length;
        HashMap<Integer, Integer> hm = new HashMap<>();
        for(int i=0; i<n; i++){
           int element = arr[i];
//           start
           if(hm.containsKey(element)){
                int value = hm.get(element);
                hm.put(element, value+1);
           }
           else{
               hm.put(element, 1);
           }
//           end
//you can replace this whole section with int hm.put(element, hm.getOrDefault(element, 0) + 1);
        }

        for(int i=0; i<n; i++){
            int value = hm.get(arr[i]);
            if(value == 1){
                return arr[i];
            }
        }
        return -1;
    }

    public boolean permutationCheck(int[] a, int[] b){
        Arrays.sort(a); //O(nLogn)
        Arrays.sort(b); //O(mLogm)
        return Arrays.equals(a, b); //min(n, m);
//        sc: recursion stack as equals use dual-pivot quick sort
    }

    public boolean permutationCheckO(int[] a, int[] b){
        if(a.length == 0 && b.length == 0) return true;
        if(a.length != b.length) return false;
        HashMap<Integer, Integer> hm = new HashMap<>(); // space complexity O(N)
        for(int item : a){
            hm.put(item, hm.getOrDefault(item, 0) + 1);
        }
            //n
        for(int item: b){
            hm.put(item, hm.getOrDefault(item, 0) - 1);
        }
        //n

        for(int item: hm.keySet()){
            if(hm.get(item) > 0){
                return false;
            }
        }
        //n

//        TC : O(3N) or O(N);

        return true;
    }

    /**
     * N = total number of elements in arr
     * M = number of unique elements in arr
     * K = number of top elements required
     *
     * Time Complexity:
     *
     * 1. Build frequency HashMap:
     *    O(N) average
     *
     * 2. Iterate over HashMap and create FrequencyElements:
     *    O(M)
     *
     * 3. Sort the FrequencyElements list:
     *    O(M log M)
     *
     * 4. Extract the top K elements:
     *    O(K)
     *
     * Total:
     *    O(N + M + M log M + K)
     *
     * Since M <= N and K <= M:
     *    O(N + M log M)
     *
     * Worst case:
     *    If all elements are unique, M = N
     *    Therefore: O(N log N)
     *
     *
     * Space Complexity:
     *
     * 1. HashMap stores M unique elements:
     *    O(M)
     *
     * 2. ans stores M FrequencyElements:
     *    O(M)
     *
     * 3. finalAns stores K elements:
     *    O(K)
     *
     * Total:
     *    O(M + K)
     *
     * Since K <= M:
     *    O(M)
     *
     * Worst case:
     *    If all elements are unique, M = N
     *    Therefore: O(N)
     *
     *
     * Note:
     * HashMap has O(1) average time for get/put.
     * HashMap space is O(M), not O(N log N).
     */

    public ArrayList<Integer> topKFrequency(int[] arr, int k){
        ArrayList<FrequencyElements> ans = new ArrayList<>();
        HashMap<Integer, Integer> hm = new HashMap<>();

        for(int item: arr){
            hm.put(item, hm.getOrDefault(item, 0)+1);
        }



        for(int item: hm.keySet()){
            ans.add(new FrequencyElements(item, hm.get(item)));
        }

        Collections.sort(ans);
        ArrayList<Integer> finalAns = new ArrayList<>();
        for(int i=0; i<k; i++){
            finalAns.add(ans.get(i).getItem());
        }
        return finalAns;
    }


    public int majorityElements(int[] arr){
        HashMap<Integer, Integer> hm = new HashMap<>();
        for(int item: arr) hm.put(item, hm.getOrDefault(item, 0)+1);
        for(int item: hm.keySet()) if(hm.get(item) > arr.length/2) return item;
        return -1;
    }


    public int majorityElementsO(int[] arr){
        Arrays.sort(arr);
        int element = arr[0];
        int highestCount = 1;
        int localCount = 1;
        int ansElement = arr[0];
        for(int i=0; i<arr.length-1; i++){
            element = arr[i];

            if(element == arr[i+1]){
                localCount++;
                if(localCount>highestCount)
                {
                    highestCount = localCount;
                    ansElement = arr[i];
                }
            }else{
                localCount = 1;
            }
        }
        return (highestCount>arr.length/2) ? ansElement : -1;
    }

    public int majorityElementsBoyersVoting(int[] arr){
       int candidate = 0;
       int count = 0;
       for(int item : arr){
           if(count == 0){
               candidate = item;
               count = 1;
           }
           else if(candidate == item){
               count++;
           }
           else {
               count--;
           }
       }

       count = 0;

       for(int item : arr){
           if(item == candidate){
               count ++;
           }
       }

       return count>arr.length/2 ? candidate : -1;
    }

    public int minimumDeletions(int[] arr){
        HashMap<Integer, Integer> hm = new HashMap<>();
        for(int item : arr){
            hm.put(item, hm.getOrDefault(item, 0) + 1);
        }
        Set<Integer> hasSeen = new HashSet<>();
        int count = 0;
        for(int item : hm.keySet()){
            int freq = hm.get(item);
            while(hasSeen.contains(freq)){
                freq--;
                count++;
                if(freq == 0) break;
            }
            hasSeen.add(freq);
        }
        return count;
    }


}
