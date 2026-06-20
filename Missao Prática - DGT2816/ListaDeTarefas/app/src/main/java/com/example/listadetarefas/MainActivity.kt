package com.example.listadetarefas

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.AudioDeviceCallback
import android.media.AudioDeviceInfo
import android.media.AudioManager
import android.os.Bundle
import android.provider.Settings
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    // Lista simulando mensagens importantes para o funcionário
    private val mensagens = mutableListOf(
        "Aviso de segurança: área de manutenção isolada.",
        "Próxima pausa às 10h.",
        "Lembrete: hidratação a cada 30 minutos.",
        "Mensagem da coordenação: Reunião acessível na sala 2."
    )

    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var tts: TextToSpeech
    private lateinit var audioManager: AudioManager

    // gerador de falas dinâmicas (poderia ser mensagens novas vindas da empresa)
    private var proxId = 1

    // ── Passo 3: Detecção Dinâmica de Dispositivos de Áudio ──────────────────
    // Callback registrado no AudioManager para monitorar conexão/desconexão
    // de dispositivos de áudio em tempo real enquanto o app está em execução.
    private val audioDeviceCallback = object : AudioDeviceCallback() {
        override fun onAudioDevicesAdded(addedDevices: Array<out AudioDeviceInfo>?) {
            super.onAudioDevicesAdded(addedDevices)
            if (saidaAudioDisponivel(AudioDeviceInfo.TYPE_BLUETOOTH_A2DP)) {
                Log.d("WearDebug", "Fone Bluetooth conectado")
                Toast.makeText(
                    this@MainActivity,
                    "Fone Bluetooth conectado — áudio disponível.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        override fun onAudioDevicesRemoved(removedDevices: Array<out AudioDeviceInfo>?) {
            super.onAudioDevicesRemoved(removedDevices)
            if (!saidaAudioDisponivel(AudioDeviceInfo.TYPE_BLUETOOTH_A2DP)) {
                Log.d("WearDebug", "Fone Bluetooth desconectado")
                Toast.makeText(
                    this@MainActivity,
                    "Fone Bluetooth desconectado.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        Log.d("WearDebug", "App Assistente Doma iniciado")
        Toast.makeText(this, "Assistente Doma pronto", Toast.LENGTH_SHORT).show()

        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager

        // Registra o callback para detecção dinâmica (Passo 3)
        audioManager.registerAudioDeviceCallback(audioDeviceCallback, null)

        // inicia TTS (texto → fala)
        tts = TextToSpeech(this, this)

        // pega referências de UI
        val listView = findViewById<ListView>(R.id.lista)
        val botaoFalar = findViewById<Button>(R.id.buttonFalar)

        // conecta lista de mensagens ao ListView
        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            mensagens
        )
        listView.adapter = adapter

        // tocar em uma mensagem = ler aquela mensagem em voz alta
        listView.setOnItemClickListener { _, _, position, _ ->
            val texto = mensagens[position]
            falarTextoSePossivel(texto)
        }

        // botão "Falar alerta" = gerar um novo alerta e ler em voz alta
        botaoFalar.setOnClickListener {
            val novoAlerta = "Alerta urgente ${proxId}: Dirija-se ao ponto de encontro imediatamente."
            proxId += 1

            mensagens.add(0, novoAlerta) // coloca no topo
            adapter.notifyDataSetChanged()

            falarTextoSePossivel(novoAlerta)
        }
    }

    // callback do TextToSpeech init
    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            // tenta configurar português BR
            val result = tts.setLanguage(Locale("pt", "BR"))
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.w("WearDebug", "TTS: idioma pt-BR não suportado, usando padrão")
                tts.language = Locale.getDefault()
            }
        } else {
            Log.e("WearDebug", "Falha ao inicializar TTS")
        }
    }

    // Fala em voz alta se houver saída de áudio disponível
    private fun falarTextoSePossivel(texto: String) {
        if (saidaAudioDisponivel(AudioDeviceInfo.TYPE_BLUETOOTH_A2DP) ||
            saidaAudioDisponivel(AudioDeviceInfo.TYPE_BUILTIN_SPEAKER)
        ) {
            // Temos onde tocar áudio -> falar
            tts.speak(texto, TextToSpeech.QUEUE_FLUSH, null, "DOMA_TTS")
            Toast.makeText(this, "Lendo em voz alta…", Toast.LENGTH_SHORT).show()
        } else {
            // ── Passo 4: Facilitar a Conexão Bluetooth ────────────────────────
            // Sem saída de áudio disponível: direciona o usuário às configurações
            // Bluetooth para parear um fone de ouvido, em vez de apenas exibir erro.
            Toast.makeText(
                this,
                "Sem saída de áudio. Abrindo configurações Bluetooth…",
                Toast.LENGTH_SHORT
            ).show()

            val intent = Intent(Settings.ACTION_BLUETOOTH_SETTINGS).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                putExtra("EXTRA_CONNECTION_ONLY", true)
                putExtra("EXTRA_CLOSE_ON_CONNECT", true)
                putExtra("android.bluetooth.devicepicker.extra.FILTER_TYPE", 1)
            }
            startActivity(intent)
        }
    }

    // Verifica se existe uma saída de áudio do tipo solicitado
    private fun saidaAudioDisponivel(tipo: Int): Boolean {
        // Wear OS pode não ter alto-falante, então checamos
        if (!packageManager.hasSystemFeature(PackageManager.FEATURE_AUDIO_OUTPUT)) {
            return false
        }

        // percorre as saídas disponíveis
        val dispositivosSaida = audioManager.getDevices(AudioManager.GET_DEVICES_OUTPUTS)
        return dispositivosSaida.any { it.type == tipo }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Remove o callback de áudio para evitar vazamento de memória
        audioManager.unregisterAudioDeviceCallback(audioDeviceCallback)
        // libera a engine de voz quando a activity morre
        if (::tts.isInitialized) {
            tts.stop()
            tts.shutdown()
        }
    }
}
