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
#include <queue>

using namespace std;

class Solution
{
public:
    vector<int> rightSideView(TreeNode *root)
    {
        if (!root)
        {
            return {};
        }
        vector<int> ans;
        queue<TreeNode *> q;
        q.push(root);
        int currLayerSum = 1;
        int currCnt = 0;
        int nextLayerSum = 0;
        while (!q.empty())
        {
            TreeNode *curr = q.front();
            q.pop();
            currCnt++;
            if (curr->left)
            {
                q.push(curr->left);
                nextLayerSum++;
            }
            if (curr->right)
            {
                q.push(curr->right);
                nextLayerSum++;
            }
            if (currCnt == currLayerSum)
            {
                ans.push_back(curr->val);
                currLayerSum = nextLayerSum;
                nextLayerSum = 0;
                currCnt = 0;
            }
        }
        return ans;
    }
};