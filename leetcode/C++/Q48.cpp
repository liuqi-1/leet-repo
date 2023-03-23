#include <vector>
#include <iostream>

using namespace std;

class Solution
{
private:
    void swap(vector<vector<int>> &matrix, int &temp, int x, int y)
    {
        int tt = matrix[x][y];
        matrix[x][y] = temp;
        temp = tt;
    }

public:
    void rotate(vector<vector<int>> &matrix)
    {
        int n = matrix.size();
        int start = 0;
        int end = n / 2; // 逐层旋转，记录需要旋转多少层
        int width = n;   // 记录每一层的宽度
        for (int x = start; x < end; x++)
        {
            int smallB = x;
            int bigB = x + width - 1;
            for (int step = 0; step < width - 1; step++)
            {
                int temp = matrix[x][x + step];

                int nextX = smallB + step;
                int nextY = bigB;
                swap(matrix, temp, nextX, nextY);

                nextX = bigB;
                nextY = bigB - step;
                swap(matrix, temp, nextX, nextY);

                nextX = bigB - step;
                nextY = smallB;
                swap(matrix, temp, nextX, nextY);

                matrix[x][x + step] = temp;
            }
            width -= 2;
        }
    }
};