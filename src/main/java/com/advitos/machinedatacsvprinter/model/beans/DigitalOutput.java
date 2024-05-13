package com.advitos.machinedatacsvprinter.model.beans;


import com.advitos.machinedatacsvprinter.model.BinaryLabviewData;
import com.advitos.machinedatacsvprinter.model.SWVersion;
import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DigitalOutput implements BinaryLabviewData {

    private int type;
    private boolean hmi;
    private boolean dem;

    @Override
    public void parse(ByteBuf buffer, SWVersion version) {
        basicVersion(buffer);

    }

    private void basicVersion(ByteBuf buffer) {
        type = buffer.readInt();
        hmi = buffer.readBoolean();
        dem = buffer.readBoolean();
    }

}
