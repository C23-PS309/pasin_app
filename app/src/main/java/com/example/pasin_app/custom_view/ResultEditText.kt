package com.example.pasin_app.custom_view

import android.content.Context
import android.graphics.Canvas
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import com.example.pasin_app.R

class ResultEditText : AppCompatEditText {
    private var hasUserInput: Boolean = false

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No implementation needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Update the flag to indicate user input
                hasUserInput = s?.isNotBlank() ?: false
            }

            override fun afterTextChanged(s: Editable?) {
                // No implementation needed
            }
        })
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        hint = context.getString(R.string.title_hint)
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
    }

    fun hasInput(): Boolean {
        return hasUserInput
    }
}