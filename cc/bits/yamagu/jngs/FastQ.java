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

    static final byte[] map = new byte[128];
    static {
        String[] mm = {"ACBDGHK\nMNSRUTWVYacbdghkmnsrutwvy",
                       "TGVHCDM\nKNSYAAWBRTGVHCDMKNSYAAWBR"};
        for (int i = 0; i < mm[0].length(); i++){
            map[mm[0].charAt(i)] = (byte) mm[1].charAt(i);
        }
    }
    public FastQ reverseComplement(){
        StringBuilder buf = new StringBuilder("");
        StringBuilder rqual = new StringBuilder(qual);
        for(int i = 0; i<this.seq.length(); i++){
            buf.append((char)map[seq.charAt(i)]) ;
        }
        return new FastQ(name, buf.reverse().toString(), rqual.reverse().toString());
    }
}

