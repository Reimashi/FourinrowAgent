public enum FourinrowChip { 
    RED(8), BLUE(16);
 
    private final int value;
 
    FourinrowChip (int value) {
        this.value = value;
    }
 
    public int getMask() {
        return this.value;
    }
}