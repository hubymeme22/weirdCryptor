package encryption

class WeirdCryptor {
    var outputHolderEncrypted: CharArray = CharArray(0)
    var outputHolderDecrypted: String = ""

    var inputText: String = ""
    var keyText: String = ""
    constructor(plainText: String, keyText: String) {
        this.inputText = plainText
        this.keyText = keyText
    }

    // for deriving unique key value
    private fun getKeyVal(): Int {
        var initialBias = (314 and 0xFF)
        keyText.forEachIndexed { index, character ->
            if (index % 2 == 0) {
                initialBias = (initialBias and character.code)
            } else {
                initialBias = (initialBias xor character.code)
            }
        }

        return initialBias
    }

    // circular right shifting for 8-bit integer
    fun shiftRight8(inputChar: Int, shifts: Int): Char {
        val initialShift = (inputChar shr shifts) and 0xFF
        val rightCut = (inputChar shl (8 - shifts)) and 0xFF
        return (initialShift xor rightCut).toChar()
    }

    // circular left shifting for 8-bit integer
    fun shiftLeft8(inputChar: Int, shifts: Int): Char {
        val initialShift = (inputChar shl shifts) and 0xFF
        val rightCut = (inputChar shr (8 - shifts)) and 0xFF
        return (initialShift xor rightCut).toChar()
    }


    fun encrypt() { }
    fun decrypt() { }
}