package com.myself.lession;

// Definition for singly-linked list.
 public class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     ListNode(int ...val){
         ListNode listNode = new ListNode();
         ListNode least = listNode;
         for (int i = 0; i < val.length; i++) {
             least = new ListNode(val[i],new ListNode());
             least = listNode.next;
         }
     }
 }

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result =  new ListNode();
        ListNode c = result;
        int p = 0;
        //
        while(!(l1==null &&l2==null&&p==0)){
            int s =( l1==null?0:l1.val)+(l2==null?0:l2.val)+p;
            int q = s/10;
            p = s>=10?1:0;
            c = new ListNode(q);
            c = c.next;
            l1 = l1==null?null:l1.next;
            l2 = l2==null?null:l2.next;
        }
        return result;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1, 2, 3, 4);
        System.out.println(listNode);
    }
}