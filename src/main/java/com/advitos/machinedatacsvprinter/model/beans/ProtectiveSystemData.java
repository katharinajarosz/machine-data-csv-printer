/**
 *
 */
package com.advitos.machinedatacsvprinter.model.beans;


import com.advitos.machinedatacsvprinter.model.BinaryLabviewData;
import com.advitos.machinedatacsvprinter.model.SWVersion;
import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProtectiveSystemData implements BinaryLabviewData {

    private int unknownMessageCount;
    private int crcErrorCount;
    private int messageCounterErrorCount;
    private byte sendMsgCounter;
    private byte protectState;
    private byte protectSubstate;
    private byte controlStatusByte;
    private byte protectStatusByte;
    private List<SondData> sondData = new ArrayList<>();
    private List<PumpData> pumpData = new ArrayList<>();
    private List<AnalogIn> analogIn = new ArrayList<>();
    private byte lastReceivedMessageID;
    private byte lastSendMessageID;
    private byte lastSendMainState;
    private byte lastSendSubState;
    private int rs232CommunicationLoopExecutionTime;


    @Override
    public void parse(ByteBuf buffer, SWVersion version) {
        basicVersion(buffer, version);

    }

    private void basicVersion(ByteBuf buffer, SWVersion version) {
        buffer.readInt();
        buffer.readInt();
        buffer.readInt();
        buffer.readByte();
        buffer.readByte();
        buffer.readByte();
        buffer.readByte();
        buffer.readByte();
        for (int i = 0; i < 4; i++) {
            SondData sd = new SondData();
            sd.parse(buffer, version);
			sondData.add(sd);
        }
        for (int i = 0; i < 2; i++) {
            PumpData pd = new PumpData();
            pd.parse(buffer, version);
			pumpData.add(pd);
        }
        for (int i = 0; i < 10; i++) {
            AnalogIn ai = new AnalogIn();
            ai.parse(buffer, version);
			analogIn.add(ai);
        }
        buffer.readByte();
        buffer.readByte();
        buffer.readByte();
        buffer.readByte();
        buffer.readInt();
    }

}
