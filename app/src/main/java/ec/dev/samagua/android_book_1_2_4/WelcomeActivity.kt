package ec.dev.samagua.android_book_1_2_4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import ec.dev.samagua.android_book_1_2_4.ui.theme.Androidbook124Theme

class WelcomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Androidbook124Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SecondFormScreen(
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
fun SecondFormScreen(name: String, modifier: Modifier = Modifier) {
    ConstraintLayout(
        modifier = modifier
            .semantics { testTagsAsResourceId = true }
            .fillMaxWidth()
            .padding(4.dp)
    ) {

        val (fullNameField) = createRefs();

        Text(
            text = stringResource(R.string.header_text),
            style = TextStyle(fontSize = 24.sp, textAlign = TextAlign.Center),
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
                .constrainAs(fullNameField) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }

        )
    }



}

@Preview(showBackground = true)
@Composable
fun SecondFormScreenPreview() {
    Androidbook124Theme {
        SecondFormScreen("Android")
    }
}