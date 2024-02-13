package SkillCategory.listnode.dualpointers;

import SkillCategory.listnode.ListNode;


public class Medium86_PartitionList {
    public ListNode partition(ListNode head, int x) {
        ListNode minDummy = new ListNode();
        ListNode maxDummy = new ListNode();
        ListNode minCur = minDummy;
        ListNode maxCur = maxDummy;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val < x) {
                minCur.next = cur;
                minCur = minCur.next;
            } else {
                maxCur.next = cur;
                maxCur = maxCur.next;
            }
            ListNode next = cur.next;
            cur.next = null;
            cur = next;
        }
        minCur.next = maxDummy.next;
        return minDummy.next;
    }
}
