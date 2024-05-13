/**
 *
 */
package com.advitos.machinedatacsvprinter.model.beans;


import com.advitos.machinedatacsvprinter.model.BinaryLabviewData;
import com.advitos.machinedatacsvprinter.model.SWVersion;
import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter @Setter
public class BalancingData implements BinaryLabviewData {

	private float ufHost;
	private float ufControl;
	private float ufWeight;
	private float acidFlowConrol;
	private float acidWeight;
	private float acidDensity;
	private float baseFlowControl;
	private float baseWeight;
	private float baseDensity;
	private float scale1row;
	private float scale2row;
	private float scaleDeviation;
	private float scaleSumCurrent;
	private float balanceOffset;

	@Override
	public void parse(ByteBuf buffer, SWVersion version) {
		if (Objects.requireNonNull(version) == SWVersion.VERSION_3_6_2) {
			basicVersion(buffer);
		} else {
			versionGeq390(buffer);
		}
	}

	private void basicVersion(ByteBuf buffer) {
		for (int i = 0; i < 13; i++) {
			buffer.readFloat();
		}
	}

	private void versionGeq390(ByteBuf buffer) {
		for (int i = 0; i < 14; i++) {
			buffer.readFloat();
		}
	}

}
