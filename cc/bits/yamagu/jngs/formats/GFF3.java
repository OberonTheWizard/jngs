package cc.bits.yamagu.jngs.formats;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.ArrayList;
import cc.bits.yamagu.jngs.binning.Interval;
import cc.bits.yamagu.jngs.binning.IntervalImpl;

public class GFF3 implements Interval {
    // chr1    RNG     Segmental_Duplications_hg19     10000   19844   1       0       .       color=FFFF00;Name=chr1:10000-19844;Other location:
    String raw = null;

    String seqname;
    String source;
    String feature;
    String score;
    String strand;
    String frame;
    String attributes;
    Interval interval;
    public GFF3(){
    }
    public static GFF3 parse(String line){
        String[] buf = line.split("\t");

        GFF3 gff = new GFF3();
        gff.raw = line;
        gff.seqname = buf[0];
        gff.source = buf[1];
        gff.feature = buf[2];
        int start = Integer.parseInt(buf[3]);
        int end   = Integer.parseInt(buf[4]);
        gff.interval = new IntervalImpl(gff.seqname, start, end);
        gff.score = buf[5];
        gff.strand = buf[6];
        gff.frame = buf[7];
        gff.attributes = buf[8];

        return gff;
    }
    public String getChromosome(){
        return seqname;
    }
    public int getStart(){
        return this.interval.getStart();
    }
    public int getEnd(){
        return this.interval.getEnd();
    }
    public int getBin(){
        return this.interval.getBin();
    }
    public String toString(){
        return raw;
    }

    public static void main(String[] argv){
        ArrayList<GFF3> list = new ArrayList<GFF3>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(argv[0]));
            String line;
            while(null != (line = br.readLine())){
                if(line.startsWith("#")){
                    continue;
                }
                GFF3 gff = GFF3.parse(line);
                list.add(gff);
            }
            br.close();
            System.out.println(list.size());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public String getName(){
        HashMap<String, String> map = getAttributeMap();
        return map.get("Name");
    }
    public HashMap<String, String> getAttributeMap(){
        HashMap<String, String> map = new HashMap<String, String>();
        String[] att = this.attributes.split(";");
        for(String attribute: att){
            String[] key_value = attribute.split("=", 2);
            if(key_value.length == 2){
                map.put(key_value[0], key_value[1]);
            }else if(key_value.length == 1){
                map.put(key_value[0], key_value[0]);
            }
        }
        return map;
    }
}
