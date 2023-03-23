
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
    int helper(TreeNode *root, int *maxV)
    {
        if (!root)
        {
            return 0;
        }
        int leftMax = helper(root->left, maxV);
        int rightMax = helper(root->right, maxV);
        int candidate = root->val; // 以该节点为转折点，最长路径是多少
        int ret = root->val;       // 返回经过该节点，单边最长是多少
        if (leftMax > 0)
        {
            candidate += leftMax;
            ret = max(ret, root->val + leftMax);
        }
        if (rightMax > 0)
        {
            candidate += rightMax;
            ret = max(ret, root->val + rightMax);
        }
        *maxV = max(*maxV, candidate);
        return ret;
    }

public:
    int maxPathSum(TreeNode *root)
    {
        int ans = root->val;
        helper(root, &ans);
        return ans;
    }
};