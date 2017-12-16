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

import org.junit.Assert.assertEquals
import org.junit.Test


class PeselUtilTest {
    @Test
    fun validPeselTest() {
        var pesel = PeselUtil("67881319915")
        assertEquals(pesel.isValid(), true)
        assertEquals(pesel.error, null)

        pesel = PeselUtil("78060533750")
        assertEquals(pesel.isValid(), true)
        assertEquals(pesel.error, null)

        pesel = PeselUtil("61021854465")
        assertEquals(pesel.isValid(), true)
        assertEquals(pesel.error, null)

        pesel = PeselUtil("09272910390")
        assertEquals(pesel.isValid(), true)
        assertEquals(pesel.error, null)

        pesel = PeselUtil("35111831513")
        assertEquals(pesel.isValid(), true)
        assertEquals(pesel.error, null)
    }

    @Test
    fun invalidPeselTest() {
        val pesel = PeselUtil("67881319910")
        assertEquals(pesel.isValid(), false)
        assertEquals(pesel.error, ValidatorError.INVALID)
    }

    @Test
    fun emptyPeselTest() {
        val pesel = PeselUtil("")
        assertEquals(pesel.isValid(), false)
        assertEquals(pesel.error, ValidatorError.WRONG_LENGTH)
    }

    @Test
    fun shortPeselTest() {
        val pesel = PeselUtil("123")
        assertEquals(pesel.isValid(), false)
        assertEquals(pesel.error, ValidatorError.WRONG_LENGTH)
    }

    @Test
    fun longPeselTest() {
        val pesel = PeselUtil("12345678901234")
        assertEquals(pesel.isValid(), false)
        assertEquals(pesel.error, ValidatorError.WRONG_LENGTH)
    }

    @Test
    fun notNumberPeselTest() {
        val pesel = PeselUtil("abc")
        assertEquals(pesel.isValid(), false)
        assertEquals(pesel.error, ValidatorError.WRONG_LENGTH)
    }

    @Test
    fun mixedLetterNumberPeselTest() {
        val pesel = PeselUtil("123abc34247")
        assertEquals(pesel.isValid(), false)
        assertEquals(pesel.error, ValidatorError.INVALID_INPUT)
    }

    @Test
    fun checkGenderForValidPeselTest() {
        var pesel = PeselUtil("67881319915")
        assertEquals(pesel.isValid(), true)
        assertEquals(pesel.getGender(), PeselUtil.Gender.MALE)

        pesel = PeselUtil("61021854465")
        assertEquals(pesel.isValid(), true)
        assertEquals(pesel.getGender(), PeselUtil.Gender.FEMALE)
    }

    @Test
    fun checkGenderForInvalidPeselTest() {
        val pesel = PeselUtil("67881319911")
        assertEquals(pesel.isValid(), false)
        assertEquals(pesel.getGender(), null)
    }

    @Test
    fun checkBirthDateForValidPeselTest() {
        var pesel = PeselUtil("67881319915")
        assertEquals(pesel.isValid(), true)
        assertEquals(pesel.getBirthDate(), "1867-08-13")

        pesel = PeselUtil("61021854465")
        assertEquals(pesel.isValid(), true)
        assertEquals(pesel.getBirthDate(), "1961-02-18")

        pesel = PeselUtil("01301055623")
        assertEquals(pesel.isValid(), true)
        assertEquals(pesel.getBirthDate(), "2001-10-10")

        pesel = PeselUtil("33450593635")
        assertEquals(pesel.isValid(), true)
        assertEquals(pesel.getBirthDate(), "2133-05-05")
    }

    @Test
    fun checkBirthDateForInvalidPeselTest() {
        val pesel = PeselUtil("67881319911")
        assertEquals(pesel.isValid(), false)
        assertEquals(pesel.getBirthDate(), null)
    }

}