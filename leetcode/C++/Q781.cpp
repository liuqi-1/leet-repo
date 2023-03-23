#include <iostream>
#include <vector>
#include <unordered_map>

using namespace std;

class Solution
{
public:
    int numRabbits(vector<int> &answers)
    {
        unordered_map<int, int> map;
        int ans = 0;
        for (auto i : answers)
        {
            map[i]++;
        }
        for (auto it : map)
        {
            int k = it.first;
            int v = it.second;
            ans += v;
            if (v % (k + 1) != 0)
            {
                ans += k + 1 - v % (k + 1);
            }
        }
        return ans;
    }
};

int main()
{
    return 0;
}