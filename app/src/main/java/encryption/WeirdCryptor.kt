package encryption

class WeirdCryptor {
    var inputText: String = ""
    var inputCharArr: CharArray = CharArray(0)
    var keyText: String = ""

    constructor(plainText: String, keyText: String) {
        this.inputText = plainText
        this.keyText = keyText
    }

    constructor(charArr: CharArray, keyText: String) {
        this.inputCharArr = charArr
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

    // literally encrypts the buffer
    fun encrypt(): CharArray {
        val valueHolder: CharArray = CharArray(inputText.length)
        inputText.forEachIndexed { index, character ->
            var generatedKeyValue = this.getKeyVal()
            val keyLength = keyText.length

            // oops, another bias
            if (generatedKeyValue % 2 == 0) {
                generatedKeyValue += 1
            }

            val firstPart = shiftLeft8(character.code, generatedKeyValue).code
            val secondPart = firstPart xor keyText[index % keyLength].code
            val finalPart = shiftRight8(secondPart, generatedKeyValue)

            valueHolder[index] = finalPart
        }

        return valueHolder
    }

    // decrypts the encrypted buffer
    fun decrypt(): String {
        val valueHolder: CharArray = CharArray(inputCharArr.size)
        inputCharArr.forEachIndexed { index, character ->
            var generatedKeyValue = this.getKeyVal()
            val keyLength = keyText.length

            // bias retrieval again!!
            if (generatedKeyValue % 2 == 0) {
                generatedKeyValue += 1
            }

            // unwrapping the circular shifts
            val firstReversePart = shiftLeft8(character.code, generatedKeyValue).code
            val secondReversePart = firstReversePart xor keyText[index % keyLength].code
            val finalReversePart = shiftRight8(secondReversePart, generatedKeyValue)

            valueHolder[index] = finalReversePart
        }

        return String(valueHolder)
    }
}