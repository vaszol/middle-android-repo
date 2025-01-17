package com.example.androidpracticumcustomview.ui.theme

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.children

/*
Задание:
Реализуйте необходимые компоненты;
Создайте проверку что дочерних элементов не более 2-х;
Предусмотрите обработку ошибок рендера дочерних элементов.
Задание по желанию:
Предусмотрите параметризацию длительности анимации.
 */

class CustomContainer @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    private val animateAlfaDuration: Long = 2000,
    private val animateYDuration: Long = 5000
) : FrameLayout(context, attrs) {

    init {
        setWillNotDraw(false)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        setMeasuredDimension(widthSize, heightSize)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        children.forEach { item ->
            if (item.alpha == 1f) {
                val y = when (children.indexOf(item)) {
                    0 -> -measuredHeight / 4f
                    else -> measuredHeight / 4f
                }

                item.apply { alpha = 0f }
                item
                    .animate()
                    .alpha(1f)
                    .setDuration(animateAlfaDuration)
                    .start()
                item
                    .animate()
                    .translationY(y)
                    .setDuration(animateYDuration)
                    .start()
            }
        }
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun addView(child: View) {
        if (childCount == 2) {
            throw IllegalStateException()
        }
        this.addView(child, childCount)
    }
}