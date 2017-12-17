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

import org.junit.Assert
import org.junit.Test

class RegonValidatorTest {

    @Test
    fun validRegonTest() {
        var regon = RegonValidator("739941302")
        Assert.assertEquals(regon.isValid(), true)
        Assert.assertEquals(regon.error, null)

        regon = RegonValidator("457280735")
        Assert.assertEquals(regon.isValid(), true)
        Assert.assertEquals(regon.error, null)
    }

    @Test
    fun invalidRegonTest() {
        val regon = RegonValidator("457280733")
        Assert.assertEquals(regon.isValid(), false)
        Assert.assertEquals(regon.error, ValidatorError.INVALID)
    }

    @Test
    fun emptyRegonTest() {
        val regon = RegonValidator("")
        Assert.assertEquals(regon.isValid(), false)
        Assert.assertEquals(regon.error, ValidatorError.WRONG_LENGTH)
    }

    @Test
    fun shortRegonTest() {
        val regon = RegonValidator("1234")
        Assert.assertEquals(regon.isValid(), false)
        Assert.assertEquals(regon.error, ValidatorError.WRONG_LENGTH)
    }

    @Test
    fun longRegonTest() {
        val regon = RegonValidator("123412341234")
        Assert.assertEquals(regon.isValid(), false)
        Assert.assertEquals(regon.error, ValidatorError.WRONG_LENGTH)
    }

    @Test
    fun invalidInputTest() {
        val regon = RegonValidator("PL457280735")
        Assert.assertEquals(regon.isValid(), false)
        Assert.assertEquals(regon.error, ValidatorError.WRONG_LENGTH)
    }
}