#include <iostream>
#include <vector>

using namespace std;

/**
 * https://www.hello-algo.com/chapter_dynamic_programming/knapsack_problem/
 * 问题定义：给定n个物品，第i个物品的重量为 wgt[i-1]、价值为 val[i-1]，和一个容量为cap的背包。
 * 每个物品只能选择一次，问在限定背包容量下能够放入物品的最大价值
 */

// 版本1：初始DP版本
int fun(int cap, vector<int> &wgt, vector<int> &val)
{
    int n = wgt.size();
    vector<vector<int>> dp(n + 1, vector<int>(cap + 1, 0));
    for (int i = 0; i < n; i++)
    {
        for (int c = 1; c <= cap; c++)
        {
            if (c < wgt[i])
            {
                dp[i + 1][c] = dp[i][c];
            }
            else
            {
                dp[i + 1][c] = max(dp[i][c], val[i] + dp[i][c - wgt[i]]);
            }
        }
    }
    return dp[n][cap];
}

// 版本2：空间优化版本
int fun1(int cap, vector<int> wgt, vector<int> val)
{
    int n = wgt.size();
    vector<int> state(cap + 1, 0);
    for (int i = 0; i < n; i++)
    {
        vector<int> temp(cap + 1, 0);
        for (int c = 1; c <= cap; c++)
        {
            if (c < wgt[i])
            {
                temp[c] = state[c];
            }
            else
            {
                temp[c] = max(state[c], val[i] + state[c - wgt[i]]);
            }
        }
        state = temp;
    }
    return state[cap];
}

int main()
{
    vector<int> wgt = {10, 20, 30, 40, 50};
    vector<int> val = {50, 120, 150, 210, 240};
    int cap = 50;
    cout << "初始版本：" << fun(cap, wgt, val) << endl;
    cout << "空间优化版本：" << fun1(cap, wgt, val) << endl;
    return 0;
}