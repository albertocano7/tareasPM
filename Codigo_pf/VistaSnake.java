package com.example.proyectofinalpm;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class VistaSnake extends SurfaceView implements Runnable {

    // ── Hilo y control ────────────────────────────────────────
    private Thread hilo;
    private boolean corriendo  = false;
    private boolean juegoActivo = false;

    // Tablero
    private static final int CELDAS = 20;
    private int tamCelda;
    private int offsetX, offsetY;

    // Serpiente
    private int[] serpX = new int[CELDAS * CELDAS];
    private int[] serpY = new int[CELDAS * CELDAS];
    private int longitud;
    private int dirX, dirY;
    private int nextDirX, nextDirY;
    private int comidaX, comidaY;

    // Puntuación
    private int puntos;
    private int record;

    private long velocidad = 200;

    private Paint pintaCuerpo  = new Paint();
    private Paint pintaCabeza  = new Paint();
    private Paint pintaComida  = new Paint();
    private Paint pintaTablero = new Paint();
    private Paint pintaTexto   = new Paint();

    public VistaSnake(Context context) {
        super(context);

        pintaCuerpo.setColor(Color.parseColor("#4CAF50"));
        pintaCabeza.setColor(Color.parseColor("#81C784"));
        pintaComida.setColor(Color.parseColor("#F44336"));
        pintaTablero.setColor(Color.parseColor("#1A1A1A"));
        pintaTexto.setColor(Color.WHITE);
        pintaTexto.setTextSize(40);
        pintaTexto.setTypeface(Typeface.DEFAULT_BOLD);
        pintaTexto.setTextAlign(Paint.Align.CENTER);

        getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder h) { reanudar(); }

            @Override
            public void surfaceChanged(SurfaceHolder h, int f, int w, int t) {
                calcularTablero(w, t);
                iniciarJuego();
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder h) { pausar(); }
        });
    }

    private void calcularTablero(int ancho, int alto) {
        tamCelda = Math.min(ancho, alto) / CELDAS;
        offsetX  = (ancho - tamCelda * CELDAS) / 2;
        offsetY  = (alto  - tamCelda * CELDAS) / 2;
    }

    private void iniciarJuego() {
        longitud  = 3;
        serpX[0]  = CELDAS / 2;     serpY[0] = CELDAS / 2;
        serpX[1]  = CELDAS / 2 - 1; serpY[1] = CELDAS / 2;
        serpX[2]  = CELDAS / 2 - 2; serpY[2] = CELDAS / 2;
        dirX      = 1; dirY  = 0;
        nextDirX  = 1; nextDirY = 0;
        puntos    = 0;
        velocidad = 200;
        generarComida();
        juegoActivo = true;
    }

    private void generarComida() {
        boolean libre;
        do {
            libre   = true;
            comidaX = (int)(Math.random() * CELDAS);
            comidaY = (int)(Math.random() * CELDAS);
            for (int i = 0; i < longitud; i++)
                if (serpX[i] == comidaX && serpY[i] == comidaY) {
                    libre = false;
                    break;
                }
        } while (!libre);
    }

    // ── Llamado desde los botones
    public void cambiarDireccion(int dx, int dy) {
        if (!juegoActivo) {
            iniciarJuego(); // toca cualquier botón para reiniciar
            return;
        }
        // Evita ir en dirección opuesta
        if (dx == -dirX && dy == -dirY) return;
        nextDirX = dx;
        nextDirY = dy;
    }

    private void actualizar() {
        // Aplicar dirección solicitada
        dirX = nextDirX;
        dirY = nextDirY;

        // Mover cuerpo
        for (int i = longitud - 1; i > 0; i--) {
            serpX[i] = serpX[i - 1];
            serpY[i] = serpY[i - 1];
        }

        // Mover cabeza
        serpX[0] = (serpX[0] + dirX + CELDAS) % CELDAS;
        serpY[0] = (serpY[0] + dirY + CELDAS) % CELDAS;

        // Comer
        if (serpX[0] == comidaX && serpY[0] == comidaY) {
            longitud++;
            puntos += 10;
            if (puntos > record) record = puntos;
            if (velocidad > 80)  velocidad -= 5;
            generarComida();
        }

        // Colisión con sí misma
        for (int i = 1; i < longitud; i++) {
            if (serpX[0] == serpX[i] && serpY[0] == serpY[i]) {
                juegoActivo = false;
                break;
            }
        }
    }

    private void dibujar() {
        if (!getHolder().getSurface().isValid()) return;
        Canvas canvas = getHolder().lockCanvas();
        if (canvas == null) return;

        int w = canvas.getWidth();
        int h = canvas.getHeight();

        canvas.drawColor(Color.BLACK);

        // Marcador
        canvas.drawText("Puntos: " + puntos + "   Récord: " + record,
                w / 2f, offsetY - 10, pintaTexto);

        // Tablero
        canvas.drawRect(offsetX, offsetY,
                offsetX + tamCelda * CELDAS,
                offsetY + tamCelda * CELDAS, pintaTablero);

        if (!juegoActivo) {
            pintaTexto.setTextSize(55);
            canvas.drawText("GAME OVER", w / 2f, h / 2f - 30, pintaTexto);
            pintaTexto.setTextSize(30);
            canvas.drawText("Presiona un botón para reiniciar",
                    w / 2f, h / 2f + 20, pintaTexto);
            pintaTexto.setTextSize(40);
            getHolder().unlockCanvasAndPost(canvas);
            return;
        }

        // Cuadrícula
        Paint grid = new Paint();
        grid.setColor(Color.parseColor("#222222"));
        grid.setStrokeWidth(1);
        for (int i = 0; i <= CELDAS; i++) {
            canvas.drawLine(offsetX + i * tamCelda, offsetY,
                    offsetX + i * tamCelda, offsetY + tamCelda * CELDAS, grid);
            canvas.drawLine(offsetX, offsetY + i * tamCelda,
                    offsetX + tamCelda * CELDAS, offsetY + i * tamCelda, grid);
        }

        // Cuerpo
        for (int i = 1; i < longitud; i++) {
            int x = offsetX + serpX[i] * tamCelda + 1;
            int y = offsetY + serpY[i] * tamCelda + 1;
            canvas.drawRect(x, y, x + tamCelda - 2, y + tamCelda - 2, pintaCuerpo);
        }

        // Cabeza
        int hx = offsetX + serpX[0] * tamCelda + 1;
        int hy = offsetY + serpY[0] * tamCelda + 1;
        canvas.drawRect(hx, hy, hx + tamCelda - 2, hy + tamCelda - 2, pintaCabeza);

        // Comida
        int fx = offsetX + comidaX * tamCelda + 2;
        int fy = offsetY + comidaY * tamCelda + 2;
        canvas.drawOval(fx, fy, fx + tamCelda - 4, fy + tamCelda - 4, pintaComida);

        getHolder().unlockCanvasAndPost(canvas);
    }

    @Override
    public void run() {
        while (corriendo) {
            long inicio = System.currentTimeMillis();
            if (juegoActivo) actualizar();
            dibujar();
            long transcurrido = System.currentTimeMillis() - inicio;
            long espera = velocidad - transcurrido;
            if (espera > 0) {
                try { Thread.sleep(espera); }
                catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
        }
    }

    public void pausar() {
        corriendo = false;
        if (hilo != null) {
            try { hilo.join(); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }
    }

    public void reanudar() {
        corriendo = true;
        hilo = new Thread(this);
        hilo.start();
    }
}