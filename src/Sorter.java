public class Sorter
{
    public static Contact[] contacts;

    public static Contact[] selectionSort(Contact[] contacts) {
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
            contacts = swapContacts(contacts, i, currentLowestValueIndex);
        }
        return contacts;
    }

    public static Contact[] insertionSort(Contact[] contacts) {
        // TODO Q3
        int n  = contacts.length;
        for(int i = 0; i < n-1; i++)
        {
            // Check if the current element is less than the next
            if(contacts[i].compareTo(contacts[i+1]) > 0)
            {
                contacts = swapContacts(contacts, i, i+1);
                for(int j = i; j > 0; j--)
                {
                    // If the previous contact is 'greater' than the current
                    if(contacts[j].compareTo(contacts[j-1]) < 0)
                    {
                        contacts = swapContacts(contacts, j, j-1);
                    }
                    else
                    {
                        break;
                    }
                }
            }
        }
        return contacts;
    }

    public static void quickSort( int low, int high) {
        // TODO Q4
        if(high > low)
        {
            int pivot = partition(low, high);
            quickSort(low, pivot-1);
            quickSort(pivot + 1, high);
        }
    }

    private static int partition(int low, int high)
    {
        int pivotIndex = low;
        Contact pivot = contacts[pivotIndex];
        do {
            while( low <= high && !(contacts[low].compareTo(pivot) > 0)) low++;
            while(contacts[high].compareTo(pivot) > 0) high--;
            if(low<high) contacts = swapContacts(contacts, low, high);
        }while(low < high);

        contacts = swapContacts(contacts, pivotIndex, high);
        return high;
    }

    public static Contact[] mergeSort(Contact[] contacts, int low, int high) {
        // TODO Q5
        // If array has one item then return the item
        if(low==high) return new Contact[]{contacts[low]};
        // Split array
        int middle_index = low + (high-low)/2;
        // Call merge sort on the left half
        Contact[] leftHalf = mergeSort(contacts, low, middle_index);
        // Call merge sort on the right half
        Contact[] rightHalf = mergeSort(contacts, middle_index+1, high);
        // Merge halves together
        contacts = merge(leftHalf, low, middle_index, rightHalf, middle_index+1, high);

        return contacts;
    }

    private static Contact[] merge(Contact[] leftHalf, int leftLow, int leftHigh, Contact[] rightHalf, int rightLow, int rightHigh)
    {
        int leftLength = leftHigh - leftLow + 1;
        int rightLength = rightHigh - rightLow + 1;
        Contact[] mergedArray = new Contact[leftLength + rightLength];
        int leftCounter = 0; int rightCounter = 0; int counter = 0;
        while(leftCounter < leftLength && rightCounter < rightLength)
        {
            if(leftHalf[leftCounter].compareTo(rightHalf[rightCounter]) <= 0)
            {
                mergedArray[counter] = leftHalf[leftCounter];
                leftCounter++;
            }
            else
            {
                mergedArray[counter] = rightHalf[rightCounter];
                rightCounter++;
            }
            counter++;
        }
        // Exit condition is either one of the two while loops
        // i.e. make sure that no elements are left unadded in either the right or left array
        while (leftCounter < leftLength)
        {
            mergedArray[counter] = leftHalf[leftCounter];
            leftCounter++;
            counter++;
        }

        while (rightCounter < rightLength)
        {
            mergedArray[counter] = rightHalf[rightCounter];
            rightCounter++;
            counter++;
        }

        return mergedArray;
    }

    private static Contact[] swapContacts(Contact[] contacts, int firstElementIndex, int secondElementIndex)
    {
        // Store the first element temporarily so we can assign it to the second element value
        Contact tempContact = contacts[firstElementIndex];
        contacts[firstElementIndex] = contacts[secondElementIndex];
        contacts[secondElementIndex] = tempContact;
        return contacts;
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
