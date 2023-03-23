#include <vector>
#include <deque>

using namespace std;

// 动态规划
class Solution
{
public:
    int maxSubArray(vector<int> &nums)
    {
        int pre = 0, ans = nums[0];
        for (const auto &x : nums)
        {
            pre = max(pre + x, x);
            ans = max(ans, pre);
        }
        return ans;
    }
};

// 前缀和一次遍历
class Solution
{
public:
    int maxSubArray(vector<int> &nums)
    {
        int sum = 0;
        int minSum = 0;
        int ans = nums[0];
        for (int i = 0; i < nums.size(); i++)
        {
            sum += nums[i];
            ans = max(ans, max(sum, sum - minSum));
            minSum = min(minSum, sum);
        }
        return ans;
    }
};