package com.core.dream_sakura.format;

public interface IColor {
    public int alpha();

    public int red();

    public int green();

    public int blue();

    default public int toInt() {
        return this.red() << 16 | this.green() << 8 | this.blue();
    }
}
