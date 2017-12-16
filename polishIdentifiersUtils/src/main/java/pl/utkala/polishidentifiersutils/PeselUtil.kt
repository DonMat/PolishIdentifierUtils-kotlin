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

class PeselUtil(private val pesel: String) {
    enum class Gender { MALE, FEMALE }

    private val WEIGHTS = listOf(1, 3, 7, 9, 1, 3, 7, 9, 1, 3, 1)
    var error: ValidatorError? = null
    private var valid = false

    fun isValid(): Boolean {
        return if (valid || checkInput())
            validate()
        else
            false
    }

    fun getGender(): Gender? {
        return if (isValid()) {
            if (pesel[9].toInt() % 2 != 0) Gender.MALE else Gender.FEMALE
        } else
            null
    }

    fun getBirthDate(): String? {
        if (isValid()) {
            var year = Integer.parseInt(pesel.substring(0, 2), 10)
            var month = Integer.parseInt(pesel.substring(2, 4), 10)
            val day = Integer.parseInt(pesel.substring(4, 6), 10)

            when {
                month > 80 -> {
                    year += 1800
                    month -= 80
                }
                month > 60 -> {
                    year += 2200
                    month -= 60
                }
                month > 40 -> {
                    year += 2100
                    month -= 40
                }
                month > 20 -> {
                    year += 2000
                    month -= 20
                }
                else -> year += 1900
            }

            return year.toString() + "-" + String.format("%02d", month) + "-" + String.format("%02d", day)
        } else {
            return null
        }
    }

    private fun checkInput(): Boolean {
        if (pesel.isBlank() || pesel.length != 11) {
            error = ValidatorError.WRONG_LENGTH
            return false
        }

        if (!pesel.matches(Regex("^\\d{11}$"))) {
            error = ValidatorError.INVALID_INPUT
            return false
        }

        return true
    }

    private fun validate(): Boolean {
        var sum = 0
        pesel.forEachIndexed { index, value -> sum += (value.toInt() * WEIGHTS[index]) }
        return if (sum % 10 == 0) {
            valid = true
            true
        } else {
            error = ValidatorError.INVALID
            false
        }
    }
}
