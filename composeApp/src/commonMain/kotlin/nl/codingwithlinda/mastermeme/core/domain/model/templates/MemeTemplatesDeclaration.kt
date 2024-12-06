package nl.codingwithlinda.mastermeme.core.domain.model.templates
import mastermeme.composeapp.generated.resources.Res
import mastermeme.composeapp.generated.resources._0_2reqtg
import mastermeme.composeapp.generated.resources._0_3x8soh
import mastermeme.composeapp.generated.resources._0_8c9dbh
import mastermeme.composeapp.generated.resources._0__i_bet_hes_thinking_about_other_women
import mastermeme.composeapp.generated.resources._1_49su9f
import mastermeme.composeapp.generated.resources._1__third_world_skeptical_kid
import mastermeme.composeapp.generated.resources._1_clown_applying_makeup
import mastermeme.composeapp.generated.resources._1_is_this_a_pigeon
import mastermeme.composeapp.generated.resources._2_1yz6z4
import mastermeme.composeapp.generated.resources._2_2eb198
import mastermeme.composeapp.generated.resources._2_3eqjd8
import mastermeme.composeapp.generated.resources._2_7wxtd1
import mastermeme.composeapp.generated.resources._3_2byuiy
import mastermeme.composeapp.generated.resources._3__43iacv
import mastermeme.composeapp.generated.resources._3_jack_sparrow_being_chased
import mastermeme.composeapp.generated.resources._3_waiting_skeleton
import mastermeme.composeapp.generated.resources._4_1jgrgn
import mastermeme.composeapp.generated.resources._4_leonardo_dicaprio_cheers
import mastermeme.composeapp.generated.resources._4_running_away_balloon
import mastermeme.composeapp.generated.resources._4_scared
import mastermeme.composeapp.generated.resources._5_1op9wy
import mastermeme.composeapp.generated.resources._5_24zoa8
import mastermeme.composeapp.generated.resources._5_58eyvu
import mastermeme.composeapp.generated.resources._5_i_was_told_there_would_be
import mastermeme.composeapp.generated.resources._6_21ajtl
import mastermeme.composeapp.generated.resources._6_2t8r9a
import mastermeme.composeapp.generated.resources._6_3f0mvv
import mastermeme.composeapp.generated.resources._6_boardroom_meeting_suggestion
import mastermeme.composeapp.generated.resources._7_2w2e5e
import mastermeme.composeapp.generated.resources._7_34vt4i
import mastermeme.composeapp.generated.resources._7_3igo27
import mastermeme.composeapp.generated.resources._8_14p2is
import mastermeme.composeapp.generated.resources._8_59c1hh
import mastermeme.composeapp.generated.resources._8_64sz4u
import mastermeme.composeapp.generated.resources._8_two_buttons
import mastermeme.composeapp.generated.resources._9_2bbctk
import mastermeme.composeapp.generated.resources._9_2qx7sw
import mastermeme.composeapp.generated.resources._9_6rcrc1
import mastermeme.composeapp.generated.resources._9_6u6ylb
import mastermeme.composeapp.generated.resources.___1__grus_plan
import mastermeme.composeapp.generated.resources.___change_my_mind_
import mastermeme.composeapp.generated.resources.___hide_the_pain_harold
import mastermeme.composeapp.generated.resources.___the_rock_driving
import mastermeme.composeapp.generated.resources.___two_buttons
import mastermeme.composeapp.generated.resources.__disaster_girl
import mastermeme.composeapp.generated.resources.__epic_handshake
import mastermeme.composeapp.generated.resources.__left_exit_12_off_ramp
import mastermeme.composeapp.generated.resources.__sad_pablo_escobar
import mastermeme.composeapp.generated.resources.vector_18
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.getDrawableResourceBytes
import org.jetbrains.compose.resources.getSystemResourceEnvironment

val drawableNames = listOf(
    Res.drawable._0_2reqtg ,
    Res.drawable ._0_3x8soh ,
    Res.drawable ._0_8c9dbh ,
    Res.drawable ._0__i_bet_hes_thinking_about_other_women ,
    Res.drawable ._1_49su9f ,
    Res.drawable ._1__third_world_skeptical_kid ,
    Res.drawable ._1_clown_applying_makeup ,
    Res.drawable ._1_is_this_a_pigeon ,
    Res.drawable ._2_1yz6z4 ,
    Res.drawable ._2_2eb198 ,
    Res.drawable ._2_3eqjd8 ,
    Res.drawable ._2_7wxtd1 ,
    Res.drawable ._3_2byuiy ,
    Res.drawable ._3__43iacv ,
    Res.drawable ._3_jack_sparrow_being_chased ,
    Res.drawable ._3_waiting_skeleton ,
    Res.drawable ._4_1jgrgn ,
    Res.drawable ._4_leonardo_dicaprio_cheers ,
    Res.drawable ._4_running_away_balloon ,
    Res.drawable ._4_scared ,
    Res.drawable ._5_1op9wy ,
    Res.drawable ._5_24zoa8 ,
    Res.drawable ._5_58eyvu ,
    Res.drawable ._5_i_was_told_there_would_be ,
    Res.drawable ._6_2t8r9a ,
    Res.drawable ._6_3f0mvv ,
    Res.drawable ._6_21ajtl ,
    Res.drawable ._6_boardroom_meeting_suggestion ,
    Res.drawable ._7_2w2e5e ,
    Res.drawable ._7_3igo27 ,
    Res.drawable ._7_34vt4i ,
    Res.drawable ._8_14p2is ,
    Res.drawable ._8_59c1hh ,
    Res.drawable ._8_64sz4u ,
    Res.drawable ._8_two_buttons ,
    Res.drawable ._9_2bbctk ,
    Res.drawable ._9_2qx7sw ,
    Res.drawable ._9_6rcrc1,
    Res.drawable._9_6u6ylb ,
    Res.drawable .___1__grus_plan ,
    Res.drawable .___change_my_mind_ ,
    Res.drawable .___hide_the_pain_harold ,
    Res.drawable .___the_rock_driving ,
    Res.drawable .___two_buttons ,
    Res.drawable .__disaster_girl ,
    Res.drawable .__epic_handshake ,
    Res.drawable .__left_exit_12_off_ramp ,
    Res.drawable .__sad_pablo_escobar ,
)

@OptIn(ExperimentalResourceApi::class)
suspend fun templateToBytes(
    drawableResource: DrawableResource
): ByteArray {
    return getDrawableResourceBytes(
        environment = getSystemResourceEnvironment(),
        resource = drawableResource,
    )
}
val templates: List<MemeTemplate> = drawableNames.map {
    MemeTemplate(
        id = it.toString(),
        drawableResource = it
    )
}
val emptyMemeTemplate = MemeTemplate(
    id = "",
    drawableResource = Res.drawable.vector_18
)