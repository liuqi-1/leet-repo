#include <algorithm>
#include <unordered_set>
#include <vector>

using namespace std;

/**
 * 总时间复杂度为 O(n)
 * 外层循环遍历连续序列的开头
 * 内层循环遍历连续序列的剩余数字
 */
class Solution
{
private:
    unordered_set<int> set;

public:
    int longestConsecutive(vector<int> &nums)
    {
        for (auto num : nums)
        {
            set.insert(num);
        }
        int ret = 0;
        for(const int& num:set){
            // 存在前驱节点，则直接跳过
            if(set.find(num-1)!=set.end()){
                continue;
            }
            // 不存在前驱节点，则是连续序列的开头
            int cnt = 1;
            int right = num+1;
            while(set.find(right)!=set.end()){
                right++;
                cnt++;
            }
            ret = max(ret,cnt);
        }
        return ret;
    }
};