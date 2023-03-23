
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
public:
    ListNode *addTwoNumbers(ListNode *l1, ListNode *l2)
    {
        ListNode preHead = ListNode(-1);
        ListNode *curr = &preHead;
        int remain = 0;
        while (l1 || l2)
        {
            // 求和
            int sum = remain;
            sum += l1 ? l1->val : 0;
            sum += l2 ? l2->val : 0;
            remain = (sum - sum % 10) / 10;
            sum = sum % 10;
            // 重组链表
            ListNode *temp = l1 ? l1 : l2;
            temp->val = sum;
            l1 = l1 ? l1->next : l1;
            l2 = l2 ? l2->next : l2;
            curr->next = temp;
            curr = curr->next;
        }
        if (remain)
        {
            curr->next = new ListNode(remain);
        }
        return preHead.next;
    }
};