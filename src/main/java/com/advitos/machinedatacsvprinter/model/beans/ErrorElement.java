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
public class ErrorElement implements BinaryLabviewData {

	private int errorId;
	private int errorLevel;

	@Override
	public void parse(ByteBuf buffer, SWVersion version) {
		basicVersion(buffer);

	}

	private void basicVersion(ByteBuf buffer) {
		errorId = buffer.readInt();
		errorLevel = buffer.readInt();
	}

}
