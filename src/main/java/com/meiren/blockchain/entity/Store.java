package com.meiren.blockchain.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.meiren.blockchain.common.io.BlockChainInput;
import com.meiren.blockchain.common.io.BlockChainOutput;
import com.meiren.blockchain.common.serializer.HashSerializer;
import com.meiren.blockchain.common.serializer.HexSerializer;
import com.meiren.blockchain.common.serializer.TimestampSerializer;
import com.meiren.blockchain.common.util.HashUtils;

import java.io.IOException;
import java.io.Serializable;

/**
 * meiren Block Store.
 * 
 * @author jijc
 */
public class Store implements Serializable {

	/**
	 * int32_t, transaction data format version (signed)
	 */
	public int version;

	/**
	 * something to store
	 */
	@JsonSerialize(using = HexSerializer.class)
	public byte[] storeScript;

	/**
	 * uint32_t, the block number or timestamp at which this transaction is
	 * unlocked:
	 *
	 * 0 Not locked
	 *
	 * < 500000000 Block number at which this transaction is unlocked
	 *
	 * >= 500000000 UNIX timestamp at which this transaction is unlocked
	 *
	 * If all TxIn inputs have final (0xffffffff) sequence numbers then
	 * lock_time is irrelevant. Otherwise, the transaction may not be added to a
	 * block until after lock_time (see NLockTime).
	 */
	@JsonSerialize(using = TimestampSerializer.class)
	public long lock_time;

	private byte[] storeHash = null;

	public Store(BlockChainInput input) throws IOException {
		this.version = input.readInt();
		long storeScriptLength = input.readVarInt();
		this.storeScript = input.readBytes((int) storeScriptLength);
		this.lock_time = input.readUnsignedInt();
	}

	/**
	 * Get store hash (actually calculate the hash of store data).
	 */
	@JsonSerialize(using = HashSerializer.class)
	public byte[] getStoreHash() {
		if (this.storeHash == null) {
			this.storeHash = HashUtils.doubleSha256(this.toByteArray());
		}
		return this.storeHash;
	}

	public byte[] toByteArray() {
		BlockChainOutput buffer = new BlockChainOutput();
		buffer.writeInt(this.version);
		buffer.writeVarInt(this.storeScript.length);
		buffer.write(this.storeScript);
		buffer.writeUnsignedInt(lock_time);
		return buffer.toByteArray();
	}

	// http://BlockChain.stackexchange.com/questions/3374/how-to-redeem-a-basic-tx
	public void validate() {
		BlockChainOutput output = new BlockChainOutput();
		output.writeInt(this.version);
		// TODO:
	}

}
