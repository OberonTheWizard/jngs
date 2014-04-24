package cc.bits.yamagu.jngs;

import java.io.File;
import java.io.Reader;
import java.io.InputStream;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class FastaReader {
    FastaHandler handler = null;
    public FastaReader(){
        handler = new FastaHandler(){
            public void handle(Fasta f){
                System.out.println(f.toString());
            }
        };
    }
    public FastaReader(FastaHandler h){
        this.handler = h;
    }

    private void handle(String name, String seq){
        if(this.handler != null){
            this.handler.handle(new Fasta(name, seq));
        }
    }
    public void read(File f) throws FileNotFoundException, IOException{
        read(new FileReader(f));
    }
    public void read(String path) throws IOException{
        read(new File(path));
    }
    public void read(Reader r) throws IOException{
        String line;
        String name = null;
        StringBuilder seq = null;
        BufferedReader br = new BufferedReader(r);
        while(null != (line = br.readLine())){
            if(line.startsWith(">")){
                if(name != null){
                    handle(name, seq.toString());
                }
                name = line.substring(1).trim();
                seq = new StringBuilder("");
            }
        }
        br.close();
        if(name != null){
            handle(name, seq.toString());
        }
    }
}
