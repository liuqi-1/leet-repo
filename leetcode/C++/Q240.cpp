#include <vector>
#include <queue>

using namespace std;

class Solution
{
public:
    bool searchMatrix(vector<vector<int>> &matrix, int target)
    {
        int m = matrix.size(), n = matrix[0].size();
        int x = m - 1, y = 0;
        while (x >= 0 && y < n)
        {
            if (matrix[x][y] == target)
            {
                return true;
            }
            if (matrix[x][y] > target)
            {
                x--;
            }
            else
            {
                y++;
            }
        }
        return false;
    }
};