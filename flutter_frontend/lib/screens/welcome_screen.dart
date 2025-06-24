import 'package:flutter/material.dart';

class WelcomeScreen extends StatelessWidget {
  final String userName;

  const WelcomeScreen({super.key, required this.userName});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Welcome')),
      body: Center(
        child: Text(
          'Hello, $userName!',
          style: const TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
        ),
      ),
    );
  }
}
