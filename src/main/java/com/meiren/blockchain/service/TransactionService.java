package com.meiren.blockchain.service;

import com.meiren.blockchain.entity.Transaction;
import com.meiren.blockchain.entity.TxIn;
import com.meiren.blockchain.entity.TxOut;

/**
 * @author jijiecong   （这里替换为自己的名字）
 * @ClassName: TransactionService
 * @Description: ${todo}
 * @date 2018/4/18 11:04
 */
public interface TransactionService {

	public byte[] buildTransaction(TxIn[] tx_ins, TxOut[] tx_outs, byte[] operatorHash);

	public Transaction findByTxHash(String txHash);

	public Boolean verify(TxIn[] tx_ins, TxOut[] tx_outs, byte[] operatorHash);
}
