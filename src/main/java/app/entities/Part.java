package app.entities;

public class Part {
    private String partName, partNumber, vendor, shipped, received;
    private int qty;

    public Part(String partName, String partNumber, String vendor, int qty, String shipped, String received) {
        this.partName = partName;
        this.partNumber = partNumber;
        this.vendor = vendor;
        this.qty = qty;
        this.shipped = shipped;
        this.received = received;
    }

    public String getPartName() {
        return partName;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public String getVendor() {
        return vendor;
    }

    public int getQty() {
        return qty;
    }

    public String getShipped() {
        return shipped;
    }

    public String getReceived() {
        return received;
    }
}
