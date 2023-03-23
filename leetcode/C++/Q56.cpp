#include <vector>
#include <algorithm>

using namespace std;

class Solution
{
public:
    vector<vector<int>> merge(vector<vector<int>> &intervals)
    {
        vector<vector<int>> ans;
        sort(intervals.begin(), intervals.end(),
             [](const vector<int> &a, const vector<int> &b)
             {
                 if (a[0] != b[0])
                     return a[0] < b[0];
                 return a[1] < b[1];
             });
        ans.push_back(intervals[0]);
        for (int i = 0; i < intervals.size(); i++)
        {
            if (ans[ans.size() - 1][1] >= intervals[i][0])
            {
                ans[ans.size() - 1][1] = max(ans[ans.size() - 1][1], intervals[i][1]);
                continue;
            }
            ans.push_back({intervals[i][0], intervals[i][1]});
        }
        return ans;
    }
};