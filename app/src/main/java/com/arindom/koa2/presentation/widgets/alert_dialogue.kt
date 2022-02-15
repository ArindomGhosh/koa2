package com.arindom.koa2.presentation.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arindom.koa2.R
import com.arindom.koa2.presentation.theme.Koa2Theme
import com.arindom.koa_mvi_core.foundation.VoidCallback

@Composable
fun KoaAlertInfo(
    modifier: Modifier = Modifier,
    title: String,
    text: String
) {
    val openDialog = remember { mutableStateOf(true) }
    if (openDialog.value) {
        AlertDialog(
            modifier = modifier,
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = title)
            },
            text = {
                Text(text = text)
            },
            buttons = {
                Row(
                    modifier = Modifier.padding(all = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { openDialog.value = false }) {
                        Text(text = LocalContext.current.getString(R.string.dismiss))
                    }
                }
            }
        )
    }
}

@Composable
fun KoaAlertAction(
    modifier: Modifier = Modifier,
    title: String,
    text: String,
    action: VoidCallback
) {

    val openDialog = remember { mutableStateOf(true) }
    if (openDialog.value) {
        AlertDialog(
            modifier = modifier,
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = title)
            },
            text = {
                Text(text = text)
            },
            buttons = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = { openDialog.value = false }) {
                        Text(text = LocalContext.current.getString(R.string.dismiss))
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Button(
                        onClick = action
                    ) {
                        Text(text = LocalContext.current.getString(R.string.confirm))
                    }
                }
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun KoaAlertInfoPreview() {
    Koa2Theme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            contentAlignment = Alignment.Center,
        ) {
            KoaAlertInfo(title = "Alert", text = "Alert Message")
        }
    }
}