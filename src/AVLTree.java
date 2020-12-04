class AVLTree {
  AVLTreeNode root;
  // Note: you may define other variables here
  String[] initialValues;

  public AVLTree(String[] array) {
    // TODO implement this
    root = null;
    initialValues = array;
  }

  public void createTestTree() {
    // TODO implement this
    for (String initialValue : initialValues) {
      insert(initialValue);
    }
  }

  private void clockwiseSingleRotation(AVLTreeNode node)
  {
    // Checking if the node we are rotating around is the root (as we will need to update it later)
    boolean rotatingAboutOrigin = node.value.equals(root.value);
    System.out.println("clockwiseSingleRotation on:" + node.value);
    AVLTreeNode leftSubtreeNode = node.left;
    // No rotation happens, return node
    if(leftSubtreeNode == null) return;

    node.left = leftSubtreeNode.right;
    updateHeight(node);
    node.height--;
    leftSubtreeNode.right = node;


    if(rotatingAboutOrigin)
    {
      root = leftSubtreeNode;
    }
    System.out.println("After rotation on:" + node.value);

    // Updating heights
    updateHeight(leftSubtreeNode);
    leftSubtreeNode.height++;
  }

  private void anticlockwiseSingleRotation(AVLTreeNode node)
  {
    // Checking if the node we are rotating around is the root (as we will need to update it later)
    boolean rotatingAboutOrigin = node.value.equals(root.value);
    System.out.println("anticlockwiseSingleRotation on:" + node.value);
    AVLTreeNode rightSubtreeNode = node.right;
    // No rotation, return node
    if(rightSubtreeNode == null) return;

    node.right = rightSubtreeNode.left;
    // Make sure the changes to the node positions are reflected in the height values
    updateHeight(node);
    node.height--;
    rightSubtreeNode.left = node;

    if(rotatingAboutOrigin)
    {
      root = rightSubtreeNode;
    }
    System.out.println("After rotation on:" + node.value);

    // Updating heights
    updateHeight(rightSubtreeNode);
    rightSubtreeNode.height++;
  }

  public void print() {
    // TODO implement this
    if(root==null) return;

    // Pre-order traversal
    printSubtree(root, 0);
  }

  private void printSubtree(AVLTreeNode node, int indentCount)
  {
    if(node==null) return;
    // Print the current value
    for(int i = 0; i < indentCount; i++)
    {
      System.out.print("\t");
    }
    System.out.println(node.value);

    // Print left subtree
    printSubtree(node.left, indentCount+1);
    // Print right subtree
    printSubtree(node.right, indentCount+1);
  }

  public boolean inTree(String e) { 
    // TODO implement this
    AVLTreeNode node = root;
    while(node != null)
    {
      int compareValue = e.compareTo(node.value);
      // Found the node we are looking for
      if (e.equals(node.value)) return true;
        // Current node has a greater value than the node we are looking for
      else if (compareValue > 0)
      {
        // Go Right
        node = node.right;
      }
      // Current node has a smaller value than the node we are looking for
      else
      {
        // Go Left
        node = node.left;
      }
    }
    return false;
  }

  private void updateHeight(AVLTreeNode node)
  {
    // If node is null
    if(node == null) throw new NullPointerException();

    int leftSubtreeHeight,rightSubtreeHeight;

    // Try to get the height of left subtree
    try
    {
      leftSubtreeHeight = node.left.height;
    }
    catch(NullPointerException e)
    {
      leftSubtreeHeight = 1;
    }

    // Try to get the height of right subtree
    try
    {
      rightSubtreeHeight = node.right.height;
    }
    catch(NullPointerException e)
    {
      rightSubtreeHeight = 1;
    }

    // Update the height- its the max of either the left or right subtree
    node.height = 1 + Math.max(leftSubtreeHeight, rightSubtreeHeight);
  }

  public void insert(String e)
  {
    // If no root add one
    if(root == null)
    {
      root = new AVLTreeNode(e);
    }
    // If root already exists
    else
    {
      // Find a suitable place to add the new node
      AVLTreeNode node = root;
      AVLTreeNode previousNode = null;
      AVLTreeNode currentNode = null;
      while(node!=null)
      {
        boolean isNewNodeFirst = e.compareTo(node.value) < 0;
        previousNode = currentNode;
        currentNode = node;
        if(isNewNodeFirst)
        {
          node = node.left;
        }
        else
        {
          node = node.right;
        }
      }

      boolean isNewNodeFirst = e.compareTo(currentNode.value) < 0;
      AVLTreeNode newNode = new AVLTreeNode(e);

      // Before we insert we need to check if the insertion will cause an imbalance
      int balance = getBalance(previousNode);

      // What is the balance going to be after insertion
      if(isNewNodeFirst)
      {
        balance--;
        currentNode.left = newNode;
      }
      else
      {
        balance++;
        currentNode.right = newNode;
      }

      /*
        THIS IS THE CODE THAT SHOULD ENABLE QUESTION 6 PART C
        IT, HOWEVER DOES NOT WORK
        HERE ARE MY REASONS WHY:
          * the pointers are not correct, after a rotation around a non-root node, that rotation isn't made to the tree if we traverse the root
          * the heights are not updated appropriately
       */
      //adjustTree(previousNode, balance);
    }
  }

  private void adjustTree(AVLTreeNode node, int balance)
  {
    // If balanced don't need to perform further operations
    if(balance==0 || balance==1 || balance==-1) return;

    if(balance > 1)
    {
      anticlockwiseSingleRotation(node);
    }
    else
    {
      clockwiseSingleRotation(node);
    }
  }

  private int getBalance(AVLTreeNode node)
  {
    int leftSubtreeHeight;
    int rightSubtreeHeight;

    try
    {
      leftSubtreeHeight = node.left.height;
    }
    catch(NullPointerException e)
    {
      leftSubtreeHeight = 0;
    }

    try
    {
      rightSubtreeHeight = node.right.height;
    }
    catch(NullPointerException e)
    {
      rightSubtreeHeight = 0;
    }
    return rightSubtreeHeight - leftSubtreeHeight;
  }


}
