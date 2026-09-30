#include <iostream>
#include <vector>

using namespace std;

/**
 * https://www.hello-algo.com/chapter_dynamic_programming/unbounded_knapsack_problem/#1
 * 问题定义：给定n个物品，第i个物品的重量为 wgt[i-1]、价值为 val[i-1]，和一个容量为cap的背包。
 * 每个物品可以重复选取，问在限定背包容量下能够放入物品的最大价值
 */

int solution(vector<int> &wgt, vector<int> &val, int cap)
{
    int n = wgt.size();
    vector<int> dp(cap + 1, 0);
    for (int i = 0; i < n; i++)
    {
        vector<int> temp(cap + 1, 0);
        for (int c = 1; c <= cap; c++)
        {
            if (wgt[i] > c)
            {
                temp[c] = dp[c];
            }
            else
            {
                temp[c] = max(dp[c], val[i] + temp[c - wgt[i]]); // 完全背包问题，物品可以重复选取
                // temp[c] = max(dp[c], val[i] + dp[c - wgt[i]]); // 01背包问题，物品只能选择一次
            }
        }
        dp = temp;
    }
    return dp[cap];
}

int main()
{
    vector<int> wgt = {10, 20, 30, 40, 50};
    vector<int> val = {50, 120, 150, 210, 240};
    int cap = 50;
    cout << solution(wgt, val, cap) << endl;
    return 0;
}