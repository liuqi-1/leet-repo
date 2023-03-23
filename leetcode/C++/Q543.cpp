struct TreeNode
{
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

#include <algorithm>

using namespace std;

class Solution
{
private:
    int ans = 0;

public:
    int helper(TreeNode *root)
    {
        if (!root)
        {
            return 0;
        }
        int left_max_depth = helper(root->left);
        int right_max_depth = helper(root->right);
        ans = max(ans, left_max_depth + right_max_depth);
        return max(left_max_depth, right_max_depth) + 1;
    }

    int diameterOfBinaryTree(TreeNode *root)
    {
        helper(root);
        return ans;
    }
};