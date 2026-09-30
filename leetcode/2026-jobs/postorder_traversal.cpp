#include <vector>
#include <iostream>
#include <stack>

using namespace std;

/**
 * 题目描述：实现二叉树的后序遍历（递归+非递归两种方式实现
 */

class TreeNode
{
public:
    TreeNode *left;
    TreeNode *right;
    int val;
    TreeNode(TreeNode *left, TreeNode *right, int val)
    {
        this->left = left;
        this->right = right;
        this->val = val;
    }
};

void dfs(TreeNode *root, vector<int> &ans)
{
    if (!root)
    {
        return;
    }
    dfs(root->left, ans);
    dfs(root->right, ans);
    ans.push_back(root->val);
}

vector<int> postorder_traversal_recursive(TreeNode *root)
{
    vector<int> ans;
    dfs(root, ans);
    return ans;
}

/**
 * 非递归版本的二叉树后序遍历，注意curr指针和last指针的区别
 */
vector<int> postorder_traversal_non_recursive(TreeNode *root)
{
    TreeNode *curr = root;
    TreeNode *last = nullptr;
    stack<TreeNode *> stk;
    vector<int> ans;
    while (curr || !stk.empty())
    {
        while (curr != nullptr)
        {
            stk.push(curr);
            curr = curr->left;
        }
        TreeNode *top = stk.top();
        if (top->right == nullptr || top->right == last)
        {
            ans.push_back(top->val);
            last = top;
            stk.pop();
        }
        else
        {
            curr = top->right;
        }
    }
    return ans;
}

int main()
{
    // 构造如下测试二叉树：
    //         1
    //        / \
    //       2   3
    //      / \   \
    //     4   5   6
    // 后序遍历期望输出：4 5 2 6 3 1
    TreeNode *n4 = new TreeNode(nullptr, nullptr, 4);
    TreeNode *n5 = new TreeNode(nullptr, nullptr, 5);
    TreeNode *n6 = new TreeNode(nullptr, nullptr, 6);
    TreeNode *n2 = new TreeNode(n4, n5, 2);
    TreeNode *n3 = new TreeNode(nullptr, n6, 3);
    TreeNode *root = new TreeNode(n2, n3, 1);

    // vector<int> ans = postorder_traversal_recursive(root);
    vector<int> ans = postorder_traversal_non_recursive(root);

    cout << "后序遍历结果：";
    for (int v : ans)
    {
        cout << v << " ";
    }
    cout << endl;

    // 先释放子节点，再释放父节点
    delete n4;
    delete n5;
    delete n6;
    delete n2;
    delete n3;
    delete root;

    return 0;
}