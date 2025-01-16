package com.example.androidpracticumcustomview.ui.theme

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

/*
Задание:
Реализуйте необходимые компоненты;
Создайте проверку что дочерних элементов не более 2-х;
Предусмотрите обработку ошибок рендера дочерних элементов.
Задание по желанию:
Предусмотрите параметризацию длительности анимации.
 */
@Composable
fun CustomContainerCompose(
    firstChild: @Composable (() -> Unit)?,
    secondChild: @Composable (() -> Unit)?,
    animateDuration: Long = 2000
) {
    // Блок создания и инициализации переменных
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val firstChildAlpha = remember { Animatable(0f) }
    val firstChildTop = remember { Animatable(0f) }
    val secondChildAlpha = remember { Animatable(0f) }
    val secondChildTop = remember { Animatable(0f) }

    // Блок активации анимации при первом запуске
    LaunchedEffect(Unit) {
        launch {
            firstChildAlpha.animateTo(targetValue = 1f, tween(animateDuration.toInt()))
            secondChildAlpha.animateTo(targetValue = 1f, tween(animateDuration.toInt()))
        }
        launch {
            firstChildTop.animateTo((-size.height / 7).toFloat(), tween(animateDuration.toInt()))
            secondChildTop.animateTo((size.height / 7).toFloat(), tween(animateDuration.toInt()))
        }
    }

    // Основной контейнер
    Box(
        modifier = Modifier
            .fillMaxSize()
            .onSizeChanged {
                size = it
            }
    ) {
        firstChild?.let {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(y = firstChildTop.value.dp)
                    .alpha(firstChildAlpha.value)
            ) {
                it()
            }
        }
        secondChild?.let {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(y = secondChildTop.value.dp)
                    .alpha(secondChildAlpha.value)
            ) {
                it()
            }
        }
    }
}