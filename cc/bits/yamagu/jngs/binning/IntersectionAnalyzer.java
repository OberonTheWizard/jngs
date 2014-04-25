package cc.bits.yamagu.jngs.binning;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.TreeMap;

public class IntersectionAnalyzer<T1 extends Interval, T2 extends Interval> {
    ArrayList<T1> list;
    HashMap<Integer, ArrayList<T2>> map;
    IntersectionHandler<T1, T2> handler;

    public void analyze(ArrayList<T1> list, HashMap<Integer, ArrayList<T2>> map){
        this.list = list;
        this.map = map;
        GenomeBin binning = new GenomeBin();
        for(T1 query: list){
            TreeMap<Integer, Integer> start = binning.getBin(query.getStart(), query.getStart());
            TreeMap<Integer, Integer> end = binning.getBin(query.getEnd(), query.getEnd());
            boolean found = false;
            if(start.size() != end.size()){
                // System.out.println("error " + start.size() + " " + end.size());
                throw new RuntimeException("Bin level mismatch: " + start.size() +  " " + end.size());
            }
            // System.out.println("--");
             for(int i = 0; i<start.size(); i++){
                 // System.out.println("level " + i + ": " + start.get(i) + " .. " + end.get(i));
                 for(int bin = start.get(i); bin <= end.get(i); bin++){
                     if(map.containsKey(bin)){
                         for(T2 hit: map.get(bin)){
                             if(IntervalImpl.hasIntersection(query, hit)){
                                 int[] region = IntervalImpl.getIntersection(query, hit);
                                 handleIntersection(query, hit, region);
                                 found = true;
                             }
                         }
                     }
                 }
             }
             if(!found){
                 handleNoIntersection(query);
             }
        }
        // System.out.println("ok");
    }
    private void handleIntersection(T1 interval1, T2 interval2, int[] region){
        this.handler.handleIntersection(interval1, interval2, region);
    }
    private void handleNoIntersection(T1 interval){
        this.handler.handleNoIntersection(interval);
    }
    public void setHandler(IntersectionHandler<T1, T2> handler){
        this.handler = handler;
    }
}
