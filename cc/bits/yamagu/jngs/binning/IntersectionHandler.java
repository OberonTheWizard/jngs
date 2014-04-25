package cc.bits.yamagu.jngs.binning;

public interface IntersectionHandler<T1 extends Interval, T2 extends Interval> {
    public void handleIntersection(T1 data1, T2 data2, int[] region);
    public void handleNoIntersection(T1 data1);
}
