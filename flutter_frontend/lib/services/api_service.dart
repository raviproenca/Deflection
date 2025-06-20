import 'dart:convert';
import 'package:http/http.dart' as http;
import '../models/prediction_request.dart';

class ApiService {
  static const String baseUrl = 'http://localhost:8080';

  Future<Map<String, dynamic>> predictThreat(PredictionRequest request) async {
    final url = Uri.parse('$baseUrl/ml/predict');

    final response = await http.post(
      url,
      headers: {'Content-Type': 'application/json'},
      body: jsonEncode(request.toJson()),
    );

    if (response.statusCode == 200) {
      return jsonDecode(response.body);
    } else {
      throw Exception('Erro na requisição: ${response.statusCode}');
    }
  }
}
