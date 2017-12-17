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

class NipUtil(private val nip: String) {

    private val WEIGHTS = intArrayOf(6, 5, 7, 2, 3, 4, 5, 6, 7)
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
        val nipNumbers = trimNotDigits(nip)

        return if (nipNumbers.length != 10) {
            error = ValidatorError.WRONG_LENGTH
            false
        } else if (!nipNumbers.matches(Regex("^\\d{10}$"))) {
            error = ValidatorError.INVALID_INPUT
            false
        } else
            true
    }

    private fun validate(): Boolean {
        val nipNumbers = trimNotDigits(nip)
        val sum = (0 until nipNumbers.length - 1).sumBy { (nipNumbers[it].toString().toInt() * WEIGHTS[it]) }

        return if (sum % 11 == nipNumbers.last().toString().toInt()) {
            valid = true
            true
        } else {
            error = ValidatorError.INVALID
            false
        }
    }

    private fun trimNotDigits(input: String): String {
        return if (input.isBlank()) "" else input.replace(Regex("[PL\\s-]"), "")
    }
}