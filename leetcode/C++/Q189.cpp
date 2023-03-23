#include <vector>
#include <cstring>
#include <iostream>
#include <algorithm>

using namespace std;

// 借用额外数组
class Solution1
{
public:
    void rotate(vector<int> &nums, int k)
    {
        int len = nums.size();
        vector<int> temp(len);
        for (int i = 0; i < nums.size(); i++)
        {
            temp[(i + k) % len] = nums[i];
        }
        nums.assign(temp.begin(), temp.end());
    }
};

// 环形数组转换
class Solution2
{
public:
    void rotate(vector<int> &nums, int k)
    {
        int n = nums.size();
        int start = 0;
        int count = 0;
        while (count < nums.size())
        {
            int previous = start;
            int current = start;
            int x = nums[current];
            do
            {
                int temp = nums[(current + k) % n];
                nums[(current + k) % n] = x;
                x = temp;
                current = (current + k) % n;
                count++;
            } while (previous != current);
            start++;
        }
    }
};

class Solution
{
public:
    void rotate(vector<int> &nums, int k)
    {
        int n = nums.size();
        int start = 0;
        int count = __gcd(n, k);
        for (int start = 0; start < count; start++)
        {
            int previous = start;
            int current = start;
            int x = nums[current];
            do
            {
                int next = (current + k) % n;
                int temp = nums[next];
                nums[next] = x;
                x = temp;
                current = next;
            } while (previous != current);
        }
    }
};

int main()
{
    cout << (-1) % 2 << endl; // 结果为-1
    cout << -3 % -5 << endl;  // 结果为-3
    cout << -6 % -5 << endl;  // 结果为-1
    return 1;
}