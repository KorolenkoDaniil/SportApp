package com.example.sportapp.shared.BottomBar


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp


@Composable
fun ExperimentalInputs() {
    LazyColumn {
        item { BasicTextFieldExample() }

        item { OutlinedTextFieldExample() }

        item { TextFieldWithIcons() }

        item { PasswordTextFieldExample() }

        item { TextFieldWithError() }

        item { SearchBarWithCustomPadding() }

        item { PasswordTextFieldWithToggle() }

        item { EnhancedTextFieldExample() }
    }
}
@Composable
fun SearchBarWithCustomPadding() {
    var query = remember { mutableStateOf("") }

    BasicTextField(
        maxLines = 1,
        value = query.value,
        onValueChange = { query.value = it },
        textStyle = TextStyle(color = Color.Black),
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 0.dp, color = Color.Transparent, shape = RoundedCornerShape(30.dp))
            .padding(horizontal = 8.dp, vertical = 8.dp),
        decorationBox = { innerTextField ->
            Row(
                Modifier
                    .background(Color.White, shape = RoundedCornerShape(30.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(Icons.Filled.Search, contentDescription = "Search Icon")
                Box(Modifier.weight(1f)) {
                    if (query.value.isEmpty()) {
                        Text("Search...", color = Color.Gray)
                    }
                    innerTextField()
                }
                if (query.value.isNotEmpty()) {
                    IconButton(
                        modifier = Modifier.size(30.dp),
                        onClick = { query.value = "" }) {
                        Icon(Icons.Filled.Clear, contentDescription = "Clear Text")
                    }
                }
            }
        }
    )
}



@Composable
fun BasicTextFieldExample() {
    var text = remember { mutableStateOf("") }

    TextField(

        value = text.value,
        onValueChange = { text.value = it },
        label = { Text("Enter your name") },
        placeholder = { Text("John Doe") },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedContainerColor = Color.Red,
            unfocusedContainerColor = Color.Red,

            ),


        )
}


@Composable
fun OutlinedTextFieldExample() {
    val text = remember { mutableStateOf("") }

    OutlinedTextField(
        value = text.value,
        onValueChange = { text.value = it },
        label = { Text("Email Address") },
        placeholder = { Text("example@domain.com") },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth(),
        isError = text.value.isNotEmpty() && !text.value.contains('@'),
    )
}


@Composable
fun TextFieldWithIcons() {
    val text = remember { mutableStateOf("") }

    TextField(
        value = text.value,
        onValueChange = { text.value = it },
        label = { Text("Search") },
        placeholder = { Text("Type here...") },
        singleLine = true,
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search Icon") },
        trailingIcon = {
            if (text.value.isNotEmpty()) {
                IconButton(onClick = { text.value = "" }) {
                    Icon(Icons.Filled.Clear, contentDescription = "Clear Text")
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth(),
    )
}


@Composable
fun PasswordTextFieldExample() {
    var password = remember { mutableStateOf("") }

    TextField(
        value = password.value,
        onValueChange = { password.value = it },
        label = { Text("Password") },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth(),
        isError = password.value.isNotEmpty() && password.value.length < 8,
        colors = TextFieldDefaults.colors()
    )
}


@Composable
fun TextFieldWithError() {
    var text = remember { mutableStateOf("") }
    val isValid = text.value.length >= 3

    TextField(
        value = text.value,
        onValueChange = { text.value = it },
        label = { Text("Username") },
        isError = !isValid,
        placeholder = {
            if (!isValid) Text("Username must be at least 3 characters")
        },
        modifier = Modifier
            .fillMaxWidth(),
    )
}


@Composable
fun PasswordTextFieldWithToggle() {
    val (password, setPassword) = remember { mutableStateOf("") }
    val (passwordVisible, setPasswordVisible) = remember { mutableStateOf(false) }

    TextField(
        value = password,
        onValueChange = setPassword,
        label = { Text("Password") },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.colors(),
        trailingIcon = {
            val image = if (passwordVisible) Icons.Filled.Lock else Icons.Filled.CheckCircle
            val description = if (passwordVisible) "Hide password" else "Show password"
            IconButton(onClick = { setPasswordVisible(!passwordVisible) }) {
                Icon(image, contentDescription = description)
            }
        },
        modifier = Modifier
            .fillMaxWidth()
    )
}


@Composable
fun EnhancedTextFieldExample() {
    val text = remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = text.value,
        onValueChange = { text.value = it },
        label = { Text("Label") },
        placeholder = { Text("Enter text") },
        leadingIcon = { Icon(Icons.Filled.Info, contentDescription = "Info") },
        trailingIcon = {
            IconButton(onClick = { text.value = "" }) {
                Icon(Icons.Filled.Clear, contentDescription = "Clear")
            }
        },
        isError = text.value.length > 10,
        visualTransformation = if (text.value.length > 10) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
            }
        ),
        singleLine = true,
        maxLines = 1,
        readOnly = false,
        enabled = true,
        textStyle = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold),
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Blue,
            unfocusedTextColor = Color.Gray,
            disabledTextColor = Color.LightGray,
            errorTextColor = Color.Red,
            focusedContainerColor = Color.LightGray,
            unfocusedContainerColor = Color.LightGray,
            disabledContainerColor = Color.Gray,
            errorContainerColor = Color.Red,
            cursorColor = Color.Blue,
            errorCursorColor = Color.Red,
            selectionColors = TextSelectionColors(
                handleColor = Color.Blue,
                backgroundColor = Color.LightGray.copy(alpha = 0.4f)
            ),
            focusedIndicatorColor = Color.Green,
            unfocusedIndicatorColor = Color.Gray,
            disabledIndicatorColor = Color.LightGray,
            errorIndicatorColor = Color.Yellow,
            focusedLeadingIconColor = Color.Magenta,
            unfocusedLeadingIconColor = Color.Cyan,
            disabledLeadingIconColor = Color.Gray,
            errorLeadingIconColor = Color.Gray,
            focusedTrailingIconColor = Color.Magenta,
            unfocusedTrailingIconColor = Color.Cyan,
            disabledTrailingIconColor = Color.Gray,
            errorTrailingIconColor = Color.Red,
            focusedLabelColor = Color.Magenta,
            unfocusedLabelColor = Color.Gray,
            disabledLabelColor = Color.LightGray,
            errorLabelColor = Color.Red,
            focusedPlaceholderColor = Color.Magenta,
            unfocusedPlaceholderColor = Color.LightGray,
            disabledPlaceholderColor = Color.Gray,
            errorPlaceholderColor = Color.Red,
            focusedSupportingTextColor = Color.Green,
            unfocusedSupportingTextColor = Color.Gray,
            disabledSupportingTextColor = Color.LightGray,
            errorSupportingTextColor = Color.Red,
            focusedPrefixColor = Color.Blue,
            unfocusedPrefixColor = Color.Gray,
            disabledPrefixColor = Color.LightGray,
            errorPrefixColor = Color.Red,
            focusedSuffixColor = Color.Blue,
            unfocusedSuffixColor = Color.Gray,
            disabledSuffixColor = Color.LightGray,
            errorSuffixColor = Color.Red
        )
    )


}