#include <iostream>
#include <vector>
#include <unordered_map>

using namespace std;

class Solution
{
public:
    long long countBadPairs(vector<int> &nums)
    {
        unordered_map<int, int> record;
        int n = nums.size();
        long long result = 1ll * n * (n - 1) / 2;
        for (int j = 0; j < n; j++)
        {
            result -= record[j - nums[j]]++;
        }
        return result;
    }
};

int main()
{
    Solution s;
    vector<int> nums = {1, 2, 3, 4, 5};
    cout << s.countBadPairs(nums) << endl; // Output: 5
    return 0;
}