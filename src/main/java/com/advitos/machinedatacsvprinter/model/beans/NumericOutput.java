package com.advitos.machinedatacsvprinter.model.beans;


import com.advitos.machinedatacsvprinter.model.BinaryLabviewData;
import com.advitos.machinedatacsvprinter.model.SWVersion;
import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NumericOutput implements BinaryLabviewData {

    private int type;
    private float dem;
    private float cd;

    @Override
    public void parse(ByteBuf buffer, SWVersion version) {
        basicVersion(buffer);


    }

    private void basicVersion(ByteBuf buffer) {
        type = buffer.readInt();
        dem = buffer.readFloat();
        cd = buffer.readFloat();
    }

}
