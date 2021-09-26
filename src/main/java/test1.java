import java.security.Key;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.Condition;

class test1 {

    public static void main(String[] args) {
        int nums[] = new int[]{0, 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

        int[][] matrix = new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}};
//        findRepeatNumber(nums);
//        boolean numberIn2DArray = findNumberIn2DArray(matrix, 29);
//        System.out.println("numberIn2DArray====" + numberIn2DArray);
//        System.out.println(fib(5));
//        System.out.println(numWays(7));
//        System.out.println(myPow(2.00000, -2147483648));
//        ListNode node = new ListNode(4);
//        node.next = new ListNode(5);
//        node.next.next = new ListNode(1);
//        node.next.next.next = new ListNode(9);
//        deleteNode2(node, 9);
        int[] arr = new int[]{3, 1, 4, 6, 8, 2, 9};
//        qs(arr, 0, arr.length - 1);
        gb(arr);
        for (int i : arr) {
            System.out.printf("" + i);
        }
    }

    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        return null;
    }

    public static int findRepeatNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.merge(nums[i], 1, Integer::sum);
        }
        Integer max = 0;
        int numm = 0;
        for (Integer key : map.keySet()) {
            if (map.get(key) > max) {
                max = map.get(key);
                numm = key;
            }
        }
        return numm;
    }

    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int x = matrix.length, y = matrix[0].length;
        int i = 0, j = y - 1;
        while (i < x & j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }

    /**
     * 替换空格
     *
     * @param s We are happy.
     * @return We%20are%20happy.
     */
    public String replaceSpace(String s) {
//        if (s == null) {
//            return null;
//        }
//        char[] array = s.toCharArray();
//        StringBuilder newStr = new StringBuilder();
//        for (int i = 0; i < array.length; i++) {
//            if ((array[i] + "").equals(" ")) {
//                newStr.append("%20");
//            }else {
//                newStr.append(array[i]);
//            }
//        }
//        return newStr.toString();
        int length = s.length();
        char[] array = new char[length * 3];
        int size = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                array[size++] = '%';
                array[size++] = '2';
                array[size++] = '0';
            } else {
                array[size++] = c;
            }
        }
        return new String(array, 0, size);
    }

    /**
     * 从头打印链表
     *
     * @param head [1,3,2]
     * @return [2, 3, 1]
     */
    public int[] reversePrint(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }
        int size = stack.size();
        int[] ints = new int[size];
        for (int i = 0; i < size; i++) {
            ints[i] = stack.pop();
        }
        return ints;
    }

    private Map<Integer, Integer> map;

    /**
     * 重建二叉树
     *
     * @param preorder [3,9,20,15,7]
     * @param inorder  [9,3,15,20,7]
     * @return [3, 9, 20, null, null, 15, 7]
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int size = preorder.length;
        map = new HashMap<>();
        for (int i = 0; i < size; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(preorder, inorder, 0, size - 1, 0, size - 1);
    }

    private TreeNode buildTree(int[] preorder, int[] inorder, int pl, int pr, int il, int ir) {
        if (pl > pr) {
            return null;
        }
        int root = preorder[pl];
        int inoRootPoi = map.get(root);

        TreeNode treeNode = new TreeNode(root);
        int left_size = inoRootPoi - il;
        treeNode.left = buildTree(preorder, inorder, pl + 1, pl + left_size, il, inoRootPoi - 1);
        treeNode.right = buildTree(preorder, inorder, pl + left_size + 1, pr, inoRootPoi + 1, ir);
        return treeNode;
    }

    /**
     * 两个栈实现队列
     */
    class CQueue {

        Stack<Integer> stack1;
        Stack<Integer> stack2;

        public CQueue() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        public void appendTail(int value) {
            stack1.push(value);
        }

        public int deleteHead() {
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
            if (stack2.isEmpty()) {
                return -1;
            } else {
                return stack2.pop();
            }
        }
    }

    /**
     * 斐波那契数列
     *
     * @param n 5
     * @return 5
     */
    public static int fib(int n) {
        int a = 0, b = 1, sum;
        for (int i = 0; i < n; i++) {
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return a;
    }

    /**
     * 青蛙跳台阶问题
     *
     * @param n 7
     * @return 21
     */
    public static int numWays(int n) {
        if (n == 0) {
            return 1;
        }
        if (n <= 3) {
            return n;
        }
        // f(1) = 1 f(2) = 2 f(3) = 3
        int a = 1, b = 2, sum = 0;
        for (int i = 3; i <= n; i++) {
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return sum;
    }

    /**
     * 旋转数组的最小数字
     *
     * @param numbers [3,4,5,1,2]
     * @return 1
     */
    public int minArray(int[] numbers) {
        int low = 0, high = numbers.length - 1;
        while (low < high) {
            int pivot = low + (high - low) / 2;
            if (numbers[pivot] < numbers[high]) {
                high = pivot;
            } else if (numbers[pivot] > numbers[high]) {
                low = pivot + 1;
            } else {
                high -= 1;
            }
        }
        return numbers[low];
    }

    /**
     * 剪绳子
     *
     * @param n 10
     * @return 36
     */
    public int cuttingRope(int n) {
        return 0;
    }

    /**
     * 数值的整数次方
     *
     * @param x 2
     * @param n 3
     * @return 8
     */
    public static double myPow(double x, int n) {
        long m = n;
        if (m < 0) {
            x = 1 / x;
            m = -m;
        }
        double num = 1;
        while (m > 0) {
            num *= x;
            m--;
        }
        return num;
    }

    /**
     * 打印从1到最大的n位数
     *
     * @param n 1
     * @return [1, 2, 3, 4, 5, 6, 7, 8, 9]
     */
    public int[] printNumbers(int n) {
        int max = (int) Math.pow(10, n) - 1;
        int[] ints = new int[max];
        for (int i = 0; i < max; i++) {
            ints[i] = i + 1;
        }
        return ints;
    }

    StringBuilder str;
    int count = 0, nine = 0, n, start;
    char[] nums, arrs = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public String printNumbers1(int n) {
        str = new StringBuilder();
        return null;
    }

    /**
     * 删除链表的节点
     *
     * @param head [4,5,1,9]
     * @param val  5
     * @return [4, 1, 9]
     */
    public static ListNode deleteNode(ListNode head, int val) {
        if (head.val == val) {
            return head.next;
        }
        ListNode preNode = head, nextNode = head.next;
        while (nextNode != null) {
            if (nextNode.val != val) {
                nextNode = nextNode.next;
                preNode = preNode.next;
            } else {
                preNode.next = nextNode.next;
                nextNode = nextNode.next;
            }
        }
        return head;
    }

    public static ListNode deleteNode2(ListNode head, int val) {
        if (head.val == val) {
            return head.next;
        }
        ListNode preNode = head, nextNode = head.next;
        while (nextNode != null) {
            if (nextNode.val == val) {
                break;
            } else {
                preNode = nextNode;
                nextNode = nextNode.next;
            }
        }
        if (nextNode != null) {
            preNode.next = nextNode.next;
        }
        return head;
    }

    /**
     * 反转链表
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode curNode = head, node = null;
        while (curNode.next != null) {
            ListNode temp = curNode.next;
            curNode.next = node;
            node = curNode;
            curNode = temp;
        }
        return node;
    }

    /**
     * 改变数组将奇数置于偶数前面
     * 方法1：开辟空间
     *
     * @param nums [1,2,3,4]
     * @return [1, 3, 2, 4] 或 [3,1,2,4] ...
     */
    public int[] exchange(int[] nums) {
        int size = nums.length;
        int[] newNums = new int[size];
        int j = 0, m = size - 1;
        for (int i = 0; i < size; i++) {
            if (nums[i] % 2 == 1) {
                newNums[j] = nums[i];
                j++;
            } else {
                newNums[m] = nums[i];
                m--;
            }
        }
        return newNums;
    }

    /**
     * 改变数组将奇数置于偶数前面
     * 方法2：两头交换
     *
     * @param nums [1,2,3,4]
     * @return [1, 3, 2, 4] 或 [3,1,2,4] ...
     */
    public int[] exchange1(int[] nums) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            if (nums[i] % 2 == 1) {
                i++;
                continue;
            }
            if (nums[j] % 2 == 0) {
                j--;
                continue;
            }
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        return nums;
    }

    /**
     * 链表的倒数第 n 个数
     * 方法1：查询长度法
     *
     * @param head 1->2->3->4->5
     * @param k    2
     * @return 4->5
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        int size = 0;
        ListNode temp = head;
        while (temp.next != null) {
            size++;
            temp = temp.next;
        }
        if (size <= k) {
            return head;
        }
        while (size > k) {
            if (head == null) {
                break;
            }
            head = head.next;
            size--;
        }
        return head;
    }

    /**
     * 链表的倒数第 n 个数
     * 方法2：双指针法
     *
     * @param head 1->2->3->4->5
     * @param k    2
     * @return 4->5
     */
    public ListNode getKthFromEnd1(ListNode head, int k) {
        ListNode node1 = head, node2 = head;
        while (k > 0) {
            if (node2 == null) {
                return node1;
            }
            node2 = node2.next;
            k--;
        }
        while (node2 != null) {
            node1 = node1.next;
            node2 = node2.next;
        }
        return node1;
    }

    /**
     * 上到下打印二叉树 II
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return new ArrayList<>();
        }
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll != null) {
                    list.add(poll.val);
                    queue.add(poll.left);
                    queue.add(poll.right);
                }
            }
            if (list.size() > 0) lists.add(list);
        }
        return lists;
    }

    public List<List<Integer>> levelOrder3(TreeNode root) {
        int level = 1;
        List<List<Integer>> lists = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return new ArrayList<>();
        }
        queue.add(root);
        while (!queue.isEmpty()) {
            LinkedList<Integer> list = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll != null) {
                    if (level % 2 == 0) {
                        list.addFirst(poll.val);
                    } else {
                        list.addLast(poll.val);
                    }
                    queue.add(poll.left);
                    queue.add(poll.right);
                }
            }
            level++;
            if (list.size() > 0) lists.add(list);
        }
        return lists;
    }

    public boolean verifyPostorder(int[] postorder) {
        return verifyPostorder(postorder, 0, postorder.length - 1);
    }

    public boolean verifyPostorder(int[] postorder, int i, int j) {
        if (i > j) return true;
        int m = i;
        while (postorder[m] < postorder[j]) m++;
        int n = m;
        while (postorder[n] > postorder[j]) n++;
        return n == j && verifyPostorder(postorder, i, m - 1) && verifyPostorder(postorder, m, j - 1);
    }

    /**
     * 二叉树中和为某一值的路径
     */
    List<List<Integer>> lists = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        if (root == null) {
            return new ArrayList<>();
        }
        pathSum(root, target, new ArrayList<>());
        return lists;
    }

    public void pathSum(TreeNode root, int target, List<Integer> list) {
        if (root == null) {
            return;
        }
        List<Integer> newList = new ArrayList<>(list);
        int value = root.val;
        if (value > target) {
            return;
        }
        target = target - value;
        newList.add(value);
        if (root.left == null && root.right == null && target == 0) {
            lists.add(newList);
        }
        pathSum(root.left, target, newList);
        pathSum(root.right, target, newList);
    }

    /**
     * 数组中出现次数超过一半的数字
     *
     * @param nums [1, 2, 3, 2, 2, 2, 5, 4, 2]
     * @return 2
     */
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) == null) {
                map.put(nums[i], 1);
            } else {
                map.put(nums[i], map.get(nums[i]) + 1);
            }
        }
        int num = nums.length / 2;
        System.out.println("---:" + num);
        if (num % 2 == 1) {
            num++;
        }
        System.out.println(num);
        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) >= num) {
                return nums[i];
            }
        }
        return 0;
    }

    /**
     * 数组中出现次数超过一半的数字
     *
     * @param nums [1, 2, 3, 2, 2, 2, 5, 4, 2]
     * @return 2
     */
    public int majorityElement1(int[] nums) {
        Integer count = 0, data = null;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                data = nums[i];
            }
            count += (data == nums[i]) ? 1 : -1;
        }
        return data;
    }

    /**
     * 字符串的排列
     *
     * @param s abc
     * @return ["abc","acb","bac","bca","cab","cba"]
     */
    List<String> list = new ArrayList<>();
    char[] chars;

    public String[] permutation(String s) {
        chars = s.toCharArray();
        dfs(0);
        return list.toArray(new String[list.size()]);
    }

    void dfs(int x) {
        if (x == chars.length - 1) {
            list.add(String.valueOf(chars));
            return;
        }
        Set<Character> charSet = new HashSet<>();
        for (int i = x; i < chars.length; i++) {
            if (charSet.contains(chars[i])) {
                continue;
            }
            charSet.add(chars[i]);
            swap(i, x);
            dfs(x + 1);
            swap(i, x);
        }
    }

    void swap(int a, int b) {
        char tmp = chars[a];
        chars[a] = chars[b];
        chars[b] = tmp;
    }

    public Node copyRandomList(Node head) {
        if (head == null) return null;
        Node curHead = head;
        Map<Node, Node> map = new HashMap<>();
        while (curHead != null) {
            map.put(curHead, new Node(curHead.val));
            curHead = curHead.next;
        }
        Node node = map.get(head);
        Node curNode = node;
        while (head != null) {
            curNode.next = map.get(head.next);
            curNode.random = map.get(head.random);
            head = head.next;
            curNode = curNode.next;
        }
        return node;
    }

    public int[] getLeastNumbers(int[] arr, int k) {
        if (k >= arr.length) return arr;
        sort(arr, 0, arr.length - 1);
        System.out.println(new String(arr, 0, arr.length - 1));
        return Arrays.copyOf(arr, k);
    }

    private void sort(int[] arr, int left, int right) {
        if (left > right) {
            return;
        }
        int tempLow = arr[left];
        int i = left, j = right;
        while (left < right) {
            while (left < right && arr[right] >= tempLow) {
                right--;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] <= tempLow) {
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = tempLow;
        sort(arr, i, left - 1);
        sort(arr, left + 1, j);
    }

    public int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i] += Math.max(nums[i - 1], 0);
            res = Math.max(nums[i], res);
        }
        return res;
    }

    public int findNthDigit(int n) {
        return 0;
    }

    public String minNumber(int[] nums) {
        minNumberSort(nums, 0, nums.length - 1);
        StringBuilder str = new StringBuilder();
        for (int num : nums) {
            str.append(num);
        }
        return str.toString();
    }

    private void minNumberSort(int[] nums, int left, int right) {
        if (left >= right) return;
        int i = left, j = right;
        int temp = nums[left];
        while (i < j) {
            while (i < j && (nums[j] + "" + temp).compareTo(temp + "" + nums[j]) > 0) j--;
            nums[i] = nums[j];
            while (i < j && (nums[i] + "" + temp).compareTo(temp + "" + nums[i]) <= 0) i++;
            nums[j] = nums[i];
        }
        nums[i] = temp;
        minNumberSort(nums, left, i - 1);
        minNumberSort(nums, i + 1, right);
    }

    public int translateNum(int num) {
        String s = num + "";
        int a = 1, b = 1;
        for (int i = 2; i <= s.length(); i++) {
            String str = s.substring(i - 2, i);
            int c = str.compareTo("10") >= 0 && str.compareTo("25") <= 0 ? a + b : a;
            b = a;
            a = c;
        }
        return a;
    }

    public char firstUniqChar(String s) {
        if (s == null || s.length() == 0) {
            return ' ';
        }
        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : chars) {
            map.merge(c, 1, Integer::sum);
        }
        for (char c : chars) {
            if (map.get(c) == 1) {
                return c;
            }
        }
        return ' ';
    }

    int row, col;
    List<Integer> maxList;

    public int maxValue(int[][] grid) {
        row = grid.length;
        col = grid[0].length;
        maxList = new ArrayList<>();
        maxValue(grid, 0, 0, grid[0][0]);
        return Collections.max(maxList);
    }

    public void maxValue(int[][] grid, int r, int c, int num) {
        if (r == row && c == col) {
            maxList.add(num);
        }
        if (r < row) {
            maxValue(grid, r + 1, c, num + grid[r + 1][c]);
        }
        if (c < col) {
            maxValue(grid, r, c + 1, num + grid[r][c + 1]);
        }
    }

    public int maxValue2(int[][] grid) {
        int row = grid.length - 1, col = grid[0].length - 1;
        for (int i = 0; i <= col; i++) {
            for (int j = 0; j <= row; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if (i == 0) {
                    grid[i][j] = grid[i][j] + grid[i][j - 1];
                } else if (j == 0) {
                    grid[i][j] = grid[i][j] + grid[i - 1][j];
                } else {
                    grid[i][j] = grid[i][j] + Math.max(grid[i][j - 1], grid[i - 1][j]);
                }
            }
        }
        return grid[row][col];
    }

    /**
     * 最长不含重复字符的子字符串
     *
     * @param s abcabcbb
     * @return 3
     */
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int num = 0, temp = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int j = map.getOrDefault(chars[i], -1);
            map.put(chars[i], j);
            temp = temp < i - j ? temp + 1 : i - j;
            num = Math.max(temp, num);
        }
        return num;
    }

    /**
     * 丑数
     *
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        int a = 0, b = 0, c = 0;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int n1 = dp[a] * 2, n2 = dp[b] * 3, n3 = dp[c] * 5;
            dp[i] = Math.min(Math.min(n1, n2), n3);
            if (dp[i] == n1) a++;
            if (dp[i] == n2) b++;
            if (dp[i] == n3) c++;
        }
        return dp[n - 1];
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode node = headB;
        while (headA != null) {
            while (node != null) {
                if (headA == node) {
                    return node;
                }
                node = node.next;
            }
            node = headB;
            headA = headA.next;
        }
        return null;
    }

    public int search(int[] nums, int target) {
        return searchNum(nums, target) - searchNum(nums, target - 1);
    }

    public int searchNum(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int m = (i + j) / 2;
            if (nums[m] <= target) {
                i = m + 1;
            } else {
                j = m - 1;
            }
        }
        return i;
    }

    public int missingNumber(int[] nums) {
        int length = nums.length;
        for (int i = 0; i <= length; i++) {
            if (i != nums[i]) {
                return i;
            }
        }
        return length;
    }

    public int missingNumber1(int[] nums) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int m = (i + j) / 2;
            if (nums[m] == m) {
                i = m + 1;
            } else {
                j = m - 1;
            }
        }
        return i;
    }

    int num, k;

    public int kthLargest(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return num;
    }

    void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.right);
        if (k == 0) {
            return;
        }
        if (--k == 0) num = node.val;
        dfs(node.left);
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int num = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            for (int i = 0; i < queue.size(); i++) {
                TreeNode poll = queue.poll();
                if (poll.left != null) queue.add(poll.left);
                if (poll.right != null) queue.add(poll.right);
            }
            num++;
        }
        return num;
    }

    // 快排
    public static void qs(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        // 随机取一个数作为临界值
        swap(arr, l + (int) (Math.random() * (r - l + 1)), r);
        int[] result = qs1(arr, l, r);
        qs(arr, l, result[0]);
        qs(arr, result[1] + 1, r);
    }

    public static void swap(int[] arr, int l, int r) {
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }

    public static int[] qs1(int[] arr, int l, int r) {
        if (l > r) {
            return new int[]{-1, -1};
        }
        if (l == r) {
            return new int[]{l, r};
        }
        int i = l - 1;
        int j = r;
        int index = l;
        while (index < j) {
            if (arr[index] == arr[r]) {
                index++;
            } else if (arr[index] < arr[r]) {
                swap(arr, ++i, index++);
            } else {
                swap(arr, --j, index);
            }
        }
        swap(arr, j, r);
        return new int[]{i + 1, j};
    }

    /**
     * 归并排序
     *
     * @param arr
     */
    public static void gb(int[] arr) {
        gb(arr, 0, arr.length - 1);
    }

    public static void gb(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = l + ((r - l) >> 1);
        gb(arr, l, mid);
        gb(arr, mid + 1, r);
        gb1(arr, l, mid, r);
    }

    public static void gb1(int[] arr, int l, int mid, int r) {
        int[] temp = new int[r - l + 1];
        int i = 0;
        int p1 = l, p2 = mid + 1;
        while (p1 <= mid && p2 <= r) {
            temp[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            temp[i++] = arr[p1++];
        }
        while (p2 <= r) {
            temp[i++] = arr[p2++];
        }
        for (int j = 0; j < temp.length; j++) {
            arr[l + j] = temp[j];
        }
    }

    /**
     * 堆排序
     *
     * @param arr
     */
    public static void ds(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }

        // swap() 操作依次把堆顶部最大的值放在最后面
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }

    }

    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1; // 左孩子的下标
        while (left < heapSize) {
            // 计算两个孩子的大小，谁的值大，就把下标给 largest
            // 1：只有左孩子的情况，largest = left
            // 2：同时有左孩子和有孩子，右孩子的值 <= 左孩子值，largest = left
            // 3：同时有左孩子和有孩子，右孩子的值 > 左孩子值，largest = left + 1
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            // 父节点和子节点谁的值大，largest 等于谁的下标
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = 2 * index + 1;
        }
    }

    /**
     * 平衡二叉树
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        return balanced(root) != -1;
    }

    public int balanced(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = balanced(root.left);
        if (left == -1) return -1;
        int right = balanced(root.right);
        if (right == -1) return -1;
        return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
    }

    public static class ListNode {

        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}




