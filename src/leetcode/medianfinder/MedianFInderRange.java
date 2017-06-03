

package leetcode.medianfinder;

public class MedianFInderRange {

    int size = 0;
    int sum = 0;
    int[] buckets = new int[1000];

    public static void main(String[] args) {
        MedianFInderRange mr = new MedianFInderRange();
        mr.addNum(2);
        mr.addNum(3);
        mr.addNum(5);
        mr.addNum(6);
        System.out.println(mr.getMean());
        System.out.println(mr.getMedian());
    }

    public void addNum(int n) {
        buckets[n]++;
        size++;
        sum += n;
    }

    public double getMean() {
        return (double) sum / size;

    }

    public double getMedian() {

        int total = 0;
        int i = 0;
        for (; i < buckets.length; i++) {
            total += buckets[i];
            if (size % 2 == 0) {
                if (total > size / 2) {
                    return i;
                } else if (total == size / 2) {
                    int curr = i;

                    while (buckets[++i] == 0)
                        ;
                    return (double) (i + curr) / 2;
                }
            } else {
                if (total > size / 2) {
                    return i;
                }

            }

        }
        return -1;
    }

}
