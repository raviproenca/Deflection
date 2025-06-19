from fastapi import FastAPI
from pydantic import BaseModel
import numpy as np

app = FastAPI(title="Deflection")

class logData(BaseModel):
   ip: str
   login_attempts: int
   country: str
   time_of_day: str

@app.get("/")
def read_root():
   return {"message": "Deflection ML API is running"}

@app.post("/ml/predict")
def predict_threat(data: logData):
   fake_score = np.random.rand()

   threat_level = "Low"
   if fake_score > 0.7:
      threat_level = "High"
   elif fake_score > 0.4:
      threat_level = "Medium"

   return {
      "ip": data.ip,
      "score": round(float(fake_score), 2),
      "threat_level": threat_level,
      "recommendation": "Block IP" if threat_level == "High" else "Monitor"
   }
