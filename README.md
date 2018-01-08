# Scala Blockchain
A simple blockchain implementation in Scala.  Developed for learning purposes.

## Architecture
The following objects are the core components of this blockchain implementation.

### A Block
```
case class Block(index: Int, timestamp: Long, previousHash: String, transactions: List[Transaction]) {
  def hash: String = Crypto.hash(index.toString + timestamp.toString + previousHash.toString + transactions.toString)
}
```

**Sample Block JSON**
```
{
	"index": 0, // (first block: 0)
	"previousHash": "0", // (hash of previous block, first block is 0) (64 bytes)
	"timestamp": 1465154705, // number of seconds since January 1, 1970
	"nonce": 0, // nonce used to identify the proof-of-work step.
	"transactions": [ // list of transactions inside the block
		{ // transaction 0
			"id": "63ec3ac02f...8d5ebc6dba", // random id (64 bytes)
			"hash": "563b8aa350...3eecfbd26b", // hash taken from the contents of the transaction: sha256 (id + data) (64 bytes)
			"type": "regular", // transaction type (regular, fee, reward)
			"data": {
				"inputs": [], // list of input transactions
				"outputs": [] // list of output transactions
			}
		}
	],
	"hash": "c4e0b8df46...199754d1ed" // hash created from the contents of the block(64 bytes)
}
```

### The Wallet 
```
/**
  * A wallet class, which represents a list of KeyPair values
  *
  * @param id the ID of this wallet
  * @param keyPairs the list of KeyPairs in this wallet
  */
class Wallet(id: String, keyPairs: List[KeyPair]) {

  def getPublicKeyByIndex(index: Int): String
  def getSecretByPublicKey(publicKey: String): String
  def doesPublicKeyExist(publicKey: String): Boolean
  def getPublicKeys: List[String]
}
```
