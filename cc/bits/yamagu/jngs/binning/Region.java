package cc.bits.yamagu.jngs.binning;

public class Region implements IRegion, Comparable<IRegion>{
    int start;
    int end;
    public Region(int start, int end){
        this.start = start;
        this.end = end;
    }
    public int getStart(){
        return start;
    }
    public int getEnd(){
        return end;
    }
    public int compareTo(IRegion x){
        if(x.getStart() != this.start){
            return this.start - x.getStart();
        }
        if(x.getEnd() != this.end){
            return this.end - x.getEnd();
        }
        return 0;
    }
}
