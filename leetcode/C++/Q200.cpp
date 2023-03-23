#include <vector>

using namespace std;

class Solution
{

private:
    // 深搜，将该位置附近的1全部变成0
    void dfs(vector<vector<char>> &grid, int i, int j)
    {
        if (i + 1 < grid.size() && grid[i + 1][j] == '1')
        {
            grid[i + 1][j] = '0';
            dfs(grid, i + 1, j);
        }
        if (i - 1 >= 0 && grid[i - 1][j] == '1')
        {
            grid[i - 1][j] = '0';
            dfs(grid, i - 1, j);
        }
        if (j + 1 < grid[0].size() && grid[i][j + 1] == '1')
        {
            grid[i][j + 1] = '0';
            dfs(grid, i, j + 1);
        }
        if (j - 1 >= 0 && grid[i][j - 1] == '1')
        {
            grid[i][j - 1] = '0';
            dfs(grid, i, j - 1);
        }
    }

public:
    int numIslands(vector<vector<char>> &grid)
    {
        int m = grid.size();
        int n = grid[0].size();
        int ans = 0;
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (grid[i][j] == '1')
                {
                    ans++;
                    dfs(grid, i, j);
                }
            }
        }
        return ans;
    }
};