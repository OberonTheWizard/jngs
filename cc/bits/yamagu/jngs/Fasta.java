package cc.bits.yamagu.jngs;

public class Fasta {
    String name;
    String seq;
    public Fasta(String name, String seq){
        this.name = name;
        this.seq = seq;
    }
    public String getSequence(){
        return seq;
    }
    public void setSequence(String s){
        this.seq = s;
    }
    public String getName(){
        return name;
    }
    public void setName(String n){
        this.name = n;
    }
    public String toString(){
        return ">" + this.name + "\n" + this.seq + "\n";
    }
}
