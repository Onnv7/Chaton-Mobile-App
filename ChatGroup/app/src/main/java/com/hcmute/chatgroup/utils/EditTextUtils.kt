package com.hcmute.chatgroup.utils

import android.annotation.SuppressLint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.text.InputType
import android.text.TextUtils
import android.view.MotionEvent
import android.widget.EditText
import androidx.core.content.ContextCompat
import com.hcmute.chatgroup.R

class EditTextUtils {
    companion object {
        fun isEmpty(edt: EditText): Boolean {
            if (TextUtils.isEmpty(edt.text)) return true
            return false
        }

        @SuppressLint("ClickableViewAccessibility")
        fun setOnVisiblePasswordSwitch(editText: EditText) {
            editText.setOnTouchListener { view, event ->
                val drawable = editText.compoundDrawables[2]

                if (event.action == MotionEvent.ACTION_UP) {
                    val drawableRight: Rect = drawable?.bounds ?: return@setOnTouchListener false
                    val location = IntArray(2)
                    editText.getLocationOnScreen(location)
                    val emptySpace = (editText.height - editText.paddingTop
                            - editText.paddingBottom - drawable.bounds.height()) / 2
                    val y = event.rawY.toInt() - location[1] - emptySpace;
                    val x = event.rawX.toInt() - (editText.right - editText.compoundPaddingRight)


                    if ((x >= drawableRight.left && x <= drawableRight.right) && (y >= 0 && y <= drawable.bounds.height())) {
                        if (editText.inputType and InputType.TYPE_CLASS_TEXT == InputType.TYPE_CLASS_TEXT &&
                            editText.inputType and InputType.TYPE_TEXT_VARIATION_PASSWORD == InputType.TYPE_TEXT_VARIATION_PASSWORD
                        ) {

                            editText.inputType =
                                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PERSON_NAME
                            val visibleDrawable: Drawable? =
                                ContextCompat.getDrawable(view.context, R.drawable.icon_visibility)
                            editText.setCompoundDrawablesWithIntrinsicBounds(editText.compoundDrawables[0], editText.compoundDrawables[1], visibleDrawable, null)
                        } else {
                            editText.inputType =
                                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                            val visibleOffDrawable: Drawable? =
                                ContextCompat.getDrawable(
                                    view.context,
                                    R.drawable.icon_visibility_off
                                )
                            editText.setCompoundDrawablesWithIntrinsicBounds(editText.compoundDrawables[0], editText.compoundDrawables[1], visibleOffDrawable, null)
                        }


                        return@setOnTouchListener true
                    }
                }

                false
            }
        }
    }
}