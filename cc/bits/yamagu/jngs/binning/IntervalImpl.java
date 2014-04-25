package cc.bits.yamagu.jngs.binning;

public class IntervalImpl implements Interval{
    String chr;
    int start;
    int end;
    int bin;
    public IntervalImpl(){
    }
    public IntervalImpl(String chr, int start, int end){
        this.chr = chr;
        this.start = start;
        this.end = end;
        this.bin = GenomeBin.getBin(start, end).lastEntry().getValue();
    }
    public int getBin(){
        return this.bin;
    }
    public String getChromosome(){
        return chr;
    }
    public int getStart(){
        return start;
    }
    public int getEnd(){
        return end;
    }
    public static Interval parse(String data){
        String[] buf = data.split(":");
        IntervalImpl i = new IntervalImpl();
        i.chr = buf[0];
        String[] posbuf = buf[1].split("-");
        if(posbuf[0].indexOf("..") > 0){
            posbuf = posbuf[0].split("..");
        }
        i.start = Integer.parseInt(posbuf[0]);
        i.end = Integer.parseInt(posbuf[1]);
        return i;
    }
    public static int[] getIntersection(Interval i1, Interval i2){
        int[] buf = new int[2];
        if(i1.getChromosome().equals(i2.getChromosome())){
            if(i1.getStart() > i2.getStart()){
                Interval tmp = i2;
                i2 = i1;
                i1 = tmp;
            }
            if(i2.getStart() <= i1.getEnd()){
                int region_start = i2.getStart();
                int region_end = i1.getEnd();
                if(i1.getEnd() > i2.getEnd()){
                    region_end = i2.getEnd();
                }
                buf[0] = region_start;
                buf[1] = region_end;
            }
        }
        return buf;
    }
    public static boolean hasIntersection(Interval i1, Interval i2){
        if(i1.getChromosome().equals(i2.getChromosome())){
            if(i1.getStart() > i2.getStart()){
                Interval tmp = i2;
                i2 = i1;
                i1 = tmp;
            }
            if(i2.getStart() <= i1.getEnd()){
                return true;
            }
        }
        return false;
    }
}
