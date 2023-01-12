package com.example.cupcake.test

import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.rules.ActivityScenarioRule

//Эта функция расширения позволяет сократить объем кода,
//который вы пишете при поиске компонента пользовательского интерфейса по его строковому ресурсу.
//Вместо того, чтобы писать это:
//composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.my_string)
//Теперь вы можете использовать следующую инструкцию:
//composeTestRule.onNodeWithStringId(R.string.my_string)
fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.onNodeWithStringId(
    @StringRes id: Int
): SemanticsNodeInteraction = onNodeWithText(activity.getString(id))