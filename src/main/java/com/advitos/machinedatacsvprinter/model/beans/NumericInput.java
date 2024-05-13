package com.advitos.machinedatacsvprinter.model.beans;


import com.advitos.machinedatacsvprinter.model.BinaryLabviewData;
import com.advitos.machinedatacsvprinter.model.SWVersion;
import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NumericInput implements BinaryLabviewData {


	private int type;
	private float r;
	private float f;
	private float cd;

	@Override
	public void parse(ByteBuf buffer, SWVersion version) {
		basicVersion(buffer);

	}

	private void basicVersion(ByteBuf buffer) {
		type = buffer.readInt();
		r = buffer.readFloat();
		f = buffer.readFloat();
		cd = buffer.readFloat();
	}

}
