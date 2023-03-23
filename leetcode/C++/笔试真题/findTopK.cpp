/**
 * 题目描述：找出数组中第k大的元素
 */
#include <queue>
#include <iostream>
#include <vector>

using namespace std;

class Solution
{
public:
    int findTopK(vector<int> &nums, int k)
    {
        // 使用lambda自定义比较函数创建小顶堆
        auto cmp = [](int a, int b)
        { return a > b; };
        priority_queue<int, vector<int>, decltype(cmp)> q(cmp);

        for (auto &a : nums)
        {
            if (q.size() < k)
            {
                q.push(a);
            }
            else if (a > q.top())
            {
                q.pop();
                q.push(a);
            }
        }
        return q.top();
    }
};

int main()
{

    Solution solution;
    vector<int> nums = {1, 2, 1, 2, 2, 3, 5, 6, 2};
    cout << solution.findTopK(nums, 3);
    return 0;
}