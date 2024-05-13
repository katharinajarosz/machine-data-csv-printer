package com.advitos.machinedatacsvprinter.model.beans;


import com.advitos.machinedatacsvprinter.model.BinaryLabviewData;
import com.advitos.machinedatacsvprinter.model.SWVersion;
import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClosedLoop implements BinaryLabviewData {


    private int type;
    private float hmi;
    private float proc;
    private float dem;
    private float y;
    private float inp;
    private float e;


    @Override
    public void parse(ByteBuf buffer, SWVersion version) {
        basicVersion(buffer);
    }

    private void basicVersion(ByteBuf buffer) {
        type = buffer.readInt();
        hmi = buffer.readFloat();
        proc = buffer.readFloat();
        dem = buffer.readFloat();
        y = buffer.readFloat();
        inp = buffer.readFloat();
        e = buffer.readFloat();
    }

}
