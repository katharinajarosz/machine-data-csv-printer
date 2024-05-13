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
public class SondData implements BinaryLabviewData {

	private short upH;
	private short uTemp;
	private float temperature;
	private float ph;

	@Override
	public void parse(ByteBuf buffer, SWVersion version) {
		basicVersion(buffer);

	}

	private void basicVersion(ByteBuf buffer) {
		upH = buffer.readShort();
		uTemp = buffer.readShort();
		temperature = buffer.readFloat();
		ph = buffer.readFloat();
	}

}
