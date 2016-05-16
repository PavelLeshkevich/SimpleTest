package diffprocessor;

/**
 * Created by VavilauA on 6/19/2015.
 */
public class Processor {
    long limit;
    public Processor(long limit) {
        // TODO: initialize.
        this.limit = limit;
    }

    public void doProcess(SortedLimitedList<Double> mustBeEqualTo, SortedLimitedList<Double> expectedOutput) {

        while (true) {
            int cnt = 0;
            SortedLimitedList.Entry<Double> entryOne = mustBeEqualTo.getFirst();
            SortedLimitedList.Entry<Double> entryTwo = expectedOutput.getFirst();
            while (entryOne != null && entryTwo != null) {
                if (entryOne.getValue() < entryTwo.getValue()) {
                    SortedLimitedList.Entry<Double> tmp = entryOne;
                    entryOne = entryOne.getNext();
                    mustBeEqualTo.remove(tmp);
                    cnt++;
                } else if (entryOne.getValue().equals(entryTwo.getValue())) {
                    entryOne = entryOne.getNext();
                    entryTwo = entryTwo.getNext();
                } else {
                    if (mustBeEqualTo.getCount() < limit) {
                        mustBeEqualTo.addBefore(entryOne, entryTwo.getValue());
                        entryTwo = entryTwo.getNext();
                        cnt++;
                    } else {
                        entryTwo = entryTwo.getNext();
                    }
                }

            }
            if (entryOne != null && entryTwo == null) {
                while(entryOne != null) {
                    SortedLimitedList.Entry<Double> tmp = entryOne;
                    entryOne = entryOne.getNext();
                    mustBeEqualTo.remove(tmp);
                    cnt++;
                }
            }
            if(entryOne == null && entryTwo != null) {
                while (entryTwo != null && mustBeEqualTo.getCount() < limit) {
                    mustBeEqualTo.addLast(entryTwo.getValue());
                    entryTwo = entryTwo.getNext();
                    cnt++;
                }
            }
            if(cnt == 0) {
                break;
            }
        }
    }

        // TODO: make "mustBeEqualTo" list equal to "expectedOutput".
        // 0. Processor will be created once and then will be used billion times.
        // 1. Use methods: AddFirst, AddLast, AddBefore, AddAfter, Remove to modify list.
        // 2. Do not change expectedOutput list.
        // 3. At any time number of elements in list could not exceed the "Limit".
        // 4. "Limit" will be passed into Processor's constructor. All "mustBeEqualTo" and "expectedOutput" lists will have the same "Limit" value.
        // 5. At any time list elements must be in non-descending order.
        // 6. Implementation must perform minimal possible number of actions (AddFirst, AddLast, AddBefore, AddAfter, Remove).
        // 7. Implementation must be fast and do not allocate excess memory.
}
