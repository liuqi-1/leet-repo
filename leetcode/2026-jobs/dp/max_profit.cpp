/**
任务描述：
1. 每个任务有开始时间，结束时间，和收益
2. 同一时间只能完成一项任务，同一任务只能选择一次
求最大收益

思路:
1. 将任务按照结束时间排序，然后用DP，DP[i]表示任务i的时候最大收益
2. dp[i] = max(dp[i-1], v[i]+dp[j])  // j 是结束时间 小于 任务i 的最后一个任务
*/

#include <bits/stdc++.h>
using namespace std;

// 加权区间调度：每个任务 (start, end, profit)，任务在时间上不重叠才能同时选，求最大收益。
// 约定：一个任务的结束时刻 == 下一个任务的开始时刻，视为不冲突（可衔接）。
int maxProfit(const vector<int>& startTime, const vector<int>& endTime, const vector<int>& profit) {
    int n = (int)startTime.size();

    // 按“结束时间”升序存放任务：{end, start, profit}
    vector<array<int, 3>> jobs(n);
    for (int i = 0; i < n; ++i)
        jobs[i] = {endTime[i], startTime[i], profit[i]};
    sort(jobs.begin(), jobs.end());

    // 结束时间单独抽出一份，随排序结果有序，供二分使用
    vector<int> ends(n);
    for (int i = 0; i < n; ++i)
        ends[i] = jobs[i][0];

    // dp[i] = 只考虑前 i+1 个任务（按结束时间排好序）时的最大收益
    vector<long long> dp(n);
    for (int i = 0; i < n; ++i) {
        const auto& [end, start, p] = jobs[i];
        // 在前面的任务里二分找“结束时间 <= start”的最后一个任务 j；找不到则 j = -1
        int j = int(upper_bound(ends.begin(), ends.begin() + i, start) - ends.begin()) - 1;
        long long take = p + (j >= 0 ? dp[j] : 0);   // 选任务 i：收益 + 之前与之兼容的最优解
        long long skip = i > 0 ? dp[i - 1] : 0;      // 不选任务 i
        dp[i] = max(take, skip);
    }
    return (int)dp[n - 1];
}

int main() {
    // 三组用例，期望输出：120 / 150 / 6
    vector<vector<int>> st = {{1, 2, 3, 3}, {1, 2, 3, 4, 6}, {1, 1, 1}};
    vector<vector<int>> et = {{3, 4, 5, 6}, {3, 5, 10, 6, 9}, {2, 3, 4}};
    vector<vector<int>> pf = {{50, 10, 40, 70}, {20, 20, 100, 70, 60}, {5, 6, 4}};
    for (int t = 0; t < 3; ++t)
        cout << maxProfit(st[t], et[t], pf[t]) << "\n";
}