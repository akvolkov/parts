package app.models;

public class Filter {
    String partName, partNumber, vendor, qty;
    TimeRange shipped, received;

    public Filter(String partName, String partNumber, String vendor, String qty, TimeRange shipped, TimeRange received) {
        this.partName = partName;
        this.partNumber = partNumber;
        this.vendor = vendor;
        this.qty = qty;
        this.shipped = shipped;
        this.received = received;
    }

    public TimeRange getShipped() {
        return shipped;
    }

    public void setShipped(TimeRange shipped) {
        this.shipped = shipped;
    }

    public TimeRange getReceived() {
        return received;
    }

    public void setReceived(TimeRange received) {
        this.received = received;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public static class TimeRange {
        private String after;
        private String before;

        /**
         * inner class describing the time interval
         * @param after - beginning of range
         * @param before - end of range
         */
        public TimeRange(String after, String before) {
            this.after = after;
            this.before = before;
        }

        public String getAfter() {
            return after;
        }

        public void setAfter(String after) {
            this.after = after;
        }

        public String getBefore() {
            return before;
        }

        public void setBefore(String before) {
            this.before = before;
        }
    }
}
