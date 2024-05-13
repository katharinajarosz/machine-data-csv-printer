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
public class ProcessControlData implements BinaryLabviewData {

	private byte systemState;
	private byte idleState;
	private byte startupState;
	private byte serviceState;
	private byte preparationState;
	private byte treatmentState;
	private byte postprocessState;
	private byte desinfectionState;
	private byte activeSubsequence;
	private byte subsequenceState;
	private boolean testTreatment;

	@Override
	public void parse(ByteBuf buffer, SWVersion version) {
		if (Objects.requireNonNull(version) == SWVersion.VERSION_3_12_0) {
			versionGeq3120(buffer);
		} else {
			basicVersion(buffer);
		}

	}

	private void versionGeq3120(ByteBuf buffer) {
		basicVersion(buffer);
		testTreatment = buffer.readBoolean();
	}

	private void basicVersion(ByteBuf buffer) {
		systemState= buffer.readByte();
		idleState= buffer.readByte();
		startupState= buffer.readByte();
		serviceState= buffer.readByte();
		preparationState= buffer.readByte();
		treatmentState= buffer.readByte();
		postprocessState= buffer.readByte();
		desinfectionState= buffer.readByte();
		activeSubsequence= buffer.readByte();
		subsequenceState= buffer.readByte();
	}

}
