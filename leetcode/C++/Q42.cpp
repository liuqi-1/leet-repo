#include<vector>
#include<stack>

using namespace std;

// 动态规划方法



// 单调栈方法
class Solution1 {
public:
    int trap(vector<int>& height) {
        stack<int> s; // 维护单调递减的栈
        int ans=0;
        int bottom;
        for(int i=0;i<height.size();i++){
            if(s.empty()){
                s.push(height[i]);
                bottom = height[i];
                continue;
            }
            if(height[i]<=s.top()){
                s.push(height[i]);
                continue;
            }
            int minH = min(height[i],bottom);
            int cnt = 1;
            while(!s.empty() && s.top()<height[i]){
                int t = s.top();
                s.pop();
                ans += minH-t;
                cnt++;
            }
            if(height[i]>bottom){
                bottom=height[i];
                s.push(height[i]);
            }else if(height[i]<bottom){
                // 如果当前高度，比栈底元素少，while循环中被装水的柱子，不能简单去除。未来如果遇到更高的右边界，还可以装更多的水
                while(cnt--){
                    s.push(height[i]);
                }
            }
        }
        return ans;
    }
};

// 单调栈另一种解法
class Solution {
public:
    int trap(vector<int>& height) {
        stack<int> s;
        int ans = 0;
        for(int i=0;i<height.size();i++){
            while(!s.empty() && height[i]>height[s.top()]){
                int peek = s.top();
                s.pop();
                if(s.empty()){
                    break;
                }
                int left = s.top();
                int width = i-left-1;
                ans += width * (min(height[i],height[left]) - height[peek]);
            }
            s.push(i);
        }
        return ans;
    }
};