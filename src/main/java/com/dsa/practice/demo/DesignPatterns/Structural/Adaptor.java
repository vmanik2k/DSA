package com.dsa.practice.demo.DesignPatterns.Structural;

/*
🔹 Adapter Design Pattern
📌 Description

The Adapter Pattern is a structural design pattern that allows incompatible interfaces to work together.
It acts as a bridge between two incompatible classes by converting one interface into another that the client expects.

👉 In simple words: It lets classes with different interfaces work together without modifying their source code.

📌 Real-World Analogy

A mobile charger adapter converts 220V AC to 5V DC so your phone can charge.

A card reader adapts an SD card to be used in a USB port.

📌 Pros

✅ Helps integrate existing classes without modifying them
✅ Promotes code reusability
✅ Provides loose coupling between systems with mismatched interfaces

📌 Cons

❌ Adds extra layers → increases complexity
❌ Can lead to too many adapters if system design isn’t thought out properly

📌 Applications

Legacy system integration (new code needs to talk to old code)

Working with 3rd-party libraries (whose interfaces can’t be changed)

GUI libraries (adapting widgets to different frameworks)

I/O streams in Java (InputStreamReader adapts InputStream to Reader)
*/

//📌 Example (Java – Adapter Pattern)
//Step 1: Target Interface (what client expects)
interface MediaPlayer {
    void play(String audioType, String fileName);
}

//Step 2: Adaptee (incompatible class we want to use)
class AdvancedMediaPlayer {
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file: " + fileName);
    }

    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file: " + fileName);
    }
}

//Step 3: Adapter Class
class MediaAdapter implements MediaPlayer {
    private AdvancedMediaPlayer advancedPlayer = new AdvancedMediaPlayer();

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedPlayer.playVlc(fileName);
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedPlayer.playMp4(fileName);
        } else {
            System.out.println("Invalid media type: " + audioType);
        }
    }
}

//Step 4: Client (uses Target Interface)
class AudioPlayer implements MediaPlayer {
    private MediaAdapter adapter;

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing mp3 file: " + fileName);
        } else {
            adapter = new MediaAdapter();
            adapter.play(audioType, fileName);
        }
    }
}

//Step 5: Test
public class Adaptor {
    public static void main(String[] args) {
        AudioPlayer player = new AudioPlayer();

        player.play("mp3", "song.mp3");
        player.play("mp4", "movie.mp4");
        player.play("vlc", "clip.vlc");
        player.play("avi", "video.avi");
    }
}

/*
Output
Playing mp3 file: song.mp3
Playing mp4 file: movie.mp4
Playing vlc file: clip.vlc
Invalid media type: avi

📌 Types of Adapters

Class Adapter (uses inheritance)

Adapter extends Adaptee and implements Target interface.

Object Adapter (uses composition)

Adapter contains a reference to Adaptee and delegates calls.

More flexible (since Java doesn’t support multiple inheritance).

👉 In Java, Object Adapter (composition) is preferred.*/
