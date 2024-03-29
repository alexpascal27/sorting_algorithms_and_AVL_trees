public class Sorter
{

    public static void selectionSort(Contact[] contacts) {
        cursiveSelectionSort(contacts);
    }

    public static void insertionSort(Contact[] contacts) {
        cursiveInsertionSort(contacts);
    }

    public static void quickSort(Contact[] contacts) {
        quickSort(contacts, 0, contacts.length-1);
    }

    public static void mergeSort(Contact[] contacts)
    {
        recursiveMergeSort(contacts, 0, contacts.length-1);
    }

    public static void cursiveSelectionSort(Contact[] contacts) {
        // TODO Q2
        int n = contacts.length;
        for(int i = 0; i < n; i++)
        {
            Contact currentLowestValue = contacts[i];
            int currentLowestValueIndex = i;
            for(int j = i; j < n; j++)
            {
                Contact newContact = contacts[j];
                // If we found a 'smaller' contact
                if(newContact.compareTo(currentLowestValue) < 0)
                {
                    currentLowestValue = newContact;
                    currentLowestValueIndex = j;
                }
            }
            // Swap
            swapContacts(contacts, i, currentLowestValueIndex);
        }
    }

    public static void cursiveInsertionSort(Contact[] contacts) {
        // TODO Q3
        int n  = contacts.length;
        for(int i = 0; i < n-1; i++)
        {
            // Check if the current element is less than the next
            if(contacts[i].compareTo(contacts[i+1]) > 0)
            {
                swapContacts(contacts, i, i+1);
                for(int j = i; j > 0; j--)
                {
                    // If the previous contact is 'greater' than the current
                    if(contacts[j].compareTo(contacts[j-1]) < 0)
                    {
                        swapContacts(contacts, j, j-1);
                    }
                    else
                    {
                        break;
                    }
                }
            }
        }
    }

    public static void quickSort( Contact[] contacts, int low, int high) {
        // TODO Q4
        if(high > low)
        {
            int pivot = partition(contacts, low, high);
            quickSort(contacts, low, pivot-1);
            quickSort(contacts, pivot + 1, high);
        }
    }

    private static int partition(Contact[] contacts, int low, int high)
    {
        int pivotIndex = low;
        Contact pivot = contacts[pivotIndex];
        do {
            while( low <= high && !(contacts[low].compareTo(pivot) > 0)) low++;
            while(contacts[high].compareTo(pivot) > 0) high--;
            if(low<high) swapContacts(contacts, low, high);
        }while(low < high);

        swapContacts(contacts, pivotIndex, high);
        return high;
    }

    public static void recursiveMergeSort(Contact[] contacts, int low, int high) {

        // If array has one item then return the item
        if(low>=high) return;
        // Split array
        int middle_index = low + (high-low)/2;
        // Call merge sort on the left half
        recursiveMergeSort(contacts, low, middle_index);
        // Call merge sort on the right half
        recursiveMergeSort(contacts, middle_index+1, high);
        // Merge halves together
        merge(contacts, low, middle_index, high);
    }

    private static void merge(Contact[] contacts, int low, int mid, int high)
    {
        // low, middle, middle+1, high
        int leftLength = mid - low + 1;
        int rightLow = mid+1;
        int rightLength = high - rightLow + 1;

        // Create a temporary left and right array
        Contact[] leftArray = new Contact[leftLength];
        Contact[] rightArray = new Contact[rightLength];

        // Initialise the left and right arrays
        System.arraycopy(contacts, low, leftArray, 0, leftLength);
        System.arraycopy(contacts, rightLow, rightArray, 0, rightLength);

        int leftCounter = 0; int rightCounter = 0; int counter = low;
        while(leftCounter < leftLength && rightCounter < rightLength)
        {
            if(leftArray[leftCounter].compareTo(rightArray[rightCounter]) <= 0)
            {
                contacts[counter] = leftArray[leftCounter];
                leftCounter++;
            }
            else
            {
                contacts[counter] = rightArray[rightCounter];
                rightCounter++;
            }
            counter++;
        }
        // Exit condition is either one of the two while loops
        // i.e. make sure that no elements are left unadded in either the right or left array
        while (leftCounter < leftLength)
        {
            contacts[counter] = leftArray[leftCounter];
            leftCounter++;
            counter++;
        }

        while (rightCounter < rightLength)
        {
            contacts[counter] = rightArray[rightCounter];
            rightCounter++;
            counter++;
        }
    }

    private static void swapContacts(Contact[] contacts, int firstElementIndex, int secondElementIndex)
    {
        // Store the first element temporarily so we can assign it to the second element value
        Contact tempContact = contacts[firstElementIndex];
        contacts[firstElementIndex] = contacts[secondElementIndex];
        contacts[secondElementIndex] = tempContact;
    }

    public static void print(Contact[] contacts)
    {
        System.out.print("[ ");
        for(int i = 0; i < contacts.length; i++)
        {
            System.out.print(contacts[i].firstName + " " + contacts[i].lastName);
            if(i!= contacts.length-1) System.out.print(",\t");
        }
        System.out.println(" ]");
    }
}
