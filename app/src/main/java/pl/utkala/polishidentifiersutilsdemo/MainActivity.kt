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

package pl.utkala.polishidentifiersutilsdemo

import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import pl.utkala.polishidentifiersutils.NipValidator
import pl.utkala.polishidentifiersutils.PeselUtil
import pl.utkala.polishidentifiersutils.RegonValidator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkButton.setOnClickListener { _ -> verify(numberTypeGroup.checkedRadioButtonId) }
    }

    private fun verify(type: Int) {
        when (type) {
            R.id.peselType -> verifyPesel()
            R.id.nipType -> verifyNip()
            R.id.regonType -> verifyRegon()
        }
    }

    private fun verifyRegon() {
        val regon = RegonValidator(editText.text.toString())
        if (regon.isValid()) {
            validationResult.setTextColor(ContextCompat.getColor(this, R.color.colorGreen))
            validationResult.setText(R.string.valid_text)
        } else {
            validationResult.setTextColor(Color.RED)
            validationResult.text = String.format(getString(R.string.invalid_regon_text), regon.error)
        }
    }

    private fun verifyNip() {
        val nip = NipValidator(editText.text.toString())
        if (nip.isValid()) {
            validationResult.setTextColor(ContextCompat.getColor(this, R.color.colorGreen))
            validationResult.setText(R.string.valid_text)
        } else {
            validationResult.setTextColor(Color.RED)
            validationResult.text = String.format(getString(R.string.invalid_nip_text), nip.error)
        }
    }

    private fun verifyPesel() {
        val pesel = PeselUtil(editText.text.toString())
        if (pesel.isValid()) {
            validationResult.setTextColor(ContextCompat.getColor(this, R.color.colorGreen))
            val message = "${getText(R.string.valid_text)}, ${pesel.getBirthDate()}, ${pesel.getGender()}"
            validationResult.text = message
        } else {
            validationResult.setTextColor(Color.RED)
            validationResult.text = String.format(getString(R.string.invalid_pesel_text), pesel.error)
        }
    }
}
