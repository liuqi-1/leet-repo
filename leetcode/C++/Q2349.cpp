#include<unordered_set>
#include<unordered_map>
#include<queue>
using namespace std;

class NumberContainers {
private:
    unordered_map<int,int> container; // map<index, number>
    unordered_map<int,priority_queue<int, vector<int>, greater<>>> heaps; // map<number, index>

public:
    NumberContainers() {
        
    }
    
    void change(int index, int number) {
        // lgn 级别的时间复杂度
        container[index]=number;
        heaps[number].push(index);
    }
    
    int find(int number) {
        // lgn级别的时间复杂度
        while(!heaps[number].empty() && container[heaps[number].top()]!=number){
            heaps[number].pop();
        }
        if(heaps[number].empty()){
            return -1;
        }

        return heaps[number].top();
    }
};
