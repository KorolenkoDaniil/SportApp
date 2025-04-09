using Newtonsoft.Json;

namespace SportAppServer.Entities.Models
{
    public class SportMatch
    {
        [JsonProperty("competition_id")]
        public required string CompetitionId { get; set; }


        [JsonProperty("season_id")]
        public required string SeasonId { get; set; }


        [JsonProperty("match_id")]
        public required string MatchId { get; set; }


        [JsonProperty("match_unique_code")]
        public required string MatchUniqueCode { get; set; }


        [JsonProperty("date")]
        public DateTime Date { get; set; }


        [JsonProperty("match_day_id")]
        public required string MatcDayId { get; set; }


        [JsonProperty("match_day_name")]
        public required string MatchDayName { get; set; }


        [JsonProperty("match_day_short_name")]
        public required string MatchDayShortName { get; set; }


        [JsonProperty("date_order")]
        public required string DateOrder { get; set; }


        [JsonProperty("team_a_id")]
        public required string TeamAId { get; set; }


        [JsonProperty("teamAName")]
        public required string TeamAName { get; set; }


        [JsonProperty("team_a_short_name")]
        public required string TeamAShortName { get; set; }


        [JsonProperty("team_a_acronym")]
        public required string TeamAAcronym { get; set; }


        [JsonProperty("team_b_id")]
        public required string TeamBId { get; set; }


        [JsonProperty("team_b_name")]
        public required string TeamBName { get; set; }


        [JsonProperty("team_b_short_name")]
        public required string TeamBShortName { get; set; }


        [JsonProperty("team_b_acronym")]
        public required string TeamBAcronym { get; set; }


        [JsonProperty("goals_team_a")]
        public int GoalsTeamA { get; set; }


        [JsonProperty("goals_team_b")]
        public int GoalsTeamB { get; set; }


        [JsonProperty("stadium_name")]
        public required string StadiumName { get; set; }


        [JsonProperty("stadium_city")]
        public required string StadiumCity { get; set; }


        [JsonProperty("match_status")]
        public int MatchStatus { get; set; }


        [JsonProperty("is_abandoned")]
        public int IsAbandoned { get; set; }


        [JsonProperty("is_postponed")]
        public int IsPostponed { get; set; }


        [JsonProperty("is_suspended")]
        public int IsSuspended { get; set; }


        [JsonProperty("broadcaster")]
        public required string Broadcaster { get; set; }


        [JsonProperty("match_start_time")]
        public required string MatchStartTime { get; set; }


        [JsonProperty("match_phase")]
        public required string MatchPhase { get; set; }


        [JsonProperty("opta_id")]
        public required string OptaId { get; set; }


        [JsonProperty("is_forfeit_win")]
        public int IsForfeitWin { get; set; }


        [JsonProperty("minute")]
        public int Minute { get; set; }
    }
}
