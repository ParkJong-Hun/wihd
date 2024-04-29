package co.kr.parkjonghun.whatishedoingwithandroid.system.theme

import androidx.compose.ui.graphics.Color

object SeedColors {
    object Mascot {
        val Light = Color(0xFF5698D2)
        val Dark = Color(0xFF5698D2)

        val SecondLight = Color(0xFF2163AF)
        val SecondDark = Color(0xFF2163AF)

        val ThirdLight = Color(0xFF205378)
        val ThirdDark = Color(0xFF205378)

        object Extensive {
            val BrightLight = Color(0xFF9FBCD5)
            val BrightDark = Color(0xFF252F38)

            val DuskLight = Color(0xFF0D2130)
            val DuskDark = Color(0xFF3B9EE6)
        }
    }

    object White {
        val FadeLight = Color(0xFFFCFCFC)
        val FadeDark = Color(0xFF000000)

        val Light = Color(0xFFFFFFFF)
        val Dark = Color(0xFF171719)

        val FrontLight = Color(0xFFE4E4F7)
        val FrontDark = Color(0xFF242427)
    }

    object Black {
        val Light = Color(0xFF000000)
        val Dark = Color(0xFFFFFFFF)

        val DarkGrayLight = Color(0xFF333333)
        val DarkGrayDark = Color(0xFFCCCCCC)

        val GrayLight = Color(0xFFA6A6A6)
        val GrayDark = Color(0xFF78777B)
    }

    object Other {
        val LinkLight = Color(0xFF0C43B7)
        val LinkDark = Color(0xFF267EF7)

        val WarnLight = Color(0xFFE0A825)
        val WarnDark = Color(0xFFA88A25)

        val ErrorLight = Color(0xFFE02525)
        val ErrorDark = Color(0xFFA82525)
    }
}

// Light
internal val PrimaryLight = SeedColors.Black.Light
internal val OnPrimaryLight = SeedColors.White.Light
internal val PrimaryContainerLight = SeedColors.White.FadeLight
internal val OnPrimaryContainerLight = SeedColors.Black.Light
internal val InversePrimaryLight = SeedColors.White.FadeLight

internal val SecondaryLight = SeedColors.Black.DarkGrayLight
internal val OnSecondaryLight = SeedColors.White.Light
internal val SecondaryContainerLight = SeedColors.White.Light
internal val OnSecondaryContainerLight = SeedColors.Black.DarkGrayLight

internal val TertiaryLight = SeedColors.Mascot.Extensive.DuskLight
internal val OnTertiaryLight = SeedColors.White.Light
internal val TertiaryContainerLight = SeedColors.White.FrontLight
internal val OnTertiaryContainerLight = SeedColors.Mascot.Extensive.BrightLight

internal val BackgroundLight = SeedColors.White.Light
internal val OnBackgroundLight = SeedColors.Black.Light

internal val SurfaceLight = SeedColors.White.FadeLight
internal val OnSurfaceLight = SeedColors.Black.Light
internal val SurfaceVariantLight = SeedColors.White.FadeLight
internal val OnSurfaceVariantLight = SeedColors.Black.Light
internal val InverseSurfaceLight = SeedColors.Black.Light
internal val InverseOnSurfaceLight = SeedColors.White.Light
internal val SurfaceBrightLight = SeedColors.White.FadeLight
internal val SurfaceContainerLight = SeedColors.White.FadeLight
internal val SurfaceContainerHighLight = SeedColors.White.FrontLight
internal val SurfaceContainerHighestLight = SeedColors.White.FrontLight
internal val SurfaceContainerLowLight = SeedColors.White.Light
internal val SurfaceContainerLowestLight = SeedColors.White.Light
internal val SurfaceDimLight = SeedColors.White.FrontLight
internal val SurfaceTintLight = SeedColors.White.FrontLight

internal val ErrorLight = SeedColors.Other.ErrorLight
internal val OnErrorLight = SeedColors.White.Light
internal val ErrorContainerLight = SeedColors.White.Light
internal val OnErrorContainerLight = SeedColors.Other.ErrorLight

internal val OutlineLight = SeedColors.Black.GrayLight
internal val OutlineVariantLight = SeedColors.Black.DarkGrayLight

internal val ScrimLight = SeedColors.Black.Light

// Dark
internal val PrimaryDark = SeedColors.Black.Dark
internal val OnPrimaryDark = SeedColors.White.Dark
internal val PrimaryContainerDark = SeedColors.White.FadeDark
internal val OnPrimaryContainerDark = SeedColors.Black.Dark
internal val InversePrimaryDark = SeedColors.White.FadeDark

internal val SecondaryDark = SeedColors.Black.DarkGrayDark
internal val OnSecondaryDark = SeedColors.White.Dark
internal val SecondaryContainerDark = SeedColors.White.Dark
internal val OnSecondaryContainerDark = SeedColors.Black.DarkGrayDark

internal val TertiaryDark = SeedColors.Mascot.Extensive.DuskDark
internal val OnTertiaryDark = SeedColors.White.Dark
internal val TertiaryContainerDark = SeedColors.White.FrontDark
internal val OnTertiaryContainerDark = SeedColors.Mascot.Extensive.BrightDark

internal val BackgroundDark = SeedColors.White.Dark
internal val OnBackgroundDark = SeedColors.Black.Dark

internal val SurfaceDark = SeedColors.White.FadeDark
internal val OnSurfaceDark = SeedColors.Black.Dark
internal val SurfaceVariantDark = SeedColors.White.FadeDark
internal val OnSurfaceVariantDark = SeedColors.Black.Dark
internal val InverseSurfaceDark = SeedColors.Black.Dark
internal val InverseOnSurfaceDark = SeedColors.White.Dark
internal val SurfaceBrightDark = SeedColors.White.FadeDark
internal val SurfaceContainerDark = SeedColors.White.FadeDark
internal val SurfaceContainerHighDark = SeedColors.White.FrontDark
internal val SurfaceContainerHighestDark = SeedColors.White.FrontDark
internal val SurfaceContainerLowDark = SeedColors.White.Dark
internal val SurfaceContainerLowestDark = SeedColors.White.Dark
internal val SurfaceDimDark = SeedColors.White.FrontDark
internal val SurfaceTintDark = SeedColors.White.FrontDark

internal val ErrorDark = SeedColors.Other.ErrorDark
internal val OnErrorDark = SeedColors.White.Dark
internal val ErrorContainerDark = SeedColors.White.Dark
internal val OnErrorContainerDark = SeedColors.Other.ErrorDark

internal val OutlineDark = SeedColors.Black.GrayDark
internal val OutlineVariantDark = SeedColors.Black.DarkGrayDark

internal val ScrimDark = SeedColors.Black.Dark
