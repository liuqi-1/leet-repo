#include <string>
#include <unordered_set>

using namespace std;

class Solution
{
public:
    int lengthOfLongestSubstring(string s)
    {
        if(s.size()==0){
            return 0;
        }
        int ans = 0;
        unordered_set<char> set;
        int left = 0;
        int right = 0;
        set.insert(s.at(left));
        while (left < s.size() && right < s.size())
        {
            while (right + 1 < s.size() && set.find(s.at(right + 1)) == set.end())
            {
                set.insert(s.at(right + 1));
                right++;
            }
            ans = max(ans, right - left + 1);
            set.erase(s.at(left));
            left++;
        }
        return ans;
    }
};