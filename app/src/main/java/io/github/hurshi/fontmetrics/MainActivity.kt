package io.github.hurshi.fontmetrics

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import io.github.hurshi.fontmetrics.textview_style.TextViewActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initClick()
        updateTextViews()
    }

    private fun initClick() {
        updateButton.setOnClickListener {
            myFontMetricsView.setText(etTextString.text.toString())
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
        toTextViewActivity.setOnClickListener { startActivity(Intent(this, TextViewActivity::class.java)) }
    }

    private fun updateTextViews() {
        myFontMetricsView.setText(etTextString.text.toString())
        myFontMetricsView.setTextSizeInPixels(350)
        tvTop.text = myFontMetricsView.fontMetrics.top.toString()
        tvAscent.text = myFontMetricsView.fontMetrics.ascent.toString()
        tvBaseline.text = (0f).toString()
        tvDescent.text = myFontMetricsView.fontMetrics.descent.toString()
        tvBottom.text = myFontMetricsView.fontMetrics.bottom.toString()
        tvTextBounds.text = "w=${myFontMetricsView.textBounds.width()} h=${myFontMetricsView.textBounds.height()}"
        tvWidth.text = myFontMetricsView.measuredTextWidth.toString()
        tvLeadingValue.text = myFontMetricsView.fontMetrics.leading.toString()
    }

    private fun hideKeyboard(view: View?) {
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}
