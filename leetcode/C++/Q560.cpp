#include <vector>
#include <unordered_map>

using namespace std;

class Solution
{
public:
    int subarraySum(vector<int> &nums, int k)
    {
        int sum = 0;
        unordered_map<int, int> map;
        int ans = 0;
        for (int i = 0; i < nums.size(); i++)
        {
            sum += nums[i];
            map[sum]++;
            ans += map[sum - k];
            if (sum == k)
            {
                ans++;
            }
        }
        return ans;
    }
};