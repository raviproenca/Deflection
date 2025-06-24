import 'dart:convert';
import 'package:flutter_frontend/models/users_register.dart';
import 'package:flutter_frontend/models/users_login.dart';
import 'package:flutter_frontend/models/users_response';
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

  Future<Map<String, dynamic>> newUser(UserRequest request) async {
    final url = Uri.parse('$baseUrl/users/register');

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

  Future<UserResponse> getUser(UserLogin request) async {
    final url = Uri.parse('http://localhost:8080/users/login');

    final response = await http.post(
      url,
      headers: {'Content-Type': 'application/json'},
      body: jsonEncode({'email': request.email, 'password': request.password}),
    );

    if (response.statusCode == 200) {
      final data = jsonDecode(response.body);
      return UserResponse.fromJson(data);
    } else {
      throw Exception('Failed to login');
    }
  }
}
