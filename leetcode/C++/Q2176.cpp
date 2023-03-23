#include <iostream>
#include <unordered_set>
#include <string.h>
#include <vector>
using namespace std;

class Solution
{
public:
    int countPairs(vector<int> &nums, int k)
    {
        unordered_set<int> IndexSet[101];
        int ret = 0;
        for (int j = 0; j < nums.size(); j++)
        {
            int value = nums[j];
            for (auto i : IndexSet[value])
            {
                if (i * j % k == 0)
                {
                    ret++;
                }
            }
            IndexSet[value].insert(j);
        }
        return ret;
    }
};