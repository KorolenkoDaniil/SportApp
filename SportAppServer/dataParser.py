from selenium import webdriver
from selenium.webdriver.common.by import By
import time
from datetime import datetime
import json
import os


class News:
    def __init__(self, sport, date_time, text):
        self.sport = sport
        self.date_time = date_time
        self.text = text

    def to_dict(self):
        return {
            "sport": self.sport,
            "date_time": self.date_time, 
            "text": self.text
        }


driver = webdriver.Chrome()
driver.get("https://sport5.by/")
driver.maximize_window()

print("window is max opened")

def search_news_list():
    time.sleep(3)
    news_elements = driver.find_elements(By.CLASS_NAME, "wrapper-text-news")
    print("Количество новостей:", len(news_elements))

    news_list = []

    print(len(news_elements))
    for news_element in news_elements:
        try:
            sport = news_element.find_element(By.CLASS_NAME, "sport").text.strip()
            time_only = news_element.find_element(By.CLASS_NAME, "time").text.strip()
            text = news_element.find_element(By.TAG_NAME, "h5").text.strip()

            current_date_only = datetime.now().strftime("%Y-%m-%d")
            full_date = f"{current_date_only}T{time_only}:00"

            news = News(sport=sport, date_time=full_date, text=text)
            news_list.append(news)
            print(len(news_list))

            print(news)

        except Exception as e:
            print(f"Ошибка обработки элемента: {e}")

    file_name = "C:\\Users\\korol\\AndroidStudioProjects\\SportApp\\SportAppServer\\news_list.json"

    with open(file_name, "w", encoding="utf-8") as file:
       json.dump([news.to_dict() for news in news_list], file, ensure_ascii=False, indent=4)

search_news_list()

