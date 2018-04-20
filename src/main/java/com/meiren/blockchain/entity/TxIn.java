package com.meiren.blockchain.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.meiren.blockchain.common.io.BlockChainInput;
import com.meiren.blockchain.common.io.BlockChainOutput;
import com.meiren.blockchain.common.serializer.HashSerializer;
import com.meiren.blockchain.common.util.BytesUtils;

import java.io.IOException;

/**
 * @author jijiecong   （这里替换为自己的名字）
 * @ClassName: TxIn
 * @Description: ${todo}
 * @date 2018/4/17 17:04
 */
public class TxIn {
	/**
	 * 32-bytes, the hash of the referenced transaction.
	 */
	@JsonSerialize(using = HashSerializer.class)
	public byte[] hash;

	/**
	 * uint32, the index of the specific output in the transaction. The first
	 * output is 0, etc.
	 */
	public long index;

	public TxIn(byte[] hash, long index){
		this.hash = hash;
		this.index = index;
	}

	public TxIn(BlockChainInput input) throws IOException {
		this.hash = input.readBytes(32);
		this.index = input.readUnsignedInt();
	}

	public boolean isbase() {
		return BytesUtils.isZeros(this.hash);
	}

	public byte[] toByteArray() {
		return new BlockChainOutput().write(hash).writeUnsignedInt(index).toByteArray();
	}
}
