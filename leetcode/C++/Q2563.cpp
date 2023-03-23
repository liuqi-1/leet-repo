#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

class Solution
{
public:
    long long countFairPairs(vector<int> &nums, int lower, int upper)
    {
        sort(nums.begin(), nums.end(),greater<int>());
        long long ans = 0;
        for (int j = 0; j < nums.size(); j++)
        {
            int l = lower_bound(nums.begin(), nums.begin() + j, lower - nums[j]) - nums.begin();
            int r = upper_bound(nums.begin(), nums.begin() + j, upper - nums[j]) - nums.begin();
            ans += r - l;
        }
        return ans;
    }
};

int main()
{

    return 0;
}