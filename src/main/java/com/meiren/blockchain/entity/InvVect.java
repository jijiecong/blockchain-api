package com.meiren.blockchain.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.meiren.blockchain.common.io.BlockChainInput;
import com.meiren.blockchain.common.io.BlockChainOutput;
import com.meiren.blockchain.common.serializer.HashSerializer;

import java.io.IOException;

public class InvVect {

	/**
	 * Any data of with this number may be ignored.
	 */
	public static final int ERROR = 0;

	/**
	 * Hash is related to a transaction.
	 */
	public static final int MSG_TX = 1;

	/**
	 * Hash is related to a data block.
	 */
	public static final int MSG_BLOCK = 2;

	/**
	 * Hash of a block header; identical to MSG_BLOCK. Only to be used in
	 * getdata message. Indicates the reply should be a merkleblock message
	 * rather than a block message; this only works if a bloom filter has been
	 * set.
	 */
	public static final int MSG_FILTERED_BLOCK = 3;

	/**
	 * Hash of a block header; identical to MSG_BLOCK. Only to be used in
	 * getdata message. Indicates the reply should be a cmpctblock message. See
	 * BIP 152 for more info.
	 */
	public static final int MSG_CMPCT_BLOCK = 4;

	/**
	 * uint32
	 */
	public int type;

	/**
	 * 32-bytes hash
	 */
	@JsonSerialize(using = HashSerializer.class)
	public byte[] hash;

	public InvVect() {
	}

	public InvVect(BlockChainInput input) throws IOException {
		this.type = input.readInt();
		this.hash = input.readBytes(32);
	}

	public byte[] toByteArray() {
		return new BlockChainOutput().writeInt(this.type).write(this.hash).toByteArray();
	}
}
