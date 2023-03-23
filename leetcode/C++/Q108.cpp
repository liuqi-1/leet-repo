#include <vector>

using namespace std;

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
    TreeNode *helper(vector<int> &nums, int left, int right)
    {
        // 如果子树中没有元素，返回空树
        if (left > right)
        {
            return nullptr;
        }
        // 计算该子树的中间根节点
        int middle = (left + right) / 2;
        TreeNode *root = new TreeNode(nums[middle]); // 必须在堆中创建，否则会导致栈中的数据被销毁，造成悬空指针
        root->left = helper(nums, left, middle - 1);
        root->right = helper(nums, middle + 1, right);
        return root;
    }

public:
    TreeNode *sortedArrayToBST(vector<int> &nums)
    {
        return helper(nums, 0, nums.size() - 1);
    }
};