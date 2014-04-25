package cc.bits.yamagu.jngs.binning;

import java.util.TreeMap;
import java.util.HashMap;

public class BinTree {
    BinNode root = new BinNode(0);
    GenomeBin binner = new GenomeBin();
    HashMap<Integer, BinNode> plainMap = new HashMap<Integer, BinNode>();
    public BinTree(){
    }
    public void add(IRegion region){
        TreeMap<Integer, Integer> map = binner.getBin(region.getStart(), region.getEnd());
        // dump
        // for(int level: map.navigableKeySet()){
        //    System.out.println(level + " " + map.get(level));
        // }
        BinNode node = null;
        Integer lastBinId = 0;
        for(int level: map.navigableKeySet()){
           // System.out.println(level + " " + map.get(level));
           Integer binId = map.get(level);
           if(!plainMap.containsKey(binId)){
               plainMap.put(binId, node = new BinNode(binId));
               if(binId != 0){
                   plainMap.get(lastBinId).add(node);
               }
           }
           lastBinId = binId;
        }
        node.add(region);
    }
    public static void main(String[] argv){
        BinTree tree = new BinTree();
        Region r = new Region(10000, 20000);
        tree.add(r);
    }
}
