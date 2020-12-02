public class AVLTreeNode
{
    AVLTreeNode above, left, right;
    String value;
    // 0 means same height, -x means left has x higher height,  +x means right has x higher height
    int balance = 0;

    public AVLTreeNode(String value) {
        this.value = value;
    }

    public void updateBalance()
    {
        balance = 0;
        if(left!=null)
        {
            balance += (1 + left.balance);
        }
        if(right!=null)
        {
            balance += (1 + right.balance);
        }
    }
}
