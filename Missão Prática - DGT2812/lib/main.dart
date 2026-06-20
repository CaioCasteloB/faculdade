import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Demonstração de layout Flutter',
      home: Scaffold(
        appBar: AppBar(title: const Text('Demonstração de layout Flutter')),
        body: ListView(
          children: [
            Image.asset(
              'assets/images/lake.jpg',
              width: 600,
              height: 240,
              fit: BoxFit.cover,
            ),

            titleSection(),
            buttonSection(),
            textSection(),

            // Aqui você poderá incluir outros widgets do seu app.
          ],
        ),
      ),
    );
  }

  // --- Seção de título ---
  Widget titleSection() {
    return Container(
      padding: const EdgeInsets.all(32),
      child: Row(
        children: [
          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                const Text(
                  'Oeschinen Lake Campground',
                  style: TextStyle(fontWeight: FontWeight.bold),
                ),
                const SizedBox(height: 8),
                Text(
                  'Kandersteg, Switzerland',
                  style: TextStyle(color: Colors.grey[500]),
                ),
              ],
            ),
          ),
          const Icon(Icons.star, color: Colors.red),
          const Text('41'),
        ],
      ),
    );
  }

  // --- Seção de botões ---
  Widget buttonSection() {
    return Row(
      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
      children: [
        buildButtonColumn(Icons.call, 'CALL'),
        buildButtonColumn(Icons.near_me, 'ROUTE'),
        buildButtonColumn(Icons.share, 'SHARE'),
      ],
    );
  }

  Widget buildButtonColumn(IconData icon, String label) {
    return Column(
      mainAxisSize: MainAxisSize.min,
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        Icon(icon, color: Colors.blue),
        const SizedBox(height: 8),
        Text(
          label,
          style: const TextStyle(
            fontSize: 12,
            fontWeight: FontWeight.w400,
            color: Colors.blue,
          ),
        ),
      ],
    );
  }

  // --- Seção de texto ---
  Widget textSection() {
    return const Padding(
      padding: EdgeInsets.all(32),
      child: Text(
        'O Lago Oeschinen fica aos pés do Blüemlisalp nos Alpes Berneses. '
        'Situado a 1.578 metros acima do nível do mar, é um dos lagos alpinos mais '
        'amplos. Um passeio de teleférico a partir de Kandersteg, seguido por meia hora '
        'de caminhada por pastagens e floresta de pinheiros, leva você ao lago, que '
        'aquece até 20 graus Celsius no verão. As atividades desfrutadas aqui incluem '
        'remo e andar no tobogã de verão.',
        softWrap: true,
      ),
    );
  }
}
