class Solution {
public:
    int numWaterBottles(int numBottles, int numExchange) {
        int drinkBottles = numBottles;
        int emptyBottles = numBottles;
        while(emptyBottles>=numExchange){
            emptyBottles -= numExchange;
            drinkBottles++;
            emptyBottles++;
        }
        return drinkBottles;
    }
};