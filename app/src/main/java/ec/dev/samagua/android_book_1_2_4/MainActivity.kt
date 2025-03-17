package ec.dev.samagua.android_book_1_2_4

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.platform.LocalContext
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

const val FULL_NAME_KEY = "FULL_NAME_KEY"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Androidbook124Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FirstFormScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FirstFormScreen(modifier: Modifier = Modifier) {
    var fullName by rememberSaveable { mutableStateOf("") }

    ConstraintLayout(
        modifier = modifier
            .semantics { testTagsAsResourceId = true }
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        val (fullNameField, buttonField) = createRefs()
        val context = LocalContext.current

        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult()
        ) { _ ->
            // Handle result if needed
        }

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
                val tmp1 = fullName.trim()
                if (tmp1.isNotBlank()) {
                    // Set the name of the Activity to launch
                    val welcomeIntent = Intent(context, WelcomeActivity::class.java)
                    welcomeIntent.putExtra(FULL_NAME_KEY, fullName)
                    launcher.launch(welcomeIntent)
                }
                else {
                    Toast.makeText(context, R.string.full_name_label, Toast.LENGTH_LONG).show()
                }
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
        FirstFormScreen()
    }
}