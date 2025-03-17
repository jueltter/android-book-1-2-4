package ec.dev.samagua.android_book_1_2_4

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import ec.dev.samagua.android_book_1_2_4.ui.theme.Androidbook124Theme
import java.util.UUID

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Androidbook124Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FirstFormScreen(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FirstFormScreen(name: String, modifier: Modifier = Modifier) {
    var fullName by rememberSaveable { mutableStateOf("") }

    ConstraintLayout(
        modifier = modifier
            .semantics { testTagsAsResourceId = true }
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        val (fullNameField, buttonField) = createRefs();

        OutlinedTextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = { Text(text = stringResource(R.string.full_name_label)) },
            modifier = Modifier
                .testTag("fullNameId")
                .padding(top = 16.dp, start = 24.dp, end = 24.dp)
                .fillMaxWidth()
                .constrainAs(fullNameField) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            textStyle = TextStyle(fontSize = 28.sp),
            singleLine = true

        )

        Button(
            onClick = {

            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,  // button color
                contentColor = Color.White  // Text color
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 24.dp, end = 24.dp)
                .constrainAs(buttonField) {
                    top.linkTo(fullNameField.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Text(
                text = stringResource(R.string.submit_button_text),
                fontSize = 24.sp
            )

        }


    }
}

@Preview(showBackground = true)
@Composable
fun FirstFormScreenPreview() {
    Androidbook124Theme {
        FirstFormScreen("Android")
    }
}