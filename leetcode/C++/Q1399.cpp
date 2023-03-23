#include <iostream>
#include <unordered_map>
#include <algorithm>
using namespace std;

class Solution
{
public:
    int countLargestGroup(int n)
    {
        int SumArr[37]; // 数位和最大为 36，四位数，每一位都是9
        int maxV = 0;
        int ans = 0;
        for (int i = 1; i <= n; i++)
        {
            int temp = i;
            int sum = 0;
            while (temp != 0)
            {
                sum += temp % 10;
                temp /= 10;
            }
            SumArr[sum]++;
            if (SumArr[sum] == maxV)
            {
                ans++;
            }
            else if (SumArr[sum] > maxV)
            {
                maxV = SumArr[sum];
                ans = 1;
            }
        }
        return ans;
    }
};