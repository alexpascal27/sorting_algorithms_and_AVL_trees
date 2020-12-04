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

  private AVLTreeNode singleRotation(AVLTreeNode node, boolean clockwise)
  {
    if(node==null) return null;

    if(clockwise) return clockwiseSingleRotation(node);
    else return anticlockwiseSingleRotation(node);
  }

  private AVLTreeNode clockwiseSingleRotation(AVLTreeNode node)
  {
    // Checking if the node we are rotating around is the root (as we will need to update it later)
    boolean rotatingAboutOrigin = node.value.equals(root.value);
    System.out.println("clockwiseSingleRotation on:" + node.value);
    AVLTreeNode leftSubtreeNode = node.left;
    // No rotation happens, return node
    if(leftSubtreeNode == null) return node;

    node.left = leftSubtreeNode.right;
    node = updateHeight(node);
    leftSubtreeNode.right = node;


    if(rotatingAboutOrigin)
    {
      root = leftSubtreeNode;
    }
    System.out.println("After rotation on:" + node.value);

    // Updating heights
    leftSubtreeNode = updateHeight(leftSubtreeNode);

    return leftSubtreeNode;
  }

  private AVLTreeNode anticlockwiseSingleRotation(AVLTreeNode node)
  {
    // Checking if the node we are rotating around is the root (as we will need to update it later)
    boolean rotatingAboutOrigin = node.value.equals(root.value);
    System.out.println("anticlockwiseSingleRotation on:" + node.value);
    AVLTreeNode rightSubtreeNode = node.right;
    // No rotation, return node
    if(rightSubtreeNode == null) return node;

    node.right = rightSubtreeNode.left;
    // Make sure the changes to the node positions are reflected in the height values
    node = updateHeight(node);
    rightSubtreeNode.left = node;

    if(rotatingAboutOrigin)
    {
      root = rightSubtreeNode;
    }
    System.out.println("After rotation on:" + node.value);

    // Updating heights
    rightSubtreeNode = updateHeight(rightSubtreeNode);

    return rightSubtreeNode;
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

  private AVLTreeNode updateHeight(AVLTreeNode node)
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
    return node;
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

      print();

      previousNode = adjustTree(previousNode, balance);
    }
  }

  private AVLTreeNode adjustTree(AVLTreeNode node, int balance)
  {
    // If balanced don't need to perform further operations
    if(balance==0 || balance==1 || balance==-1) return node;

    if(balance > 1)
    {
      node = anticlockwiseSingleRotation(node);
    }
    else
    {
      node = clockwiseSingleRotation(node);
    }
    return node;
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
