/*
 * Copyright (C) 2017 Mateusz Utkała
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pl.utkala.polishidentifiersutils

class RegonValidator(private val regon: String) {

    var error: ValidatorError? = null
    private var valid = false

    fun isValid(): Boolean {
        return when {
            valid -> true
            checkInput() -> validate()
            else -> false
        }
    }

    private fun checkInput(): Boolean {
        val regonNumbers = trimNotDigits(regon)

        return if (regonNumbers.length != 9 && regonNumbers.length != 14) {
            error = ValidatorError.WRONG_LENGTH
            false
        } else if (!regonNumbers.matches(Regex("^\\d{9}$")) && !regonNumbers.matches(Regex("^\\d{14}$"))) {
            error = ValidatorError.INVALID_INPUT
            false
        } else
            true
    }

    private fun validate(): Boolean {
        val regonNumbers = trimNotDigits(regon)
        return if (regonNumbers.length == 9) validateRegon9(regonNumbers) else validateRegon14(regonNumbers)
    }

    private fun validateRegon9(regonNumbers: String): Boolean {
        val weights = intArrayOf(8, 9, 2, 3, 4, 5, 6, 7)
        val sum = (0 until regonNumbers.length - 1).sumBy { (regonNumbers[it].toString().toInt() * weights[it]) }
        return if (sum % 11 == regonNumbers.last().toString().toInt()) {
            valid = true
            true
        } else {
            error = ValidatorError.INVALID
            false
        }
    }

    private fun validateRegon14(regonNumbers: String): Boolean {
        val weights = intArrayOf(2, 4, 8, 5, 0, 9, 7, 3, 6, 1, 2, 4, 8)
        val sum = (0 until regonNumbers.length - 1).sumBy { (regonNumbers[it].toString().toInt() * weights[it]) }
        return if (sum % 11 == regonNumbers.last().toString().toInt()) {
            valid = true
            true
        } else {
            error = ValidatorError.INVALID
            false
        }
    }

    private fun trimNotDigits(input: String): String {
        return if (input.isBlank()) "" else input.replace(Regex("[\\s-]"), "")
    }
}