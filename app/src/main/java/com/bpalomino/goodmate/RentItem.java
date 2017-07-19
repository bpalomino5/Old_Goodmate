package com.bpalomino.goodmate;


/**
 * Created by bpalomino on 7/17/17.
 */

public class RentItem {
    public String type;
    public String value;
    public int imageRes;
    public int tag;

    public RentItem(int imageRes, int tag) {
        this.type = "";
        this.value = "";
        this.imageRes = imageRes;
        this.tag = tag;
    }
}
