using Newtonsoft.Json;

namespace SportAppServer.Entities.Models
{
    public class SportMatch
    {
        [JsonProperty("competition_id")]
        public required string CompetitionId { get; set; }


        [JsonProperty("season_id")]
        public string SeasonId { get; set; }


        [JsonProperty("match_id")]
        public string MatchId { get; set; }


        [JsonProperty("match_unique_code")]
        public string MatchUniqueCode { get; set; }


        [JsonProperty("date")]
        public DateTime Date { get; set; }


        [JsonProperty("match_day_id")]
        public string MatcDayId { get; set; }


        [JsonProperty("match_day_name")]
        public string MatchDayName { get; set; }


        [JsonProperty("match_day_short_name")]
        public string MatchDayShortName { get; set; }


        [JsonProperty("date_order")]
        public string DateOrder { get; set; }


        [JsonProperty("team_a_id")]
        public string TeamAId { get; set; }


        [JsonProperty("teamAName")]
        public string TeamAName { get; set; }


        [JsonProperty("team_a_short_name")]
        public string TeamAShortName { get; set; }


        [JsonProperty("team_a_acronym")]
        public string TeamAAcronym { get; set; }


        [JsonProperty("team_b_id")]
        public string TeamBId { get; set; }


        [JsonProperty("team_b_name")]
        public string TeamBName { get; set; }


        [JsonProperty("team_b_short_name")]
        public string TeamBShortName { get; set; }


        [JsonProperty("team_b_acronym")]
        public string TeamBAcronym { get; set; }


        [JsonProperty("goals_team_a")]
        public int GoalsTeamA { get; set; }


        [JsonProperty("goals_team_b")]
        public int GoalsTeamB { get; set; }


        [JsonProperty("stadium_name")]
        public string StadiumName { get; set; }


        [JsonProperty("stadium_city")]
        public string StadiumCity { get; set; }


        [JsonProperty("match_status")]
        public int MatchStatus { get; set; }


        [JsonProperty("is_abandoned")]
        public int IsAbandoned { get; set; }


        [JsonProperty("is_postponed")]
        public int IsPostponed { get; set; }


        [JsonProperty("is_suspended")]
        public int IsSuspended { get; set; }


        [JsonProperty("broadcaster")]
        public string Broadcaster { get; set; }


        [JsonProperty("match_start_time")]
        public string MatchStartTime { get; set; }


        [JsonProperty("match_phase")]
        public string MatchPhase { get; set; }


        [JsonProperty("opta_id")]
        public string OptaId { get; set; }


        [JsonProperty("is_forfeit_win")]
        public int IsForfeitWin { get; set; }


        [JsonProperty("minute")]
        public int Minute { get; set; }
    }
}
