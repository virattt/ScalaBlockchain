package miner

import blockchain.Blockchain
import block.Block
import transaction.Transaction

class Miner(blockchain: Blockchain) {

  /**
   *
   * @param rewardAddress
   * @return
   */
  def mine(rewardAddress: String): Block ={
    null
  }

  /**
   *
   * @param address
   * @param previousBlock
   * @param transactions
   * @return
   */
  def generateNextBlock(address: String, previousBlock: Block, transactions: List[Transaction]): Block = {
    null
  }

  /**
   *
   * @param block
   * @param difficulty
   * @return
   */
  def proveWorkFor(block: Block, difficulty: String): Unit = {

  }

}
