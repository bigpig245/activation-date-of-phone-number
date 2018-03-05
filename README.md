# activation-date-of-phone-number
The problem is to find the last activation date of phone number's owner.
If the owner extends his/her paid, the deactivation date of the previous period and the activation date of current period should be the same.
Otherwise, the phone number will be treated as changed phone number's owner.

1. The strategy and algorithm: 
  - When reading input csv file, I will use a map to store data. The key of map is phone number, the values is the list of rows contains phone number data.
(I will use TreeMap for sorting the phone number automatically) (a)
  - Then loop all the values of the map: (b)
    + With each list value, I will sort this list by activation in descending order (using default java) (b1)
    + Set default real activation item is the first item of list (b2)
    + Loop the list from the second item until the last item: (b3)
      * If the activation of the real activation is equal to the deactivation of the current item, update the real activation item is the current item
      * else keep the real activation item and skip the others.
 - For the reading/writing csv content file, I will use open csv, a lightweight libary helps the reading/writing csv more easier.
 
2. Time complexity & memory complexity:
- Time complexity:
  + step a: the time complexity is O(N)
  + step b + b2: because we split the N list into sublist and the loop each sublist, so the time complexity of 2 step is O(N)
  + step b1: the time complexity of default sort in java is O(NlogN)
  Then the time complexity of my algorithm is O(N) + O(N) + O(NlogN) = O(NlogN), this is an acceptable time complexity for the dataset with 50k items.

- Memory complexity:
  + step a: the time complexity is O(N)
  + step b + b2: because we split the N list into sublist and the loop each sublist, so the time complexity of 2 step is O(N)
  + step b1: the time complexity of default sort in java is O(logN)
  + output print list only contains phone number & activation date: O(N)
  Then the memory complexity of my algorithm:  O(N) + O(N) + O(logN) + O(N) = O(N)
