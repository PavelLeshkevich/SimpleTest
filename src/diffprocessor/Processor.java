package diffprocessor;

import sun.plugin.dom.exception.InvalidStateException;

/**
 * Created by VavilauA on 6/19/2015.
 */
public class Processor {
    long limit;
    public Processor(long limit) {
        this.limit = limit;
    }

    public void doProcess(SortedLimitedList<Double> mustBeEqualTo, SortedLimitedList<Double> expectedOutput) {
        try {
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
                    while (entryOne != null) {
                        SortedLimitedList.Entry<Double> tmp = entryOne;
                        entryOne = entryOne.getNext();
                        mustBeEqualTo.remove(tmp);
                        cnt++;
                    }
                }
                if (entryOne == null && entryTwo != null) {
                    while (entryTwo != null && mustBeEqualTo.getCount() < limit) {
                        mustBeEqualTo.addLast(entryTwo.getValue());
                        entryTwo = entryTwo.getNext();
                        cnt++;
                    }
                }
                if (cnt == 0) {
                    break;
                }
            }
        } catch (InvalidStateException e) {
            e.printStackTrace();
        }
    }
}
