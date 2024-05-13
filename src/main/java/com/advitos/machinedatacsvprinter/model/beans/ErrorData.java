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

@Getter @Setter
public class ErrorData implements BinaryLabviewData {

	private List<ErrorElement> errorElements = new ArrayList<>();
	private float phReservoirRangeCheck;

	@Override
	public void parse(ByteBuf buffer, SWVersion version) {
		basicVersion(buffer, version);

	}

	private void basicVersion(ByteBuf buffer, SWVersion version) {
		int size = buffer.readInt();
		if (size <= 500) {
			for (int i = 0; i < size; i++) {
				ErrorElement e = new ErrorElement();
				e.parse(buffer, version);
				errorElements.add(e);
			}
		}
		phReservoirRangeCheck= buffer.readFloat();
	}

}
