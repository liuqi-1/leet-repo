/**
字节DataAML二面
问题描述：给KV Cache存储写一个LRU
Cache。只能存储n个token的kv值，当超过n个token时，删除最久未使用的token。
同时每个Token的KV Cache还有过期时间，如果读取的KV Cache超过过期时间，应该返回空
*/

#include <iostream>
#include <queue>
#include <unordered_map>
#include <vector>

using namespace std;

class Comparator {
public:
  bool operator()(const pair<int, int> &p1, const pair<int, int> &p2) {
    return p1.second > p2.second;
  }
};

class Tensor {
public:
  int tid;
  float *kval;
  float *vval;
};

class Node {
public:
  int tid;
  Tensor *data;
  Node *next;
  Node *prev;
};

class LRUCache {
private:
  unordered_map<int, int> timeRecord;
  priority_queue<pair<int, int>, vector<pair<int, int>>, Comparator> pq;
  unordered_map<int, Node *> cache;
  Node *head;
  Node *tail;
  int capacity;
  int expireTime = 60; // 超过60就过期

  void delete_token(int tid) {
    if (!cache.count(tid)) {
      return;
    }
    Node *n = cache[tid];
    n->next->prev = n->prev;
    n->prev->next = n->next;
    delete n;

    timeRecord.erase(tid);
    cache.erase(tid);
  }

  void delete_one_token(int timeNow) {
    if (head->next == tail) {
      return;
    }
    while (!pq.empty()) {
      pair<int, int> p = pq.top();
      int tid = p.first;
      int time = p.second;
      pq.pop();

      if (!timeRecord.count(tid) || timeRecord[tid] != time) {
        continue;
      }
      if (timeNow - time <= expireTime) {
        break;
      }
      delete_token(tid);
      return;
    }
    delete_token(tail->prev->tid);
  }

  void move_to_head(int tid) {
    Node *n = cache[tid];
    n->prev->next = n->next;
    n->next->prev = n->prev;

    n->prev = head;
    n->next = head->next;
    head->next = n;
    n->next->prev = n;
  }

  void fresh_time(int tid, int timeNow) {
    timeRecord[tid] = timeNow;
    pq.push({tid, timeNow});
  }

  void insert_to_first(int tid, Tensor *data) {
    Node *n = new Node();
    cache[tid] = n;
    n->tid = tid;
    n->data = data;

    n->prev = head;
    n->next = head->next;
    head->next = n;
    n->next->prev = n;
  }

public:
  LRUCache(int capacity) : capacity(capacity) {
    head = new Node();
    tail = new Node();
    head->next = tail;
    tail->prev = head;
  }

  Tensor *read(int tid, int timeNow) {
    if (!cache.count(tid)) {
      return nullptr;
    }
    if (timeNow - timeRecord[tid] > expireTime) {
      delete_token(tid);
      return nullptr;
    }
    move_to_head(tid);
    fresh_time(tid, timeNow);
    return cache[tid]->data;
  };

  void insert(int tid, Tensor *data, int timeNow) {
    insert_to_first(tid, data);
    fresh_time(tid, timeNow);
    while (cache.size() > capacity) {
      delete_one_token(timeNow);
    }
  }
};

int main() {

  float *data = new float[10];
  Tensor *kv = new Tensor();
  kv->tid = 100;
  kv->kval = data;
  kv->vval = data;

  LRUCache lru(10);
  lru.insert(100, kv, 10);
  Tensor *ret = lru.read(100, 70);
  cout << ret << " " << kv << endl;

  return 0;
}