// Copyright (c) 2015, Intel Corporation
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions are
// met:
//
// 1. Redistributions of source code must retain the above copyright
// notice, this list of conditions and the following disclaimer.
// 2. Redistributions in binary form must reproduce the above
// copyright notice, this list of conditions and the following disclaimer
// in the documentation and/or other materials provided with the
// distribution.
// 3. Neither the name of the copyright holder nor the names of its
// contributors may be used to endorse or promote products derived from
// this software without specific prior written permission.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
// "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
// LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
// A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
// HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
// SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
// LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
// DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
// THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
// (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
// OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

package com.intel.samples.kotlincalculator.android

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.intel.samples.kotlincalculator.common.CalculatorAdapter

class CalcActivity : AppCompatActivity(), View.OnClickListener {

    private var calcText: TextView? = null

    private var showStr: String = ""

    private var myCalculatorAdapter: CalculatorAdapter = CalculatorAdapter()

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        myCalculatorAdapter = CalculatorAdapter()

        setContentView(R.layout.calc_activity)
        calcText = findViewById(R.id.calc_text) as TextView
        calcText!!.textSize = 50f

        val btn1 = findViewById(R.id.bdig_1) as Button
        val btn2 = findViewById(R.id.bdig_2) as Button
        val btn3 = findViewById(R.id.bdig_3) as Button
        val btn4 = findViewById(R.id.bdig_4) as Button
        val btn5 = findViewById(R.id.bdig_5) as Button
        val btn6 = findViewById(R.id.bdig_6) as Button
        val btn7 = findViewById(R.id.bdig_7) as Button
        val btn8 = findViewById(R.id.bdig_8) as Button
        val btn9 = findViewById(R.id.bdig_9) as Button
        val btn0 = findViewById(R.id.bdig_0) as Button
        val btn10 = findViewById(R.id.b_c) as Button
        val btn11 = findViewById(R.id.b_div) as Button
        val btn12 = findViewById(R.id.b_mul) as Button
        val btn13 = findViewById(R.id.b_minus) as Button
        val btn14 = findViewById(R.id.b_sum) as Button
        val btn15 = findViewById(R.id.b_dot) as Button
        val btn16 = findViewById(R.id.b_equal) as Button

        btn0.setOnClickListener(this)
        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
        btn3.setOnClickListener(this)
        btn4.setOnClickListener(this)
        btn5.setOnClickListener(this)
        btn6.setOnClickListener(this)
        btn7.setOnClickListener(this)
        btn8.setOnClickListener(this)
        btn9.setOnClickListener(this)
        btn10.setOnClickListener(this)
        btn11.setOnClickListener(this)
        btn12.setOnClickListener(this)
        btn13.setOnClickListener(this)
        btn14.setOnClickListener(this)
        btn15.setOnClickListener(this)
        btn16.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        var symbol = ""

        // boolean isEqual = false;
        when (v.id) {
            R.id.bdig_1 -> symbol = "1"
            R.id.bdig_2 -> symbol = "2"
            R.id.bdig_3 -> symbol = "3"
            R.id.bdig_4 -> symbol = "4"
            R.id.bdig_5 -> symbol = "5"
            R.id.bdig_6 -> symbol = "6"
            R.id.bdig_7 -> symbol = "7"
            R.id.bdig_8 -> symbol = "8"
            R.id.bdig_9 -> symbol = "9"
            R.id.bdig_0 -> symbol = "0"
            R.id.b_c -> symbol = "C"
            R.id.b_dot -> symbol = "."
            R.id.b_equal -> symbol = "="
            R.id.b_sum -> symbol = "+"
            R.id.b_minus -> symbol = "-"
            R.id.b_mul -> symbol = "*"
            R.id.b_div -> symbol = "/"
            else -> {
                println("Not defined action by " + v.id)
                return
            }
        }
        if (symbol !== "") {
            showStr = myCalculatorAdapter!!.sendNewSymbol(symbol)
            calcText!!.text = showStr
        }
    }

}