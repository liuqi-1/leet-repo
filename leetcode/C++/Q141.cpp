

struct ListNode
{
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(nullptr) {}
};
class Solution
{
public:
    bool hasCycle(ListNode *head)
    {
        // 至少三个成环
        if (!head || !head->next || !head->next->next)
        {
            return false;
        }
        // 使用快慢指针找环
        ListNode *fast = head;
        ListNode *slow = head;
        do
        {
            fast = fast->next->next;
            slow = slow->next;
            if (fast == slow)
            {
                return true;
            }
        } while (fast && slow && fast->next && fast != slow);
        return false;
    }
};