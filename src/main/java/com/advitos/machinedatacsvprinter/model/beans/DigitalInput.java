package com.advitos.machinedatacsvprinter.model.beans;


import com.advitos.machinedatacsvprinter.model.BinaryLabviewData;
import com.advitos.machinedatacsvprinter.model.SWVersion;
import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DigitalInput implements BinaryLabviewData {

	private int type;
	private boolean cd;
	private boolean r;

	@Override
	public void parse(ByteBuf buffer, SWVersion version) {
		basicVersion(buffer);
	}

	private void basicVersion(ByteBuf buffer) {
		type = buffer.readInt();
		cd = buffer.readBoolean();
		r = buffer.readBoolean();
	}

}
