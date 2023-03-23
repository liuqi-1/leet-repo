#include <vector>
#include <unordered_set>

using namespace std;

// O(m+n)额外空间的做法
class Solution1
{
public:
    void setZeroes(vector<vector<int>> &matrix)
    {
        int m = matrix.size();
        int n = matrix[0].size();
        unordered_set<int> zeroRow;
        unordered_set<int> zeroCol;
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (matrix[i][j] == 0)
                {
                    zeroRow.insert(i);
                    zeroCol.insert(j);
                }
            }
        }
        for (auto &row : zeroRow)
        {
            for (int j = 0; j < n; j++)
            {
                matrix[row][j] = 0;
            }
        }
        for (auto &col : zeroCol)
        {
            for (int i = 0; i < m; i++)
            {
                matrix[i][col] = 0;
            }
        }
    }
};

// 常量空间解决方案
class Solution
{
public:
    void setZeroes(vector<vector<int>> &matrix)
    {
        int m = matrix.size();
        int n = matrix[0].size();
        int flagRowZero = 0;
        int flagColZero = 0;
        // 判断第一列是否有零
        for (int i = 0; i < m; i++)
        {
            if (!matrix[i][0])
            {
                flagColZero = 1;
                break;
            }
        }
        // 判断第一行是否存在零
        for (int j = 0; j < n; j++)
        {
            if (!matrix[0][j])
            {
                flagRowZero = 1;
                break;
            }
        }
        // 遍历整个数组
        for (int i = 1; i < m; i++)
        {
            for (int j = 1; j < n; j++)
            {
                if (!matrix[i][j])
                {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        // 更新数组
        for (int i = 1; i < m; i++)
        {
            for (int j = 1; j < n; j++)
            {
                if (!matrix[i][0] || !matrix[0][j])
                {
                    matrix[i][j] = 0;
                }
            }
        }
        // 根据两个标记变量跟新第一行或第一列
        if (flagRowZero)
        {
            for (int i = 0; i < n; i++)
            {
                matrix[0][i] = 0;
            }
        }
        if (flagColZero)
        {
            for (int i = 0; i < m; i++)
            {
                matrix[i][0] = 0;
            }
        }
    }
};