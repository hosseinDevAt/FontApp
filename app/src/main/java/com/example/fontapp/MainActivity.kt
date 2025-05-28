package com.example.fontapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SliderState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fontapp.ui.theme.FontAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val fontList = listOf(
            FontInformation(id = 1, imageUrl = R.drawable.yekan, "فونت یکان", R.font.yekan, "Yekan"),
            FontInformation(id = 2, imageUrl = R.drawable.negare, "فونت نگاره", R.font.negare, "Negare"),
            FontInformation(id = 3, imageUrl = R.drawable.rubik, "فونت روبیک", R.font.rubik, "Rubik")
        )

        setContent {
            FontAppTheme {
                FontProject(fontList)
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun FontProject(fontList : List<FontInformation>) {

        val sliderState by remember {
            mutableStateOf(SliderState(
                value = 12f,
                valueRange = 6f..96f
            ))
        }

        var textState by remember {
            mutableStateOf("سلام")
        }

        var fontFamily by remember {
            mutableStateOf(
                FontFamily(
                    Font(R.font.negare)
                )
            )
        }

        val changeFont: (font : Int) -> Unit = {
            fontFamily = FontFamily(
                Font(it)
            )
        }

        Box(
            modifier = Modifier.fillMaxSize(),
        ){

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.CenterStart
            ) {

                Slider(
                    state = sliderState,
                    modifier = Modifier
                        .width(200.dp)
                        .graphicsLayer(
                            rotationZ = -90f,
                            transformOrigin = TransformOrigin(0.25f, 1f)
                        ),

                    colors = SliderDefaults.colors(
                        thumbColor = Color.Red,
                        activeTrackColor = Color.Red,
                    ),

                )

            }


            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ){

                TextField(
                    value = textState,
                    onValueChange = {
                        textState = it
                    },
                    colors = TextFieldDefaults.colors(
                        cursorColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        selectionColors = TextSelectionColors(
                            handleColor = Color.Red,
                            backgroundColor = Color.LightGray
                        )
                    ),
                    textStyle = TextStyle(
                        textAlign = TextAlign.Center,
                        fontSize = sliderState.value.sp,
                        textDirection = TextDirection.Rtl,
                        fontFamily = fontFamily

                    )
                )
            }


            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ){

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .horizontalScroll(
                        rememberScrollState()
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    fontList.forEach {
                        Card(
                            shape = RoundedCornerShape(8.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            ),
                            elevation = CardDefaults.cardElevation(6.dp),
                            modifier = Modifier.padding(horizontal = 6.dp)
                        ) {

                            Column(
                                modifier = Modifier
                                    .padding(vertical = 12.dp, horizontal = 16.dp)
                                    .clickable {
                                        changeFont(it.font)
                                    },
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                Image(
                                    painter = painterResource(it.imageUrl),
                                    contentDescription = it.contentDescription,
                                    modifier = Modifier.size(40.dp)
                                )

                                Spacer(Modifier.height(8.dp))

                                Text(
                                    it.name
                                )

                            }

                        }

                    }
                }

            }

        }

    }

}
