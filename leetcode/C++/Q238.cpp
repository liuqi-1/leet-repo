#include <vector>

using namespace std;

class Solution
{
public:
    vector<int> productExceptSelf(vector<int> &nums)
    {
        int n = nums.size();
        vector<int> ans(n);
        int product = 1;
        for (int i = 0; i < nums.size(); i++)
        {
            ans[i] = product;
            product *= nums[i];
        }
        product = 1;
        for (int i = nums.size() - 1; i >= 0; i--)
        {
            ans[i] *= product;
            product *= nums[i];
        }
        return ans;
    }
};