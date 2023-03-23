struct TreeNode
{
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

#include <vector>
#include <algorithm>
#include <iostream>
extern "C"
{
#include "limits.h"
}
using namespace std;

class Solution
{
private:
    bool helper(TreeNode *root, long lower, long upper)
    {
        if (!root)
        {
            return true;
        }
        if (root->val <= lower || root->val >= upper)
        {
            return false;
        }
        return helper(root->left, lower, root->val) && helper(root->right, root->val, upper);
    }

public:
    bool isValidBST(TreeNode *root)
    {
        return helper(root, LONG_MIN, LONG_MAX);
    }
};

class Solution1
{
private:
    // 返回：子树的最大值，子树的最小值，子树是否是平衡二叉树
    vector<int> helper(TreeNode *root)
    {
        int maxV = root->val;
        int minV = root->val;
        if (root->left)
        {
            vector<int> ans = helper(root->left);
            if (!ans[2] || ans[0] >= root->val)
            {
                return {-1, -1, 0};
            }
            minV = min(minV, ans[1]);
        }
        if (root->right)
        {
            vector<int> ans = helper(root->right);
            if (!ans[2] || ans[1] <= root->val)
            {
                return {-1, -1, 0};
            }
            maxV = max(maxV, ans[0]);
        }
        return {maxV, minV, 1};
    }

public:
    bool isValidBST(TreeNode *root)
    {
        return helper(root)[2];
    }
};

int main()
{
    cout << sizeof(int) << endl;
    cout << sizeof(long) << endl;
    cout << sizeof(unsigned long) << endl;
    cout << sizeof(long long) << endl;
    cout << sizeof(short) << endl;
    return 0;
}