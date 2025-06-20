class PredictionRequest {
  final String ip;
  final int loginAttempts;
  final String country;
  final String timeOfDay;

  PredictionRequest({
    required this.ip,
    required this.loginAttempts,
    required this.country,
    required this.timeOfDay,
  });

  Map<String, dynamic> toJson() {
    return {
      'ip': ip,
      'loginAttempts': loginAttempts,
      'country': country,
      'timeOfDay': timeOfDay,
    };
  }
}
