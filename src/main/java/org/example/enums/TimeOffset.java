package org.example.enums;

public enum TimeOffset {
    MOS_NOV(4), NOV_MOS(-4), MOS_OMS(3), OMS_MOS(-3), NOV_OMS(-1), OMS_NOV(1);
    private int offset;

    TimeOffset(int offset) {
        this.offset = offset;
    }

    public int getOffset() {
        return offset;
    }
}
