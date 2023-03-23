#include <queue>
#include <list>

using namespace std;

struct ListNode
{
    int val;
    ListNode *next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode *next) : val(x), next(next) {}
};

// 双指针
class Solution
{
public:
    ListNode *removeNthFromEnd(ListNode *head, int n)
    {
        ListNode *pre = head;
        ListNode *post = head;
        for (int i = 0; i < n; i++)
        {
            post = post->next;
        }
        // 要删除的元素就是头部元素，直接返回即可
        if (!post)
        {
            return pre->next;
        }
        while (post->next)
        {
            pre = pre->next;
            post = post->next;
        }
        // 要删除的元素不是头部元素，那么原来的head还是head，直接返回即可
        pre->next = pre->next->next;
        return head;
    }
};

// 队列，空间复杂度为O(n)，时间复杂度为O(L)
class Solution1
{
public:
    ListNode *removeNthFromEnd(ListNode *head, int n)
    {
        queue<ListNode *> q;
        ListNode preHead = ListNode(0);
        preHead.next = head;
        ListNode *temp = &preHead;
        while (temp)
        {
            q.push(temp);
            temp = temp->next;
            if (q.size() > n + 1)
            {
                q.pop();
            }
        }
        temp = q.front();
        temp->next = temp->next->next;
        return preHead.next;
    }
};