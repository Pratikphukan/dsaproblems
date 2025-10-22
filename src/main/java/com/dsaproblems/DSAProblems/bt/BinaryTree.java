package com.dsaproblems.DSAProblems.bt;

import java.util.*;

public class BinaryTree {

    int maximum = Integer.MIN_VALUE;

    public void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.data + " ");
            inorder(node.right);
        }
    }

    // it is a DFS traversal
    // you are traveling one depth at a time
    // you are going from top to bottom
    // any algorithm that works in a fail fast manner
    // TC is O(n) and SC is O(height of tree)
    public void preorder(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preorder(node.left);
            preorder(node.right);
        }
    }

    // it is a DFS traversal
    // you are traveling one depth at a time
    // you are going from bottom to top
    // any algorithm that works in a fail fast manner
    // TC is O(n) and SC is O(height of tree)
    public void postorder(Node node) {
        if (node != null) {
            postorder(node.left);
            postorder(node.right);
            System.out.print(node.data + " ");
        }
    }

    public int getSumOfNodes(Node node) {
        if (node == null) {
            return 0;
        }
        return node.data + getSumOfNodes(node.left) + getSumOfNodes(node.right);
    }

    public int sumOfLeafNodes(Node head) {
        if (head == null) {
            return 0;
        }
        if (head.left == null && head.right == null) { // check leaf node
            return head.data;
        }
        return sumOfLeafNodes(head.left) + sumOfLeafNodes(head.right);
    }

    public int sumOfLeftLeafNodes(Node head, boolean leftleaf) {
        if (head == null) {
            return 0;
        }
        if (head.left == null && head.right == null && leftleaf) {
            return head.data;
        }
        return sumOfLeftLeafNodes(head.left, true) + sumOfLeftLeafNodes(head.right, false);
    }

    int currentLevel = 0;
    Node deepestLeftLeafNode = null;

    // in preorder manner
    public void deepestLeftLeafNode(Node head, int level, boolean leftleaf) {
        if (head == null) {
            return;
        }
        if (head.left == null && head.right == null && leftleaf && level > currentLevel) {
            currentLevel = level;
            deepestLeftLeafNode = head;
        }
        deepestLeftLeafNode(head.left, level + 1, true);
        deepestLeftLeafNode(head.right, level + 1, false);
    }

    public int differenceOfSumOfOddEvenLevels(Node head) {
        if (head == null) {
            return 0;
        }
        return head.data - differenceOfSumOfOddEvenLevels(head.left) - differenceOfSumOfOddEvenLevels(head.right);
    }

    // it follows postorder traversal
    public int getNumberOfNodes(Node node) {
        if (node == null) {
            return 0;
        }
        return getNumberOfNodes(node.left) + 1 + getNumberOfNodes(node.right);
    }

    // it follows postorder traversal
    // also called height of the root node
    public int getHeightOfTree(Node node) {
        if (node == null) {
            return 0;// you can return -1 if you consider edges to be height
        }
        return max(getHeightOfTree(node.left), getHeightOfTree(node.right)) + 1;
    }

    public int getNumberOfValidNodes(Node node, int ancestorValue) {
        if (node == null) {
            return 0;
        }
        if (node.data > ancestorValue)
            return 1 + getNumberOfValidNodes(node.left, Math.max(ancestorValue, node.data))
                    + getNumberOfValidNodes(node.right, Math.max(ancestorValue, node.data));
        else
            return getNumberOfValidNodes(node.left, Math.max(ancestorValue, node.data))
                    + getNumberOfValidNodes(node.right, Math.max(ancestorValue, node.data));
    }

    public int getIterativeHeightOfTree(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> q = new LinkedList<Node>();
        q.add(head);
        int height = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Node temp = q.remove();
                if (temp.left != null) {
                    q.add(temp.left);
                }
                if (temp.right != null) {
                    q.add(temp.right);
                }
            }
            height++;
        }
        return height;
    }

    private int max(int a, int b) {
        return a > b ? a : b;
    }

    public int findMaxValue(Node node) {
//		if(node==null) {
//			return Integer.MIN_VALUE;
//		}
//		int l = findMaxValue(node.left);
//		int r = findMaxValue(node.right);
//		return Math.max(Math.max(l, r),node.data);
        if (node == null) {
            return Integer.MIN_VALUE;
        }
        maximum = Math.max(maximum, node.data);
        int x = Math.max(findMaxValue(node.left), findMaxValue(node.right));
        return Math.max(maximum, x);
    }

    // TC to search anything in a height balanced BT is O(n)
    public int isHeightBalanced(Node head) {
        if (head == null) {
            return 0;
        }
        if (head.left == null && head.right == null) {
            return 1;
        }
        int lh = isHeightBalanced(head.left);
        int rh = isHeightBalanced(head.right);
        if (lh == -1 || rh == -1) {
            return -1;
        }
        if (Math.abs(lh - rh) > 1) {
            return -1;
        }
        return Math.max(lh, rh) + 1;
    }

    // it is depth first search
    // process the left subtree, right subtree, root
    // moving in bottom to top manner
    public ArrayList<Integer> iterativePostorderTraversal(Node A) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (A == null) return ans;
        Deque<Node> stack = new ArrayDeque<>();
        stack.addFirst(A);
        while (!stack.isEmpty()) {
            Node temp = stack.removeFirst();
            if (temp.left != null) {
                stack.addFirst(temp.left);
            }
            if (temp.right != null) {
                stack.addFirst(temp.right);
            }
            ans.add(0, temp.data); //inserting at the front of the list (ans.add(0, ...)) is (O(n)) per operation
        }
        return ans;
    }

    // it is depth first search
    // any algorithm that works in a fail fast manner
    // moving in top to bottom manner
    public ArrayList<Integer> iterativePreorderTraversal(Node A) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (A == null) return ans;
        Deque<Node> stack = new ArrayDeque<>();
        stack.addFirst(A);
        while (!stack.isEmpty()) {
            Node temp = stack.removeFirst();
            if (temp.right != null) {
                stack.addFirst(temp.right);
            }
            if (temp.left != null) {
                stack.addFirst(temp.left);
            }
            ans.add(temp.data);
        }
        return ans;
    }

    public ArrayList<Integer> iterativeInorderTraversal(Node A) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (A == null) return ans;
        Deque<Node> stack = new ArrayDeque<>();
        while (A != null) {
            stack.push(A);
            A = A.left;
        }
        while (!stack.isEmpty()) {
            Node temp = stack.pop();
            ans.add(temp.data);
            if (temp.right != null) {
                Node node = temp.right;
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }
        }
        return ans;
    }

    public ArrayList<Integer> iterativeInorderTraversal1(Node head) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (head == null) {
            return ans;
        }
        Node temp = head;// not necessary if we are returning an arraylist
        Stack<Node> stack = new Stack<Node>();
        while (temp != null || !stack.isEmpty()) {
            while (temp != null) {
                stack.push(temp);
                temp = temp.left;
            }
            temp = stack.pop();
            ans.add(temp.data);
            temp = temp.right;
        }
        return ans;
    }

    public int getMaximumWidth(Node head) {
        if (head == null) return 0;
        int maxWidth = 1;
        Deque<Node> q = new ArrayDeque<>();
        q.addLast(head);
        while (!q.isEmpty()) {
            int size = q.size();
            maxWidth = Math.max(maxWidth, size);
            while (size > 0) {
                Node temp = q.removeFirst();
                if (temp.left != null) {
                    q.add(temp.left);
                }
                if (temp.right != null) {
                    q.add(temp.right);
                }
                size--;
            }
        }
        return maxWidth;
    }

    public TreeNode constructBTLevelOrderTraversal(ArrayList<Integer> A) {
        int count = 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(new TreeNode(A.get(0)));
        TreeNode curr = null;
        for (int i = 1; i < A.size(); i++) {
            TreeNode temp = new TreeNode(A.get(i));
            if (count == 0) {
                curr = q.poll();
            }
            if (count == 0) {
                count++;
                curr.left = temp;
            } else {
                count = 0;
                curr.right = temp;
            }
            if (A.get(i) != -1) {
                q.add(temp);
            }
        }
        return q.peek();
    }

    public static Node constBT(int arr[], int n) {

        Node root = null;
        Node curr = null;
        int index = 0;
        Queue<Node> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {

            if (root == null) {
                root = new Node(arr[i]);
                q.add(root);
                curr = q.peek();
                index = 1;
            } else {
                if (arr[i] == -1) {

                    if (index == 1)
                        index = 2;
                    else {
                        q.remove();
                        curr = q.peek();
                        index = 1;

                    }
                } else if (index == 1) {
                    curr.left = new Node(arr[i]);
                    q.add(curr.left);
                    index = 2;

                } else if (index == 2) {
                    curr.right = new Node(arr[i]);
                    q.add(curr.right);
                    q.remove();
                    curr = q.peek();
                    index = 1;
                }

            }
        }
        return root;
    }

    public ArrayList<Integer> iterativeReverseLevelOrderTraversal(Node head) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        if (head == null) {
            return ans;
        }
        Queue<Node> q = new LinkedList<Node>();
        q.add(head);
        Stack<Node> stack = new Stack<Node>();
        while (!q.isEmpty()) {
            Node temp = q.remove();
            if (temp.right != null) {
                q.add(temp.right); // right node will become the second node while popping from stack
            }
            if (temp.left != null) {
                q.add(temp.left);
            }
            stack.push(temp);
        }
        while (!stack.isEmpty()) {
            ans.add(stack.pop().data);
        }
        return ans;
    }

    public int getLevelOfNode(Node head, int key, int level) {
        if (head == null) {
            return 0;
        }
        if (head.data == key) {
            return level;
        }
        int l = getLevelOfNode(head.left, key, level + 1);
        if (l != 0) {
            return l;
        }
        l = getLevelOfNode(head.right, key, level + 1);
        return l;
    }

    public ArrayList<Integer> printBetweenLevels(Node head, int lmin, int lmax) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        if (head == null) {
            return ans;
        }
        Queue<Node> q = new LinkedList<Node>();
        q.add(head);
        int level = 1;// at the root
        while (!q.isEmpty() && level <= lmax) {
            int size = q.size();
            while (size > 0) {
                Node temp = q.remove();
                if (level >= lmin && level <= lmax) {
                    ans.add(temp.data);
                }
                if (temp.left != null) {
                    q.add(temp.left);
                }
                if (temp.right != null) {
                    q.add(temp.right);
                }
                size--;
            }
            level++;
        }
        return ans;
    }

    public ArrayList<ArrayList<Integer>> printSpiralOrder(Node head) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
        if (head == null) {
            return ans;
        }
        Stack<Node> s1 = new Stack<Node>();
        Stack<Node> s2 = new Stack<Node>();
        s1.push(head);
        while (!s1.isEmpty() || !s2.isEmpty()) {
            ArrayList<Integer> aux = new ArrayList<>();
            while (!s1.isEmpty()) {
                Node temp = s1.pop();
                aux.add(temp.data);
                if (temp.left != null) {
                    s2.push(temp.left);
                }
                if (temp.right != null) {
                    s2.push(temp.right);
                }
            }
            if (!aux.isEmpty()) {
                ans.add(aux);
            }
            aux = new ArrayList<>();
            while (!s2.isEmpty()) {
                Node temp = s2.pop();
                aux.add(temp.data);
                if (temp.right != null) {
                    s1.push(temp.right);
                }
                if (temp.left != null) {
                    s1.push(temp.left);
                }
            }
            if (!aux.isEmpty()) {
                ans.add(aux);
            }
        }
        return ans;
    }

    public Node buildBinaryTreePart1(ArrayList<Integer> pre, ArrayList<Integer> in) {
        int size = in.size() - 1; // size of both the arrays will be the same
        return buildTreeUsingPreIn1(pre, in, 0, size, 0, size);
    }

    private Node buildTreeUsingPreIn1(ArrayList<Integer> pre, ArrayList<Integer> in, int sin, int ein, int spr,
                                      int epr) {
        if (sin > ein) {
            return null;
        }
        Node head = new Node(pre.get(spr));
        int idx = in.indexOf(head.data);
        int count = idx - sin; // (idx-1-sin)+1
        head.left = buildTreeUsingPreIn1(pre, in, sin, idx - 1, spr + 1, spr + count);
        head.right = buildTreeUsingPreIn1(pre, in, idx + 1, ein, spr + count + 1, epr);
        return head;
    }

    /*
     * we cannot build one tree from one kind of traversal using one single
     * traversal, you can build multiple trees the first element of a preorder
     * traversal will always be root
     */
    public Node buildBinaryTree1(ArrayList<Integer> pre, ArrayList<Integer> in) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < in.size(); i++) {
            int item = in.get(i);
            map.put(item, i);
        }
        int size = in.size() - 1; // size of both the arrays will be the same
        return buildTreeUsingPreIn(pre, in, map, 0, size, 0, size);
    }

    public Node buildTreeUsingPreIn(ArrayList<Integer> pre, ArrayList<Integer> in, HashMap<Integer, Integer> map,
                                    int sin, int ein, int spr, int epr) {
        if (sin > ein) {
            return null;
        }
        Node head = new Node(pre.get(spr)); // root will be the start of the preorder array
        // search for the head in the inorder array
        int idx = map.get(head.data); // index of the root in the inorder array
        int count = idx - sin; // count of elements in the left subtree
        head.left = buildTreeUsingPreIn(pre, in, map, sin, idx - 1, spr + 1, spr + count);
        head.right = buildTreeUsingPreIn(pre, in, map, idx + 1, ein, spr + count + 1, epr);
        return head;
    }

    public Node buildBinaryTree2(ArrayList<Integer> pos, ArrayList<Integer> in) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < in.size(); i++) {
            int item = in.get(i);
            map.put(item, i);
        }
        int size = in.size() - 1; // size of both the arrays will be the same
        return buildTreeUsingPosIn(pos, in, map, 0, size, 0, size);
    }

    public Node buildTreeUsingPosIn(ArrayList<Integer> pos, ArrayList<Integer> in, HashMap<Integer, Integer> map,
                                    int sin, int ein, int spo, int epo) {
        if (sin > ein) {
            return null;
        }
        Node head = new Node(pos.get(epo)); // last node is the root in the case of post order
        // search for the head in the inorder array
        int idx = map.get(head.data);
        int count = idx - sin; // count of elements in the left subtree
        head.left = buildTreeUsingPosIn(pos, in, map, sin, idx - 1, spo, spo + count - 1);
        head.right = buildTreeUsingPosIn(pos, in, map, idx + 1, ein, spo + count, epo - 1);
        return head;
    }

    public Collection<Integer> topView(TreeNode head) {
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        if (head == null) {
            return map.values();
        }
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(head);
        while (!q.isEmpty()) {
            TreeNode temp = q.remove();
            int h = temp.height;
            if (!map.containsKey(temp.height)) {
                map.put(temp.height, temp.data);
            }
            if (temp.left != null) {
                temp.left.height = h - 1;
                q.add(temp.left);
            }
            if (temp.right != null) {
                temp.right.height = h + 1;
                q.add(temp.right);
            }

        }
        return map.values();
    }

//	public Collection<Integer> topView1(Node head) {
//		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
//		if (head == null) {
//			return map.values();
//		}
//		Queue<TreeNode> q = new LinkedList<TreeNode>();
//		q.add(new TreeNode(head, 0));
//		while (!q.isEmpty()) {
//			Node temp = ((LinkedList<TreeNode>)q).remove().node;
//			int h = ((LinkedList<TreeNode>)q).remove().height;
//			if (!map.containsKey(h)) {
//				map.put(h, temp.data);
//			}
//			if (temp.left != null) {
//				q.add(new TreeNode(temp.left, h-1));
//			}
//			if (temp.right != null) {
//				q.add(new TreeNode(temp.right, h+1));
//			}
//
//		}
//		return map.values();
//	}

    public Collection<Integer> bottomView(TreeNode head) {
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        if (head == null) {
            return map.values();
        }
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(head);
        while (!q.isEmpty()) {
            TreeNode temp = q.remove();
            int h = temp.height; // get the parent height and assign the height to the children
            map.put(temp.height, temp.data);
            if (temp.left != null) {
                temp.left.height = h - 1;
                q.add(temp.left);
            }
            if (temp.right != null) {
                temp.right.height = h + 1;
                q.add(temp.right);
            }
        }
        return map.values();
    }

    public void getVerticalOrder(Node head, int h, Map<Integer, ArrayList<Integer>> map) {
        if (head != null) {
//			if (!map.containsKey(h)) {
//				ArrayList<Integer> v = new ArrayList<Integer>();
//				v.add(head.data);
//				map.put(h, v);
//			} else {
//				ArrayList<Integer> v = map.get(h);
//				v.add(head.data);
//				map.put(h, v);
//			}
            if (!map.containsKey(h)) {
                map.put(h, new ArrayList<>());
            }
            map.get(h).add(head.data);
            getVerticalOrder(head.left, h - 1, map);
            getVerticalOrder(head.right, h + 1, map); // preorder type of code
        }
    }

    public ArrayList<Integer> getVerticalOrder1(Node head) {
        ArrayList<Integer> ans = new ArrayList<>();
        Queue<Pair> q = new LinkedList<>();
        int l = 0;
        q.add(new Pair(head, 0));
        while (!q.isEmpty()) {
            Pair t = q.remove();
            Node temp = t.node;
            int dist = t.level;
//			Node temp = ((LinkedList<Pair>) q).peekFirst().node;
//			int dist = ((LinkedList<Pair>) q).peekFirst().level;

            if (temp.left != null) {
                q.add(new Pair(temp.left, dist - 1));
            }
            if (temp.right != null) {
                q.add(new Pair(temp.right, dist + 1));
            }
            if (dist == l) {
                ans.add(temp.data);
                l++;
            }
            q.remove(); // retrieves and removes the head of this queue
        }
        return ans;
    }

    public void getVerticalOrderSum(Node head, int h, TreeMap<Integer, Integer> map) {
        if (head != null) {
            if (!map.containsKey(h)) {
                map.put(h, head.data);
            } else {
                map.put(h, head.data + map.get(h));
            }
            getVerticalOrderSum(head.left, h - 1, map);
            getVerticalOrderSum(head.right, h + 1, map); // preorder typr of code
        }

//		if(head!=null) {
//			getVerticalOrderSum(head.left, h-1, map);
//			if(!map.containsKey(h)) {
//				map.put(h, head.data);
//			}else {
//				map.put(h, head.data+map.get(h));
//			}
//			getVerticalOrderSum(head.right, h+1, map); //preorder typr of code
//		}
    }

    // it will take O(n) time for searching
    // it will follow preorder traversal, fail fast approach
    // if the first operand is true, overall result will be true in case of OR
    // if the first operand is false, overall result will be false in case of AND
    public boolean recursiveSearch(Node head, int key) {
        if (head == null) {
            return false;
        }
        if (key == head.data) {
            return true;
        }
        return recursiveSearch(head.left, key) || recursiveSearch(head.right, key);
    }

    public boolean iterativeSearch(Node head, int key) {
        if (head == null) {
            return false;
        }
        Queue<Node> q = new LinkedList<Node>();
        q.add(head);
        while (!q.isEmpty()) {
            Node temp = q.remove();
            if (temp.data == key) {
                return true;
            }
            if (temp.left != null) {
                q.add(temp.left);
            }
            if (temp.right != null) {
                q.add(temp.right);
            }
        }
        return false;
    }

    public boolean checkRootToLeafSum(Node head, int val) {
        if (head == null) {
            return false;
        }
        if (head.left == null && head.right == null && val == head.data) {
            return true;
        }
        return checkRootToLeafSum(head.left, val - head.data) || checkRootToLeafSum(head.right, val - head.data);
        // once true is encountered the later part of the code is not run
    }

    int maxSum = Integer.MIN_VALUE;

    public void maxSumfromRootToLeaf(Node head, int sum) {
        if (head == null) {
            return;
        }
        if (head.left == null && head.right == null && (head.data + sum) > maxSum) {
            maxSum = head.data + sum;
            return;
        }
        maxSumfromRootToLeaf(head.left, sum + head.data);
        maxSumfromRootToLeaf(head.right, sum + head.data);
    }

    // the traversal is preorder
    public boolean checkIdenticalTrees(Node head1, Node head2) {
        if (head1 == null && head2 == null) {
            return true;
        }
        if (head1 == null || head2 == null) {
            return false;
        }
        return head1.data == head2.data && checkIdenticalTrees(head1.left, head2.left)
                && checkIdenticalTrees(head1.right, head2.right);
    }

    public boolean checkIdenticalStructure(Node head1, Node head2) {
        if (head1 == null && head2 == null) {
            return true;
        }
        if (head1 == null || head2 == null) {
            return false;
        }
        return checkIdenticalStructure(head1.left, head2.left) && checkIdenticalStructure(head1.right, head2.right);
    }

    public boolean checkMirrorTrees(Node head1, Node head2) {
        if (head1 == null && head2 == null) {
            return true;
        }
        if (head1 == null || head2 == null) {
            return false;
        }
        return head1.data == head2.data && checkMirrorTrees(head1.left, head2.right)
                && checkMirrorTrees(head1.right, head2.left);
    }

    public boolean checkMirrorStructure(Node head1, Node head2) {
        if (head1 == null && head2 == null) {
            return true;
        }
        if (head1 == null || head2 == null) {
            return false;
        }
        return checkMirrorStructure(head1.left, head2.right) && checkMirrorStructure(head1.right, head2.left);
    }

    public Node getParent(Node head, int val) { // in preorder manner
        if (head == null || head.data == val) {
            return null;
        }
        if ((head.left != null && head.left.data == val) || (head.right != null && head.right.data == val)) {
            return head;
        }
        Node l = getParent(head.left, val);
        if (l != null) {
            return l;
        }
        return getParent(head.right, val);
    }

    public Node getSibling(Node head, int val) {
        if (head == null || head.data == val) {
            return null;
        }
        if (head.left != null && head.left.data == val) {
            return head.right;
        }
        if (head.right != null && head.right.data == val) {
            return head.left;
        }
        Node l = getSibling(head.left, val);
        if (l != null) {
            return l;
        }
        return getSibling(head.right, val);
    }

    public ArrayList<Integer> getAncestors(Node head, int val) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        printAncestors(head, val, ans);
        return ans;
    }

    // the TC->O(n), as we need to traverse every node
    public boolean printAncestors(Node head, int val, ArrayList<Integer> ans) {
        if (head == null) {
            return false;
        }
        if (head.data == val || printAncestors(head.left, val, ans) || printAncestors(head.right, val, ans)) {
            ans.add(head.data);
            return true;
        }
        return false;
    }

    public Node lowestCommonAncestor1(Node head, int n1, int n2) {
        if (head == null || head.data == n1 || head.data == n2) { // if one of them is found, then it will return the
            // node
            return head;
        }
        Node leftlca = lowestCommonAncestor1(head.left, n1, n2);
        Node rightlca = lowestCommonAncestor1(head.right, n1, n2);
        if (leftlca != null && rightlca != null) {
            return head;
        } else if (leftlca != null) {
            return leftlca;
        } else {
            return rightlca;
        }
    }

    public Node lowestCommonAncestor2(Node head, int n1, int n2) {
        if (head == null || head.data == n1 || head.data == n2) {
            return head;
        }
        boolean checkn1 = recursiveSearch(head.left, n1);
        boolean checkn2 = recursiveSearch(head.left, n2);
        if (checkn1 && checkn2) {
            return lowestCommonAncestor2(head.left, n1, n2);
        } else if (checkn1 || checkn2) {
            return head;
        } else {
            return lowestCommonAncestor2(head.right, n1, n2);
        }
    }

    boolean checkn1 = false;
    boolean checkn2 = false;

    public Node lowestCommonAncestor(Node head, int n1, int n2) {
        Node lca = this.lowestCommonAncestorUtil(head, n1, n2);
        if (checkn1 && checkn2) {
            return lca;
        }
        return null;
    }

    public Node lowestCommonAncestorUtil(Node head, int n1, int n2) {
        if (head == null) {
            return null;
        }
        if (head.data == n1) {
            checkn1 = true;
            return head;
        }
        if (head.data == n2) {
            checkn2 = true;
            return head;
        }
        Node leftlca = lowestCommonAncestorUtil(head.left, n1, n2);
        Node rightlca = lowestCommonAncestorUtil(head.right, n1, n2);
        if (leftlca != null && rightlca != null) {
            return head;
        }
        return leftlca != null ? leftlca : rightlca;
    }

    public void printBoundaryLeft(ArrayList<Integer> ans, Node head) {
        if (head != null) {
            if (head.left != null) {
                ans.add(head.data);
                printBoundaryLeft(ans, head.left);
            } else if (head.right != null) {
                ans.add(head.data);
                printBoundaryLeft(ans, head.right);
            }
        }
    }

    public void printBoundaryRight(ArrayList<Integer> ans, Node head) {
        if (head != null) {
            if (head.right != null) {
                printBoundaryRight(ans, head.right);
                ans.add(head.data);
            } else if (head.left != null) {
                printBoundaryRight(ans, head.left);
                ans.add(head.data);
            }
        }
    }

    public void printLeaves(ArrayList<Integer> ans, Node head) {
        if (head != null) {
            printLeaves(ans, head.left);
            if (head.left == null && head.right == null) {
                ans.add(head.data);
            }
            printLeaves(ans, head.right);
        }
    }

    public ArrayList<Integer> printBoundary(Node head) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        ans.add(head.data);
        printBoundaryLeft(ans, head.left);
        printLeaves(ans, head);
        printBoundaryRight(ans, head.right);
        return ans;
    }
}
