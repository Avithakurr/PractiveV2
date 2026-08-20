public class FrequencyElements implements Comparable<FrequencyElements>{
    private int item;
    private int frequency;

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public FrequencyElements(int item, int frequency) {
        this.item = item;
        this.frequency = frequency;
    }

    @Override
    public int compareTo(FrequencyElements o) {
        if(this.frequency == o.frequency){
            return Integer.compare(this.item, o.item);
        }
        return  Integer.compare(o.frequency, this.frequency);
    }
}
