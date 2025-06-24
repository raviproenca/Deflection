import 'package:flutter/material.dart';
import 'package:flutter_frontend/models/users_login.dart';
import 'package:flutter_frontend/screens/register_screen.dart';
import 'package:flutter_frontend/screens/welcome_screen.dart';
import 'package:flutter_frontend/services/api_service.dart';

class LoginScreen extends StatefulWidget {
  const LoginScreen({Key? key}) : super(key: key);

  @override
  State<LoginScreen> createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {
  final _apiService = ApiService();
  final _emailController = TextEditingController();
  final _passwordController = TextEditingController();

  Future<void> _login() async {
    String email = _emailController.text;
    String password = _passwordController.text;

    try {
      final request = UserLogin(email: email, password: password);
      final user = await _apiService.getUser(request);

      Navigator.push(
        context,
        MaterialPageRoute(
          builder: (context) => WelcomeScreen(userName: user.name),
        ),
      );
    } catch (e) {
      ScaffoldMessenger.of(
        context,
      ).showSnackBar(SnackBar(content: Text('Login failed: $e')));
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: SingleChildScrollView(
          padding: const EdgeInsets.symmetric(horizontal: 32),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              // LOGO ou TÍTULO
              const Icon(
                Icons.shield_outlined,
                color: Colors.deepPurple,
                size: 100,
              ),
              const SizedBox(height: 16),
              const Text(
                'DEFLECTION',
                style: TextStyle(
                  fontSize: 28,
                  color: Colors.deepPurple,
                  fontWeight: FontWeight.bold,
                  letterSpacing: 2,
                ),
              ),
              const SizedBox(height: 40),

              // EMAIL FIELD
              TextField(
                controller: _emailController,
                style: const TextStyle(color: Colors.white),
                decoration: InputDecoration(
                  filled: true,
                  fillColor: const Color(0xFF1E293B),
                  labelText: 'Email',
                  labelStyle: const TextStyle(color: Colors.white70),
                  border: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(12),
                  ),
                  prefixIcon: const Icon(
                    Icons.email_outlined,
                    color: Colors.white70,
                  ),
                ),
              ),
              const SizedBox(height: 20),

              // PASSWORD FIELD
              TextField(
                controller: _passwordController,
                obscureText: true,
                style: const TextStyle(color: Colors.white),
                decoration: InputDecoration(
                  filled: true,
                  fillColor: const Color(0xFF1E293B),
                  labelText: 'Senha',
                  labelStyle: const TextStyle(color: Colors.white70),
                  border: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(12),
                  ),
                  prefixIcon: const Icon(
                    Icons.lock_outline,
                    color: Colors.white70,
                  ),
                ),
              ),
              const SizedBox(height: 10),

              // Forgot Password
              Align(
                alignment: Alignment.centerRight,
                child: TextButton(
                  onPressed: () {},
                  child: const Text(
                    'Esqueceu a senha?',
                    style: TextStyle(color: Color(0xFF38BDF8)),
                  ),
                ),
              ),

              const SizedBox(height: 20),

              // LOGIN BUTTON
              SizedBox(
                width: double.infinity,
                child: ElevatedButton(
                  onPressed: _login,
                  style: ElevatedButton.styleFrom(
                    backgroundColor: const Color(0xFF38BDF8),
                    padding: const EdgeInsets.symmetric(vertical: 16),
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(12),
                    ),
                  ),
                  child: const Text(
                    'ENTRAR',
                    style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold),
                  ),
                ),
              ),

              const SizedBox(height: 20),

              // Divider
              Row(
                children: const [
                  Expanded(child: Divider(color: Colors.white24)),
                  Padding(
                    padding: EdgeInsets.symmetric(horizontal: 8),
                    child: Text('ou', style: TextStyle(color: Colors.white54)),
                  ),
                  Expanded(child: Divider(color: Colors.white24)),
                ],
              ),
              const SizedBox(height: 20),

              // CREATE ACCOUNT
              TextButton(
                onPressed: () {
                  Navigator.push(
                    context,
                    MaterialPageRoute(
                      builder: (context) => const RegisterScreen(),
                    ),
                  );
                },
                child: const Text(
                  'Criar nova conta',
                  style: TextStyle(color: Color(0xFF38BDF8)),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
