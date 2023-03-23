
#include<vector>
#include<unordered_map>
#include<queue>
using namespace std;

class TaskManager {

private:
    unordered_map<int, pair<int,int>> map;
    priority_queue<pair<int,int>> queue;

public:
    TaskManager(vector<vector<int>>& tasks) {
        for(vector<int> t:tasks){
            add(t[0],t[1],t[2]);
        }
    }

    void add(int userId, int taskId, int priority) {
        map[taskId]={userId, priority};
        queue.emplace(priority, taskId);
    }
    
    void edit(int taskId, int newPriority) {
        map[taskId].second = newPriority;
        queue.emplace(newPriority, taskId);
    }
    
    void rmv(int taskId) {
        map.erase(taskId);
    }
    
    int execTop() {
        while(!queue.empty()){
            pair<int,int> t = queue.top();
            queue.pop();
            if(map.find(t.second)!=map.end() && map[t.second].second==t.first){
                int userId = map[t.second].first;
                map.erase(t.second);
                return userId;
            }
        }
        return -1;
    }
};