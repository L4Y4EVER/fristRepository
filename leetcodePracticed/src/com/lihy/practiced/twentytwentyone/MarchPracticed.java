package com.lihy.practiced.twentytwentyone;

import java.util.*;

/**
 * 三月算法练习
 *
 * @author lhy
 * @date 2021/3/3
 */
public class MarchPracticed {
    MarchPracticed(){

    }

    public static void main(String[] args) {
//        MarchPracticed marchPracticed = new MarchPracticed();
//        marchPracticed.countBits(2);

        List list = new ArrayList(16);
        System.out.println(list.size());
    }

    /**
     * 判断一个二叉树的前序遍历序列化是否正确
     * 二叉树的前序便利为每次都操作当前节点，然后是左子节点，然后是右子节点
     * 按照这个操作就是，当遇到两个#号的时候，表示该子树终结了
     * 首先明确二叉树的定义，以及前序遍历的定义
     * 当前节点，0，左子节点 0 + 1；左子节点一直到头，就是到一个# 终止了
     *
     * @param preorder 入参
     * @return 是否正确
     */
    public boolean isValidSerialization(String preorder) {
        if ("".equals(preorder) || preorder.length() == 0){
            return true;
        }

        // 根据 , 分割字符串
        String[] split = preorder.split(",");
        Deque<Integer> deque = new LinkedList<>();
        deque.push(1);

        int num = 0;
        while (num < split.length ){
            if (deque.isEmpty()){
                return false;
            }
            String str = split[num];
            // 如果当前元素为# 则进行以下判断
            if ("#".equals(str)){
                // 遇到空值消耗一个槽
                int top = deque.pop() - 1;
                if (top > 0){
                    deque.push(top);
                }
                num ++;
            }else { // 遇到非空节点，消耗一个槽，并补充两个槽
                int top = deque.pop() - 1;
                if (top > 0) {
                    deque.push(top);
                }
                deque.push(2);
                num++;
            }
        }

        return deque.isEmpty();
    }

    int[][] sums;

    public MarchPracticed(int[][] matrix) {
        int m = matrix.length;
        if (m > 0) {
            int n = matrix[0].length;
            sums = new int[m][n + 1];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    sums[i][j + 1] = sums[i][j] + matrix[i][j];
                }
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int i = row1; i <= row2; i++) {
            sum += sums[i][col2 + 1] - sums[i][col1];
        }
        return sum;
    }

    /**
     * 基本计算器
     *
     * @param s 入参
     * @return 结果
     */
    public int calculateOne(String s) {
        Deque<Integer> ops = new LinkedList<Integer>();
        ops.push(1);
        int sign = 1;

        int ret = 0;
        int n = s.length();
        int i = 0;
        while (i < n) {
            if (s.charAt(i) == ' ') {
                i++;
            } else if (s.charAt(i) == '+') {
                sign = ops.peek();
                i++;
            } else if (s.charAt(i) == '-') {
                sign = -ops.peek();
                i++;
            } else if (s.charAt(i) == '(') {
                ops.push(sign);
                i++;
            } else if (s.charAt(i) == ')') {
                ops.pop();
                i++;
            } else {
                long num = 0;
                while (i < n && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                ret += sign * num;
            }
        }
        return ret;
    }


    /**
     * 基本计算器||
     *
     * @param s 入参
     * @return 结果
     */
    public int calculate(String s) {
        if ("".equals(s) || s.length() == 0){
            return 0;
        }
        // 顺序接入
        int[] nums = new int[s.length()];
        LinkedHashMap<Character,Integer> hashMap = new LinkedHashMap<>();


        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if ( c == '+' || c == '-' || c == '*' || c == '/' ){

            }
        }


        return 0;
    }

    /**
     * 删除重复字符
     *
     * @param str s
     * @return 删除后的字符串
     */
    public String removeDuplicates(String str) {

        if("".equals(str) || str.length() == 0){
            return "";
        }
        StringBuilder stack = new StringBuilder();
        int top = -1;

        for (int i = 0; i < str.length(); i++){
            char ch = str.charAt(i);
            if (top >= 0 && stack.charAt(top) == ch) {
                stack.deleteCharAt(top);
                --top;
            } else {
                stack.append(ch);
                ++top;
            }
        }

        return stack.toString();
    }






    boolean[][] f;
    List<List<String>> ret = new ArrayList<List<String>>();
    List<String> ans = new ArrayList<String>();
    int n;


    public void dfs(String s, int i) {
        if (i == n) {
            ret.add(new ArrayList<String>(ans));
            return;
        }
        for (int j = i; j < n; ++j) {
            if (f[i][j]) {
                ans.add(s.substring(i, j + 1));
                dfs(s, j + 1);
                ans.remove(ans.size() - 1);
            }
        }
    }

    /**
     * 分割回文串
     *
     * @param s s
     * @return 回文串
     */
    public List<List<String>> partition(String s) {
        n = s.length();
        f = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(f[i], true);
        }

        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                f[i][j] = (s.charAt(i) == s.charAt(j)) && f[i + 1][j - 1];
            }
        }

        dfs(s, 0);
        return ret;
    }

    /**
     * 03-06
     *
     * @param nums 入参
     * @return 结果
     */
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ret = new int[n];
        Arrays.fill(ret, -1);
        Deque<Integer> stack = new LinkedList<Integer>();
        int cap = 2;
        for (int i = 0; i < n * cap - 1; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i % n]) {
                ret[stack.pop()] = nums[i % n];
            }
            stack.push(i % n);
        }
        return ret;
    }


    /**
     * 03-05
     * 两个栈实现一个队列
     */
    class MyQueue {
        private Deque<Integer> deque;
        private Deque<Integer> que;


        /** Initialize your data structure here. */
        public MyQueue() {
            deque = new LinkedList<>();
            que = new LinkedList<>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            deque.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            int size = deque.size();
            while ( size > 0){
                que.push(deque.pop());
                size --;
            }
            int pop = que.pop();

            int qSize = que.size();
            while (qSize >0 ){
                deque.push(que.pop());
                qSize --;
            }

            return pop;
        }

        /** Get the front element. */
        public int peek() {
            int size = deque.size();
            while ( size > 0){
                que.push(deque.pop());
                size --;
            }

            int peek = que.peek();

            int qSize = que.size();
            while (qSize >0 ){
                deque.push(que.pop());
                qSize --;
            }

            return peek;
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return deque.isEmpty() && que.isEmpty();
        }
    }
    
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0) {
            return 0;
        }
        
        int n = envelopes.length;
        Arrays.sort(envelopes, (e1, e2) -> {
            if (e1[0] != e2[0]) {
                return e1[0] - e2[0];
            } else {
                return e2[1] - e1[1];
            }
        });

        int[] f = new int[n];
        Arrays.fill(f, 1);
        int ans = 1;
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (envelopes[j][1] < envelopes[i][1]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }

    public int[] countBits(int num) {

        int[] memo = new int[num + 1];
        for (int i = num; num >0; i--){
            String s = intToBinary(i);
            int count = 0;
            for (int j = 0;j < s.length(); j++){
                if (s.charAt(j) == '1'){
                    count++;
                }
            }
            memo[i] = count;
        }

        return memo;
    }



    public String intToBinary(int n){
        String  str = "";
        while(n!=0) {
            str = n % 2 + str;
            n = n / 2;
        }
        return str;
    }
}

/**
 * 3-13 题
 */
class MyHashSet {

    private Map<Integer,Object> hashSet;


    /** Initialize your data structure here. */
    public MyHashSet() {
        hashSet = new HashMap<>();
    }

    public void add(int key) {
        hashSet.put(key,null);
    }

    public void remove(int key) {
        hashSet.remove(key);
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        return hashSet.containsKey(key);
    }
}
class MyHashMap {

    private Node[] table;

    private int capacity = 43;

    /** Initialize your data structure here. */
    public MyHashMap() {
        table = new Node[capacity];
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int index = key % capacity;
        Node node = new Node(key, value);
        Node curNode = table[index];
        if (curNode == null){
            table[index] = node;
        }else {
            if (key != curNode.key){
                if (curNode.next == null){
                    curNode.next = node;
                }else {
                    Node perNode = curNode;
                    Node next = curNode.next;

                    while (next != null){
                        perNode = perNode.next;
                        next = next.next;
                    }
                    perNode.next = node;
                }

            }else {
                curNode.value = value;
            }
        }
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int index = key % capacity;
        Node curNode = table[index];
        if (curNode == null){
            return -1;
        }else {
            if (curNode.key == key){
                return curNode.value;
            }else {
                Node cur = curNode;
                while (cur != null){
                    if (cur.key == key){
                        return cur.value;
                    }
                    cur = cur.next;
                }
                return -1;
            }
        }

    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int index = key % capacity;
        Node curNode = table[index];
        if (curNode != null){
            if (curNode.next == null){
                table[index] = null;
            }else {
                // 删除链表的节点
                Node dummy = new Node();
                dummy.next = curNode;
                Node perNode = dummy;
                Node cur = curNode;

                while (cur != null){

                    Node next = cur.next;
                    if (cur.key == key){
                        perNode.next = next;
                        cur.next = null;
                        break;
                    }
                    perNode = cur;
                    cur = next;

                }

                table[index] = dummy.next;

            }
        }
    }

    private static class Node{
        public int key;
        public int value;

        public Node next;

        public Node(){
        }

        public Node(int key,int value){
            this.key = key;
            this.value = value;
        }
    }
}

class MyHashMapLeet {
    private class Pair {
        private int key;
        private int value;

        public Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    private static final int BASE = 769;
    private LinkedList[] data;

    /** Initialize your data structure here. */
    public MyHashMapLeet() {
        data = new LinkedList[BASE];
        for (int i = 0; i < BASE; ++i) {
            data[i] = new LinkedList<Pair>();
        }
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int h = hash(key);
        Iterator<Pair> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Pair pair = iterator.next();
            if (pair.getKey() == key) {
                pair.setValue(value);
                return;
            }
        }
        data[h].offerLast(new Pair(key, value));
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int h = hash(key);
        Iterator<Pair> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Pair pair = iterator.next();
            if (pair.getKey() == key) {
                return pair.value;
            }
        }
        return -1;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int h = hash(key);
        Iterator<Pair> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Pair pair = iterator.next();
            if (pair.key == key) {
                data[h].remove(pair);
                return;
            }
        }
    }

    private static int hash(int key) {
        return key % BASE;
    }
}
