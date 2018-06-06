package com.meiren.blockchain.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.meiren.blockchain.common.serializer.TimestampSerializer;

/**
 * @author jijiecong   （这里替换为自己的名字）
 * @ClassName: UTxO
 * @Description: ${todo}
 * @date 2018/4/17 17:04
 */
public class UTxO {
	/**
	 * 32-bytes, the hash of the referenced transaction.
	 */
	public String txHash;

	/**
	 * uint32, the index of the specific output in the transaction. The first
	 * output is 0, etc.
	 */
	public long index;

	public long value;

	public String merchant;

	@JsonSerialize(using = TimestampSerializer.class)
	public long lockTime;

	public String getTxHash() {
		return txHash;
	}

	public void setTxHash(String txHash) {
		this.txHash = txHash;
	}

	public long getIndex() {
		return index;
	}

	public void setIndex(long index) {
		this.index = index;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	public String getMerchant() {
		return merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	public long getLockTime() {
		return lockTime;
	}

	public void setLockTime(long lockTime) {
		this.lockTime = lockTime;
	}
}
