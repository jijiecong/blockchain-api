package com.meiren.blockchain.entity;



import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.meiren.blockchain.common.io.BlockChainInput;
import com.meiren.blockchain.common.io.BlockChainOutput;
import com.meiren.blockchain.common.serializer.HashSerializer;
import com.meiren.blockchain.common.util.BytesUtils;
import com.meiren.blockchain.common.util.HashUtils;

import java.io.IOException;
import java.io.Serializable;

/**
 * meiren block data.
 * 
 * @author jijc
 */
public class Block implements Serializable {

	public Header header;
	public Transaction[] transactions;

	@JsonSerialize(using = HashSerializer.class)
	private byte[] blockHash = null;

	public Block (){}

	public byte[] getBlockHash() {
		if (this.blockHash == null) {
			byte[] data = this.header.toByteArray();
			this.blockHash = HashUtils.doubleSha256(data);
		}
		return this.blockHash;
	}

	public byte[] calculateMerkleHash() {
		byte[][] hashes = java.util.Arrays.asList(this.transactions).stream().map((transaction) -> {
			return transaction.getTxHash();
		}).toArray(byte[][]::new);
		while (hashes.length > 1) {
			hashes = merkleHash(hashes);
		}
		return hashes[0];
	}

	byte[][] merkleHash(byte[][] hashes) {
		int count = hashes.length / 2;
		int extra = hashes.length % 2;
		byte[][] results = new byte[count + extra][];
		for (int i = 0; i < count; i++) {
			results[i] = HashUtils.doubleSha256(BytesUtils.concat(hashes[2 * i], hashes[2 * i + 1]));
		}
		if (extra == 1) {
			results[count] = HashUtils
					.doubleSha256(
							BytesUtils.concat(hashes[hashes.length - 1], hashes[hashes.length - 1]));
		}
		return results;
	}

	public Block(BlockChainInput input) throws IOException {
		this.header = new Header(input);
		long txCount = input.readVarInt(); // do not store txn_count
		this.transactions = new Transaction[(int)txCount];
		for (int i = 0; i < this.transactions.length; i++) {
			this.transactions[i] = new Transaction(input);
		}
	}

	public byte[] toByteArray() {
		BlockChainOutput output = new BlockChainOutput();
		output.write(header.toByteArray());
		output.writeVarInt(this.transactions.length);
		for (Transaction transaction : transactions) {
			output.write(transaction.toByteArray());
		}
		return output.toByteArray();
	}
}
