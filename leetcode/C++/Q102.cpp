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
    vector<vector<int>> levelOrder(TreeNode *root)
    {
        vector<vector<int>> res;
        if (!root)
        {
            return res;
        }
        queue<TreeNode *> q;
        q.push(root);
        int currLayer = 1;
        int nextLayer = 0;
        int currCnt = 0;
        vector<int> c;
        while (!q.empty())
        {
            TreeNode *curr = q.front();
            q.pop();
            c.push_back(curr->val);
            currCnt++;
            if (curr->left)
            {
                q.push(curr->left);
                nextLayer++;
            }
            if (curr->right)
            {
                q.push(curr->right);
                nextLayer++;
            }
            if (currCnt == currLayer)
            {
                currLayer = nextLayer;
                nextLayer = 0;
                currCnt = 0;
                res.push_back(c);
                c = vector<int>();
            }
        }
        return res;
    }
};