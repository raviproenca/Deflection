from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
import numpy as np
import logging

app = FastAPI(title="Deflection")
logger = logging.getLogger("uvicorn.error")

class logData(BaseModel):
    ip: str
    noise: bool
    riot: bool
    classification: str
    abuseConfidenceScore: int
    totalReports: int
    numDistinctUsers: int
    reputation: int
    malicious: int
    suspicious: int
    undetected: int
    harmless: int
    country: str
    asOwner: str
    asn: int

@app.post("/ml/predict")
def predict_threat(data: logData):
    try:
        fake_score = np.random.rand()

        if not data.ip:
            raise ValueError("IP inválido")

        threat_level = "Low"
        if fake_score > 0.7:
            threat_level = "High"
        elif fake_score > 0.4:
            threat_level = "Medium"

        return {
            "ip": data.ip,
            "noise": data.noise,
            "riot": data.riot,
            "score": round(float(fake_score), 2),
            "threat_level": threat_level,
            "recommendation": "Block IP" if threat_level == "High" else "Monitor"
        }

    except ValueError as ve:
        logger.error(f"Erro de validação: {ve}")
        raise HTTPException(status_code=422, detail=str(ve))

    except Exception as e:
        logger.error(f"Erro interno no modelo: {e}")
        raise HTTPException(status_code=500, detail="Erro interno na predição")
