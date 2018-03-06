package com.meiren.blockchain.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.meiren.blockchain.common.io.BitcoinInput;
import com.meiren.blockchain.common.serializer.TimestampSerializer;

import java.io.IOException;

/**
 * Timestamp and network address.
 * 
 * @author Michael Liao
 */
public class TimestampNetworkAddress {

	/**
	 * uint32
	 */
	@JsonSerialize(using = TimestampSerializer.class)
	public long timestamp;

	public NetworkAddress address;

	public TimestampNetworkAddress(BitcoinInput input) throws IOException {
		this.timestamp = input.readUnsignedInt();
		this.address = new NetworkAddress(input, true);
	}
}
