package io.github.hurshi.fontmetrics.textview_style

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import io.github.hurshi.fontmetrics.R
import kotlinx.android.synthetic.main.activity_textview.*


class TextViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_textview)

        initClick()
        updateTextViews()
    }

    private fun initClick() {
        updateButton.setOnClickListener {
            myFontMetricsView.text = etTextString.text.toString()
            updateTextViews()
            hideKeyboard(currentFocus)
        }
        cbTop.setOnClickListener { myFontMetricsView.setTopVisible(cbTop.isChecked); }
        cbAscent.setOnClickListener { myFontMetricsView.setAscentVisible(cbAscent.isChecked); }
        cbBaseline.setOnClickListener { myFontMetricsView.setBaselineVisible(cbBaseline.isChecked); }
        cbDescent.setOnClickListener { myFontMetricsView.setDescentVisible(cbDescent.isChecked); }
        cbBottom.setOnClickListener { myFontMetricsView.setBottomVisible(cbBottom.isChecked); }
        cbTextBounds.setOnClickListener { myFontMetricsView.setBoundsVisible(cbTextBounds.isChecked); }
        cbWidth.setOnClickListener { myFontMetricsView.setWidthVisible(cbWidth.isChecked); }
    }

    private fun updateTextViews() {
        myFontMetricsView.text = etTextString.text.toString()
        myFontMetricsView.post {
            tvTop.text = (myFontMetricsView.paint.fontMetrics.top + myFontMetricsView.baseline).toString()
            tvAscent.text = (myFontMetricsView.paint.fontMetrics.ascent + myFontMetricsView.baseline).toString()
            tvBaseline.text = myFontMetricsView.baseline.toString()
            tvDescent.text = (myFontMetricsView.paint.fontMetrics.descent + myFontMetricsView.baseline).toString()
            tvBottom.text = (myFontMetricsView.paint.fontMetrics.bottom + myFontMetricsView.baseline).toString()
            val textRect = Rect().also { rect ->
                myFontMetricsView.paint.getTextBounds(
                    myFontMetricsView.text.toString(),
                    0,
                    myFontMetricsView.text.length,
                    rect
                )
            }
            tvTextBounds.text = "w=${textRect.width()} h=${textRect.height()}"
            tvWidth.text = myFontMetricsView.paint.measureText(myFontMetricsView.text.toString()).toString()
            tvLeadingValue.text = myFontMetricsView.paint.fontMetrics.leading.toString()
            tvSizeLabel.text =
                "TextView Size:\n       width=${myFontMetricsView.width} height=${myFontMetricsView.height}\n" +
                        "       padL=${myFontMetricsView.paddingLeft} padR=${myFontMetricsView.paddingRight}\n" +
                        "       padT=${myFontMetricsView.paddingTop} padB=${myFontMetricsView.paddingBottom}"
        }
    }

    private fun hideKeyboard(view: View?) {
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}
