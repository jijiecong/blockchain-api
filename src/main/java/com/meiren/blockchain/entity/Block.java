package com.meiren.blockchain.entity;



import com.meiren.blockchain.common.io.BlockChainInput;
import com.meiren.blockchain.common.io.BlockChainOutput;
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
	public Store[] stores;

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
		byte[][] hashes = java.util.Arrays.asList(this.stores).stream().map((store) -> {
			return store.getStoreHash();
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
		long storeCount = input.readVarInt(); // do not store txn_count
		this.stores = new Store[(int) storeCount];
		for (int i = 0; i < this.stores.length; i++) {
			this.stores[i] = new Store(input);
		}
	}

	public byte[] toByteArray() {
		BlockChainOutput output = new BlockChainOutput();
		output.write(header.toByteArray());
		output.writeVarInt(this.stores.length);
		for (Store store : stores) {
			output.write(store.toByteArray());
		}
		return output.toByteArray();
	}
}
