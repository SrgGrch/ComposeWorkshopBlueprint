package com.srggrch.composeworkshop.ui.compoments

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.calculateTargetValue
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

private val TrackWidth = 56.dp
private val TrackStrokeWidth = 32.dp
private val ThumbDiameter = 24.dp

private val ThumbRippleRadius = 24.dp

private val HorizontalPadding = 4.dp

private val DefaultSwitchPadding = 2.dp
private val SwitchWidth = TrackWidth
private val SwitchHeight = TrackStrokeWidth
private val ThumbPathLength = TrackWidth - ThumbDiameter - HorizontalPadding

private val AnimationSpec = TweenSpec<Float>(durationMillis = 100)

private val ThumbDefaultElevation = 1.dp
private val ThumbPressedElevation = 6.dp
private const val SwitchPositionalThreshold = 0.7f

@Composable
fun Switch(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {

}


@Composable
private fun BoxScope.SwitchImpl(
    checked: Boolean,
    enabled: Boolean,
    colors: SwitchColors,
    thumbValue: () -> Float,
    interactionSource: InteractionSource
) {

}

@Immutable
private data class SwitchColors(
    private val _thumbColor: Color,
    private val checkedTrackColor: Color,
    private val checkedPressedTrackColor: Color,
    private val uncheckedTrackColor: Color,
    private val uncheckedPressedTrackColor: Color,
    private val disabledCheckedTrackColor: Color,
    private val disabledUncheckedTrackColor: Color,
) {
    val thumbColor: Color
        @Composable
        get() = _thumbColor

    @Composable
    fun trackColor(enabled: Boolean, checked: Boolean, pressed: Boolean): State<Color> {
        return rememberUpdatedState(
            if (enabled) {
                if (pressed) {
                    if (checked) checkedPressedTrackColor else uncheckedPressedTrackColor
                } else {
                    if (checked) checkedTrackColor else uncheckedTrackColor
                }
            } else {
                if (checked) disabledCheckedTrackColor else disabledUncheckedTrackColor
            }
        )
    }
}


private fun DrawScope.drawTrack(trackColor: Color, trackWidth: Float, strokeWidth: Float) {
    val strokeRadius = strokeWidth / 2
    drawLine(
        color = trackColor,
        start = Offset(strokeRadius, center.y),
        end = Offset(trackWidth - strokeRadius, center.y),
        strokeWidth = strokeWidth,
        cap = StrokeCap.Round
    )
}


@Preview
@Composable
private fun SwitchPreview(
    @PreviewParameter(SwtichParamsProvider::class) params: SwtichParamsProvider.Params
) {
    Switch(params.checked, {}, enabled = params.enabled)
}

private class SwtichParamsProvider : PreviewParameterProvider<SwtichParamsProvider.Params> {
    override val values: Sequence<Params>
        get() = sequenceOf(
            Params(checked = true, enabled = true),
            Params(checked = false, enabled = true),
            Params(checked = true, enabled = false),
            Params(checked = false, enabled = false)
        )

    data class Params(
        val checked: Boolean,
        val enabled: Boolean
    )
}