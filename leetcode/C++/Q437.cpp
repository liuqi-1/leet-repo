struct TreeNode
{
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

#include <unordered_map>

using namespace std;

class Solution1
{
private:
    long long sumNow = 0;
    unordered_map<long long, int> map;
    int res = 0;
    void helper(TreeNode *root, long long targetSum)
    {
        if (!root)
        {
            return;
        }
        sumNow += root->val;
        res += map[sumNow - targetSum];
        map[sumNow]++;
        helper(root->left, targetSum);
        helper(root->right, targetSum);
        map[sumNow]--;
        sumNow -= root->val;
    }

public:
    int pathSum(TreeNode *root, int targetSum)
    {
        sumNow = 0;
        res = 0;
        map.clear();
        map[0] = 1;
        helper(root, targetSum);
        return res;
    }
};

class Solution
{
private:
    int helper(TreeNode *root, int targetSum)
    {
        
    }

public:
    int pathSum(TreeNode *root, int targetSum)
    {
    }
};