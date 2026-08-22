package com.example.sportapp.support2026.presentation.screeens.commonWidgets.passwordTextInput

import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.sportapp.support2026.presentation.screeens.commonWidgets.AppTextField

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier
) {
    val passwordVisible = remember { mutableStateOf(false) }

    AppTextField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        modifier = modifier,
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image = if (passwordVisible.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
            val description = if (passwordVisible.value) "Скрыть пароль" else "Показать пароль"

            Icon(
                imageVector = image,
                contentDescription = description,
                modifier = Modifier.clickable { passwordVisible.value = !passwordVisible.value }
            )
        }
    )
}