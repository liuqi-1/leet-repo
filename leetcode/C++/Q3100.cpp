class Solution {
public:
    int maxBottlesDrunk(int numBottles, int numExchange) {
        int drinkBootles = numBottles;
        int emptyBottles = numBottles;
        while(emptyBottles>=numExchange){
            emptyBottles -= numExchange;
            numExchange++;
            drinkBootles++;
            emptyBottles++;
        }
        return drinkBootles;
    }
};