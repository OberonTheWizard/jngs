package cc.bits.yamagu.jngs;

import java.util.zip.GZIPInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.FileInputStream;

public class FastQReader {
    String path;
    FastQHandler handler = null;
    public FastQReader(String path, FastQHandler h){
        this.handler = h;
        this.path = path;
    }
    public void read() throws IOException{
        BufferedReader br = null;
        try {
            if(path.endsWith(".gz")){
                br = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(path))));
            }else {
                br = new BufferedReader(new FileReader(path));
            }
            String line = null;
            int count = 0;
            String name = null;
            String seq = null;
            String qual  = null;
            while(null != (line = br.readLine())){
                if(count == 0){
                    name = line;
                }else if(count == 1){
                    seq = line;
                }else if(count == 2){
                    // noop
                }else if(count == 3){
                    handleFastQ(new FastQ(name, seq, line));
                    count = -1;
                }
                count++;
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
            e.printStackTrace();
        }finally {
            br.close();
        }
    }
    private void handleFastQ(FastQ fq){
        if(this.handler != null){
            handler.handle(fq);
        }
    }
}
