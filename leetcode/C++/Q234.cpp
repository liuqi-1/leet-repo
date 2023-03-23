struct ListNode
{
    int val;
    ListNode *next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode *next) : val(x), next(next) {}
};
class Solution
{

private:
    ListNode *endOfFirstHalf;

public:
    bool isPalindrome(ListNode *head)
    {
        if (!head->next)
        {
            return true;
        }
        ListNode *endOfFirstHalf = findEndOfFistHalf(head);               // 快慢指针
        ListNode *startOfSecondHalf = resverseList(endOfFirstHalf->next); // 反转链表
        ListNode *p1 = head;
        ListNode *p2 = startOfSecondHalf;
        while (p2 && p1->val == p2->val)
        {
            p1 = p1->next;
            p2 = p2->next;
        }
        if (p2)
        {
            return false;
        }
        endOfFirstHalf->next = resverseList(startOfSecondHalf);
        return true;
    }
    ListNode *findEndOfFistHalf(ListNode *head)
    {
        ListNode *fast = head;
        ListNode *slow = head;
        while (fast->next && fast->next->next)
        {
            slow = slow->next;
            fast = fast->next->next;
        }
        return slow;
    }
    ListNode *resverseList(ListNode *head)
    {
        if (!head->next)
        {
            return head;
        }
        ListNode *pre = head;
        ListNode *post = head->next;
        pre->next = nullptr;
        while (post)
        {
            ListNode *temp = post->next;
            post->next = pre;
            pre = post;
            post = temp;
        }
        return pre;
    }
};
