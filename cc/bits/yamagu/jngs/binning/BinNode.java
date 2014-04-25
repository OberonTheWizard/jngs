package cc.bits.yamagu.jngs.binning;

import java.util.TreeMap;
import java.util.TreeSet;

public class BinNode {
    Integer id;
    TreeMap<Integer, BinNode> children = new TreeMap<Integer, BinNode>();
    TreeSet<IRegion> data = new TreeSet<IRegion>();
    public BinNode(Integer id){
        this.id = id;
    }
    public Integer getId(){
        return this.id;
    }
    public void add(BinNode node){
        this.children.put(node.getId(), node);
    }
    public void add(IRegion region){
        this.data.add(region);
    }
}
