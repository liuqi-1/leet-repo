#include<vector>
#include<unordered_map>
#include<string>
#include<algorithm>
#include<array>

using namespace std;


class Solution {
public:
    vector<vector<string>> groupAnagrams(vector<string>& strs) {
        unordered_map<string, vector<string>> map;
        for(string s : strs){
            array<int,26> counts{};
            for(char c : s){
                counts[c-'a']++;
            }
            string k;
            for(int i=0;i<26;i++){
                k.push_back(i+'a');
                k.append(to_string(counts[i]));
            }
            map[k].push_back(s);
        }
        vector<vector<string>> ret;
        for(auto p:map){
            ret.emplace_back(p.second);
        }
        return ret;
    }
};

// 排序解法
class Solution1 {
public:
    vector<vector<string>> groupAnagrams(vector<string>& strs) {
        // 用哈希表，使用有序字符串当作key
        unordered_map<string, vector<string>> map;
        for(auto s:strs){
            string t = s;
            sort(t.begin(),t.end());
            map[t].push_back(s);
        }
        // 将结果整理好进行返回
        vector<vector<string>> ret;
        for(auto p:map){
            ret.emplace_back(p.second);
        }
        return ret;
    }
};