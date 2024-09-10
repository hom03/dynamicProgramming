public class HDDAllocation {
    private int[] hdds;
    private int[] files;

    public HDDAllocation(int[] hdds, int[] files)
    {
        this.hdds = hdds;
        this.files = files;
    }

    public int[] generate_allocation()
    {
        int[] allocation = new int[files.length];
        int[] remainingSpace = new int[hdds.length];
        System.arraycopy(hdds,0,remainingSpace,0,hdds.length);
        backtrack(0,allocation,remainingSpace);
        return allocation;
    }

    private boolean backtrack(int fileIndex,int[] allocation,int[] remainingSpace){
        if (fileIndex == files.length){
            return true; // All files have been allocated
        }

        for (int HDDIndex =0;HDDIndex < hdds.length;HDDIndex++){
            if (remainingSpace[HDDIndex] >= files[fileIndex]){
                // Allocate space to current HDD
                allocation[fileIndex] = HDDIndex;
                remainingSpace[HDDIndex] -= files[fileIndex];

                //try to allocate to next file
                if (backtrack(fileIndex +1,allocation,remainingSpace)){
                    return true;
                }

                //Undo allocation for backtracking
                remainingSpace[HDDIndex] += files[fileIndex];
            }
        }
        return false; //allocation failed
    }

    public static void main(String[] args) {
        // example inputs
        int[] hdds = {1000, 1000, 2000};
        int[] files = {300, 200, 300, 1200, 400, 700, 700 };

        // run the algorithm
        int[] allocation = new HDDAllocation(hdds, files).generate_allocation();

        // output the results
        for (int i=0; i<allocation.length; i++) {
            System.out.println("File "+i+" has size "+files[i]+ "MB and goes on HDD"+allocation[i] + ".");
        }
        for (int j=0; j<hdds.length; j++)
        {
            int space_used = 0;
            for (int i=0; i<allocation.length; i++) {
                if (allocation[i]==j) {
                    space_used += files[i];
                }
            }
            System.out.println("HDD"+ j + " space used " + space_used + "/" + hdds[j] + ".");
        }
    }
}
