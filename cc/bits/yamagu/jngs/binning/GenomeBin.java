package cc.bits.yamagu.jngs.binning;

import java.util.TreeMap;

public class GenomeBin {
    public static void main(String[] argv){
        GenomeBin s = new GenomeBin();
        if(argv.length == 2){
        }
        System.out.println(s.getBin(Integer.parseInt(argv[0]) , Integer.parseInt(argv[1])));
    }
    /** Given start,end in chromosome coordinates assign it
    * a bin. A range goes into the smallest bin it will fit in. */
    public static TreeMap<Integer, Integer> getBin(int chromStart,int chromEnd) {
        int genomicLength = getMaxGenomicLengthLevel();
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        int binId = calcBin(map, chromStart,chromEnd,0,0,0,0,1,0,genomicLength);
        return map;
    }

    /** length for level 0 */
    protected static int getMaxGenomicLengthLevel() {
        return 536870912;/* 2^29 */
    }
    /** maximum level in Jim Kent's algorithm */
    protected static int getMaxLevel() {
        return 4;
    }
    /** how many children for one node ? */
    protected static int getChildrenCount() {
        return 8;
    }

    private static int calcBin(
        TreeMap<Integer, Integer> map,
        final int chromStart,
        final int chromEnd,
        int binId,
        int level,
        int binRowStart,
        int rowIndex,
        int binRowCount,
        int genomicPos,
        int genomicLength) {

        if( chromStart >= genomicPos &&
            chromEnd <= (genomicPos + genomicLength)) {

            if(level >= getMaxLevel()){
                map.put(level, binId);
                return binId;
            }
            int childLength = genomicLength / getChildrenCount();
            int childBinRowCount = binRowCount * getChildrenCount();
            int childRowBinStart = binRowStart + binRowCount;
            int firstChildIndex = rowIndex * getChildrenCount();
            int firstChildBin = childRowBinStart + firstChildIndex;
            for(int i = 0; i < getChildrenCount(); ++i) {
                int n = calcBin(
                    map,
                    chromStart,
                    chromEnd,
                    firstChildBin + i,
                    level + 1,
                    childRowBinStart,
                    firstChildIndex + i,
                    childBinRowCount,
                    genomicPos + i * childLength,
                    childLength
                    );
                if(n != -1) {
                    map.put(level, binId);
                    return n;
                }
            }
            map.put(level, binId);
            return binId;
        }
        return -1;
    }
}

