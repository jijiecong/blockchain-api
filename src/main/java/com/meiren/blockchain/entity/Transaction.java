package com.meiren.blockchain.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ByteArraySerializer;
import com.meiren.blockchain.common.io.BlockChainInput;
import com.meiren.blockchain.common.io.BlockChainOutput;
import com.meiren.blockchain.common.serializer.BytesSerializer;
import com.meiren.blockchain.common.serializer.HashSerializer;
import com.meiren.blockchain.common.serializer.TimestampSerializer;
import com.meiren.blockchain.common.util.HashUtils;

import java.io.IOException;

/**
 * @author jijiecong   （这里替换为自己的名字）
 * @ClassName: Transaction
 * @Description: ${todo}
 * @date 2018/4/17 16:56
 */
public class Transaction {
	/**
	 * int32_t, transaction data format version (signed)
	 */
	public int version;

	/**
	 * a list of 1 or more transaction inputs or sources
	 */
	public TxIn[] tx_ins;

	/**
	 * a list of 1 or more transaction outputs or destinations
	 */
	public TxOut[] tx_outs;

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

	private byte[] txHash = null;

	/**
	 * operator Hash
	 * */
	@JsonSerialize(using = BytesSerializer.class)
	public byte[] operatorHash;

	public Transaction(BlockChainInput input) throws IOException {
		this.version = input.readInt();
		long tx_in_count = input.readVarInt(); // do not store count
		this.tx_ins = new TxIn[(int) tx_in_count];
		for (int i = 0; i < this.tx_ins.length; i++) {
			this.tx_ins[i] = new TxIn(input);
		}
		long tx_out_count = input.readVarInt(); // do not store count
		this.tx_outs = new TxOut[(int) tx_out_count];
		for (int i = 0; i < this.tx_outs.length; i++) {
			this.tx_outs[i] = new TxOut(input);
		}
		this.lock_time = input.readUnsignedInt();
		long operatorHashLength = input.readVarInt();
		this.operatorHash = input.readBytes((int) operatorHashLength);
	}

	/**
	 * Get transaction hash (actually calculate the hash of transaction data).
	 */
	@JsonSerialize(using = HashSerializer.class)
	public byte[] getTxHash() {
		if (this.txHash == null) {
			this.txHash = HashUtils.doubleSha256(this.toByteArray());
		}
		return this.txHash;
	}

	public byte[] toByteArray() {
		BlockChainOutput buffer = new BlockChainOutput();
		buffer.writeInt(this.version).writeVarInt(this.tx_ins.length);
		for (int i = 0; i < this.tx_ins.length; i++) {
			buffer.write(tx_ins[i].toByteArray());
		}
		buffer.writeVarInt(this.tx_outs.length);
		for (int i = 0; i < this.tx_outs.length; i++) {
			buffer.write(tx_outs[i].toByteArray());
		}
		buffer.writeUnsignedInt(lock_time);
		buffer.writeVarInt(this.operatorHash.length);
		buffer.write(this.operatorHash);
		return buffer.toByteArray();
	}

	public long getTxInCount() {
		return this.tx_ins.length;
	}

	public long getTxOutCount() {
		return this.tx_outs.length;
	}

	// http://bitcoin.stackexchange.com/questions/3374/how-to-redeem-a-basic-tx
	public void validate() {
		BlockChainOutput output = new BlockChainOutput();
		output.writeInt(this.version);
		// TODO:
	}
}
