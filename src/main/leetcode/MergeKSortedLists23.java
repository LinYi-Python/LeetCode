public class MergeKSortedLists23 {
}



 //Definition for singly-linked list.
 class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) {
          this.val = val;
          this.next = next; }
  }

class MergeKSortedLists23A {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode ans = null;
        for(int i = 0; i < lists.length; i++){
            ans = mergeEach(ans, lists[i]);
        }
        return ans;
    }
    // hot to merge two list. just match top one by one.
    public ListNode mergeEach(ListNode a, ListNode b){
        if(a == null || b == null){
            return a == null? b: a;
        }
        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead;
        ListNode aPtr = a;
        ListNode bPtr = b;

        while(aPtr != null && bPtr != null){
            if(aPtr.val > bPtr.val){
                curr.next = bPtr;
                bPtr = bPtr.next;
            }else{
                curr.next = aPtr;
                aPtr = aPtr.next;
            }
            curr = curr.next;
        }
        curr.next = (aPtr == null? bPtr: aPtr);
        return dummyHead.next;
    }
}