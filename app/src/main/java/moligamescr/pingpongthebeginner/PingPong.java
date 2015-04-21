package moligamescr.pingpongthebeginner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.util.Random;

public class PingPong extends ActionBarActivity {
    private MediaPlayer mp;
    public static String SwitchSilencio = "ValorSwitchSilencioSeleccionado";
    private SharedPreferences sp;

    private int tiempoRestante = 60;
    private int intervaloTiempoRestante = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ping_pong);
        new CountDownTimer(tiempoRestante * 1000, intervaloTiempoRestante * 1000){
            @Override
            public void onTick(long miliSegundosParaFinalizar){
                TextView puntajeActualJugador = (TextView) findViewById(R.id.MarcadorJugador);
                puntajeActualJugador.setText(" " + Integer.toString(PingPongView.puntaje_obtenido));

                TextView tiempoRestante = (TextView) findViewById(R.id.TiempoRestante);
                tiempoRestante.setText("Tiempo restante: " + Integer.toString((int) (miliSegundosParaFinalizar / 1000)));
            }

            @Override
            public void onFinish() {
                startActivity(new Intent(PingPong.this, FinalDeJuego.class));
                finish();
            }

        }.start();

        this.sp = getSharedPreferences(SwitchSilencio, MODE_PRIVATE);
        this.mp = new MediaPlayer();

        if (cargarPrefenciasValorSwitchSilencio()){
            inicializarMusica();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mp.isPlaying()){
            mp.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mp.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mp.isPlaying()){
            mp.stop();
            mp.release();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mp.isPlaying()){
            mp.stop();
            mp.release();
        }
    }

    public void inicializarMusica() {
        Random r = new Random();
        int numeroCancionAleatoria = r.nextInt(9)+1;
            switch (numeroCancionAleatoria){
                case 1:
                    mp = MediaPlayer.create(PingPong.this, R.raw.song01);
                    break;
                case 2:
                    mp = MediaPlayer.create(PingPong.this, R.raw.song02);
                    break;
                case 3:
                    mp = MediaPlayer.create(PingPong.this, R.raw.song03);
                    break;
                case 4:
                    mp = MediaPlayer.create(PingPong.this, R.raw.song04);
                    break;
                case 5:
                    mp = MediaPlayer.create(PingPong.this, R.raw.song05);
                    break;
                case 6:
                    mp = MediaPlayer.create(PingPong.this, R.raw.song06);
                    break;
                case 7:
                    mp = MediaPlayer.create(PingPong.this, R.raw.song07);
                    break;
                case 8:
                    mp = MediaPlayer.create(PingPong.this, R.raw.song08);
                    break;
                case 9:
                    mp = MediaPlayer.create(PingPong.this, R.raw.song09);
                    break;
            }
        mp.start();
    }

    public boolean cargarPrefenciasValorSwitchSilencio(){
        this.sp = getSharedPreferences(SwitchSilencio, MODE_PRIVATE);
        return sp.getBoolean("valorSwitchSilencio", true);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MenuPingPong.class));
    }

}