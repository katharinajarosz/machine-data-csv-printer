/**
 *
 */
package com.advitos.machinedatacsvprinter.model.beans;


import com.advitos.machinedatacsvprinter.model.BinaryLabviewData;
import com.advitos.machinedatacsvprinter.model.SWVersion;
import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class IOData implements BinaryLabviewData {

	private long timestamp1;
	private long timestamp2;
	private byte supplyMode;
	private byte wasteMode;
	private byte gpMode;
	private byte heaterMode;

	@Override
	public void parse(ByteBuf buffer, SWVersion version) {
		basicVersion(buffer);


	}

	private void basicVersion(ByteBuf buffer) {
		timestamp1 = buffer.readLong();
		timestamp2 = buffer.readLong();
		supplyMode = buffer.readByte();
		wasteMode = buffer.readByte();
		gpMode = buffer.readByte();
		heaterMode = buffer.readByte();
	}

}
