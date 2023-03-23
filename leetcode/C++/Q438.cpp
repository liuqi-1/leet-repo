#include <string>
#include <vector>
#include <iostream>

using namespace std;

class Solution
{
public:
    bool judge(int *count, int *target)
    {
        for (int i = 0; i < 26; i++)
        {
            if (count[i] != target[i])
            {
                return false;
            }
        }
        return true;
    }
    vector<int> findAnagrams(string s, string p)
    {
        vector<int> ans;
        // p更长，那么不可能有满足条件的结果
        if (p.size() > s.size()){
            return ans;
        }
        // 计算p中各个字符出现的次数
        int target[26];
        for (int i = 0; i < p.size(); i++){
            target[p.at(i) - 'a']++;
        }
        // 用滑动窗口进行异位词的匹配
        int left = -1;
        int right = p.size() - 2;
        int count[26];
        for (int i = 0; i <= right; i++){
            count[s.at(i) - 'a']++;
        }
        while (right + 1 < s.size()){
            count[s.at(++right) - 'a']++;
            if (left == -1){
                left++;
            }else{
                count[s.at(left++) - 'a']--;
            }
            if (judge(count, target)){
                ans.push_back(left);
            }
        }
        return ans;
    }
};