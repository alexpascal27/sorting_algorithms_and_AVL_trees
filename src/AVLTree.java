class AVLTree {
  AVLTreeNode root = null;
  // Note: you may define other variables here
  String[] initialValues;

  public AVLTree(String[] array) {
    // TODO implement this
    initialValues = array;
  }

  public void createTestTree() {
    // TODO implement this
    for (String initialValue : initialValues) {
      insert(initialValue);
    }
  }
  /*
  private AVLTreeNode findNode()
  {
    AVLTreeNode previousNode = null;
    boolean isCurrentRightOfPrevious = false;
    AVLTreeNode currentNode = root;
    while(currentNode != null)
    {
      int compareValue = node.value.compareTo(currentNode.value);
      // Found the node we are looking for
      if(compareValue==0) break;
        // Current node has a greater value than the node we are looking for
      else if(compareValue > 0)
      {
        // Go left
        previousNode = currentNode;
        isCurrentRightOfPrevious = false;
        currentNode = currentNode.left;
      }
      // Current node has a smaller value than the node we are looking for
      else
      {
        // go right subtree
        previousNode = currentNode;
        isCurrentRightOfPrevious = true;
        currentNode = currentNode.right;
      }
    }
  }

 */

  private void singleRotation(AVLTreeNode node, boolean clockwise)
  {
    if(node==null) return;

    if(clockwise) clockwiseSingleRotation(node);
    else anticlockwiseSingleRotation(node);
  }

  private void clockwiseSingleRotation(AVLTreeNode node)
  {
    AVLTreeNode leftSubtreeNode = node.left;
    if(leftSubtreeNode == null) return;
    AVLTreeNode rightLeftSubNode = leftSubtreeNode.right;
    leftSubtreeNode.right = node;
    AVLTreeNode previousNode = node.above;
    node.above = leftSubtreeNode;
    node.left = rightLeftSubNode;
    rightLeftSubNode.above = node;

    // If there is a node above
    if(previousNode!=null)
    {
      boolean isCurrentRightOfPrevious;
      if(previousNode.right != null)
      {
        isCurrentRightOfPrevious = previousNode.right.value.equals(node.value);
      }
      else
      {
        isCurrentRightOfPrevious = !previousNode.left.value.equals(node.value);
      }

      if(isCurrentRightOfPrevious)
      {
        // New root
        previousNode.right = leftSubtreeNode;
      }
      else
      {
        // New root
        previousNode.left = leftSubtreeNode;
      }
      leftSubtreeNode.above = previousNode;
    }
    else
    {
      root = leftSubtreeNode;
    }
  }

  private void anticlockwiseSingleRotation(AVLTreeNode node)
  {
    boolean rotatingAboutOrigin = node.value.equals(root.value);
    System.out.println("anticlockwiseSingleRotation on:" + node.value);
    AVLTreeNode rightSubtreeNode = node.right;
    if(rightSubtreeNode == null) return;
    AVLTreeNode leftRightSubNode = rightSubtreeNode.left;
    rightSubtreeNode.left = node;
    AVLTreeNode previousNode = node.above;
    node.above = rightSubtreeNode;
    node.right = leftRightSubNode;
    node.left = null;
    rightSubtreeNode.above = previousNode;
    // If there is a node above
    if(previousNode!=null)
    {
      System.out.println("Previous Node Value:" + previousNode.value);
      boolean isCurrentRightOfPrevious;
      if (previousNode.right != null) {
        isCurrentRightOfPrevious = previousNode.right.value.equals(node.value);
      } else {
        isCurrentRightOfPrevious = !previousNode.left.value.equals(node.value);
      }

      if (isCurrentRightOfPrevious) {
        // New root
        previousNode.right = rightSubtreeNode;
      } else {
        // New root
        previousNode.left = rightSubtreeNode;
      }
    }
    if(rotatingAboutOrigin)
    {
      root = rightSubtreeNode;
    }
    System.out.println("After rotation on:" + node.value);
    node.updateBalance();
    print();
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

  private void updateTreeFromNode(AVLTreeNode node)
  {
    // If node is null
    if(node == null) throw new NullPointerException();

    while(node != null)
    {
      node.updateBalance();
      //checkBalanceAndRotate(node);
      node = node.above;
    }
  }

  private void checkBalanceAndRotate(AVLTreeNode node)
  {
    System.out.println("Checking balance for node with value:" + node.value +" it has balance:" + node.balance);
    // Right imbalance then anticlockwise rotation
    if(node.balance > 1)
    {
      singleRotation(node, false);
    }
    // Left imbalance then clockwise rotation
    if(node.balance < -1)
    {
      singleRotation(node, true);
    }
  }

  public void insert(String e) {
    // TODO implement this
    if(root == null)
    {
      root = new AVLTreeNode(e);
      root.above = null;
    }
    else
    {
      // Find a suitable place to add the new node
      AVLTreeNode node = root;
      AVLTreeNode previousNode = null;
      while(node!=null)
      {
        boolean isNewNodeFirst = e.compareTo(node.value) < 0;
        previousNode = node;
        if(isNewNodeFirst)
        {
          node = node.left;
        }
        else
        {
          node = node.right;
        }
      }

      boolean isNewNodeFirst = e.compareTo(previousNode.value) < 0;
      AVLTreeNode newNode = new AVLTreeNode(e);
      newNode.above = previousNode;
      if(isNewNodeFirst)
      {
        previousNode.left = newNode;

      }
      else
      {
        previousNode.right = newNode;
      }
      updateTreeFromNode(previousNode);
    }
  }
}
