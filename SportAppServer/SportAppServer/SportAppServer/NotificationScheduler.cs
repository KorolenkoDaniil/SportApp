using FirebaseAdmin.Messaging;
using Newtonsoft.Json;
using SportAppServer.Models.DTOs.Requests;

namespace SportAppServer
{
    public class NotificationScheduler
    {
        private string caledarPath = Path.Combine(Directory.GetCurrentDirectory(), "calendar.json");

        private List<SportMatch> matches = [];

        private string baseURL = "https://9f07-70-34-247-241.ngrok-free.app";



        public void Deserialisation()
        {
            using (StreamReader calendarReader = new StreamReader(caledarPath))
            {
                var text = calendarReader.ReadToEndAsync();

                matches = JsonConvert.DeserializeObject<MatchesResponse>(text.Result)!.Items;
            }
        }


        public async Task CheckNearestDateAndSetNotificationAsync()
        {
            DateTime currentTime = DateTime.Now;

          
            SportMatch closestMatch = matches
                .Where(match => match.Date > currentTime)
                .OrderBy(match => match.Date)
                .FirstOrDefault()!;

   
       
            TimeSpan delay = closestMatch.Date - currentTime - TimeSpan.FromMinutes(15);
            if (delay <= TimeSpan.Zero)
            {
                Console.WriteLine("Матч уже начался или начинается менее чем через 15 минут.");
                return;
            }

            Console.WriteLine($"Ближайший матч: {closestMatch.TeamAShortName} - {closestMatch.TeamBShortName} {closestMatch.Date}");

            await Task.Delay(delay);

            var message = new Message()
            {
                Notification = new Notification
                {
                    Title = "Скоро начнется матч!",
                    Body = $"{closestMatch.TeamAShortName} vs {closestMatch.TeamBShortName} начнется в {closestMatch.Date}",
                    ImageUrl = baseURL + "/images/kor_sport.png",
                },
                Android = new AndroidConfig
                {
                    Notification = new AndroidNotification
                    {
                        Icon = "ic_sport_icon", // Здесь укажите имя ресурса иконки из Android
                    }
                },
                Topic = "MatchNotification"
            };


            try
            {
                string response = await FirebaseMessaging.DefaultInstance.SendAsync(message);
                Console.WriteLine($"Уведомление успешно отправлено: {response}");
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Ошибка при отправке уведомления: {ex.Message}");
            }

            await CheckNearestDateAndSetNotificationAsync();
        }




    }
}
