package com.advitos.machinedatacsvprinter.model;
import io.netty.buffer.ByteBuf;

public interface BinaryLabviewData {

	void parse(ByteBuf buffer, SWVersion version);
	
}
