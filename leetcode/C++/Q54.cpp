
#include <vector>

using namespace std;

class Solution
{
public:
    vector<int> spiralOrder(vector<vector<int>> &matrix)
    {
        int left = 0, right = matrix[0].size() - 1, top = 0, bottom = matrix.size() - 1;
        int currDir = 0;
        vector<int> ans;
        int row = 0, col = 0;
        ans.push_back(matrix[row][col]);
        do
        {
            if (currDir == 0)
            { // 向右扫描
                while (col + 1 <= right)
                {
                    col++;
                    ans.push_back(matrix[row][col]);
                }
                top++;
            }
            else if (currDir == 1)
            { // 向下扫描
                while (row + 1 <= bottom)
                {
                    row++;
                    ans.push_back(matrix[row][col]);
                }
                right--;
            }
            else if (currDir == 2)
            { // 向左扫描
                while (col - 1 >= left)
                {
                    col--;
                    ans.push_back(matrix[row][col]);
                }
                bottom--;
            }
            else
            { // 向上扫描
                while (row - 1 >= top)
                {
                    row--;
                    ans.push_back(matrix[row][col]);
                }
                left++;
            }
            currDir = (currDir + 1) % 4;

        } while (left <= right && top <= bottom);
        return ans;
    }
};