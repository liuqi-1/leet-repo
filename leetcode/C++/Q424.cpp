#include <string>
#include <algorithm>
#include <iostream>

using namespace std;

class Solution
{
public:
    int characterReplacement(string s, int k)
    {
        int left = 0;
        int right = 0;
        int ans = 1;
        int times[26];
        times[s[left] - 'A']++;
        int maxTimes = 1;
        while (left <= right && right < s.length())
        {
            if (right - left + 1 - maxTimes > k)
            {
                times[s[left] - 'A']--;
                left++;
            }
            else
            {
                ans = right - left + 1;
                right++;
                if (right < s.length())
                {
                    times[s[right] - 'A']++;
                    maxTimes = max(times[s[right] - 'A'], maxTimes);
                }
            }
        }
        return ans;
    }
};