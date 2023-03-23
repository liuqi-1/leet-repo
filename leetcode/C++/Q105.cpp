
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
#include <vector>

using namespace std;

class Solution
{
private:
    unordered_map<int, int> index;
    TreeNode *myBuilder(vector<int> &preorder, int pLeft, int pRight, vector<int> &inorder, int iLeft, int iRight)
    {
        if (pRight < pLeft)
        {
            return nullptr;
        }

        int val = preorder[pLeft];
        int inIdx = index.at(val);

        int leftLen = inIdx - iLeft;
        int rightLen = iRight - inIdx;

        TreeNode *root = new TreeNode(val);
        root->left = myBuilder(preorder, pLeft + 1, pLeft + leftLen, inorder, iLeft, inIdx - 1);
        root->right = myBuilder(preorder, pLeft + leftLen + 1, pRight, inorder, inIdx + 1, iRight);
        return root;
    }

public:
    TreeNode *buildTree(vector<int> &preorder, vector<int> &inorder)
    {
        for (int i = 0; i < inorder.size(); i++)
        {
            index.emplace(inorder[i], i);
        }
        return myBuilder(preorder, 0, preorder.size() - 1, inorder, 0, inorder.size() - 1);
    }
};