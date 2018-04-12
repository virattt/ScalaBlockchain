package blockchain

import block.Block
import org.scalatest.FlatSpec
import transaction.Transaction

class BlockchainTest extends FlatSpec {

  "getBlockByHash" should "return the correct block" in {
    val block: Block = Block(0, 0, "0", List[Transaction]())
    val hash: String = block.hash

    val blockchain: Blockchain = new Blockchain(List[Block](block), null)
    val blockByHash: Block = blockchain.getBlockByHash(hash)

    assert(blockByHash.hash == block.hash)
  }

  "getLastBlock" should "return the last block" in {
    val z: Block = Block(0, 100, "0", List[Transaction]())
    val y: Block = Block(1, 200, z.hash, List[Transaction]())
    val x: Block = Block(2, 300, y.hash, List[Transaction]())

    // We add new elements to the head of the Blockchain => [ x, y, z ]
    val blockchain: Blockchain = new Blockchain(List[Block](x, y, z), null)
    val lastBlock: Block = blockchain.getLastBlock

    assert(lastBlock.hash == z.hash)
  }

  "replaceChain" should "replace existing chain when new chain is longer and valid" in {
    val genesis: Block = Blockchain.getGenesisBlock
    val z: Block = Block(1, 100, genesis.hash, List[Transaction]())
    val y: Block = Block(2, 200, z.hash, List[Transaction]())
    val x: Block = Block(3, 300, y.hash, List[Transaction]())

    val existingChain: Blockchain = new Blockchain(List[Block](y, z, genesis), null)
    val newChain: Blockchain = new Blockchain(List[Block](x, y, z, genesis), null)

    val chain: List[Block] = existingChain.replaceChain(newChain.getAllBlocks)

    assert(chain == newChain.getAllBlocks)
  }

  "replaceChain" should "NOT replace existing chain when new chain is shorter" in {
    val genesis: Block = Blockchain.getGenesisBlock
    val z: Block = Block(1, 100, genesis.hash, List[Transaction]())
    val y: Block = Block(2, 200, z.hash, List[Transaction]())
    val x: Block = Block(3, 300, y.hash, List[Transaction]())

    val existingChain: Blockchain = new Blockchain(List[Block](y, z, genesis), null)
    val newChain: Blockchain = new Blockchain(List[Block](z, genesis), null)

    val chain: List[Block] = existingChain.replaceChain(newChain.getAllBlocks)

    assert(chain != newChain.getAllBlocks)
  }

  "replaceChain" should "NOT replace existing chain when new chain is invalid" in {
    val genesis: Block = Blockchain.getGenesisBlock
    val z: Block = Block(1, 100, genesis.hash, List[Transaction]())
    val y: Block = Block(2, 200, z.hash, List[Transaction]())
    val x: Block = Block(3, 300, "Invalid hash", List[Transaction]())

    val existingChain: Blockchain = new Blockchain(List[Block](y, z, genesis), null)
    val newChain: Blockchain = new Blockchain(List[Block](x, y, z, genesis), null)

    val chain: List[Block] = existingChain.replaceChain(newChain.getAllBlocks)

    assert(chain != newChain.getAllBlocks)
  }
}
