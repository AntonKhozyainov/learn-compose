package ru.khozyainov.tiptime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Switch
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.khozyainov.tiptime.ui.theme.TipTimeTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipTimeTheme {
                TipTimeScreen()
            }
        }
    }
}

@Composable
fun TipTimeScreen() {

    //Интерфейс LocalFocusManagerиспользуется для управления фокусом в Compose.
    //Вы используете эту переменную для перемещения фокуса на текстовые поля и снятия фокуса с них
    val focusManager = LocalFocusManager.current

    val amountInput = remember { mutableStateOf("") }
    val amount = amountInput.value.toDoubleOrNull() ?: 0.0

    val tipInput = remember { mutableStateOf("") }
    val tipPercent = tipInput.value.toDoubleOrNull() ?: 0.0

    val roundUp = remember { mutableStateOf(false) }

    val tip = calculateTip(amount, tipPercent, roundUp.value)


    Column(
        modifier = Modifier.padding(32.dp),
        //Arrangement.spacedBy(8.dp) добавляет фиксированное 8dp пространство между дочерними элементами
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(R.string.calculate_tip),
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(16.dp))
        EditNumberField(
            label = R.string.bill_amount,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                //Кнопка на клавиатуре указывающая,
                //что пользователь завершил текущий ввод и хочет перейти к следующему текстовому полю
                imeAction = ImeAction.Next
            ),
            //Лямбда - onNextвыражение именованного параметра запускается,
            //когда пользователь нажимает кнопку « Далее » на клавиатуре.
            keyboardActions = KeyboardActions(
                //Функция moveFocus()перемещает фокус в указанном направлении
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            value = amountInput.value,
            onValueChange = { amountInput.value = it }
        )
        EditNumberField(
            label = R.string.how_was_the_service,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                //Кнопка на клавиатуре указывающая,
                //что пользователь завершил текущий ввод
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                //Функция clearFocus()снимает фокус с компонента, находящегося в фокусе
                //что, в свою очередь, закроет клавиатуру
                onDone = { focusManager.clearFocus() }
            ),
            value = tipInput.value,
            onValueChange = { tipInput.value = it }
        )

        RoundTheTipRow(roundUp = roundUp.value, onRoundUpChanged = { roundUp.value = it})
        Spacer(Modifier.height(24.dp))

        Text(
            text = stringResource(R.string.tip_amount, tip),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun EditNumberField(
    @StringRes label: Int,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    TextField(
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        modifier = modifier.fillMaxWidth(),
        singleLine = true,
        label = { Text(stringResource(label)) },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions
    )
}

@Composable
fun RoundTheTipRow(
    roundUp: Boolean,
    onRoundUpChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .size(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(R.string.round_up_tip))

        Switch(
            checked = roundUp,
            onCheckedChange = onRoundUpChanged,
            modifier = modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End),
            colors = SwitchDefaults.colors(
                uncheckedThumbColor = Color.DarkGray
            )
        )
    }
}

//Это делает метод общедоступным,
//но указывает другим, что он общедоступен только для целей тестирования
@VisibleForTesting
internal fun calculateTip(
    amount: Double,
    tipPercent: Double = 15.0,
    roundUp: Boolean
) : String{
    var tip = tipPercent / 100 * amount
    if (roundUp)
        tip = kotlin.math.ceil(tip)
    //средство форматирования чисел, которое вы можете использовать для форматирования чисел как валюты
    return NumberFormat.getCurrencyInstance().format(tip)
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DefaultPreview() {
    TipTimeTheme {
        TipTimeScreen()
    }
}