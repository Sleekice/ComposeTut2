package com.example.composetut2

import android.content.res.Configuration
import android.os.Bundle
import android.os.Message
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetut2.ui.theme.ComposeTut2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTut2Theme {
                // A surface container using the 'background' color from the theme
                //Uncomment For Tut1 @ -
                //https://developer.android.com/jetpack/compose/tutorial?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fjetpack-compose-for-android-developers-1%23article-https%3A%2F%2Fdeveloper.android.com%2Fjetpack%2Fcompose%2Ftutorial
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Conversation(SampleData.conversationSample)                }
            }
        }
    }

//Tutorial 1.
/**    data class Message(val author: String, val body: String)
//
//
//    @Composable
//    fun Greeting(msg: Message, modifier: Modifier = Modifier) {
//        Row(modifier = Modifier.padding(all = 8.dp)) {
//            Image(
//                painter = painterResource(R.drawable.unsplash),
//                contentDescription = "Contact profile picture",
//                modifier = Modifier
//                    // Set image size to 40 dp
//                    .size(80.dp)
//                    // Clip image to be shaped as a circle
//                    .clip(CircleShape)
//                    .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
//
//            )
//
//            // Add a horizontal space between the image and the column
//            Spacer(modifier = Modifier.width(8.dp))
//
//            Column {
//                Text(
//                    text = msg.author,
//                    color = MaterialTheme.colorScheme.,
//                    style = MaterialTheme.typography.titleSmall
//                )
//                Spacer(modifier = Modifier.height(4.dp))
//
//                Surface(shape = MaterialTheme.shapes.medium, shadowElevation = 1.dp) {
//                    Text(
//                        text = msg.body,
//                        modifier = Modifier.padding(all = 4.dp),
//                        style = MaterialTheme.typography.bodyMedium
//                    )
//                }
//            }
//        }
//    }
//
//
//    @Composable
//    fun GreetingPreview() {
//        ComposeTut2Theme {
//            Greeting(
//                msg = Message("Lexi", "Hey, take a look at Jetpack Compose, it's great!")
//            )
//        }
//    }
**/

    data class Message(val author: String, val body: String)


    @Composable
    fun MessageCard(msg: Message) {
        Row(modifier = Modifier.padding(all = 8.dp)) {
            Image(
                painter = painterResource(R.drawable.unsplash),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))

            // We keep track if the message is expanded or not in this
            // variable
            var isExpanded by remember { mutableStateOf(false) }

            // We toggle the isExpanded variable when we click on this Column
            Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
                Text(
                    text = msg.author,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleSmall
                )

                Spacer(modifier = Modifier.height(4.dp))

                Surface(
                    shape = MaterialTheme.shapes.medium,
                    shadowElevation = 1.dp,
                ) {
                    Text(
                        text = msg.body,
                        modifier = Modifier.padding(all = 4.dp),
                        // If the message is expanded, we display all its content
                        // otherwise we only display the first line
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }


    @Composable
    fun Conversation(messages: List<Message>) {
        LazyColumn {
            items(messages) { message ->
                MessageCard(message)
            }
        }
    }

    @Preview
    @Composable
    fun PreviewConversation() {
        ComposeTut2Theme {
            Conversation(SampleData.conversationSample)
        }
    }


}