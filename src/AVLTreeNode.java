public class AVLTreeNode
{
    AVLTreeNode left, right;
    String value;
    int height;

    public AVLTreeNode(String value)
    {
        this.value = value;
        height = 1;
    }

}
