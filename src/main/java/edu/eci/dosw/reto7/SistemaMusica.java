package edu.eci.dosw.reto7;

/** Receiver: a music system with volume (0-100). */
public class SistemaMusica implements Dispositivo {

    private final String name;
    private int volume; // 0-100

    public SistemaMusica(String name) {
        this.name = name;
        this.volume = 0;
    }

    public void setVolume(int volume) {
        this.volume = Math.max(0, Math.min(100, volume));
    }

    public int getVolume() {
        return volume;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " [volume=" + volume + "]";
    }
}
