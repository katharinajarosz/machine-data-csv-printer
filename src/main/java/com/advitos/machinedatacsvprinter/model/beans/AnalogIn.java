/**
 *
 */
package com.advitos.machinedatacsvprinter.model.beans;


import com.advitos.machinedatacsvprinter.model.BinaryLabviewData;
import com.advitos.machinedatacsvprinter.model.SWVersion;
import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AnalogIn implements BinaryLabviewData {

	private float f;
	private float cd;

	@Override
	public void parse(ByteBuf buffer, SWVersion version) {
		basicVersion(buffer);
	}

	private void basicVersion(ByteBuf buffer) {
		f = buffer.readFloat();
		cd = buffer.readFloat();
	}

}
