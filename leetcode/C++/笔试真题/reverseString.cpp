/***
 * 将数组中的单词，按照空格进行切分。将切分后的单词逆序，返回最终的结果
 * 最终结果中，单词之间的顺序保持不变，但是每个单词都被逆序
 *
 */
#include <string>
#include <iostream>
#include <algorithm>

using namespace std;

class Solution
{

public:
    string reverseWorld(string &str)
    {
        string ans = "";
        for (int start = 0; start < str.length();)
        {
            // 去除所有的空格
            while (start < str.size() && str[start] == ' ')
            {
                ans += ' ';
                start++;
            }
            int end = start + 1;
            while (end < str.size() && str[end] != ' ')
            {
                end++;
            }
            string subStr = str.substr(start, end - start);
            reverse(subStr.begin(), subStr.end());
            cout << subStr << endl;
            ans += subStr;
            start = end;
        }
        return ans;
    }
};

int main()
{
    Solution solution;
    string s = "I am   a student.";
    cout << solution.reverseWorld(s) << endl;
}
