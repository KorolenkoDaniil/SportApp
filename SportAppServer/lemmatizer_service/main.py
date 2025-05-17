import nltk
nltk.download('stopwords')

from fastapi import FastAPI
from pydantic import BaseModel
from typing import List
import pymorphy2
from nltk.corpus import stopwords
import re

app = FastAPI()
morph = pymorphy2.MorphAnalyzer()
stop_words = set(stopwords.words("russian"))

class SearchQuery(BaseModel):
    text: str

class LemmaResponse(BaseModel):
    lemmas: str  # Тип данных изменен на str

def preprocess_text(text: str) -> List[str]:
    text = text.strip()
    text = re.sub(r'[^\w\s]', '', text)  
    words = text.split()

    lemmas = []
    for word in words:
        word_lower = word.lower()
        if word_lower in stop_words:
            continue
        parsed = morph.parse(word)[0]
        if 'Name' in parsed.tag or 'Surn' in parsed.tag or 'Geox' in parsed.tag: 
            lemmas.append(word_lower) 
        else:
            lemmas.append(parsed.normal_form)

    return lemmas



@app.post("/lemmatize", response_model=LemmaResponse)
async def lemmatize_text(query: SearchQuery):
    lemmas = preprocess_text(query.text)
    lemmas_str = " ".join(lemmas)  
    return LemmaResponse(lemmas=lemmas_str) 
