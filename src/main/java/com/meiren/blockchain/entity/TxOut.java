package com.meiren.blockchain.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.meiren.blockchain.common.io.BlockChainInput;
import com.meiren.blockchain.common.io.BlockChainOutput;
import com.meiren.blockchain.common.serializer.BytesSerializer;

import java.io.IOException;

/**
 * @author jijiecong   （这里替换为自己的名字）
 * @ClassName: TxOut
 * @Description: ${todo}
 * @date 2018/4/17 17:05
 */
public class TxOut {

	/**
	 * int64, Transaction Value
	 */
	public long value;

	/**
	 * belong which merchant
	 * */
	@JsonSerialize(using = BytesSerializer.class)
	public byte[] merchantHash;

	@JsonSerialize(using = BytesSerializer.class)
	public byte[] receiver;

	public TxOut(Long value, byte[] merchantHash, byte[] receiver) throws IOException {
		this.value = value;
		this.merchantHash = merchantHash;
		this.receiver = receiver;
	}

	public TxOut(BlockChainInput input) throws IOException {
		this.value = input.readLong();
		long merchantHashLength = input.readVarInt();
		this.merchantHash = input.readBytes((int) merchantHashLength);
		long receiverLength = input.readVarInt();
		this.receiver = input.readBytes((int) receiverLength);
	}

	public byte[] toByteArray() {
		return new BlockChainOutput().writeLong(value).writeVarInt(merchantHash.length).write(merchantHash).writeVarInt(receiver.length).write(receiver).toByteArray();
	}
}
