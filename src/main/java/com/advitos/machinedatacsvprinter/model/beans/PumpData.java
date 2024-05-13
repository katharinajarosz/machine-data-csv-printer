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
public class PumpData implements BinaryLabviewData {

	private float host;
	private float control;
	private float meas;

	@Override
	public void parse(ByteBuf buffer, SWVersion version) {
		basicVersion(buffer);

	}

	private void basicVersion(ByteBuf buffer) {
		host = buffer.readFloat();
		control = buffer.readFloat();
		meas = buffer.readFloat();
	}

}
