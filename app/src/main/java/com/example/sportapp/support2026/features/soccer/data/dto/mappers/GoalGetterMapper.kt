package com.example.sportapp.support2026.features.soccer.data.dto.mappers

import com.example.sportapp.support2026.features.soccer.data.dto.GoalDto
import com.example.sportapp.support2026.features.soccer.data.dto.GoalGetterDto
import com.example.sportapp.support2026.features.soccer.domain.entities.Goal
import com.example.sportapp.support2026.features.soccer.domain.entities.GoalGetter

fun GoalDto.toDomain(): Goal {
    return Goal(
        goalID = this.goalID ?: 0,
        scoreTeam1 = this.scoreTeam1 ?: 0,
        scoreTeam2 = this.scoreTeam2 ?: 0,
        matchMinute = this.matchMinute ?: 0,
        goalGetterID = this.goalGetterID ?: 0,
        goalGetterName = this.goalGetterName.orEmpty(),
        scoringTeamId = this.scoringTeamId ?: 0,
        isPenalty = this.isPenalty ?: false,
        isOwnGoal = this.isOwnGoal ?: false,
        isOvertime = this.isOvertime ?: false,
        comment = this.comment.orEmpty()
    )
}


fun GoalGetter.toDto(): GoalGetterDto {
    return GoalGetterDto(
        goalGetterId = this.goalGetterId,
        goalGetterName = this.goalGetterName,
        goalCount = this.goalCount
    )
}