from selenium import webdriver
from selenium.webdriver.common.by import By
import time
from datetime import datetime
import json
import os
import requests
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import traceback

class News:
    def __init__(self, sport, date_time, title, image_id, article_texts):
        self.sport = sport
        self.date_time = date_time
        self.title = title
        self.image_id = image_id
        self.article_texts = article_texts
        

    def to_dict(self):
        return {
            "sport": self.sport,
            "date_time": self.date_time,
            "title": self.title,
            "image_id": self.image_id,
            "article_texts": self.article_texts,
        }

def copy_news_text(driver):
    try:

        article = WebDriverWait(driver, 10).until(
            EC.presence_of_element_located((By.CLASS_NAME, "mt-40"))
        )
        
        driver.execute_script("arguments[0].scrollIntoView(true);", article)

        texts = article.find_elements(By.TAG_NAME, "p")

        print(f"---------- количество найденных текстов: {len(texts)}")

        article_texts = []

        print("========================")
        for text in texts:
            article_texts.append(text.text)
        print("========================")

        return article_texts

    except Exception as e:
        print("Ошибка при скачивании изображения22:", e)






def downloadImage(driver):
    try:
      
        image_element = WebDriverWait(driver, 10).until(EC.presence_of_element_located((By.CLASS_NAME, "article-pic")))
     
        driver.execute_script("arguments[0].scrollIntoView(true);", image_element)
        image_url = image_element.get_attribute("src")
        print(f"Ссылка на изображение: {image_url}")
 
        save_folder = "C:\\Users\\korol\\AndroidStudioProjects\\SportApp\\SportAppServer\\savedImages"
        os.makedirs(save_folder, exist_ok=True)
        response = requests.get(image_url)
        if response.status_code == 200:
            image_name = os.path.join(save_folder, image_url.split('/')[-1]) 
            with open(image_name, "wb") as file:
                file.write(response.content)
            print(f"Изображение сохранено в {image_name}")
            return image_url.split('/')[-1]
        else:
            print(f"Не удалось скачать изображение, код ответа: {response.status_code}")
    except Exception as e:
        print("Ошибка при скачивании изображения:", e)



def search_news_list(driver):
    time.sleep(3)
    news_elements = driver.find_elements(By.CLASS_NAME, "wrapper-text-news")
    print("Количество новостей:", len(news_elements))

    news_list = []
    i = 0

    for news_element in news_elements:
        try:

            news_elements = driver.find_elements(By.CLASS_NAME, "wrapper-text-news")

            sport = news_elements[i].find_element(By.CLASS_NAME, "sport").text.strip()
            time_only = news_elements[i].find_element(By.CLASS_NAME, "time").text.strip()
            title = news_elements[i].find_element(By.TAG_NAME, "h5").text.strip()
            current_date_only = datetime.now().strftime("%Y-%m-%d")
            full_date = f"{current_date_only}T{time_only}:00"

           
            WebDriverWait(driver, 10).until(EC.element_to_be_clickable(news_elements[i])).click()

            time.sleep(3)
            image_id = downloadImage(driver)
            if (image_id != None):
                print("вернулась строка " + image_id)

                new_news = News(sport=sport, date_time=full_date, title=title, image_id=image_id, article_texts=copy_news_text(driver))
                news_list.append(new_news)

                driver.back()
                time.sleep(3)
            else:
                print("video")
                # TODO


            i += 1
        except Exception as e:
            
            print(f"Ошибка обработки элемента: {e}")
           
            print("Подробности ошибки:")
            print(traceback.format_exc())

    file_name = "C:/Users/korol/AndroidStudioProjects/SportApp/SportAppServer/news_list.json"
    with open(file_name, "w", encoding="utf-8") as file:
        json.dump([n.to_dict() for n in news_list], file, ensure_ascii=False, indent=4)


driver = webdriver.Chrome()
driver.get("https://sport5.by/")
driver.maximize_window()
print("window is max opened")
search_news_list(driver)
