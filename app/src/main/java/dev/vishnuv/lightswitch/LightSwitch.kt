package dev.vishnuv.lightswitch

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.vishnuv.lightswitch.ui.theme.LightSwitchTheme

@Composable
fun LightSwitch(modifier: Modifier = Modifier) {
    var lightOn by remember { mutableStateOf(false) }

    val animationDuration = 200

    val indicatorTopPadding by animateDpAsState(
        if (lightOn) 35.dp else 15.dp,
        animationSpec = tween(animationDuration),
        label = "indicatorTopPadding"
    )

    val buttonTopPadding by animateDpAsState(
        if (lightOn) 20.dp else 0.dp,
        animationSpec = tween(animationDuration),
        label = "buttonTopPadding"
    )
    val buttonBottomPadding by animateDpAsState(
        if (lightOn) 0.dp else 20.dp,
        animationSpec = tween(animationDuration),
        label = "buttonBottomPadding"
    )

    val gradient = listOf(Color(0xFF696969), Color(0xFF484848), Color(0xFF3D3D3D))
    val width = LocalConfiguration.current.screenWidthDp

    Scaffold(modifier.background(Color(0XFF383838))) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Box(
                Modifier
                    .align(Alignment.Center)
                    .padding(24.dp)
                    .size((width / 1.2).dp)
                    .clip(RoundedCornerShape(40.dp))
                    .background(Color(0XFF4E4E4E))
            ) {
                Box(
                    Modifier
                        .fillMaxSize()
                        .padding(30.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0XFF2D2D2D))
                        .border(3.dp, Color.Black, RoundedCornerShape(20.dp))
                ) {
                    Box(
                        Modifier
                            .clickable { lightOn = !lightOn }
                            .fillMaxSize()
                            .shadow(
                                elevation = if (lightOn) (-8).dp else 8.dp,
                                spotColor = Color(0xFF3D3D3D),
                                shape = RoundedCornerShape(20.dp)
                            )
                            .padding(top = buttonTopPadding, bottom = buttonBottomPadding)
                            .border(
                                width = 0.5.dp,
                                color = Color(0xFF6F6F6F),
                                shape = RoundedCornerShape(20.dp)
                            )
                            .clip(RoundedCornerShape(20.dp))
                            .background(Brush.verticalGradient(colors = if (lightOn) gradient else gradient.reversed()))
                    )
                    Box(
                        Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = indicatorTopPadding, end = 15.dp)
                            .size(8.dp)
                            .shadow(
                                elevation = 1.dp,
                                spotColor = if (lightOn) Color(0xFF36EA69) else Color(0xFFE84A36),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clip(RoundedCornerShape(4.dp))
                            .background(
                                if (lightOn) Color(0xFF36EA69) else Color(0xFFE84A36),
                                shape = CircleShape
                            )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun LightSwitchAppPreview() {
    LightSwitchTheme {
        LightSwitch()
    }
}
