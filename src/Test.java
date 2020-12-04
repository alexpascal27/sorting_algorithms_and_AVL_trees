public class Test
{
    public static void main(String[] args) {
        Contact alexz = new Contact("Alex", "Zamurca");
        Contact jivrajd = new Contact("Jivraj", "Zamurca");
        Contact jivrajz = new Contact("Jivraj", "Dhamija");
        Contact rohand = new Contact("Rohan", "Dhamija");
        Contact rohanm = new Contact("Rohan", "Mangat");
        Contact nirom = new Contact("Nirosan", "Mangat");
        Contact nirop = new Contact("Nirosan", "Pragash");
        Contact alexp = new Contact("Alex", "Pragash");
        Contact[] contactArray = new Contact[]{alexz, jivrajd, jivrajz, rohand, nirom, alexp, rohanm, nirop};

        String[] treeArray = new String[]{"1", "2", "3" , "4", "5", "6", "7"};

        //testContact(contactArray);
        //testSelectionSort(contactArray);
        //testInsertionSort(contactArray);
        //testQuickSort(contactArray);
        //testMergeSort(contactArray);
        testAVLTree(treeArray);

    }

    private static void testAVLTree(String[] array)
    {
        AVLTree tree = new AVLTree(array);
        tree.createTestTree();
        tree.print();


        System.out.println(tree.inTree("1"));
        System.out.println(tree.inTree("7"));
        System.out.println(tree.inTree("3"));
        System.out.println(tree.inTree("5"));
        System.out.println(tree.inTree("8"));
        System.out.println(tree.inTree("2"));

    }

    private static void testContact(Contact[] contactArray)
    {

        for(int i = 0; i < contactArray.length; i++)
        {
            for(int j = 0; j < contactArray.length; j++)
            {
                if(i!=j)
                {
                    int comparedTo = contactArray[i].compareTo(contactArray[j]);
                    System.out.println("Comparing: \"" + contactArray[i].firstName + " " + contactArray[i].lastName + "\" and \"" + contactArray[j].firstName + " " + contactArray[j].lastName + "\" with result: " + comparedTo);
                }
            }
        }
    }

    private static void testSelectionSort(Contact[] array)
    {
        Sorter.selectionSort(array);
        Sorter.print(array);
    }

    private static void testInsertionSort(Contact[] array)
    {
        Sorter.insertionSort(array);
        Sorter.print(array);
    }

    private static void testQuickSort(Contact[] array)
    {
        Sorter.quickSort(array);
        Sorter.print(array);
    }

    private static void testMergeSort(Contact[] array)
    {
        Sorter.mergeSort(array);
        Sorter.print(array);
    }
}
