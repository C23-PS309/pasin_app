package com.example.pasin_app.custom_view

import android.graphics.drawable.Drawable
import android.text.InputType
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.example.pasin_app.R

class LoginPasswordEditText : AppCompatEditText, View.OnTouchListener {
    private lateinit var errorIcon: Drawable
    private lateinit var visibleIcon: Drawable
    private lateinit var invisibleIcon: Drawable
    private var passwordVisible = true

    constructor(context: android.content.Context) : super(context) {
        init()
    }

    constructor(context: android.content.Context, attrs: android.util.AttributeSet) : super(
        context,
        attrs
    ) {
        init()
    }

    constructor(
        context: android.content.Context,
        attrs: android.util.AttributeSet,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        errorIcon = resources.getDrawable(R.drawable.baseline_error_24, null)
        visibleIcon = resources.getDrawable(R.drawable.baseline_visibility_24, null)
        invisibleIcon = resources.getDrawable(R.drawable.baseline_visibility_off_24, null)
        setOnTouchListener(this)

        addTextChangedListener(object : android.text.TextWatcher {
            override fun afterTextChanged(s: android.text.Editable?) {
                if (inputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD) {
                    setCompoundDrawablesWithIntrinsicBounds(null, null, invisibleIcon, null)
                } else {
                    setCompoundDrawablesWithIntrinsicBounds(null, null, visibleIcon, null)
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    if (inputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD) {
                        setCompoundDrawablesWithIntrinsicBounds(null, null, invisibleIcon, null)
                    } else {
                        setCompoundDrawablesWithIntrinsicBounds(null, null, visibleIcon, null)
                    }
                }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if ((s?.length ?: 0) < 8) {
                    setError("Password kurang dari 8 karakter", errorIcon)
                    setCompoundDrawablesWithIntrinsicBounds(null, null, errorIcon, null)
                } else { setCompoundDrawablesWithIntrinsicBounds(null, null, null, null) }
            }
        })
    }

    override fun onTouch(p0: View?, event: MotionEvent): Boolean {
        if (event.action != MotionEvent.ACTION_UP) return false
        if (event.x > width - paddingRight - visibleIcon.intrinsicWidth && event.x > width - paddingRight - invisibleIcon.intrinsicWidth) {
            passwordVisible = !passwordVisible
            val passwordInputType = if (passwordVisible) {
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
            inputType = passwordInputType
            ContextCompat.getDrawable(
                context,
                if (passwordVisible) R.drawable.baseline_visibility_24 else R.drawable.baseline_visibility_off_24
            )?.let {
                if (passwordVisible)
                    setCompoundDrawablesWithIntrinsicBounds(null, null, visibleIcon, null)
                else setCompoundDrawablesWithIntrinsicBounds(null, null, invisibleIcon, null)
            }
            return false
        }
        return false
    }
}