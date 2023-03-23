#include <vector>
#include <queue>

using namespace std;

class Solution
{
public:
    vector<int> maxSlidingWindow(vector<int> &nums, int k)
    {
        priority_queue<pair<int, int>> pq;
        int index = 0;
        vector<int> ans;
        for (; index < k - 1; index++)
        {
            pq.emplace(nums[index], index);
        }
        for (; index < nums.size(); index++)
        {
            pq.emplace(nums[index], index);
            while (!pq.empty())
            {
                pair<int, int> p = pq.top();
                if (index - p.second + 1 <= k)
                {
                    ans.push_back(p.first);
                    break;
                }
                pq.pop();
            }
        }
        return ans;
    }
};