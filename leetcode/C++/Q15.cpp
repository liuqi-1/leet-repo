#include <algorithm>
#include <vector>

using namespace std;

class Solution
{
public:
    vector<vector<int>> threeSum(vector<int> &nums){
        vector<vector<int>> ans;
        // 先排序, 按照大小，枚举 a+b+c=0，a<=b<=c
        sort(nums.begin(),nums.end());
        // 进行三重循环
        for(int i=0;i<nums.size();i++){
            if(i>0 && nums[i]==nums[i-1]){ // 避免重复枚举第一个数
                continue;
            }
            int k = nums.size()-1;
            for(int j=i+1;j<nums.size();j++){
                if(j>i+1 && nums[j]==nums[j-1]){ // 避免重复枚举第二个数。只要第一个数和第二个数不重复，那么第三个数一定不会重复
                    continue;
                }
                while(k>j && nums[i]+nums[j]+nums[k]>0){
                    k--;
                }
                if(k==j){
                    break;
                }
                if( nums[i]+nums[j]+nums[k]==0){
                    ans.push_back({nums[i],nums[j],nums[k]});
                }
            }
        }
        return ans;
    }
};