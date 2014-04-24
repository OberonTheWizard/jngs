package cc.bits.yamagu.jngs;

public class FastQ {
    String name;
    String seq;
    String qual;
    public FastQ(String name, String seq, String qual){
        this.name = name;
        this.seq = seq;
        this.qual = qual;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setSequence(String seq){
        this.seq = seq;
    }
    public String getSequence(){
        return this.seq;
    }
    public void setQuality(String qual){
        this.qual = qual;
    }
    public String getQuality(){
        return this.qual;
    }
    public String toString(){
        StringBuilder b = new StringBuilder();
        if(this.name.startsWith("@")){
            b.append(this.name);
        }else {
            b.append("@");
            b.append(this.name);
        }
        b.append("\n");
        b.append(this.seq);
        b.append("\n");
        b.append(this.qual);
        b.append("\n");
        return b.toString();
    }
}

