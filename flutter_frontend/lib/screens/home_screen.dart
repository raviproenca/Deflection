import 'package:flutter/material.dart';
import '../models/prediction_request.dart';
import '../services/api_service.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  final _apiService = ApiService();
  String result = '';

  Future<void> _sendPrediction() async {
    try {
      final request = PredictionRequest(
        ip: "192.168.1.1",
        loginAttempts: 4,
        country: "US",
        timeOfDay: "19:25",
      );

      final response = await _apiService.predictThreat(request);

      setState(() {
        result = response.toString();
      });
    } catch (e) {
      setState(() {
        result = 'Erro: $e';
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Deflection ML')),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            ElevatedButton(
              onPressed: _sendPrediction,
              child: const Text('Enviar para ML'),
            ),
            const SizedBox(height: 20),
            Text(result),
          ],
        ),
      ),
    );
  }
}
