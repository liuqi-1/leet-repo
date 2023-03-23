#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

class Solution
{
public:
    int numberOfArrays(vector<int> &differences, int lower, int upper)
    {
        long minDiff = differences[0];
        long maxDiff = differences[0];
        long preSum = differences[0];
        for (int i = 1; i < differences.size(); i++)
        {
            preSum = preSum + differences[i];
            minDiff = min(preSum, minDiff);
            maxDiff = max(preSum, maxDiff);
        }
        long ans = 0;
        if (minDiff <= 0 && maxDiff <= 0)
        {
            ans = upper + minDiff - lower + 1;
        }
        else if (minDiff >= 0 && maxDiff >= 0)
        {
            ans = upper - (lower + maxDiff) + 1;
        }
        else
        {
            int start = lower + maxDiff - minDiff;
            ans = upper - start + 1;
        }
        return max(ans, 0l);
    }
};