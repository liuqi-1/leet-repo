#include <string>
#include <unordered_map>
#include <unordered_set>
#include <iostream>

using namespace std;

class Solution
{
public:
    string minWindow(string s, string t)
    {
        unordered_map<char, int> target; // 记录t字符串每个字符的个数
        unordered_set<char> set;         // 记录哪些字符还没有满足条件
        unordered_map<char, int> map;    // 记录当前滑动窗口每个字符的个数
        for (int i = 0; i < t.size(); i++)
        {
            target[t.at(i)]++;
            set.insert(t.at(i));
        }
        int left = 0;
        int right = -1;
        int tLeft = -1;
        int tRight = -2;
        while (left <= right || right == -1)
        {
            // 找到了满足要求的字串，left++
            if (set.empty())
            {
                if (tLeft == -1 || (right - left < tRight - tLeft))
                {
                    tRight = right;
                    tLeft = left;
                }
                map[s.at(left)]--;
                if (map[s.at(left)] < target[s.at(left)])
                {
                    set.insert(s.at(left));
                }
                left++;
                continue;
            }
            // 找不到满足要求的字串，right++
            right++;
            if (right >= (int)s.size())
            {
                break;
            }
            char c = s.at(right);
            map[c]++;
            if (map[c] >= target[c] && set.find(c) != set.end())
            {
                set.erase(c);
            }
        }
        if (tLeft == -1)
        {
            return "";
        }
        return s.substr(tLeft, tRight - tLeft + 1);
    }
};