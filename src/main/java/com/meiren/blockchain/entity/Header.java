package com.meiren.blockchain.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.meiren.blockchain.common.io.BlockChainInput;
import com.meiren.blockchain.common.io.BlockChainOutput;
import com.meiren.blockchain.common.serializer.HashSerializer;
import com.meiren.blockchain.common.serializer.TimestampSerializer;

import java.io.IOException;
import java.io.Serializable;

/**
 * meiren Block header.
 * 
 * @author jijc
 */
public class Header implements Serializable {

	/**
	 * int32, block version information (note, this is signed)
	 */
	public int version;

	/**
	 * 32 bytes, The hash value of the previous block this particular block
	 * references
	 */
	@JsonSerialize(using = HashSerializer.class)
	public byte[] prevHash;

	/**
	 * 32 bytes, The reference to a Merkle tree collection which is a hash of
	 * all transactions related to this block
	 */
	@JsonSerialize(using = HashSerializer.class)
	public byte[] merkleHash;

	/**
	 * uint32, A timestamp recording when this block was created (Will overflow
	 * in 2106)
	 */
	@JsonSerialize(using = TimestampSerializer.class)
	public long timestamp;

	/**
	 * uint32, The calculated difficulty target being used for this block
	 */
	public long bits;

	/**
	 * uint32, The nonce used to generate this block to allow variations of the
	 * header and compute different hashes
	 */
	public long nonce;

	public Header(){}

	public Header(BlockChainInput input) throws IOException {
		this.version = input.readInt();
		this.prevHash = input.readBytes(32);
		this.merkleHash = input.readBytes(32);
		this.timestamp = input.readUnsignedInt();
		this.bits = input.readUnsignedInt();
		this.nonce = input.readUnsignedInt();
	}

	public byte[] toByteArray() {
		return new BlockChainOutput().writeInt(this.version).write(this.prevHash).write(this.merkleHash)
				.writeUnsignedInt(this.timestamp).writeUnsignedInt(this.bits).writeUnsignedInt(this.nonce)
				.toByteArray();
	}
}
