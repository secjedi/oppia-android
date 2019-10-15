package org.oppia.app.customview.inputInteractionView

import android.annotation.SuppressLint
import android.content.Context
import android.text.InputFilter
import android.text.method.DigitsKeyListener
import android.util.AttributeSet
import android.widget.EditText
import org.oppia.app.R
import org.oppia.app.model.InteractionObject

/** The customclass for [NumberInputInteractionView]. */
class NumberInputInteractionView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyle: Int = android.R.attr.editTextStyle
  , private var placeholder: String = "Write the digit here."
) : EditText(context, attrs, defStyle), InteractionAnswerRetriever {
  init {
    attributes()
  }

  constructor(context: Context?, placeholder: String) : this(context!!) {
    this.placeholder = placeholder
    attributes()
  }

  /** This function contains default attributes of [NumberInputInteractionView]. */
  private fun attributes() {
    setBackgroundResource(R.drawable.edit_text_background)
    val paddingPixel = context.resources.getDimension(R.dimen.padding_8)
    val density = resources.displayMetrics.density
    val paddingDp = (paddingPixel * density).toInt()
    setPadding(paddingDp, paddingDp, paddingDp, paddingDp)
    hint = placeholder
    keyListener = DigitsKeyListener.getInstance("0123456789.")
    this.setEditTextMaxLength(length = 200)
  }

  private fun setEditTextMaxLength(length: Int) {
    val filterArray = arrayOfNulls<InputFilter>(1)
    filterArray[0] = InputFilter.LengthFilter(length)
    filters = filterArray
  }

  override fun getPendingAnswer(): InteractionObject {
    return InteractionObject.newBuilder().setNormalizedString(text.toString()).build()
  }
}