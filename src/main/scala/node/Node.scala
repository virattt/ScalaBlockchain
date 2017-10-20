package node

import block.Block
import blockchain.Blockchain
import transaction.Transaction

class Node {

  def connectToBlockChain(blockchain: Blockchain): Blockchain = {
    null
  }

  /**
   *
   * @param peer
   * @return
   */
  def connectToPeer(peer: Node): Boolean = {
    false
  }

  /**
   *
   * @param peers
   * @return
   */
  def connectToPeers(peers: List[Node]): Boolean = {
    false
  }

  /**
   *
   * @param peer
   * @return
   */
  def getBlocks(peer: Node): List[Block] = {
    null
  }

  /**
   *
   * @param peer
   * @return
   */
  def getLatestBlock(peer: Node): Block = {
    null
  }

  /**
   *
   * @param block
   * @param peer
   * @return
   */
  def sendLatestBlock(block: Block, peer: Node): Unit = {

  }

  /**
   *
   * @param block
   * @return
   */
  def isReceivedBlockValid(block: Block): Boolean = {
    false
  }

  /**
   *
   * @param blocks
   * @return
   */
  def areReceivedBlocksValid(blocks: List[Block]): Boolean = {
    false
  }

  /**
   *
   * @param peer
   * @return
   */
  def getTransactions(peer: Node): Transaction = {
    null
  }

  /**
   *
   * @param transaction
   * @param peer
   * @return
   */
  def sendTransactions(transaction: Transaction, peer: Node): Unit = {

  }

  /**
   *
   * @param transaction
   * @param peer
   * @return
   */
  def isTransactionConfirmed(transaction: Transaction, peer: Node): Boolean = {
    false
  }

  /**
   *
   * @param transaction
   * @param peers
   * @return
   */
  def isTransactionConfirmed(transaction: Transaction, peers: List[Node]): Boolean = {
    false
  }
}
