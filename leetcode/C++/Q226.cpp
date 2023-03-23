/**
 * Definition for a binary tree node.

 */
struct TreeNode
{
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

class Solution
{
private:
    void invertHelper(TreeNode *root)
    {
        if (root)
        {
            invertHelper(root->left);
            invertHelper(root->right);
            TreeNode *tmp = root->left;
            root->left = root->right;
            root->right = tmp;
        }
    }

public:
    TreeNode *invertTree(TreeNode *root)
    {
        invertHelper(root);
        return root;
    }
};