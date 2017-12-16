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

    fun getGender() : Gender?{
        return if(isValid()){
            if(pesel[9].toInt() % 2 != 0) Gender.MALE else Gender.FEMALE
        } else
            null
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
