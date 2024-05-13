/**
 *
 */
package com.advitos.machinedatacsvprinter.model.beans;

import com.advitos.machinedatacsvprinter.model.BinaryLabviewData;
import com.advitos.machinedatacsvprinter.model.SWVersion;
import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.Setter;


/*@Getter
@Setter*/
public class SystemDiagnosis implements BinaryLabviewData {

	private float processorLoad;
	private float memoryUsage;
	private float systemLoopProcessingTime;
	private float loopLateCounter;
	private float hostTotalProcessorLoad;
	private float hostLK2001ProcessorLoad;
	private float hostLK2001PrivateBytes;
	private float hostReconnectCounter;
	private float hostConnectionStageChangeCounter;
	private float durationLastSavingProcess;
	private float open;
	private float setPointerToEnd;
	private float sdwrite;
	private float sdflush;
	private float sdclose;
	private float elementInQ;

	@Override
	public void parse(ByteBuf buffer, SWVersion version) {
		basicVersion(buffer);

	}

	private void basicVersion(ByteBuf buffer) {
		buffer.readFloat();
		buffer.readFloat();
		buffer.readFloat();
		buffer.readFloat();
		buffer.readFloat();
		buffer.readFloat();
		buffer.readFloat();
		buffer.readFloat();
		buffer.readFloat();
		buffer.readFloat();
		buffer.readFloat();
		buffer.readFloat();
		buffer.readFloat();
		buffer.readFloat();
		buffer.readFloat();
		buffer.readFloat();
	}

}
