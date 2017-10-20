package block

import transaction.{InputTransaction, OutputTransaction}

/**
 * An abstraction that encapsulates the data (currency, token, information, etc.) that is
 * transacted within this Blockchain.  Present-world examples of BlockData are Bitcoin (BTC),
 * Ether (ETH), Litecoin (LTC), etc.
 */
case class BlockData(inputs: List[InputTransaction], outputs: List[OutputTransaction]) {

}
